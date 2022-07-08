package qa.robots;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;
import static qa.robots.RobotEnergy.*;
import static qa.robots.RobotMovement.*;
import static qa.robots.RobotName.*;
import static qa.robots.RobotTools.*;
import static qa.robots.RobotType.*;

class FightingRobotTest {

    void setWeaponInput() {
        ByteArrayInputStream in = new ByteArrayInputStream(AUTOMATIC_RIFLE.toString().getBytes());
        System.setIn(in);
    }
    String shootingNotify = "ROBOT ACTION: Shooting";
    String strikingNotify = "ROBOT ACTION: Striking";

    @Test
    void workPositiveTest() {
        setWeaponInput();
        RobotFactory factory = new RobotFactory();

        AbstractRobot fightingAtomicRobot = factory.createRobot(FIGHTING, UNO, FLY, ATOMIC, 100, 1097, AUTOMATIC_RIFLE, shootingNotify, strikingNotify);
        fightingAtomicRobot.work(FIGHTING);
        assertEquals(FIGHTING, fightingAtomicRobot.getRobotType());
    }

    @Test
    void changeWeaponTest() {
        setWeaponInput();
        RobotFactory factory = new RobotFactory();
        AbstractRobot fightingAtomicRobot = factory.createRobot(FIGHTING, UNO, FLY, ATOMIC, 100, 1097, AUTOMATIC_RIFLE, shootingNotify, strikingNotify);
        fightingAtomicRobot.work(FIGHTING);
                assertEquals(AUTOMATIC_RIFLE, fightingAtomicRobot.getWeapon());
        fightingAtomicRobot.setWeapon(SWORD)
                .work(FIGHTING);
        assertEquals(SWORD, fightingAtomicRobot.getWeapon());
    }

}