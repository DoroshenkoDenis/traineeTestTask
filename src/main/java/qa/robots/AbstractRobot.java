package qa.robots;

import java.time.LocalDate;
import java.util.Scanner;

public abstract class AbstractRobot {

    private static final int costDieselPerAction = 15;
    private static final int costElectricityPerAction = 10;
    private static final int maxEnergyAmount = 100;
    private static final int atomicEnergyReserve = 50;
    private static final int minBatteryCharge = 20;
    private static int n = 1;
    private static String energyStatus = "full";

//   The energyReserve can be considered as creationYear (years), gas tank (liters), battery capacity (kWh)

    public int countAtomicEnergy(int energyReserve) {
        if (energyStatus.equals("empty")) {
            energyReserve = LocalDate.now().getYear();
        }
        return maxEnergyAmount - ((maxEnergyAmount / atomicEnergyReserve) * (LocalDate.now().getYear() - energyReserve));
    }

    public static int countDiesel(int energyReserve) {
        return ((energyReserve * maxEnergyAmount) / 100 - costDieselPerAction * n);
    }

    public static int countBatteryCharge(int energyReserve) {
        return ((energyReserve * maxEnergyAmount) / 100 - costDieselPerAction * n);
    }


    public void requestForFuel(String energyType) {
        System.out.println("R: I'm " + energyType + " and my fuel is not enough, so i need to refuel");
        Scanner in = new Scanner(System.in);
        System.out.print("S: Input Y to approve (or not)... ");
        String inputString = in.nextLine();
        if (inputString.equalsIgnoreCase("Y")) {
            System.out.println("M: ¯\\_(ツ)_/¯  Here buddy hold your fuel!");
            System.out.println("R: (◕‿◕)  Thank you Master, you are the best!");
            n = 1;
            energyStatus = "empty";
        } else {
            System.out.println("M: I don't like robots, goodbye! " + " ╮( ˘ ､ ˘ )╭");
            System.exit(0);
        }
    }

    private String currentEnergy;

    public void requestForWrongFuel(String energyType) {
        if (n == 1) {
            currentEnergy = energyType;
        } else {
            if (currentEnergy.equals(energyType)) {
                System.out.println(">>>>>>>>>");
            } else {
                System.out.println("R: What are you doing with me??? " + "I had a " + energyType + " now! Is it OK???");
                Scanner in = new Scanner(System.in);
                System.out.print("S: Input Y to approve (or not)... ");
                String inputString = in.nextLine();
                if (inputString.equalsIgnoreCase("Y")) {
                    System.out.println("M: ¯\\_(ツ)_/¯ " + " All OK! Don't worry it's planned");
                } else {
                    System.out.println("M: I don't like robots, goodbye! " + " ╮( ˘ ､ ˘ )╭");
                    System.exit(0);
                }
            }
        }
    }

    public void checkExpendEnergy(String energyType, int energyReserve) {

        requestForWrongFuel(energyType);

        switch (energyType) {

            case "atomic":
                int atomEnergy = countAtomicEnergy(energyReserve);
                if (atomEnergy <= 0) {
                    requestForFuel(energyType);
                } else {
                    System.out.println("R: I use " + energyType + " power and " + atomEnergy + "% energy left");
                }
                break;

            case "diesel":
                int dieselFuel = countDiesel(energyReserve);
                if (dieselFuel < (costDieselPerAction)) {
                    requestForFuel(energyType);
                } else {
                    System.out.println("R: I use " + energyType + " power and " + dieselFuel + "% energy left");
                    n++;
                }
                break;

            case "electric":
                int batteryCharge = countBatteryCharge(energyReserve);
                if (batteryCharge < (costElectricityPerAction + minBatteryCharge)) {
                    requestForFuel(energyType);
                } else {
                    System.out.println("R: I use " + energyType + " power and " + batteryCharge + "% energy left");
                    n++;
                }
                break;

            default:
                System.out.println("R: Oooops, something wrong !");
        }
    }

    public AbstractRobot setMove(String moveType) {
        n = 1;
        switch (moveType) {
            case "fly":
                System.out.println("R: I'm Flying robot and...");
                break;
            case "walk":
                System.out.println("R: I'm Walking robot and...");
                break;
            case "ride":
                System.out.println("R: I'm Riding robot and...");
                break;
            default:
                System.out.println("R: Oooops, something wrong !");
        }
        return this;
    }


    public abstract AbstractRobot letsWork(String workType, String energyType, int energyReserve);

    public void customDataExeption(String energyType, int energyReserve) {
        if (energyType.equals("atomic") && energyReserve > LocalDate.now().getYear()) {
            System.out.println("S: Incorrect value: energyReserve = " + energyReserve + ". The value cannot be greater than the current " + LocalDate.now().getYear() + " year! Please select other creation Year!");
            System.exit(0);
        } else if (!energyType.equals("atomic") && energyReserve > 100) {
            System.out.println("S: Incorrect value: energyReserve = " + energyReserve + ". The value cannot be greater than 100%!");
            System.exit(0);
        }
    }

    public AbstractRobot addBounds() {
        System.out.println("----------------------------------------------------");
        return this;
    }

}
