package com.soginteractive.editor;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

import com.badlogic.gdx.backends.lwjgl.LwjglAWTCanvas;
import com.badlogic.gdx.utils.Array;
import com.soginteractive.editor.managers.JMenuItemManager;
import com.soginteractive.editor.managers.JMenuManager;

@SuppressWarnings("serial")
public class Main extends JFrame implements ActionListener, ItemListener {

	private JMenuBar menuBar;
	private JMenu menu, menu2, menu3, menu3Submenu1;
	private JMenuItem menuItem1, menuItem2, menuItem3, menuItem4;
	private JMenuItem menu2Item1, menu2Item2, menu2Item3;
	private JMenuItem menu3Item1;
	private JMenuItem m3S1Item1, m3S1Item2, m3S1Item3, m3S1Item4, m3S1Item5,
			m3S1Item6;
	private JMenuManager metaMenuManager, menuManager, subMenuManager;
	private JMenuItemManager menu1ItemManager, menu2ItemManager,
			menu3ItemManager, menu3SubMenuItemManager;

	public Main() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final Container container = getContentPane();
		container.setLayout(new BorderLayout());

		LwjglAWTCanvas canvas = new LwjglAWTCanvas(new EditorMain());
		container.add(canvas.getCanvas(), BorderLayout.CENTER);

		menuBar = new JMenuBar();

		metaMenuManager = new JMenuManager("MenuManagers");
		menuManager = new JMenuManager("Menus");
		subMenuManager = new JMenuManager("SubMenus");

		menu = new JMenu("File");
		menu2 = new JMenu("Edit");
		menu3 = new JMenu("Window");

		menu1ItemManager = new JMenuItemManager("FileMenuItems");
		menu2ItemManager = new JMenuItemManager("EditMenuItems");
		menu3ItemManager = new JMenuItemManager("WindowMenuItems");
		menu3SubMenuItemManager = new JMenuItemManager("EditorsMenuItems");

		menuItem1 = new JMenuItem("New Project");
		menuItem2 = new JMenuItem("Open Project");
		menuItem3 = new JMenuItem("Save Project");
		menuItem4 = new JMenuItem("Close Project");

		menu2Item1 = new JMenuItem("Cut");
		menu2Item2 = new JMenuItem("Copy");
		menu2Item3 = new JMenuItem("Paste");

		menu3Item1 = new JMenuItem("Main");

		menu3Submenu1 = new JMenu("Editors");

		m3S1Item1 = new JMenuItem("Map");
		m3S1Item2 = new JMenuItem("Character");
		m3S1Item3 = new JMenuItem("Enemy");
		m3S1Item4 = new JMenuItem("Equipment");
		m3S1Item5 = new JMenuItem("Item");
		m3S1Item6 = new JMenuItem("Event");

		menu1ItemManager.menuItem(menuItem1).menuItem(menuItem2)
				.menuItem(menuItem3).menuItem(menuItem4);

		menu2ItemManager.menuItem(menu2Item1).menuItem(menu2Item2)
				.menuItem(menu2Item3);

		menu3ItemManager.menuItem(menu3Item1);

		menu3SubMenuItemManager.menuItem(m3S1Item1).menuItem(m3S1Item2)
				.menuItem(m3S1Item3).menuItem(m3S1Item4).menuItem(m3S1Item5)
				.menuItem(m3S1Item6);

		subMenuManager.menu(menu3Submenu1);

		subMenuManager.menuItemManager(menu3SubMenuItemManager);

		subMenuManager.attachActionListenerToMenuItems(this);
		subMenuManager.attachItemListenerToMenuItems(this);

		menuManager.menu(menu).menu(menu2).menu(menu3);

		menuManager.subMenuManager(subMenuManager);

		menuManager.menuItemManager(menu1ItemManager)
				.menuItemManager(menu2ItemManager)
				.menuItemManager(menu3ItemManager);

		menuManager.attachMenuItemsToMenu(menu, menu1ItemManager);
		menuManager.attachMenuItemsToMenu(menu2, menu2ItemManager);
		menuManager.attachMenuItemsToMenu(menu3, menu3ItemManager);
		menuManager.attachMenuItemsToMenu(menu3Submenu1,
				menu3SubMenuItemManager);

		menuManager.attachSubMenuMenusToMenu(menu3, subMenuManager);

		menuManager.attachActionListenerToMenuItems(this);
		menuManager.attachItemListenerToMenuItems(this);

		metaMenuManager.subMenuManager(menuManager).subMenuManager(
				subMenuManager);

		Array<JMenu> menus = menuManager.getMenus();
		for (int i = 0; i < menus.size; i++) {
			menuBar.add(menus.get(i));
		}

		container.add(menuBar, BorderLayout.NORTH);

		pack();
		setVisible(true);
		setSize(800, 600);

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Main();
			}
		});
	}

	@Override
	public void itemStateChanged(ItemEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JMenuItem source = (JMenuItem) e.getSource();

		for (int i = 0; i < metaMenuManager.getSubMenuManagers().size; i++) {
			JMenuManager menuManager = metaMenuManager.getSubMenuManagers()
					.get(i);
			for (int j = 0; j < menuManager.getMenuItemManagers().size; j++) {
				JMenuItemManager menuItemManager = menuManager
						.getMenuItemManagers().get(j);

				if (menuItemManager.isMenuItemActionPerformed(source)) {
					System.out.println("Item clicked: " + e.getActionCommand());
				}

			}

		}

	}

}
