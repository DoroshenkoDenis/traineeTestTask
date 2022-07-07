package qa.robots;

import java.util.Scanner;

import static qa.robots.RobotTools.AUTOMATIC_RIFLE;
import static qa.robots.RobotTools.SWORD;
import static qa.robots.RobotType.FIGHTING;

public class FightingRobot extends AbstractRobot {

    private RobotTools weaponType;

    /**
     * @param type
     * @return
     */
    @Override
    public AbstractRobot work(RobotType type) {
        checkExpendEnergy(getEnergy(), getCreationYear());
        if (type.equals(FIGHTING)) {
            try {
                useWeapon();
            } catch (NullPointerException exception) {
                System.out.println("SYSTEM: !!! You didn't choose a weapon !!!");
                changeWeapon();
                useWeapon();
            }
        } else {
            System.out.println("ROBOT : I'm can`t " + type + ". Only Fighting!");
        }
        return this;
    }

    public void useWeapon() {
        if (getWeapon().equals(AUTOMATIC_RIFLE)) {
            System.out.println("ROBOT ACTION: Shooting with an " + getWeapon() + " and robot loves It");
        } else {
            System.out.println("ROBOT ACTION: Striking with a " + getWeapon() + " weapon");
        }
    }

    public void changeWeapon() {
        Scanner in = new Scanner(System.in);
        System.out.print("SYSTEM: Input 'WEAPON' and approve... ");
        String inputString = in.nextLine();
        if (inputString.equalsIgnoreCase((AUTOMATIC_RIFLE).toString())) {
            setWeapon(AUTOMATIC_RIFLE);
            System.out.println("ROBOT: I'm using AUTOMATIC_RIFLE");
        } else {
            setWeapon(SWORD);
            System.out.println("ROBOT: I'm using SWORD by default");
        }
    }
}
