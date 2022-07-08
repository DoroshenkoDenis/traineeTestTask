package qa.robots;

/**
 * RobotFactory
 * * @author Denis Doroshenko
 * * @version 1.0.1
 */

//РЕАЛИЗОВАН ПОЛИМРИЗМ - ВОЗМОЖНОСТЬ СОЗДАВАТЬ ПОТОМКОВ РАЗНОГО ТИПА
public class RobotFactory {
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
        robot.setCurrentEnergyType(energy);
        robot.setRobotType(robotType);
        robot.setWorkCounter(1);
        System.out.println("SYSTEM: The robot type " + robotType + " created!");
        return robot;
    }


    public AbstractRobot createRobot(RobotType robotType, RobotName name, RobotMovement movement, RobotEnergy energy, int energyReserve, int creationYear, RobotTools weapon, String shNotify, String strNotify) {
        AbstractRobot robot = null;
        robot = new FightingRobot(robotType, name, movement, energy, energyReserve, creationYear, weapon, shNotify, strNotify);
        robot.setCurrentEnergyType(energy);
        robot.setRobotType(robotType);
        robot.setWorkCounter(1);
        System.out.println("SYSTEM: The robot type " + robotType + " created!");
        return robot;
    }

}