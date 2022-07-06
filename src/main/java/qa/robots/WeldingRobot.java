package qa.robots;

public class WeldingRobot extends AbstractRobot {

    @Override
    public AbstractRobot letsWork(String workType, String energyType, int energyReserve) {
        customDataExeption(energyType, energyReserve);
        if (workType.equals("Weld")) {
            checkExpendEnergy(energyType, energyReserve);
            System.out.println("R: I'm Welding and I love It");
        } else {
            System.out.println("R: I'm can`t " + workType + ". Only Welding!");
        }
        return this;
    }
}

