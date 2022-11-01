package today_school_store_project.team_hungryp;

public class Product {
    public String pr_no;
    public String pr_name;
    public int pr_price;
    public String pr_category;
    public int popular_price;
    public int new_price;
    public int pr_dcprice;

    //set
    public void setNo(String pr_no){this.pr_no = pr_no;}
    public void setName(String pr_name){this.pr_name = pr_name;}
    public void setPrice(int pr_price){this.pr_price = pr_price;}
    public void setCategory (String pr_category){this.pr_category = pr_category;}
    public void setPopularPrice(int popular_price) {this.popular_price = popular_price;}
    public void setNewPrice(int new_price){this.new_price = new_price;}
    public void setDcprice(int pr_dcprice){this.pr_dcprice = pr_dcprice;}


    //get
    public String getNo(){return this.pr_no;}
    public String getName(){return this.pr_name;}
    public int  getPrice(){return this.pr_price;}
    public String setCategory (){return this.pr_category;}
    public int setPopularPrice() {return this.popular_price;}
    public int setNewPrice(){return this.new_price;}
    public int setDcprice(){return this.pr_dcprice;}


}
