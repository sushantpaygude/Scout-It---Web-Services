package SpringBootPackage.entity.Products.models;

public class Product {
    private int productid;
    private int userid;
    private String productName;
    private String productDescription;
    private int productCost;
    private Long productPostDate;
    private String productStatus;
    private String category;
    private int marketplaceproductsid;
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public int getMarketplaceproductsid() {
        return marketplaceproductsid;
    }

    public void setMarketplaceproductsid(int marketplaceproductsid) {
        this.marketplaceproductsid = marketplaceproductsid;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getProductCost() {
        return productCost;
    }

    public void setProductCost(int productCost) {
        this.productCost = productCost;
    }

    public Long getProductPostDate() {
        return productPostDate;
    }

    public void setProductPostDate(Long productPostDate) {
        this.productPostDate = productPostDate;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public enum productStatusEnum{
        SALE("SALE"),
        SOLD("SOLD");
        private String statusCode;
        productStatusEnum(String s){
            this.statusCode = s;
        }

        @Override
        public String toString() {
            return statusCode;
        }
    }


}
