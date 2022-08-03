/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duonght.warehouse;

/**
 *
 * @author Trung Duong
 */
public class WarehoueError {
    private String warehouseNameError;
    private String locationError;
    private String limitAmountError;

    public WarehoueError() {
    }

    public WarehoueError(String warehouseNameError, String locationError, String limitAmountError) {
        this.warehouseNameError = warehouseNameError;
        this.locationError = locationError;
        this.limitAmountError = limitAmountError;
    }

    public String getWarehouseNameError() {
        return warehouseNameError;
    }

    public void setWarehouseNameError(String warehouseNameError) {
        this.warehouseNameError = warehouseNameError;
    }

    public String getLocationError() {
        return locationError;
    }

    public void setLocationError(String locationError) {
        this.locationError = locationError;
    }

    public String getLimitAmountError() {
        return limitAmountError;
    }

    public void setLimitAmountError(String limitAmountError) {
        this.limitAmountError = limitAmountError;
    }

    
    
}
