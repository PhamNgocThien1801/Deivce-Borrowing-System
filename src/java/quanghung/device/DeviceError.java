package quanghung.device;

public class DeviceError {

    private String deviceIDError;
    private String deviceNameError;
    private String warehouseIDError;
    private String brandNameError;
    private String quantityError;
    private String depositError;
    private String cateIDError;

    public DeviceError() {
        this.deviceIDError = null;
        this.deviceNameError = null;
        this.warehouseIDError = null;
        this.brandNameError = null;
        this.quantityError = null;
        this.depositError = null;
        this.cateIDError = null;
    }

    public DeviceError(String deviceIDError, String deviceNameError, String warehouseIDError, String brandNameError, String quantityError, String depositError, String cateIDError) {
        this.deviceIDError = deviceIDError;
        this.deviceNameError = deviceNameError;
        this.warehouseIDError = warehouseIDError;
        this.brandNameError = brandNameError;
        this.quantityError = quantityError;
        this.depositError = depositError;
        this.cateIDError = cateIDError;
    }

    public String getDeviceIDError() {
        return deviceIDError;
    }

    public void setDeviceIDError(String deviceIDError) {
        this.deviceIDError = deviceIDError;
    }

    public String getDeviceNameError() {
        return deviceNameError;
    }

    public void setDeviceNameError(String deviceNameError) {
        this.deviceNameError = deviceNameError;
    }

    public String getWarehouseIDError() {
        return warehouseIDError;
    }

    public void setWarehouseIDError(String warehouseIDError) {
        this.warehouseIDError = warehouseIDError;
    }

    public String getBrandNameError() {
        return brandNameError;
    }

    public void setBrandNameError(String brandNameError) {
        this.brandNameError = brandNameError;
    }

    public String getQuantityError() {
        return quantityError;
    }

    public void setQuantityError(String quantityError) {
        this.quantityError = quantityError;
    }

    public String getDepositError() {
        return depositError;
    }

    public void setDepositError(String depositError) {
        this.depositError = depositError;
    }

    public String getCateIDError() {
        return cateIDError;
    }

    public void setCateIDError(String cateIDError) {
        this.cateIDError = cateIDError;
    }

}