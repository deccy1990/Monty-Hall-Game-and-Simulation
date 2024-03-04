

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class MontyHallTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class MontyHallSimulation {
    public static void main(String[] args) {
        MontyHall montyHall = new MontyHall();

        System.out.println("Simulation with 1000 runs:");
        montyHall.simulate(1000);

        System.out.println("\nSimulation with 10,000 runs:");
        montyHall.simulate(10000);

        System.out.println("\nSimulation with 100,000 runs:");
        montyHall.simulate(100000);
    }
}
