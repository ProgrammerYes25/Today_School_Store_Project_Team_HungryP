package today_school_store_project.team_hungryp;

public class Product {
    public String pr_no;
    public String pr_name;
    public int pr_price;
    public String pr_category;

    //set
    public void setNo(String pr_no){this.pr_no = pr_no;}
    public void setName(String pr_name){this.pr_name = pr_name;}
    public void setPrice(int pr_price){this.pr_price = pr_price;}
    public void setCategory (String pr_category){this.pr_category = pr_category;}

    //get
    public String getNo(){return this.pr_no;}
    public String getName(){return this.pr_name;}
    public int  getPrice(){return this.pr_price;}


}
