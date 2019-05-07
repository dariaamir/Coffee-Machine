import java.util.*;

public class CoffeeMachine {

    public int water;
    public int milk;
    public int beans;
    public int cups;
    public int money;
    public MachineStates mashineState;

    enum MachineStates{
        WAITING_FOR_ACTION,
        WAITING_FOR_COFFEE_OPTION,
        WATING_FOR_REFIL_WATER,
        WATING_FOR_REFIL_MILK,
        WATING_FOR_REFIL_BEANS,
        WATING_FOR_REFIL_CUPS,
    }


    public CoffeeMachine(int water, int milk, int beans, int cups, int money) {
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.cups = cups;
        this.money = money;
        this.mashineState = MachineStates.WAITING_FOR_ACTION;
    }

    public void printRemaining(){
        System.out.format("The coffee machine has:\n" +
                "%d of water\n" +
                "%d of milk\n" +
                "%d of coffee beans\n" +
                "%d of disposable cups\n" +
                "$%d of money\n", water, milk, beans, cups, money);
    }

    public boolean checkResources(String coffeeType){
        boolean result = true;
        if (((coffeeType.equals("espresso")) && (this.water < 250 ))
                || ((coffeeType.equals("latte")) && (this.water < 350 ))
                || ((coffeeType.equals("cappuccino")) && (this.water < 200 )) ){
            result = false;
            System.out.println("Sorry, not enough water!");
        }

        if (((coffeeType.equals("latte")) && (this.milk < 75 ))
                || ((coffeeType.equals("cappuccino")) && (this.milk < 100 )) ){
            result = false;
            System.out.println("Sorry, not enough milk!");
        }

        if (((coffeeType.equals("espresso")) && (this.beans < 16 ))
                || ((coffeeType.equals("latte")) && (this.beans < 20 ))
                || ((coffeeType.equals("cappuccino")) && (this.beans < 12 )) ){
            result = false;
            System.out.println("Sorry, not enough coffee beans!");
        }
        if (this.cups == 0) {
            result = false;
            System.out.println("Sorry, not enough disposable cups!");
        }
        return result;
    }

    public void buyCoffe(Scanner sc){
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
        String action = sc.nextLine();
        switch (action){
            case "1":
                if (checkResources("espresso")) {
                    System.out.println("I have enough resources, making you a coffee!");
                    this.water -= 250;
                    this.beans -= 16;
                    this.cups -= 1;
                    this.money += 4;
                }
                break;
            case "2":
                if (checkResources("latte")) {
                    System.out.println("I have enough resources, making you a coffee!");
                    this.water -= 350;
                    this.milk -= 75;
                    this.beans -= 20;
                    this.cups -= 1;
                    this.money += 7;
                }
                break;
            case "3":
                if (checkResources("cappuccino")) {
                    System.out.println("I have enough resources, making you a coffee!");
                    this.water -= 200;
                    this.milk -= 100;
                    this.beans -= 12;
                    this.cups -= 1;
                    this.money += 6;
                }
                break;
            case "back":
                break;
        }
    }

    public void fillCoffeeMachine(Scanner sc){
        System.out.print("Write how many ml of water do you want to add: ");
        this.mashineState = MachineStates.WATING_FOR_REFIL_WATER;
        this.water += sc.nextInt();
        System.out.print("Write how many ml of milk do you want to add: ");
        this.mashineState = MachineStates.WATING_FOR_REFIL_MILK;
        this.milk += sc.nextInt();
        System.out.print("Write how many grams of coffee beans do you want to add: ");
        this.mashineState = MachineStates.WATING_FOR_REFIL_BEANS;
        this.beans += sc.nextInt();
        System.out.print("Write how many disposable cups of coffee do you want to add: ");
        this.mashineState = MachineStates.WATING_FOR_REFIL_CUPS;
        this.cups += sc.nextInt();
    }

    public void takeMoney(){
        System.out.format("I gave you $%d\n", money);
        this.money = 0;
    }

    public static void main(String[] args) {
        CoffeeMachine mash = new CoffeeMachine(400, 540, 120, 9, 550);

        String action = "";

        while (! action.equals("exit")){
            System.out.print("Write action (buy, fill, take, remaining, exit): ");

            Scanner sc = new Scanner(System.in);
            action = sc.nextLine();
            switch (action){
                case "remaining":
                    mash.printRemaining();
                    break;
                case "buy":
                    mash.mashineState = MachineStates.WAITING_FOR_COFFEE_OPTION;
                    mash.buyCoffe(sc);
                    break;
                case "fill":
                    mash.fillCoffeeMachine(sc);
                    break;
                case "take":
                    mash.takeMoney();
                    break;
            }
        }

    }
}
