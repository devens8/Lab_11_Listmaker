import java.util.ArrayList;
import java.util.Scanner;

public class Lab_11_Listmaker {
    //declare class variables
    private final static String MENU = "A-Add, D-Delete, P-Print, Q-Quit"; //menu for commands
    private static ArrayList<String> list = new ArrayList<>();
    private static Scanner input = new Scanner(System.in);//set up scanner
    private static String plist = "";
    private static boolean shouldPrintList = true;

    public static void main(String[] args) {
        System.out.println("\nWelcome to the List app. Using the commands you can create a list for tasks, shopping list, etc!\n"); //welcome message
        boolean loopVar = false;
        do {
            if(shouldPrintList) { //only prints list if p has not been called
                plist = "List is Empty"; //shows list is empty
                for (int i = 0; i < list.size(); i++) {
                    if (i == 0) { //if only one item, doesn't show comma
                        plist = ((i + 1) + ":" + list.get(i));
                    } else {
                        plist += (", " + (i + 1) + ":" + list.get(i));
                    }
                }
                SafeInput.prettyHeader(plist); //using pretty header to display list
            }
            shouldPrintList = true;
            String command = SafeInput.getRegExString(input, "Please enter a command " + MENU, "[AaDdPpQq]"); //asks user to enter a command
            //calls the method depending on the command
            if (command.equalsIgnoreCase("A")) {
                add(list);
            } else if (command.equalsIgnoreCase("D")) {
                delete(list);
            } else if (command.equalsIgnoreCase("P")) {
                print(list);
            } else {
                loopVar = quit(list);
            }
        } while (!loopVar); //makes sure that quit has been called and checked twice

    }

    //methods adds new item to the list
    private static void add(ArrayList list){
        String add = SafeInput.getNonZeroLenString(input, "Please enter what you would like to add to the list");
        list.add(add);
    }
    //method deletes item from current list
    private static void delete(ArrayList list){
        if(list.size() == 0) {
            System.out.println("The list is empty. You cannot delete items\n"); //if no items, then throws error message
        } else{
            int delete = SafeInput.getRangedInt(input, "Please enter the item number of the item you want to delete", 1, list.size());
            list.remove(delete-1);
        }
    }
    //method prints the list
    private static void print(ArrayList list){
        plist = "List is Empty";
        for(int i = 0; i < list.size(); i++){
            if (i == 0) {
                plist = ((i + 1) + ":" + list.get(i));
            } else{
                plist += (", " + (i + 1) + ":" + list.get(i));

            }
        }
            SafeInput.prettyHeader(plist);
            shouldPrintList = false;
    }

    //method gets confirmation that user would like to quit
    private static boolean quit(ArrayList list){
        return SafeInput.getYNConfirm(input, "Are you sure you would like to quit? Enter Y or N");
    }
}