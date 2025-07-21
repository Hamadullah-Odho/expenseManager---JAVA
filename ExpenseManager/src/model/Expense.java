package model;
public class Expense {
    private int id;
    private double amount;
    private String category;
    private String description;
    private String date;
    private String time;

    public void setId(int id){this.id = id;}
    public void setAmount(double amount){

        if(amount > 10){this.amount = amount;}}
    public void setCategory(String category){this.category = category;}
    public void setDescription(String description){this.description = description;}
    public void setDate(String date){this.date = date;}
    public void setTime(String time){this.time = time;}


    public int getId(){ return id;}
    public String getCategory(){return category;}
    public double getAmount(){ return amount;}
    public String getDescription(){ return description;}
    public String getDate(){ return date;}
    public String getTime(){ return time;}
}
