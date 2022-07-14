package qa.robots;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * RobotFactory
 * * @author Denis Doroshenko
 * * @version 1.0.1
 */

//полиморфизм - возможность создавать потомков разного типа, использовать их как один тип
public class RobotFactory {

    public void checkInputEnergyReserve(int energyReserve, AbstractRobot robot) {
        if (energyReserve > 100 || energyReserve < 1) {
            System.out.println("SYSTEM: Incorrect value: energyReserve = " + energyReserve + "! It must be from 1 to 100");
            Scanner in = new Scanner(System.in);
            System.out.print("SYSTEM: Input new value... ");
            String inputString = in.nextLine();
            robot.setEnergyReserve(Integer.parseInt(inputString));
        }
    }

    public void checkInputCreationYear(int creationYear, AbstractRobot robot) {
        if (creationYear > LocalDate.now().getYear() || creationYear < 0) {
            System.out.println("SYSTEM: Incorrect value: creationYear = " + creationYear + ". The value cannot be greater than the current " + LocalDate.now().getYear() + " year! Please select other creation Year!");
            Scanner in = new Scanner(System.in);
            System.out.print("SYSTEM: Input new value... ");
            String inputString = in.nextLine();
            robot.setCreationYear(Integer.parseInt(inputString));
        }
    }

    public AbstractRobot createRobot(RobotType robotType, RobotName name, RobotMovement movement, RobotEnergy energy, int energyReserve, int creationYear) {
        AbstractRobot robot = null;
        switch (robotType) {
            case COOKING:
                robot = new CookingRobot(robotType, name, movement, energy, energyReserve, creationYear);

                break;
            case WILDING:
                robot = new WeldingRobot(robotType, name, movement, energy, energyReserve, creationYear);
                break;
            default:
                System.out.println("SYSTEM: Oooops, something wrong!");
        }
        checkInputCreationYear(creationYear, robot);
        checkInputEnergyReserve(energyReserve, robot);
        robot.setCurrentEnergyType(energy);
        robot.setRobotType(robotType);
        robot.setWorkCounter(1);
        System.out.println("SYSTEM: The robot type " + robotType + " created!");
        return robot;
    }

    public AbstractRobot createRobot(RobotType robotType, RobotName name, RobotMovement movement, RobotEnergy energy, int energyReserve, int creationYear, RobotTools weapon) {
        AbstractRobot robot = new FightingRobot(robotType, name, movement, energy, energyReserve, creationYear, weapon);
        checkInputCreationYear(creationYear, robot);
        checkInputEnergyReserve(energyReserve, robot);
        robot.setCurrentEnergyType(energy);
        robot.setRobotType(robotType);
        robot.setWorkCounter(1);
        System.out.println("SYSTEM: The robot type " + robotType + " created!");
        return robot;
    }

}