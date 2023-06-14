package me.carina.rpg.common.item;

import me.carina.rpg.common.util.Array;

public class Equipments {
    WeaponSlot weaponSlot = new WeaponSlot(this);
    ShieldSlot shieldSlot = new ShieldSlot(this);
    Array<ArmorSlot> armorSlots = new Array<>(new ArmorSlot(this),new ArmorSlot(this),new ArmorSlot(this),new ArmorSlot(this));
}
