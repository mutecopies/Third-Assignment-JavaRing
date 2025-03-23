package org.project;

import org.project.entity.enemies.Dragon;
import org.project.entity.enemies.Goblin;
import org.project.entity.enemies.Skeleton;
import org.project.entity.players.Assassin;
import org.project.entity.players.Knight;
import org.project.entity.players.Wizard;
import org.project.entity.Entity;
import org.project.entity.players.Player;
import org.project.location.Location;
import org.project.object.weapons.Sword;
import org.project.object.armors.KnightArmor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    public static void main(String[] args) {
        System.out.println("⚔️ Welcome to the Adventure Game! ⚔️");

        // Choose character
        Player player = chooseCharacter();
        System.out.println("✅ You chose: " + player.getClass().getSimpleName());

        // Initialize locations
        List<Location> locations = new ArrayList<>();
        locations.add(new Location("Dark Forest"));
        locations.add(new Location("Abandoned Castle"));
        locations.add(new Location("Cursed Ruins"));

        boolean playing = true;

        while (playing) {
            System.out.println("\n📍 You are in: " + locations.get(random.nextInt(locations.size())).getName());
            System.out.println("⚠️ A wild monster appears!");

            Entity enemy = generateRandomEnemy();
            System.out.println("💀 It's a " + enemy.getClass().getSimpleName() + "!");

            boolean fighting = true;
            while (player.isAlive() && enemy.isAlive() && fighting) {
                System.out.println("\n🎮 Choose an action:");
                System.out.println("1️⃣ Attack");
                System.out.println("2️⃣ Move to another location");
                System.out.print("> ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        player.attack(enemy);
                        if (enemy.isAlive()) {
                            enemy.attack(player);
                        } else {
                            System.out.println("🎉 You defeated the " + enemy.getClass().getSimpleName() + "!");
                        }
                        break;
                    case 2:
                        System.out.println("🚶 You decided to move away...");
                        fighting = false;
                        break;
                    default:
                        System.out.println("❌ Invalid choice. Try again.");
                }
            }

            if (!player.isAlive()) {
                System.out.println("💀 You have fallen in battle. Game Over.");
                playing = false;
            } else {
                System.out.println("🏆 You survived the battle!");
                System.out.println("Do you want to continue your journey? (yes/no)");
                System.out.print("> ");
                String response = scanner.next().toLowerCase();
                if (!response.equals("yes")) {
                    playing = false;
                }
            }
        }

        System.out.println("🛡️ Thank you for playing!");
        scanner.close();
    }

    private static Player chooseCharacter() {
        while (true) {
            System.out.println("\n🎭 Choose your character:");
            System.out.println("1️⃣ Knight 🛡️");
            System.out.println("2️⃣ Assassin 🗡️");
            System.out.println("3️⃣ Wizard 🔮");
            System.out.print("> ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    return new Knight("Knight", 100, 50, new Sword("Knight's Blade", 15, 5, 10), new KnightArmor(20, 15));
                case 2:
                    return new Assassin("Assassin", 80, 70, new Sword("Dagger", 12, 3, 8), null);
                case 3:
                    return new Wizard("Wizard", 60, 100, new Sword("Magic Staff", 10, 10, 12), null);
                default:
                    System.out.println("❌ Invalid choice. Please select again.");
            }
        }
    }

    private static Entity generateRandomEnemy() {
        int randomChoice = random.nextInt(3);
        switch (randomChoice) {
            case 0:
                return new Goblin(50, 20, 5, new Sword("Rusty Dagger", 8, 0, 5));
            case 1:
                return new Dragon(150, 80, 20, new Sword("Fire Breath", 25, 15, 20));
            case 2:
                return new Skeleton(40, 10, 2, new Sword("Bone Club", 7, 0, 4));
            default:
                return new Goblin(50, 20, 5, new Sword("Rusty Dagger", 8, 0, 5));
        }
    }
}
