package me.carina.rpg.common.item;

import me.carina.rpg.common.ArrayFeature;
import me.carina.rpg.common.util.Array;

import java.util.Iterator;

public class Equipments {
    WeaponSlot weaponSlot = new WeaponSlot();
    ShieldSlot shieldSlot = new ShieldSlot();
    Array<ArmorSlot> armorSlots = new Array<>(new ArmorSlot(),new ArmorSlot(),new ArmorSlot(),new ArmorSlot());

    public WeaponSlot getWeaponSlot() {
        return weaponSlot;
    }

    public ShieldSlot getShieldSlot() {
        return shieldSlot;
    }

    public ArmorSlot getArmorSlot(int index){
        if (index >= 4) return null;
        return armorSlots.get(index);
    }

    public Array<EquipSlot> getAllSlots(){
        Array<EquipSlot> slots = new Array<>();
        slots.add(weaponSlot);
        slots.add(shieldSlot);
        slots.addAll(armorSlots);
        return slots;
    }

    public void setWeaponSlot(Equipment equipment){
        weaponSlot.setEquipment(this,equipment);
    }
    public void setShieldSlot(Equipment equipment){
        shieldSlot.setEquipment(this,equipment);
    }
    public void setArmorSlot(Equipment equipment, int index){
        if (index >= 4) throw new RuntimeException();
        armorSlots.get(index).setEquipment(this,equipment);
    }
}
