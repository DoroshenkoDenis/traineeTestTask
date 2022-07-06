package qa;

import qa.robots.AbstractRobot;
import qa.robots.CookingRobot;
import qa.robots.FightingRobot;
import qa.robots.WeldingRobot;

public class Main {

    public static void main(String[] args) {

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
        final int energyReservePercents = 50;



        AbstractRobot robot = new FightingRobot();
        robot
                .setMove(fly)
                .letsWork(fight, atomic, energyReserveDate)
                .letsWork(fight, atomic, energyReserveDate)
                .addBounds();


        AbstractRobot robot1 = new FightingRobot();
        robot1
                .setMove(walk)
                .letsWork(fight, diesel, energyReservePercents)
                .letsWork(fight, diesel, energyReservePercents)
                .addBounds();


        AbstractRobot robot2 = new FightingRobot();
        robot2
                .setMove(ride)
                .letsWork(fight, electric, energyReservePercents)
                .letsWork(fight, electric, energyReservePercents)
                .addBounds();


        AbstractRobot robot3 = new CookingRobot();
        robot3
                .setMove(ride)
                .letsWork(cook, diesel, energyReservePercents)
                .letsWork(cook, diesel, energyReservePercents)
                .addBounds();


        AbstractRobot robot4 = new CookingRobot();
        robot4
                .setMove(fly)
                .letsWork(cook, electric, energyReservePercents)
                .letsWork(cook, electric, energyReservePercents)
                .addBounds();


        AbstractRobot robot5 = new CookingRobot();
        robot5
                .setMove(walk)
                .letsWork(cook, atomic, energyReserveDate)
                .letsWork(cook, atomic, energyReserveDate)
                .addBounds();


        AbstractRobot robot6 = new WeldingRobot();
        robot6
                .setMove(walk)
                .letsWork(weld, electric, energyReservePercents)
                .letsWork(weld, electric, energyReservePercents)
                .addBounds();


        AbstractRobot robot7 = new WeldingRobot();
        robot7
                .setMove(ride)
                .letsWork(weld, atomic, energyReserveDate)
                .letsWork(weld, atomic, energyReserveDate)
                .addBounds();


        AbstractRobot robot8 = new WeldingRobot();
        robot8
                .setMove(fly)
                .letsWork(weld, diesel, energyReservePercents)
                .letsWork(weld, diesel, energyReservePercents)
                .addBounds();


    }
}