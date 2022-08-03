package quanghung.category;

public class CategoryError {

    private String cateIDError;
    private String cateNameError;

    public CategoryError() {
        this.cateIDError = "";
        this.cateNameError = "";
    }

    public CategoryError(String cateIDError, String cateNameError) {
        this.cateIDError = cateIDError;
        this.cateNameError = cateNameError;
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

}
