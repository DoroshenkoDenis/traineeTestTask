package qa;

import qa.robots.*;

import static qa.robots.RobotEnergy.*;
import static qa.robots.RobotMovement.*;
import static qa.robots.RobotName.*;
import static qa.robots.RobotTools.*;
import static qa.robots.RobotType.*;

public class Main {
//    note that need console input actions
//    after each action, the amount of energy decreases (the exception is an atomic-powered robot; its energy decreases over the years)
//    FIGHTING type robot uses SWORD by default if you don't select a weapon
//    weapons: AUTOMATIC_RIFLE, SWORD
//    movement: FLY, WALK, RIDE
//    robot/work type: FIGHTING, COOKING, WILDING
//    robot name: R2D2, HAL9000, WALLE, ASIMO, K9, TERMINATOR, MICRO, JUPITER, UNO
//    energy: ATOMIC, DIESEL, ELECTRIC
//    check pairwise: src/test/resources/RobotsPairwise.xlsx
//    check simple test example: src/test/java/qa/robots/FightingRobotTest.java


    public static void main(String[] args) throws InterruptedException {
        RobotFactory factory = new RobotFactory();

////      old atomic robot needs new engine
//        System.out.println("\n----- Fighting Atomic Robot -----");
//        AbstractRobot fightingAtomicRobot = factory.createRobot(FIGHTING, UNO, FLY, ATOMIC, 100, 1000, AUTOMATIC_RIFLE);
//        fightingAtomicRobot
//                .work(FIGHTING)
//                .work(FIGHTING);
//
////      shows fuel decries and asks for refuel
//        System.out.println("\n----- Fighting Diesel Robot -----");
//        AbstractRobot fightingDieselRobot = factory.createRobot(FIGHTING, R2D2, WALK, DIESEL, 100, 1997, AUTOMATIC_RIFLE);
//        fightingDieselRobot
//                .work(FIGHTING)
//                .work(null)
//                .work(FIGHTING)
//                .work(COOKING)
//                .work(FIGHTING)
//                .work(FIGHTING)
//                .work(FIGHTING)
//                .work(FIGHTING)
//                .work(FIGHTING);
//
////      fighting robot changes weapons
//        System.out.println("\n----- Fighting Electric Robot -----");
//        AbstractRobot fightingElectricRobot = factory.createRobot(FIGHTING, HAL9000, RIDE, ELECTRIC, 100, 1987, AUTOMATIC_RIFLE);
//        fightingElectricRobot
//                .work(FIGHTING)
//                .setWeapon(null)
//                .work(FIGHTING)
//                .setWeapon(AUTOMATIC_RIFLE)
//                .work(FIGHTING)
//                .requestForFuel(ELECTRIC);
//
////      early refueling of robot
//        System.out.println("\n----- Cooking Diesel Robot -----");
//        AbstractRobot cookingDieselRobot = factory.createRobot(COOKING, WALLE, RIDE, DIESEL, 100, 1987);
//        cookingDieselRobot
//                .work(COOKING)
//                .requestForFuel(DIESEL)
//                .work(COOKING)
//                .work(COOKING)
//                .work(COOKING);
//
////      try to use the robot for other purposes
//        System.out.println("\n----- Cooking Electric Robot -----");
//        AbstractRobot cookingElectricRobot = factory.createRobot(COOKING, ASIMO, FLY, ELECTRIC, 100, 1987);
//        cookingElectricRobot
//                .work(COOKING)
//                .work(FIGHTING)
//                .work(WILDING)
//                .work(null)
//                .work(COOKING)
//                .requestForFuel(ELECTRIC);
//
////      try to change fuelType
//        System.out.println("\n----- Cooking Atomic Robot -----");
//        AbstractRobot cookingAtomicRobot = factory.createRobot(COOKING, K9, WALK, ATOMIC, 100, 1987);
//        cookingAtomicRobot
//                .work(COOKING)
//                .setEnergy(ELECTRIC)
//                .work(COOKING)
//                .requestForFuel(ATOMIC);

//      incorrect energy reserve
        System.out.println("\n----- Wilding Electric Robot -----");
        AbstractRobot wildingElectricRobot = factory.createRobot(WILDING, TERMINATOR, WALK, ELECTRIC, -100, 1987);
        wildingElectricRobot
                .work(WILDING)
                .work(COOKING)
                .work(WILDING)
                .work(WILDING)
                .requestForFuel(ATOMIC);

//      incorrect creationYear
        System.out.println("\n----- Wilding Atomic Robot -----");
        AbstractRobot wildingAtomicRobot = factory.createRobot(WILDING, JUPITER, RIDE, ATOMIC, 100, 2222);
        wildingAtomicRobot
                .work(WILDING)
                .work(FIGHTING)
                .work(WILDING)
                .work(WILDING);

//      no more fantasy
        System.out.println("\n----- Wilding Diesel Robot -----");
        AbstractRobot wildingDieselRobot = factory.createRobot(WILDING, MICRO, FLY, DIESEL, 100, 1987);
        wildingDieselRobot
                .work(WILDING)
                .work(WILDING)
                .work(WILDING)
                .work(WILDING)
                .requestForFuel(ATOMIC);

    }
}