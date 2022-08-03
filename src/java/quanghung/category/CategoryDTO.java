package quanghung.category;

public class CategoryDTO {

    private String cateID;
    private String cateName;
    private boolean status;

    public CategoryDTO() {
        this.cateID = "";
        this.cateName = "";
        this.status = true;
    }

    public CategoryDTO(String cateID, String cateName, boolean status) {
        this.cateID = cateID;
        this.cateName = cateName;
        this.status = status;
    }

    public String getCateID() {
        return cateID;
    }

    public void setCateID(String cateID) {
        this.cateID = cateID;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
