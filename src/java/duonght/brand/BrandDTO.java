/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duonght.brand;

/**
 *
 * @author Trung Duong
 */
public class BrandDTO {
    private int brandID;
    private String brandName;
    private String cateID;
    private int status;

    public BrandDTO() {
    }

    public BrandDTO(int brandID, String brandName, String cateID, int status) {
        this.brandID = brandID;
        this.brandName = brandName;
        this.cateID = cateID;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getBrandID() {
        return brandID;
    }

    public void setBrandID(int brandID) {
        this.brandID = brandID;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCateID() {
        return cateID;
    }

    public void setCateID(String cateID) {
        this.cateID = cateID;
    }
    
}
