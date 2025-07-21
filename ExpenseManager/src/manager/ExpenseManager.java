package manager;
import model.Expense;
import java.util.*;

import model.ExpenseCategories;
import util.DateAndTime;
import util.FileHandling;
public class ExpenseManager {
    Scanner scan;
    int expenseID;
    List<Expense> expenses;
    ExpenseCategories categories;
    DateAndTime datetime;
    public ExpenseManager(){
        expenses = FileHandling.loadData();
        scan = new Scanner(System.in);
        datetime = new DateAndTime();
        categories = new ExpenseCategories();
        expenseID = FileHandling.loadExpenseId();
    }
    // Add Expense Method
    public void AddExpense(){
        try{
            System.out.println("--- Add Expense ---\n");
            System.out.println("--- Expense Type ---\n");
            int index = 0;
            for(String c : categories.getCategories()){
                System.out.println(index + " " + c);
                index++;
           }
            System.out.println("--------------------------");
            System.out.print("Enter Type Index : ");
            index = Integer.parseInt(scan.nextLine());
            System.out.print("Enter Amount : ");
            double expenseAmount = Double.parseDouble(scan.nextLine());
            System.out.print("Enter Description : ");
            String expenseDescription = scan.nextLine();
            if(expenseAmount > 10){
                AddExpenseHelper(index,expenseAmount,expenseDescription);
            }
            else {
                System.out.println("failed to add expense > amount < 10");
            }
        }
        catch (NumberFormatException e){
            System.out.println("Non - numeric data error : " + e.getMessage());
        }
        catch (Exception e){
            System.out.println("Error : " + e.getMessage());
        }
    }
    //helper method for add expense
    private void AddExpenseHelper(int index,double amount , String description) throws NumberFormatException{
        String searchCategory = categories.getCategory(index);
        Expense currentexpense = new Expense();
        currentexpense.setId(expenseID);
        currentexpense.setCategory(searchCategory);
        currentexpense.setAmount(amount);
        currentexpense.setDescription(description);
        currentexpense.setDate(datetime.getCurrentDate());
        currentexpense.setTime(datetime.getCurrentTime());
        expenses.add(currentexpense);
        System.out.println("Expense Id : " + expenseID);
        expenseID++;
        System.out.println("Expense Added Successfully");
    }

    // View All Expenses with details
    public void viewExpenses(){
        Iterator<Expense> iterator = expenses.iterator();

        System.out.println("--- Expense List ---");
        System.out.println("-----------------------------------------------------\n");
        while(iterator.hasNext()){
            Expense currentExpense = iterator.next();
            System.out.println("Expense Category : " + currentExpense.getCategory()
            + " | " + "Id :" + currentExpense.getId() + " | " + "Amount : " + currentExpense.getAmount()
            + " | " + "Description : " + currentExpense.getDescription() +
            " | " + "Date and Time : " + currentExpense.getDate() + " - " + currentExpense.getTime());
        }
        System.out.println("-----------------------------------------------------\n");
    }

    // Method for searching expense by category or expenseId
    public void searchExpense() {
        try {
            System.out.println("--- Search Expense ---\n");
            System.out.println("a > Search by category");
            System.out.println("b > Search by expenseId");
            System.out.print("Enter Choice : ");
            String userInput = scan.nextLine();
            if(userInput.equals("a")){
                searchByCategory();
            }
            else if(userInput.equals("b")) {
                searchByExpenseId();
            }
        }
        catch (NumberFormatException e){
            System.out.println("Error : non-numeric value entered in unsupported input");
        }
        catch (Exception e){
            System.out.println("Error occurred : " + e.getMessage());
        }
    }
    //helper method for searchExpense
    private void searchByCategory() throws  NumberFormatException{
        boolean notFound = true;
        int index = 0;
        for(String c : categories.getCategories()){
            System.out.println(index + " > " + c);
            index++;
        }
        System.out.println("---------------------");
        System.out.print("Enter Index : ");
        index = Integer.parseInt(scan.nextLine());
        System.out.println();
        String currentCategory = categories.getCategory(index);
        for(Expense e : expenses){
            if(e.getCategory().equals(currentCategory)){
                System.out.println("Expense Category : " + e.getCategory() + " | Id : " + e.getId()
                        +" | Amount :" + e.getAmount() + "| Description : " + e.getDescription()
                        + "| Date and Time : " + e.getDate() + "-" + e.getTime());
                notFound = false;
            }
        }
        if(notFound && currentCategory.equalsIgnoreCase("invalid index")){
            System.out.println("Enter Valid Category index");
        }
    }
    // Helper method for search expense
    private void searchByExpenseId() throws NumberFormatException{
        System.out.print("Enter expenseId :");
        int id = Integer.parseInt(scan.nextLine());
        boolean notFound = true;
        System.out.println();
        for(Expense e : expenses){
            if (e.getId() == id) {
                System.out.println("Expense Category : " + e.getCategory() + " | Id : " + e.getId()
                        +" | Amount :" + e.getAmount() + "| Description : " + e.getDescription()
                        + "| Date and Time : " + e.getDate() + "-" + e.getTime());
                notFound = false;
                break;
            }
        }
        if(notFound)System.out.println("Enter Valid expenseId");
    }

    // Delete Expense method

    public void deleteExpense(){
        try {
            System.out.println("--- Delete Expense ---\n");
            viewExpenses();
            System.out.print("Enter expenseId : ");
            int id = Integer.parseInt(scan.nextLine());
            deleteExpenseHelper(id);
        }
        catch (NumberFormatException e){
            System.out.println("Error : " + e.getMessage());
        }
        catch (Exception e){
            System.out.println("expense delete failed : " + e.getMessage());
        }
    }
    private void deleteExpenseHelper(int id) throws Exception{
        Iterator<Expense> iterator = expenses.iterator();
        boolean notFound = true;
        while (iterator.hasNext()){
            Expense currentExpense = iterator.next();

            if(currentExpense.getId() == id){
                expenses.remove(currentExpense);
                notFound = false;
                saveData(false);
                break;
            }
        }
        if(notFound) System.out.println("Enter Valid expenseId");
        else System.out.println("Deleted Successfully");
    }
    // Method for saving data
    public void saveData(boolean condition){
        FileHandling.setData(expenses,expenseID);
        if(condition) System.out.println("Expense Saved Successfully");
    }


}
