package org.project.entity.players;

import org.project.entity.Entity;
import org.project.object.armors.KnightArmor;
import org.project.object.weapons.Sword;

public class Knight extends Player {

    private boolean shieldActive; // Special ability: temporary defense boost

    public Knight(String name) {
        super(name, 150, 50, new Sword("Steel Sword", 20), new KnightArmor(10));
        this.shieldActive = false;
    }

    @Override
    public void attack(Entity target) {
        System.out.println("🛡️ " + getName() + " swings their sword at " + target.getClass().getSimpleName() + "!");
        super.attack(target);
    }

    @Override
    public void takeDamage(int damage) {
        if (shieldActive) {
            System.out.println("🛡️ " + getName() + " blocks the attack with their shield!");
            shieldActive = false; // Shield can only be used once per turn
            return;
        }
        super.takeDamage(damage);
    }

    public void shieldBlock() {
        shieldActive = true;
        System.out.println("🛡️ " + getName() + " raises their shield, blocking the next attack!");
    }
}
