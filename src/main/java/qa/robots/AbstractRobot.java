package qa.robots;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * AbstractRobot
 * * @author Denis Doroshenko
 * * @version 1.0.1
 */

//РЕАЛИЗОВАНЫ НАСЛЕДОВАНИЕ, ИНКАПСУЛЯЦИЯ, ПОЛИМОРФИЗМ - РОДИТЕЛЬ-АБСТРАКТНЫЙ КЛАС, СОКРЫТЫ ПРИВАТНЫЕ ПОЛЯ - ДОСТУП ЧЕРЕЗ ГЕТТЕРЫ-СЕТТЕРЫ, НЕСКОЛЬКО КОНСТРУКТОРОВ - ПОТОМКИ МОГУТ СОЗДАВАТЬ ЭКЗЕМПЛЯРЫ С РАЗНЫМИ ПОЛЯМИ
public abstract class AbstractRobot {

    private RobotType robotType;
    private RobotName name;
    private RobotMovement movement;
    private RobotEnergy energy;
    private RobotEnergy currentEnergyType;
    private int creationYear;
    private int energyReserve;
    private RobotTools weapon;
    private static String energyStatus = "full";

    private static final int costDieselPerAction = 15;
    private static final int costElectricityPerAction = 10;
    private static final int atomicEnergyReserve = 50;
    private static final int minBatteryCharge = 20;
    private int workCounter = 1;

    public AbstractRobot(RobotType robotType, RobotName name, RobotMovement movement, RobotEnergy energy, int energyReserve, int creationYear, RobotTools weapon) {
        this.robotType = robotType;
        this.name = name;
        this.movement = movement;
        this.energy = energy;
        this.energyReserve = energyReserve;
        this.creationYear = creationYear;
        this.weapon = weapon;
    }

    public AbstractRobot(RobotType robotType, RobotName name, RobotMovement movement, RobotEnergy energy, int energyReserve, int creationYear) {
        this.robotType = robotType;
        this.name = name;
        this.movement = movement;
        this.energy = energy;
        this.energyReserve = energyReserve;
        this.creationYear = creationYear;
    }

    /**
     * @param type - robot type
     * @return - robot instance
     */
    public abstract AbstractRobot work(RobotType type);

    /**
     * method for calculating the residual energy of an atomic engine
     * in the {@link #requestForFuel(RobotEnergy energyType)} the variable 'energyStatus' is assigned the value 'empty', so atomic energy can be restored
     *
     * @param creationYear - year of creation of the robot
     * @return - remaining energy as a percentage of service life
     */
    public int countAtomicEnergy(int creationYear) {

        if (energyStatus.equals("empty")) {
            creationYear = LocalDate.now().getYear();
        }
        return 100 - ((100 / atomicEnergyReserve) * (LocalDate.now().getYear() - creationYear));
    }

    /**
     * method for calculating diesel fuel
     * 'workCounter' - counter of actions performed by the robot
     * 'costDieselPerAction' - energy cost per action as a percentage
     *
     * @param energyReserve - capacity of the energy carrier or fuel tank
     * @return - remaining fuel as a percentage
     */
    public static int countDiesel(int energyReserve, int workCounter) {
        return energyReserve - costDieselPerAction * workCounter;
    }

    /**
     * method for calculating electric charge
     * 'workCounter' - counter of actions performed by the robot
     * 'costElectricityPerAction' - energy cost per action as a percentage
     *
     * @param energyReserve - capacity of the energy carrier or fuel tank
     * @return - remaining energy as a percentage
     */
    public static int countBatteryCharge(int energyReserve, int workCounter) {
        return energyReserve - costElectricityPerAction * workCounter;
    }

    /**
     * robot refueling method
     * for correct refueling, you need to enter the current source of energy or fuel
     *
     * @param energyType - type of energy
     */
    public AbstractRobot requestForFuel(RobotEnergy energyType) {
        try {
            if (!energyType.equals(currentEnergyType)) {
                System.out.println("SYSTEM: You can't change the energy type!");
            } else {
                System.out.println("ROBOT: I like " + energyType + " and I want to replenish fuel");
                Scanner in = new Scanner(System.in);
                System.out.print("SYSTEM: Input 'FUEL' and ENTER... ");
                String inputString = in.nextLine();
                while (!inputString.equalsIgnoreCase(currentEnergyType.toString()) || !inputString.equalsIgnoreCase("N")) {
                    if (inputString.equalsIgnoreCase(currentEnergyType.toString())) {
                        System.out.println("YOU: Here buddy hold your fuel!");
                        System.out.println("ROBOT: Thank you Master, you are the best!");
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
        } catch (NullPointerException e) {
            System.out.println("ROBOT: Don't make me laugh !");
        }
        return this;
    }

    /**
     * Method of accounting for energy consumption
     * In case of insufficient energy requests energy
     * Warns if the energy does not match the original
     * Used before the robot performs an action
     *
     * @param energyType    -
     * @param energyReserve -
     */
    public void checkExpendEnergy(RobotEnergy energyType, int creationYear, int energyReserve) {

        switch (energyType) {
            case ATOMIC:
                int atomEnergy = countAtomicEnergy(creationYear);
                if (atomEnergy <= 0) {
                    requestForFuel(energyType);
                } else {
                    System.out.println("ROBOT: I use " + energyType + " power and " + atomEnergy + "% energy left");
                }
                break;

            case DIESEL:
                int dieselFuel = countDiesel(energyReserve, workCounter);
                if (dieselFuel < (costDieselPerAction)) {
                    requestForFuel(energyType);
                } else {
                    System.out.println("ROBOT: I use " + energyType + " power and " + dieselFuel + "% energy left");
                    workCounter++;
                }
                break;

            case ELECTRIC:
                int batteryCharge = countBatteryCharge(energyReserve, workCounter);
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


    /* Getters and Setters */

    public RobotType getRobotType() {
        return robotType;
    }

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

    public void setCurrentEnergyType(RobotEnergy currentEnergyType) {
        this.currentEnergyType = currentEnergyType;
    }

    /**
     * Method for energy type setting, breaks the robot if the fuel does not match
     * blocks if energy type setting is repeated
     *
     * @param energy - type of energy
     * @return - robot instance
     */
    public AbstractRobot setEnergy(RobotEnergy energy) throws InterruptedException {
        if (!currentEnergyType.equals(energy)) {
            System.out.println("SYSTEM: You just broke a robot...");
            final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

            final Runnable runnable = new Runnable() {
                int countdownStarter = 9;

                public void run() {
                    System.out.println(countdownStarter);
                    countdownStarter--;
                    if (countdownStarter < 0) {
                        System.out.println("The robot was successfully restored, do not do this again!");
                        scheduler.shutdown();
                    }
                }
            };
            scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
            Thread.sleep(10000);
        }
        return this;
    }

    /**
     * Method for Creation Year setting
     * gives an error message if the year of manufacture of the robot is greater than the current year
     *
     * @param creationYear - year of creation of the robot
     * @return - robot instance
     */
    public AbstractRobot setCreationYear(int creationYear) {
        if (creationYear > LocalDate.now().getYear() || creationYear < 0) {
            do {
                System.out.println("SYSTEM: Incorrect value: creationYear = " + creationYear + ". The value cannot be greater than the current " + LocalDate.now().getYear() + " year! Please select other creation Year!");
                Scanner in = new Scanner(System.in);
                System.out.print("SYSTEM: Input new value... ");
                creationYear = in.nextInt();
            } while (creationYear > LocalDate.now().getYear() || creationYear < 0);
        }
        this.creationYear = creationYear;
        return this;
    }

    /**
     * Method for Energy Reserve setting
     * gives an error message if the energy source is set to more than 100% or less than 1%
     *
     * @param energyReserve -percentage of energy source
     * @return - robot instance
     */
    public AbstractRobot setEnergyReserve(int energyReserve) {
        if (energyReserve > 100 || energyReserve < 1) {
            do {
                System.out.println("SYSTEM: Incorrect value: energyReserve = " + energyReserve + "! It must be from 1 to 100");
                Scanner in = new Scanner(System.in);
                System.out.print("SYSTEM: Input new value... ");
                energyReserve = in.nextInt();
            } while (energyReserve > 100 || energyReserve < 1);
        }
        this.energyReserve = energyReserve;
        return this;
    }

    public AbstractRobot setWeapon(RobotTools weapon) {
        this.weapon = weapon;
        System.out.println("SYSTEM:  You ordered to install a new weapon");
        System.out.println("ROBOT: Now I have " + weapon + " weapon");
        return this;
    }

    public void setWorkCounter(int workCounter) {
        this.workCounter = workCounter;
    }

    protected void setRobotType(RobotType robotType) {
        this.robotType = robotType;
    }

}
