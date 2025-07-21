import java.util.*;
import manager.ExpenseManager;
public class App {
    public static void main(String[] args){

        Scanner scan = new Scanner(System.in);
        ExpenseManager manager = new ExpenseManager();
        boolean runApp = true;
        while(runApp){
            System.out.println("--- Expense Manager ---\n");
            System.out.println("a) Add expense");
            System.out.println("v) View All expense");
            System.out.println("s) Search expenses by category and expenseID");
            System.out.println("d) Delete expense by ID");
            System.out.println("w) Save expenses");
            System.out.println("c) Close application");
            System.out.println("\n-----------------------------------------");
            try {
                System.out.print("Enter Choice : ");
                String userInput = scan.nextLine();

                switch (userInput){
                    case "a":
                        manager.AddExpense();
                        break;
                    case "v":
                        manager.viewExpenses();
                        break;
                    case "s":
                        manager.searchExpense();
                        break;
                    case "d":
                        manager.deleteExpense();
                        break;
                    case "w":
                        manager.saveData(true);
                        break;
                    case "c":
                        runApp = false;
                        break;
                    default:
                        System.out.println("Enter Valid Choice");
                }
                System.out.println();
            }
            catch (NoSuchElementException e){
                System.out.println("No input Available :" + e.getMessage());
            }
        }
        scan.close();
    }
}
