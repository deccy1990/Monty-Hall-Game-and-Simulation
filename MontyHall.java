/**
 * (a) Import the java classes
 */
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections; 

/**
 * In this simulation you will play the Monty Hall game many times to decide
 * whether the contestant has a better chance of winning the car if they stick
 * with their original door or switch to the alternative door.
 * A contestant is shown three doors, labelled 1, 2 and 3. Two of the doors have
 * a goat behind them and the other has a car. The contestant choses a door
 * hoping to pick the car, but this door is not opened yet.
 * 
 * Then the game show host secretly examines what is behind the other doors and
 * selects one that has a goat (if both doors have goats, he picks one of them
 * randomly).
 * This door is eliminated from the game, leaving the door the contestant
 * selected and another door, which we will refer to as the alternative door.
 * 
 * The contestant is then asked whether they want to stick with their original
 * door, or to switch to the alternative door.
 *
 * @author Declan James Cronin
 * @version 0.1
 */

/** 
 * (a) Declared the fields listed in the scenario
 */

public class MontyHall 

{
    // Fields
    public ArrayList<String> doors;
    public Random ran;

    /** 
     * (b) add public zero-parameter constructor
     */
    // Public zero-parameter constructor
    public MontyHall() {
        doors = new ArrayList<>();
        ran = new Random();
    }

    /**
     * (c) method clears doors then populates it with two instances of string "goat" and one of the String "car" 
     */
    // Method to set up the doors
    public int setUp() {
        // Clear existing doors
        doors.clear();

        // Populate doors with two "goat" and one "car"
        doors.add("goat");
        doors.add("goat");
        doors.add("car");

        // Shuffle the doors to randomize their order
        Collections.shuffle(doors);

        // Find the door number containing "car"
        int carDoorNumber = doors.indexOf("car") + 1;  // Adding 1 to convert from 0-index to 1-index
        return carDoorNumber;
    }

    /**
     * (d) method get contestant's choice this returns random integer between 1 and 3 (inclusive), which represents the original door that the contestant chooses.
     */
    // Method to get contestant's choice
    public int getContestantChoice() {
        // Return a random integer between 1 and 3 (inclusive)
        return ran.nextInt(3) + 1;
    }

    /**
     * (e)public method that returns an int, with the signature
     */
    // Method to find the alternative door
    public int findAlternativeDoor(int contestantChoice) {
        if (doors.get(contestantChoice - 1).equals("goat")) {
            // If the selected door has a goat, return the door with the car
            for (int i = 0; i < doors.size(); i++) {
                if (i != contestantChoice - 1 && !doors.get(i).equals("goat")) {
                    return i + 1;  // Adding 1 to convert from 0-index to 1-index
                }
            }
        } else {
            // If the selected door has a car, randomly return one of the other two door numbers
            int alternativeDoor = contestantChoice;
            while (alternativeDoor == contestantChoice) {
                alternativeDoor = ran.nextInt(3) + 1;
            }
            return alternativeDoor;
        }

        // This should not be reached, but return -1 if something unexpected happens
        return -1;
    }

    /**
     * (f) Play Monty Hall game parameter indicates whether the contestant's chosen strategy is to stick or switch.
     */
    // Method to play the Monty Hall game
    public boolean playGame(boolean isStick) {
        // Set up the doors
        int carDoorNumber = setUp();

        // Get the contestant's choice
        int contestantChoice = getContestantChoice();

        // Find the alternative door
        int alternativeDoor = findAlternativeDoor(contestantChoice);

        // Determine the final choice based on the strategy
        int finalChoice = isStick ? contestantChoice : alternativeDoor;

        // Check if the final choice is the car door
        return finalChoice == carDoorNumber;
    }

    /**
     * (g)Simulates the number of runs for a specified number
     */
    // Method to simulate the Monty Hall game for a specified number of runs
    public void simulate(int numRuns) {
        int stickWins = 0;
        int switchWins = 0;

        // Simulate games with stick strategy
        for (int i = 0; i < numRuns; i++) {
            if (playGame(true)) {
                stickWins++;
            }
        }

        // Simulate games with switch strategy
        for (int i = 0; i < numRuns; i++) {
            if (playGame(false)) {
                switchWins++;
            }
        }

        // Print the results
        System.out.println("Wins by sticking: " + stickWins);
        System.out.println("Wins by switching: " + switchWins);
    }

}
