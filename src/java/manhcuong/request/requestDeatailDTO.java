/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manhcuong.request;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class requestDeatailDTO {
    private int detailID;
    private int requestID;
    private int deviceID;
    private int quantity;
    private Date borrowDate;
    private Date expiredDate;
    private String detailStatus;
    private boolean status;
    public requestDeatailDTO(){
        
    }

    public requestDeatailDTO(int detailID, int requestID, int deviceID, int quantity, Date borrowDate, Date expiredDate, String detailStatus, boolean status) {
        this.detailID = detailID;
        this.requestID = requestID;
        this.deviceID = deviceID;
        this.quantity = quantity;
        this.borrowDate = borrowDate;
        this.expiredDate = expiredDate;
        this.detailStatus = detailStatus;
        this.status = status;
    }

    public int getDetailID() {
        return detailID;
    }

    public void setDetailID(int detailID) {
        this.detailID = detailID;
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public int getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(int deviceID) {
        this.deviceID = deviceID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getDetailStatus() {
        return detailStatus;
    }

    public void setDetailStatus(String detailStatus) {
        this.detailStatus = detailStatus;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
}
