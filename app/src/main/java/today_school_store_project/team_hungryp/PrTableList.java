package today_school_store_project.team_hungryp;

public class PrTableList {
    private int prNo;
    private String prName;
    private int prPrice;
    private String prCategory;
    private int prPopular;
    private int prNew;
    private int prDcprice;

    public PrTableList(){
    }

    public PrTableList(int prNo, String prName, int prPrice,
                       String prCategory, int prPopular, int prNew,
                       int prDcprice){
        this.prNo = prNo;
        this.prName = prName;
        this.prPrice = prPrice;
        this.prCategory = prCategory;
        this.prPopular = prPopular;
        this.prNew = prNew;
        this.prDcprice = prDcprice;

    }
}
