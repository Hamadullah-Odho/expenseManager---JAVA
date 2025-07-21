package model;

final public class ExpenseCategories {

    private String[] expenseTypes = {"Rent/Mortgage","Utilities","Groceries",
            "Internet / Phone bills","Home maintenance"};

    public String[] getCategories(){
        return expenseTypes;
    }

    public String getCategory(int index){
        for(int i = 0; i < expenseTypes.length ; i++){
            if(i == index){
                return expenseTypes[i];
            }
        }
        return "Invalid Index";
    }
}
