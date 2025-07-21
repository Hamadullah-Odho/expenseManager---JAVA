package util;
import model.Expense;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
final public class FileHandling {

    private FileHandling(){}
    public static void setData(List<Expense> expenses , int expenseId){

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("src/resources/data.txt"))){
            for(Expense e : expenses){
                writer.write("Id : " + e.getId());
                writer.newLine();
                writer.write("Amount : " + e.getAmount());
                writer.newLine();
                writer.write("Category : " + e.getCategory());
                writer.newLine();
                writer.write("Description : " + e.getDescription());
                writer.newLine();
                writer.write("Date : " + e.getDate());
                writer.newLine();
                writer.write("Time : " + e.getTime());
                writer.newLine();
                writer.newLine();
            }
        }
        catch (IOException e){
            System.out.println("File Writing Failed : " + e.getMessage());
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/resources/expenseId.txt"))){
            writer.write(String.valueOf(expenseId));
        }
        catch (IOException e){
            System.out.println("File Writing Failed : " + e.getMessage());
        }
    }

    public static List<Expense> loadData(){
        List<Expense> expenses = new ArrayList<>();
        Expense currentexpense = null;
        try {
            File file = new File("src/resources/data.txt");

            if (!file.exists()) {
                System.out.println("File Does Not Exist");
                return expenses;
            }

            BufferedReader reader = new BufferedReader(new FileReader("src/resources/data.txt"));
            String line;
            while((line = reader.readLine()) != null){
                if(line.trim().isEmpty()) {
                    if (currentexpense != null) {
                        expenses.add(currentexpense);
                        currentexpense = null;
                    }
                }

                if(line.startsWith("Id :")){
                    currentexpense = new Expense();
                    currentexpense.setId(Integer.parseInt(line.substring(5).trim()));
                }
                else if (line.startsWith("Amount :")) {
                    currentexpense.setAmount(Double.parseDouble(line.substring(9).trim()));
                }
                else if (line.startsWith("Description :")) {
                    currentexpense.setDescription(line.substring(14).trim());
                }
                else if (line.startsWith("Date :")) {
                    currentexpense.setDate(line.substring(6).trim());
                }
                else if (line.startsWith("Time :")) {
                    currentexpense.setTime(line.substring(6).trim());
                }
                else if (line.startsWith("Category :")) {
                    currentexpense.setCategory(line.substring(11).trim());
                }


            }
            if(currentexpense != null){
                expenses.add(currentexpense);
            }
            reader.close();
            return expenses;
        }
        catch (IOException e){
            System.out.println("File Reading Failed : " + e.getMessage());
            return expenses;
        }
        catch (NullPointerException e){
            System.out.println("error : " + e.getMessage());
        }
        return expenses;
    }

    public static int loadExpenseId(){
        int expenseId = 0;
        try{
            BufferedReader reader = new BufferedReader(new FileReader("src/resources/expenseId.txt"));
            String line;
            while((line = reader.readLine()) != null) expenseId = Integer.parseInt(line);
            reader.close();
        }
        catch (IOException e){
            System.out.printf("File Reading failed : " + e.getMessage());
            return expenseId;
        }
        return expenseId;
    }
}
