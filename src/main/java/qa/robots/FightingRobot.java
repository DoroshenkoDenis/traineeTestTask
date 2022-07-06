package qa.robots;

public class FightingRobot extends AbstractRobot {

    @Override
    public FightingRobot letsWork(String workType, String energyType, int energyReserve) {
        customDataExeption(energyType, energyReserve);
        if (workType.equals("Fight")) {
            checkExpendEnergy(energyType, energyReserve);
            System.out.println("R: I'm Fighting and I love It");
        } else {
            System.out.println("R: I'm can`t " + workType + ". Only Fighting!");
        }
        return this;
    }


}
