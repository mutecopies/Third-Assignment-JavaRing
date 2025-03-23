package org.project.object.armors;

public abstract class Armor {
    private int defense;
    private final int maxDefense;
    private int durability;
    private final int maxDurability;
    private boolean isBroken;

    public Armor(int defense, int durability) {
        this.defense = defense;
        this.maxDefense = defense;
        this.durability = durability;
        this.maxDurability = durability;
        this.isBroken = false;
    }

    public void reduceDurability(int amount) {
        if (isBroken) return; // Already broken, no further reduction

        durability -= amount;
        if (durability <= 0) {
            durability = 0;
            checkBreak();
        }
    }

    public void checkBreak() {
        if (durability <= 0) {
            isBroken = true;
            defense = 0;
            System.out.println("⚠️ Armor has broken and provides no defense!");
        }
    }

    public void repair() {
        if (!isBroken) {
            System.out.println("🔧 Armor is not broken, no repair needed!");
            return;
        }

        System.out.println("🔧 Repairing armor...");
        durability = maxDurability;
        defense = maxDefense;
        isBroken = false;
    }

    public int getDefense() {
        return defense;
    }

    public int getDurability() {
        return durability;
    }

    public boolean isBroken() {
        return isBroken;
    }
}
