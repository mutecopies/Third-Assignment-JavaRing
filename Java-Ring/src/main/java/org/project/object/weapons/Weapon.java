package org.project.object.weapons;

import org.project.entity.Entity;

public abstract class Weapon {
    private final String name;
    private int damage;
    private int manaCost;
    private int durability;
    private final int maxDurability;
    private boolean isBroken;

    public Weapon(String name, int damage, int manaCost, int durability) {
        this.name = name;
        this.damage = damage;
        this.manaCost = manaCost;
        this.durability = durability;
        this.maxDurability = durability;
        this.isBroken = false;
    }

    public void attack(Entity target) {
        if (isBroken) {
            System.out.println("❌ " + name + " is broken and cannot be used!");
            return;
        }

        target.takeDamage(damage);
        reduceDurability(1);
        System.out.println("⚔️ " + name + " deals " + damage + " damage to " + target.getClass().getSimpleName());
    }

    public void useSpecialAbility(Entity target) {
        System.out.println("💥 " + name + " has no special ability defined!");
    }

    private void reduceDurability(int amount) {
        if (isBroken) return;

        durability -= amount;
        if (durability <= 0) {
            durability = 0;
            isBroken = true;
            System.out.println("⚠️ " + name + " has broken!");
        }
    }

    public void repair() {
        if (!isBroken) {
            System.out.println("🔧 " + name + " is not broken, no repair needed!");
            return;
        }

        System.out.println("🔧 Repairing " + name + "...");
        durability = maxDurability;
        isBroken = false;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public int getManaCost() {
        return manaCost;
    }

    public int getDurability() {
        return durability;
    }

    public boolean isBroken() {
        return isBroken;
    }
}
