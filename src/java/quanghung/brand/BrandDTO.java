package quanghung.brand;

public class BrandDTO {

    private int brandID;
    private String cateID;
    private String brandName;
    private boolean status;

    public BrandDTO() {
        this.brandID = 0;
        this.cateID = "";
        this.brandName = "";
        this.status = true;
    }

    public BrandDTO(int brandID, String cateID, String brandName, boolean status) {
        this.brandID = brandID;
        this.cateID = cateID;
        this.brandName = brandName;
        this.status = status;
    }

    public int getBrandID() {
        return brandID;
    }

    public void setBrandID(int brandID) {
        this.brandID = brandID;
    }

    public String getCateID() {
        return cateID;
    }

    public void setCateID(String cateID) {
        this.cateID = cateID;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
