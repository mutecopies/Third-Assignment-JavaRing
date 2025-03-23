package org.project.entity.enemies;

import org.project.entity.Entity;
import org.project.entity.players.Player;
import org.project.object.weapons.Weapon;

import java.util.List;

public class Dragon extends Enemy {
    private boolean isAliveAfterResurrection; // Tracks if resurrection has been used

    public Dragon(int hp, int mp, int defense, Weapon weapon) {
        super(hp, mp, defense, weapon);
        this.isAliveAfterResurrection = false;
    }

    @Override
    public void takeDamage(int damage) {
        int actualDamage = Math.max(damage - getDefense(), 1); // Apply defense reduction
        setHp(Math.max(getHp() - actualDamage, 0)); // Prevent negative HP

        System.out.println("🐉 Dragon took " + actualDamage + " damage! Remaining HP: " + getHp());

        if (!isAlive() && !isAliveAfterResurrection) {
            resurrect();
        } else if (!isAlive()) {
            System.out.println("🐉 Dragon has been defeated!");
        }
    }

    private void resurrect() {
        int revivedHp = (int) (getMaxHp() * 0.5); // Revives with 50% of max HP
        setHp(revivedHp);
        isAliveAfterResurrection = true;
        System.out.println("🐉 Dragon has resurrected with " + revivedHp + " HP!");
    }

    // 🔥 Unique Feature: Dragon Bypasses Shields
    public void attack(Entity target) {
        if (!isAlive()) return;

        int damage = getWeapon().getDamage();
        System.out.println("🔥🐉 Dragon attacks " + target.getClass().getSimpleName() + " with " + getWeapon().getName() + " for " + damage + " damage! (🛡️ Shields are ignored!)");
        target.takeDamage(damage); // Direct damage, bypassing defense
    }

    // 🔥 Extra Score Feature: Attack All Players
    public void attackMultiplePlayers(List<Player> players) {
        if (!isAlive()) return;

        int damage = getWeapon().getDamage();
        System.out.println("🔥🐉 Dragon unleashes a devastating attack, damaging ALL players for " + damage + " damage! (🛡️ Shields are ignored!)");

        for (Player player : players) {
            player.takeDamage(damage); // Bypasses shield
        }
    }

    public void defend() {
        System.out.println("🐉 Dragon roars defiantly but does not use shields!");
    }

    public void heal(int amount) {
        if (!isAlive()) return;

        int newHp = Math.min(getHp() + amount, getMaxHp()); // Prevent overhealing
        setHp(newHp);
        System.out.println("🐉 Dragon regenerates " + amount + " HP! Current HP: " + newHp);
    }
}
