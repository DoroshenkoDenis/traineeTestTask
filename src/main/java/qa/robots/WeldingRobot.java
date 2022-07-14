package qa.robots;

import static qa.robots.RobotType.WILDING;

//реализовано наследование
public class WeldingRobot extends AbstractRobot {

    public WeldingRobot(RobotType robotType, RobotName name, RobotMovement movement, RobotEnergy energy, int energyReserve, int creationYear) {
        super(robotType, name, movement, energy, energyReserve, creationYear);
    }

    @Override
    public AbstractRobot work(RobotType type) {
        try {
            if (type.equals(WILDING)) {
                checkExpendEnergy(getEnergy(), getCreationYear(), getEnergyReserve());
                System.out.println("ROBOT ACTION:  Welding");
            } else {
                System.out.println("ROBOT: I'm can`t " + type + ". Only Welding!");
            }
        } catch (NullPointerException e) {
            System.out.println("ROBOT: Really? Are you hacking me???");
        }
        return this;
    }
}

