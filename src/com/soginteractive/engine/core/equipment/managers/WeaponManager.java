package com.soginteractive.engine.core.equipment.managers;

import com.badlogic.gdx.utils.Array;
import com.soginteractive.engine.core.equipment.Weapon;
import com.soginteractive.engine.core.managers.EquipmentManager;

public class WeaponManager extends EquipmentManager {

	private Array<Weapon> weapons;

	public WeaponManager(String path) {
		super(path);
		weapons = new Array<Weapon>();
	}

	public WeaponManager weapon(Weapon weapon) {
		weapons.add(weapon);
		return this;
	}

	public Array<Weapon> getWeapons() {
		return weapons;
	}

}
