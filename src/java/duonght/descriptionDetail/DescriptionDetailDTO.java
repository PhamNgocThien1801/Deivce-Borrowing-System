package duonght.descriptionDetail;

public class DescriptionDetailDTO {

    private int detailID;
    private int descriptionID;
    private String detailName;
    private boolean status;

    public DescriptionDetailDTO() {
        this.detailID = 0;
        this.descriptionID = 0;
        this.detailName = "";
        this.status = true;
    }

    public DescriptionDetailDTO(int detailID, int descriptionID, String detailName, boolean status) {
        this.detailID = detailID;
        this.descriptionID = descriptionID;
        this.detailName = detailName;
        this.status = status;
    }

    public int getDetailID() {
        return detailID;
    }

    public void setDetailID(int detailID) {
        this.detailID = detailID;
    }

    public int getDescriptionID() {
        return descriptionID;
    }

    public void setDescriptionID(int descriptionID) {
        this.descriptionID = descriptionID;
    }

    public String getDetailName() {
        return detailName;
    }

    public void setDetailName(String detailName) {
        this.detailName = detailName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DescriptionDetailDTO{" + "detailID=" + detailID + ", detailName=" + detailName + '}';
    }
    
    

}
