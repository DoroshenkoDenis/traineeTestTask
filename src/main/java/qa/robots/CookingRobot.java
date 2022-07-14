package qa.robots;

import static qa.robots.RobotType.COOKING;

//реализовано наследование
public class CookingRobot extends AbstractRobot {

    public CookingRobot(RobotType robotType, RobotName name, RobotMovement movement, RobotEnergy energy, int energyReserve, int creationYear) {
        super(robotType, name, movement, energy, energyReserve, creationYear);
    }

    @Override
    public AbstractRobot work(RobotType type) {
        try {
            if (type.equals(COOKING)) {
                checkExpendEnergy(getEnergy(), getCreationYear(), getEnergyReserve());
                System.out.println("ROBOT ACTION: Cooking");
            } else {
                System.out.println("ROBOT: I'm can`t " + type + ". Only Cooking!");
            }
        } catch (NullPointerException e) {
            System.out.println("ROBOT: Really? Are you hacking me???");
        }
        return this;
    }


}
