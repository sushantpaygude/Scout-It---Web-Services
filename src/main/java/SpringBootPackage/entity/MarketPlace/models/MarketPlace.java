package SpringBootPackage.entity.MarketPlace.models;

public class MarketPlace {
    private int productid;
    private int usercourseid;
    private long postdate;
    private int marketplaceId;
    private boolean productpurchased;

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public int getUsercourseid() {
        return usercourseid;
    }

    public void setUsercourseid(int usercourseid) {
        this.usercourseid = usercourseid;
    }

    public long getPostdate() {
        return postdate;
    }

    public void setPostdate(long postdate) {
        this.postdate = postdate;
    }

    public int getMarketplaceId() {
        return marketplaceId;
    }

    public void setMarketplaceId(int marketplaceId) {
        this.marketplaceId = marketplaceId;
    }

    public boolean isProductpurchased() {
        return productpurchased;
    }

    public void setProductpurchased(boolean productpurchased) {
        this.productpurchased = productpurchased;
    }
}

