package duonght.warehouse;

public class WarehouseDTO {

    private int warehouseID;
    private String warehouseName;
    private String location;
    private int limitAmount;
    private boolean status;

    public WarehouseDTO() {
        this.warehouseID = 0;
        this.warehouseName = "";
        this.location = "";
        this.limitAmount = 0;
        this.status = true;
    }

    public WarehouseDTO(int warehouseID, String warehouseName, String location, int limitAmount, boolean status) {
        this.warehouseID = warehouseID;
        this.warehouseName = warehouseName;
        this.location = location;
        this.limitAmount = limitAmount;
        this.status = status;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getLimitAmount() {
        return limitAmount;
    }

    public void setLimitAmount(int limitAmount) {
        this.limitAmount = limitAmount;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
