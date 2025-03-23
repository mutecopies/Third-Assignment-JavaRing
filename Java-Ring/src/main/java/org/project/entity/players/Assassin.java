package org.project.entity.players;

import org.project.entity.Entity;
import org.project.object.armors.Armor;
import org.project.object.weapons.Weapon;

public class Assassin extends Player {

    private boolean isInvisible; // Special ability: temporary invisibility

    public Assassin(String name, Weapon weapon, Armor armor) {
        super(name, 100, 75, weapon, armor);
        this.isInvisible = false;
    }

    @Override
    public void attack(Entity target) {
        if (isInvisible) {
            int increasedDamage = getWeapon() != null ? getWeapon().getDamage() * 2 : 40;
            System.out.println("🗿 " + getName() + " strikes from the shadows at " + target.getClass().getSimpleName() + " for " + increasedDamage + " damage!");
            target.takeDamage(increasedDamage);
            isInvisible = false; // Invisibility fades after attacking
        } else {
            System.out.println("🗿 " + getName() + " swiftly attacks " + target.getClass().getSimpleName() + "!");
            super.attack(target);
        }
    }

    @Override
    public void takeDamage(int damage) {
        if (isInvisible) {
            System.out.println("🗿 " + getName() + " is invisible and avoids the attack!");
        } else {
            super.takeDamage(damage);
        }
    }

    public void becomeInvisible() {
        isInvisible = true;
        System.out.println("🗿 " + getName() + " vanishes into the shadows, becoming immune to attacks and ready for a deadly strike!");
    }
}
