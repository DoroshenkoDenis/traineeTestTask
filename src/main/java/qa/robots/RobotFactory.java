package qa.robots;

public class RobotFactory {

    public AbstractRobot createRobot(RobotType type) {
        AbstractRobot robot = null;

        switch (type) {
            case FIGHTING:
                robot = new FightingRobot();
                break;
//            case COOKING:
//                robot = new CookingRobot(robotType, name, movement, energy, creationYear, energyReserve);
//                break;
//            case WILDING:
//                robot = new WeldingRobot(robotType, name, movement, energy, creationYear, energyReserve);
//                break;
            default:
                System.out.println("SYSTEM: Oooops, something wrong!");
        }
        System.out.println("SYSTEM: The robot type " + type + " created!");
        return robot;
    }

//    public AbstractRobot createRobot(RobotType type) {
//        AbstractRobot robot = null;
//
//        switch (type) {
//            case COOKING:
//                robot = new CookingRobot(type);
//                break;
//            case WILDING:
//                robot = new WeldingRobot(type);
//                break;
//            default:
//                System.out.println("System message: Oooops, something wrong!");
//        }
//        System.out.println("System message: The robot type " + type + " created!");
//        return robot;
//    }

}