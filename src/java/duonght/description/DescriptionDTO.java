package duonght.description;

public class DescriptionDTO {

    private int descriptionID;
    private String descriptionName;
    private String cateID;
    private boolean status;

    public DescriptionDTO() {
        this.descriptionID = 0;
        this.descriptionName = "";
        this.cateID = "";
        this.status = true;
    }

    public DescriptionDTO(int descriptionID, String descriptionName, String cateID, boolean status) {
        this.descriptionID = descriptionID;
        this.descriptionName = descriptionName;
        this.cateID = cateID;
        this.status = status;
    }

    public int getDescriptionID() {
        return descriptionID;
    }

    public void setDescriptionID(int descriptionID) {
        this.descriptionID = descriptionID;
    }

    public String getDescriptionName() {
        return descriptionName;
    }

    public void setDescriptionName(String descriptionName) {
        this.descriptionName = descriptionName;
    }

    public String getCateID() {
        return cateID;
    }

    public void setCateID(String cateID) {
        this.cateID = cateID;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
