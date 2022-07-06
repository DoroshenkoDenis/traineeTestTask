package qa.robots;

public class CookingRobot extends AbstractRobot {

    @Override
    public AbstractRobot letsWork(String workType, String energyType, int energyReserve) {
        customDataExeption(energyType, energyReserve);
        if (workType.equals("Cook")) {
            checkExpendEnergy(energyType, energyReserve);
            System.out.println("R: I'm Cooking and I love It");
        } else {
            System.out.println("R: I'm can`t " + workType + ". Only Сooking!");
        }
        return this;
    }


}
