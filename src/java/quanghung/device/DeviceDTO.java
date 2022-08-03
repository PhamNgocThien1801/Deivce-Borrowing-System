package quanghung.device;

public class DeviceDTO {

    private int deviceID;
    private String deviceName;
    private String url;
    private int warehouseID;
    private String warehouseName;
    private int brandID;
    private String brandName;
    private int quantity;
    private String cateID;
    private String cateName;
    private int deposit;
    private boolean status;

    public DeviceDTO() {
        this.deviceID = 0;
        this.deviceName = "";
        this.url = "";
        this.warehouseID = 0;
        this.warehouseName = "";
        this.brandID = 0;
        this.brandName = "";
        this.quantity = 0;
        this.cateID = "";
        this.cateName = "";
        this.deposit = 0;
        this.status = true;
    }

    public DeviceDTO(int deviceID, String deviceName, String url, int warehouseID, String warehouseName, int brandID, String brandName, int quantity, String cateID, String cateName, int deposit, boolean status) {
        this.deviceID = deviceID;
        this.deviceName = deviceName;
        this.url = url;
        this.warehouseID = warehouseID;
        this.warehouseName = warehouseName;
        this.brandID = brandID;
        this.brandName = brandName;
        this.quantity = quantity;
        this.cateID = cateID;
        this.cateName = cateName;
        this.deposit = deposit;
        this.status = status;
    }

    public int getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(int deviceID) {
        this.deviceID = deviceID;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWarehouseID() {
        return warehouseID;
    }

    public void setWarehouseID(int warehouseID) {
        this.warehouseID = warehouseID;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}