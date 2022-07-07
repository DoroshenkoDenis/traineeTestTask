import org.junit.jupiter.api.Test;
import qa.robots.AbstractRobot;
import qa.robots.FightingRobot;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class RoboTest {

    // For interactions please use console
    // check source src/test/resources/RobotsPairwise.xlsx
    final String fight = "Fight";
    final String cook = "Cook";
    final String weld = "Weld";

    final String atomic = "atomic";
    final String diesel = "diesel";
    final String electric = "electric";

    final String fly = "fly";
    final String walk = "walk";
    final String ride = "ride";

    // The energyReservePercents can be considered as gas tank, battery capacity (%)
    // energyReserveDate - robot creation date, only for atomic engine
    final int energyReserveDate = 1899;
    final int energyReservePercents = 30;


//    @Test
//    public void secondLineShouldHabAlloha() throws FileNotFoundException {
//        AbstractRobot robot = new FightingRobot();
//        robot
//                .setMove(fly)
//                .letsWork(fight, atomic, energyReserveDate);
//        robot
//                .setMove(fly)
//                .letsWork(fight, atomic, energyReserveDate);
//            System.out.println("Y");
//        }
    }

