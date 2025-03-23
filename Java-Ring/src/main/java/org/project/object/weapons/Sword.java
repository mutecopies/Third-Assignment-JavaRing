package org.project.object.weapons;

import org.project.entity.Entity;
import java.util.List;

public class Sword extends Weapon {
    private int abilityCharge; // Tracks special ability usage

    public Sword(String name, int damage) {
        super(name, damage);
        this.abilityCharge = 0;
    }

    @Override
    public void attack(Entity target) {
        System.out.println("⚔️ " + getName() + " slashes " + target.getClass().getSimpleName() + " for " + getDamage() + " damage!");
        target.takeDamage(getDamage());
        abilityCharge++; // Increases ability charge with each attack
    }

    // Unique ability: Cleave Attack - Damages all enemies in range
    public void uniqueAbility(List<Entity> targets) {
        if (abilityCharge < 3) {
            System.out.println("⚡ Sword's Cleave ability is not fully charged yet! (" + abilityCharge + "/3)");
            return;
        }

        System.out.println("⚡ " + getName() + " unleashes a Cleave Attack, hitting all enemies!");
        for (Entity target : targets) {
            System.out.println("🩸 " + target.getClass().getSimpleName() + " takes " + getDamage() + " damage!");
            target.takeDamage(getDamage());
        }
        abilityCharge = 0; // Resets charge after use
    }

    public int getAbilityCharge() {
        return abilityCharge;
    }
}
