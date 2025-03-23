package org.project.entity.enemies;

import org.project.entity.Entity;
import org.project.object.weapons.Weapon;

public class Goblin extends Enemy {
    private boolean isAliveAfterResurrection; // Tracks if resurrection has been used

    public Goblin(int hp, int mp, int defense, Weapon weapon) {
        super(hp, mp, defense, weapon);
        this.isAliveAfterResurrection = false;
    }

    @Override
    public void takeDamage(int damage) {
        int actualDamage = Math.max(damage - getDefense(), 1); // Apply defense reduction
        setHp(Math.max(getHp() - actualDamage, 0)); // Prevent negative HP

        System.out.println("👹 Goblin took " + actualDamage + " damage! Remaining HP: " + getHp());

        if (!isAlive() && !isAliveAfterResurrection) {
            resurrect();
        } else if (!isAlive()) {
            System.out.println("👹 Goblin has been defeated!");
        }
    }

    private void resurrect() {
        int revivedHp = (int) (getMaxHp() * 0.5); // Revives with 50% of max HP
        setHp(revivedHp);
        isAliveAfterResurrection = true;
        System.out.println("👹 Goblin has resurrected with " + revivedHp + " HP!");
    }

    public void attack(Entity target) {
        if (!isAlive()) return;

        int damage = getWeapon().getDamage();
        System.out.println("👹 Goblin attacks " + target.getClass().getSimpleName() + " with " + getWeapon().getName() + " for " + damage + " damage!");
        target.takeDamage(damage);
    }

    public void defend() {
        int defenseBoost = 5; // Example defense boost
        System.out.println("👹 Goblin raises its arms, reducing incoming damage by " + defenseBoost);
        setDefense(getDefense() + defenseBoost);
    }

    public void heal(int amount) {
        if (!isAlive()) return;

        int newHp = Math.min(getHp() + amount, getMaxHp()); // Prevent overhealing
        setHp(newHp);
        System.out.println("👹 Goblin heals for " + amount + " HP! Current HP: " + newHp);
    }
}
