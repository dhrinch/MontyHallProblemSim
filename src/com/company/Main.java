package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int prize;
        int switchCounter = 0;
        double gameCounter = 0;
        double winCounter = 0;
        double winPercentage = 0;

        Door doors[] = new Door[3];

        String replay = "yes";

        while (replay.equals("yes")||replay.equals("y")||replay.equals("yep")) {
            prize = random.nextInt(3);
            for (int i = 0; i < doors.length; i++) {
                if (i == prize) {
                    doors[i] = new Door("car");
                } else {
                    doors[i] = new Door("pig");
                }
            }

            System.out.println("Games played: "+ (int)gameCounter);
            System.out.println("Doors switched: " + switchCounter);
            System.out.println("Cars won: " + (int)winCounter);
            System.out.println("Win percentage: " + (int)winPercentage + "%");
            System.out.println("Select a door from 0 to 2:");
            int selection = scanner.nextInt();
            scanner.nextLine();
            int doorToOpen = openDoor(doors, selection);
            System.out.println("Alright, now opening door number " + doorToOpen + ", and there is a " + doors[doorToOpen].prize + " behind it!");
            System.out.println("Would you like to switch doors now?");

            int finalDoor;
            String switcher = scanner.nextLine().toLowerCase();
            if (switcher.equals("yes") || switcher.equals("y") || switcher.equals("yep")) {
                finalDoor = openLastDoor(doors, selection);
                switchCounter ++;
                System.out.println("Okay, let's open door " + finalDoor + " now... And the prize is a " + doors[finalDoor].prize + "!");
                if(doors[finalDoor].prize.equals("car")) winCounter++;
            }
            if (switcher.equals("no") || switcher.equals("n") || switcher.equals("nope")) {
                System.out.println("Right, let's open door " + selection + " then... And the prize is a " + doors[selection].prize + "!");
                if(doors[selection].prize.equals("car")) winCounter++;
            }
            gameCounter++;
            winPercentage = (winCounter/gameCounter) * 100;

            System.out.println("Would you like to play again?");

            String playAgain = scanner.nextLine().toLowerCase();
            if (playAgain.equals("no") || playAgain.equals("n") || playAgain.equals("nope"))
            {
                System.out.println("Games played: "+ (int)gameCounter);
                System.out.println("Doors switched: " + switchCounter);
                System.out.println("Cars won: " + (int)winCounter);
                System.out.println("Win percentage: " + (int)winPercentage + "%");
                break;
            }
        }
    }

    private static int openDoor(Door[] doors, int selection) {
        Random random = new Random();
        int doorToOpen = 1;
        if (doors[selection].prize.equals("car")) {
            while (doorToOpen == selection) {
                doorToOpen = random.nextInt(3);
            }
            doors[doorToOpen].isOpen = true;
            return doorToOpen;
        }
        while (doors[doorToOpen].prize.equals("car") || doorToOpen == selection || doors[doorToOpen].isOpen == true) {
            doorToOpen = random.nextInt(3);
        }
        doors[doorToOpen].isOpen = true;
        return doorToOpen;
    }

    private static int openLastDoor(Door[] doors, int selection){
        Random random = new Random();
        int doorToOpen = 1;

        while (doors[doorToOpen].isOpen == true || doorToOpen == selection) {
            doorToOpen = random.nextInt(3);
        }
        return doorToOpen;
    }
}
