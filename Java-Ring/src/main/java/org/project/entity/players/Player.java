package org.project.entity.players;

import org.project.entity.Entity;
import org.project.object.armors.Armor;
import org.project.object.weapons.Weapon;

public abstract class Player implements Entity {
    protected String name;
    protected Weapon weapon;
    protected Armor armor;
    private int hp;
    private int maxHP;
    private int mp;
    private int maxMP;

    public Player(String name, int hp, int mp, Weapon weapon, Armor armor) {
        this.name = name;
        this.hp = hp;
        this.maxHP = hp; // Initialize max HP
        this.mp = mp;
        this.maxMP = mp; // Initialize max MP
        this.weapon = weapon;
        this.armor = armor;
    }

    @Override
    public void attack(Entity target) {
        if (weapon != null) {
            int damage = weapon.getDamage();
            System.out.println(name + " attacks with " + weapon.getName() + " for " + damage + " damage!");
            target.takeDamage(damage);
        } else {
            System.out.println(name + " has no weapon to attack!");
        }
    }

    @Override
    public void defend() {
        System.out.println(name + " is defending!");
        // TODO: Implement shield-based defense
    }

    @Override
    public void takeDamage(int damage) {
        int actualDamage = Math.max(damage - (armor != null ? armor.getDefense() : 0), 1);
        hp = Math.max(hp - actualDamage, 0);
        System.out.println(name + " took " + actualDamage + " damage! HP: " + hp + "/" + maxHP);
    }

    @Override
    public void heal(int health) {
        hp = Math.min(hp + health, maxHP);
        System.out.println(name + " heals for " + health + " HP! Current HP: " + hp + "/" + maxHP);
    }

    @Override
    public void fillMana(int mana) {
        mp = Math.min(mp + mana, maxMP);
        System.out.println(name + " restores " + mana + " MP! Current MP: " + mp + "/" + maxMP);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public int getMaxHP() {
        return maxHP;
    }

    @Override
    public int getMp() {
        return mp;
    }

    @Override
    public int getMaxMP() {
        return maxMP;
    }

    @Override
    public Weapon getWeapon() {
        return weapon;
    }

    @Override
    public Armor getArmor() {
        return armor;
    }
}
