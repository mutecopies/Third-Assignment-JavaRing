package org.project.object.armors;

public class KnightArmor extends Armor {
    private int durability; // Tracks armor's condition

    public KnightArmor(int defense) {
        super("Knight Armor", defense);
        this.durability = 100; // Starts at full durability
    }

    public void reinforce() {
        if (durability < 20) {
            System.out.println("🛡️ Knight Armor is too worn out to reinforce!");
            return;
        }

        System.out.println("🛡️ Knight Armor is reinforced, increasing defense temporarily!");
        setDefense(getDefense() + 5);
        durability -= 20; // Reinforcing reduces durability
    }

    public int getDurability() {
        return durability;
    }

    public void repair() {
        System.out.println("🔧 Repairing Knight Armor...");
        durability = 100;
    }
}
