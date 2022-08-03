package duonght.category;

public class CategoryError {

    private String cateIDError;
    private String cateNameError;
    private String descriptionNameError;
    private String brandNameError;

    public CategoryError() {
    }

    public CategoryError(String cateIDError, String cateNameError, String descriptionNameError, String brandNameError) {
        this.cateIDError = cateIDError;
        this.cateNameError = cateNameError;
        this.descriptionNameError = descriptionNameError;
        this.brandNameError = brandNameError;
    }

    public String getCateIDError() {
        return cateIDError;
    }

    public void setCateIDError(String cateIDError) {
        this.cateIDError = cateIDError;
    }

    public String getCateNameError() {
        return cateNameError;
    }

    public void setCateNameError(String cateNameError) {
        this.cateNameError = cateNameError;
    }

    public void getCateIDError(String duplicate_Cate_ID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getDescriptionName() {
        return descriptionNameError;
    }

    public void setDescriptionName(String descriptionName) {
        this.descriptionNameError = descriptionName;
    }

    public String getBrandName() {
        return brandNameError;
    }

    public void setBrandName(String brandName) {
        this.brandNameError = brandName;
    }

}
