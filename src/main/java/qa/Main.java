package qa;

import qa.robots.*;

import static qa.robots.RobotEnergy.*;
import static qa.robots.RobotMovement.*;
import static qa.robots.RobotName.*;
import static qa.robots.RobotTools.*;
import static qa.robots.RobotType.*;

public class Main {
//    note that need input actions
//    after each action, the amount of energy decreases (the exception is an atomic-powered robot; its energy decreases over the years)
//    FIGHTING type robot uses SWORD by default if you don't select a weapon
//    weapons: AUTOMATIC_RIFLE, SWORD
//    movement: FLY, WALK, RIDE
//    robot/work type: FIGHTING, COOKING, WILDING
//    robot name: R2D2, HAL9000, WALLE, ASIMO, K9, TERMINATOR, MICRO, JUPITER, UNO
//    energy: ATOMIC, DIESEL, ELECTRIC

    public static void main(String[] args) {
        RobotFactory factory = new RobotFactory();
        AbstractRobot fightingRobot = factory.createRobot(FIGHTING);
        fightingRobot.setName(R2D2).setEnergy(ATOMIC).setMovement(FLY).setCreationYear(1989)
                .work(FIGHTING)
                .work(FIGHTING)
                .setWeapon(AUTOMATIC_RIFLE)
                .work(COOKING)
                .work(FIGHTING);

    }
}