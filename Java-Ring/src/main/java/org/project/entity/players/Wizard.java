package org.project.entity.players;

import org.project.entity.Entity;
import org.project.object.armors.Armor;
import org.project.object.weapons.Weapon;

public class Wizard extends Player {

    public Wizard(String name, Weapon weapon, Armor armor) {
        super(name, 120, 100, weapon, armor);
    }

    @Override
    public void attack(Entity target) {
        System.out.println("🧙‍♂️ " + getName() + " casts a magical attack at " + target.getClass().getSimpleName() + "!");
        super.attack(target);
    }

    @Override
    public void takeDamage(int damage) {
        System.out.println("🧙‍♂️ " + getName() + " braces against the attack!");
        super.takeDamage(damage);
    }

    public void castSpell(Entity target) {
        int spellDamage = 30;
        int healingAmount = 20;

        System.out.println("🧙‍♂️ " + getName() + " casts a powerful spell! Deals " + spellDamage + " damage and heals for " + healingAmount + " HP!");
        target.takeDamage(spellDamage);
        heal(healingAmount);
    }
}