package org.project.entity.enemies;

import org.project.entity.Entity;
import org.project.object.weapons.Weapon;

public abstract class Enemy implements Entity {
    private Weapon weapon;
    private int hp;
    private int mp;
    private int defense; // New attribute

    public Enemy(int hp, int mp, int defense, Weapon weapon) {
        this.hp = hp;
        this.mp = mp;
        this.defense = defense;
        this.weapon = weapon;
    }

    @Override
    public void takeDamage(int damage) {
        int actualDamage = Math.max(damage - defense, 1); // Ensure at least 1 damage is taken
        hp = Math.max(hp - actualDamage, 0); // Prevent negative HP
        System.out.println(getClass().getSimpleName() + " took " + actualDamage + " damage! Remaining HP: " + hp);
    }

    public int getHp() {
        return hp;
    }

    public int getMp() {
        return mp;
    }

    public int getDefense() {
        return defense;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public boolean isAlive() {
        return hp > 0;
    }
}
