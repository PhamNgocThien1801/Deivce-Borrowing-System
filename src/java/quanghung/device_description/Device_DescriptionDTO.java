package quanghung.device_description;

public class Device_DescriptionDTO {

    private int device_descriptionID;
    private int deviceID;
    private int detailID;

    public Device_DescriptionDTO() {
        this.device_descriptionID = 0;
        this.deviceID = 0;
        this.detailID = 0;
    }

    public Device_DescriptionDTO(int device_descriptionID, int deviceID, int detailID) {
        this.device_descriptionID = device_descriptionID;
        this.deviceID = deviceID;
        this.detailID = detailID;
    }

    public int getDevice_descriptionID() {
        return device_descriptionID;
    }

    public void setDevice_descriptionID(int device_descriptionID) {
        this.device_descriptionID = device_descriptionID;
    }

    public int getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(int deviceID) {
        this.deviceID = deviceID;
    }

    public int getDetailID() {
        return detailID;
    }

    public void setDetailID(int detailID) {
        this.detailID = detailID;
    }

}
