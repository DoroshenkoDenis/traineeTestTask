package qa.robots;

import java.util.Scanner;

import static qa.robots.RobotTools.AUTOMATIC_RIFLE;
import static qa.robots.RobotTools.SWORD;
import static qa.robots.RobotType.FIGHTING;

//реализовано наследование
public class FightingRobot extends AbstractRobot {

    public FightingRobot(RobotType robotType, RobotName name, RobotMovement movement, RobotEnergy energy, int energyReserve, int creationYear, RobotTools weapon) {
        super(robotType, name, movement, energy, energyReserve, creationYear, weapon);
    }

    /**
     * method for the robot to perform an action
     * calls sub-method for remaining energy check
     * calls a sub-method to use the weapon
     *
     * @param type - robot/work type
     * @return -  Fighting robot instance
     */
    @Override
    public AbstractRobot work(RobotType type) {
        try {
            if (type.equals(FIGHTING)) {
                checkExpendEnergy(getEnergy(), getCreationYear(), getEnergyReserve());
                try {
                    useWeapon();
                } catch (NullPointerException exception) {
                    System.out.println("SYSTEM: !!! You didn't choose a weapon !!!");
                    changeWeapon();
                    useWeapon();
                }
            } else {
                System.out.println("SYSTEM : You are setting up a new job " + type + " for the robot");
                System.out.println("ROBOT : I'm can`t " + type + ". Only Fighting!");
            }
        } catch (NullPointerException e) {
            System.out.println("ROBOT: Really? Are you hacking me???");
        }
        return this;
    }

    /**
     * method of using a weapon
     * the robot can strike and shoot
     */
    public void useWeapon() {
        if (getWeapon().equals(AUTOMATIC_RIFLE)) {
            System.out.println("ROBOT ACTION: Shooting");
        } else {
            System.out.println("ROBOT ACTION: Striking");
        }
    }

    /**
     * method for choosing weapons
     */
    public void changeWeapon() {
        Scanner in = new Scanner(System.in);
        System.out.print("SYSTEM: Input 'WEAPON' or press any+Enter to use weapon by default... ");
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
