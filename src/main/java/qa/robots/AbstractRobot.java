package qa.robots;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * Robots creating
 * * @author Denis Doroshenko
 * * @version 1.0.1
 */

public abstract class AbstractRobot {

    private RobotType robotType;
    private RobotName name;
    private RobotMovement movement;
    private RobotEnergy energy;
    private RobotEnergy currentEnergyType;
    private int creationYear;
    private int energyReserve;
    private RobotTools weapon;

    private static final int costDieselPerAction = 15;
    private static final int costElectricityPerAction = 10;
    private static final int maxEnergyAmount = 100;
    private static final int atomicEnergyReserve = 50;
    private static final int minBatteryCharge = 20;
    private static int workCounter = 1;
    private static String energyStatus = "full";

    /**
     * @param type - robot type
     * @return -
     */
    public abstract AbstractRobot work(RobotType type);

    /**
     * @param creationYear
     * @return
     */
    public int countAtomicEnergy(int creationYear) {
        if (creationYear > LocalDate.now().getYear()) {
            System.out.println("SYSTEM: Incorrect value: energyReserve = " + creationYear + ". The value cannot be greater than the current " + LocalDate.now().getYear() + " year! Please select other creation Year!");
            System.exit(0);
        }
        if (energyStatus.equals("empty")) {
            creationYear = LocalDate.now().getYear();
        }
        return maxEnergyAmount - ((maxEnergyAmount / atomicEnergyReserve) * (LocalDate.now().getYear() - creationYear));
    }

    /**
     * @param energyReserve
     * @return
     */
    public static int countDiesel(int energyReserve) {
        return ((energyReserve * maxEnergyAmount) / 100 - costDieselPerAction * workCounter);
    }

    /**
     * @param energyReserve
     * @return
     */
    public static int countBatteryCharge(int energyReserve) {
        return ((energyReserve * maxEnergyAmount) / 100 - costDieselPerAction * workCounter);
    }

    /**
     * robot refueling method
     * for correct refueling, you need to enter the current fuel
     * @param energyType - type of energy
     */
    public void requestForFuel(RobotEnergy energyType) {
        System.out.println("ROBOT: I'm " + energyType + " and my fuel is not enough, so i need to refuel");
        Scanner in = new Scanner(System.in);
        System.out.print("SYSTEM: Input 'FUEL' and approve... ");
        String inputString = in.nextLine();
        while (!inputString.equalsIgnoreCase(currentEnergyType.toString()) || !inputString.equalsIgnoreCase("N")) {
            if (inputString.equalsIgnoreCase(currentEnergyType.toString())) {
                System.out.println("YOU: ¯\\_(ツ)_/¯  Here buddy hold your fuel!");
                System.out.println("ROBOT: (◕‿◕)  Thank you Master, you are the best!");
                workCounter = 1;
                energyStatus = "empty";
                break;
            } else if (inputString.equalsIgnoreCase("N")) {
                System.exit(0);
            } else {
                System.out.println("ROBOT: Oooops, This is not what I wanted! Try again or 'N' to exit");
            }
            inputString = in.nextLine();
        }
    }

    /**
     * @param energyType
     */
    public void requestForWrongFuel(RobotEnergy energyType) {
        if (workCounter == 1) {
            currentEnergyType = energyType;
        } else {
            if (currentEnergyType.equals(energyType)) {
                System.out.println(">>>>>>>>>");
            } else {
                System.out.println("ROBOT: What are you doing with me??? " + "I had a " + energyType + " now! Is it OK???");
                Scanner in = new Scanner(System.in);
                System.out.print("SYSTEM: Input Y to approve (or not)... ");
                String inputString = in.nextLine();
                if (inputString.equalsIgnoreCase("Y")) {
                    System.out.println("YOU: ¯\\_(ツ)_/¯ " + " All OK! Don't worry it's planned");
                } else {
                    System.out.println("YOU: I don't like robots, goodbye! " + " ╮( ˘ ､ ˘ )╭");
                    System.exit(0);
                }
            }
        }
    }

    /**
     * Method of accounting for energy consumption
     * In case of insufficient energy requests energy
     * Warns if the energy does not match the original used
     *
     * @param energyType
     * @param energyReserve
     */
    public void checkExpendEnergy(RobotEnergy energyType, int energyReserve) {
        requestForWrongFuel(energyType);

        switch (energyType) {

            case ATOMIC:
                int atomEnergy = countAtomicEnergy(energyReserve);
                if (atomEnergy <= 0) {
                    requestForFuel(energyType);
                } else {
                    System.out.println("ROBOT: I use " + energyType + " power and " + atomEnergy + "% energy left");
                }
                break;

            case DIESEL:
                int dieselFuel = countDiesel(energyReserve);
                if (dieselFuel < (costDieselPerAction)) {
                    requestForFuel(energyType);
                } else {
                    System.out.println("ROBOT: I use " + energyType + " power and " + dieselFuel + "% energy left");
                    workCounter++;
                }
                break;

            case ELECTRIC:
                int batteryCharge = countBatteryCharge(energyReserve);
                if (batteryCharge < (costElectricityPerAction + minBatteryCharge)) {
                    requestForFuel(energyType);
                } else {
                    System.out.println("ROBOT: I use " + energyType + " power and " + batteryCharge + "% energy left");
                    workCounter++;
                }
                break;

            default:
                System.out.println("ROBOT: Oooops, something wrong !");
        }
    }

    /**
     * gives an error message if the year of manufacture of the robot is greater than the current year
     * gives an error message if the energy source is set to more than 100%
     *
     * @param energyReserve - percentage of energy source
     */
    public void customDataExeption(int energyReserve) {
        if (energyReserve > 100) {
            System.out.println("SYSTEM: Incorrect value: energyReserve = " + energyReserve + ". The value cannot be greater than 100%!");
            System.exit(0);
        }
    }

    public RobotType getRobotType() {
        return robotType;
    }

    /* Getters and Setters */

    public RobotName getName() {
        return name;
    }

    public RobotMovement getMovement() {
        return movement;
    }

    public RobotEnergy getEnergy() {
        return energy;
    }

    public int getCreationYear() {
        return creationYear;
    }

    public int getEnergyReserve() {
        return energyReserve;
    }

    public static String getEnergyStatus() {
        return energyStatus;
    }

    public RobotEnergy getCurrentEnergy() {
        return currentEnergyType;
    }

    public RobotTools getWeapon() {
        return weapon;
    }

    public AbstractRobot setName(RobotName name) {
        this.name = name;
        return this;
    }

    public AbstractRobot setMovement(RobotMovement movement) {
        this.movement = movement;
        return this;
    }

    public AbstractRobot setEnergy(RobotEnergy energy) {
        this.energy = energy;
        return this;
    }

    public AbstractRobot setCreationYear(int creationYear) {
        this.creationYear = creationYear;
        return this;
    }

    public AbstractRobot setEnergyReserve(int energyReserve) {
        this.energyReserve = energyReserve;
        return this;
    }

    public AbstractRobot setWeapon(RobotTools weapon) {
        this.weapon = weapon;
        return this;
    }

}
