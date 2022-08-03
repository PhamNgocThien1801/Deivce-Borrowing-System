/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manhcuong.request;

import duonght.dto.Account;
import java.sql.Date;

/**
 *
 * @author Admin
 */
public class requestDTO implements Comparable<requestDTO>{
    private int id;
    private Account user;
    private Date requestDate;
    private String requestStatus;
    private String requestSubstance;
    private requestDetailDTO requestDetail;
    private ExtendDTO extend;
    private boolean status;
    public requestDTO(){
        
    }

    public requestDetailDTO getRequestDetail() {
        return requestDetail;
    }

    public void setRequestDetail(requestDetailDTO requestDetail) {
        this.requestDetail = requestDetail;
    }

    public requestDTO(int id, Account user, Date requestDate, String requestStatus, String requestSubstance, requestDetailDTO requestDetail, boolean status) {
        this.id = id;
        this.user = user;
        this.requestDate = requestDate;
        this.requestStatus = requestStatus;
        this.requestSubstance = requestSubstance;
        this.requestDetail = requestDetail;
        this.status = status;
        this.extend = null;
    }

    public requestDTO(int id, Account user, Date requestDate, String requestStatus, String requestSubstance, requestDetailDTO requestDetail, ExtendDTO extend, boolean status) {
        this.id = id;
        this.user = user;
        this.requestDate = requestDate;
        this.requestStatus = requestStatus;
        this.requestSubstance = requestSubstance;
        this.requestDetail = requestDetail;
        this.extend = extend;
        this.status = status;
    }

    public ExtendDTO getExtend() {
        return extend;
    }

    public void setExtend(ExtendDTO extend) {
        this.extend = extend;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getUser() {
        return user;
    }

    public void setUser(Account user) {
        this.user = user;
    }

    

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getRequestSubstance() {
        return requestSubstance;
    }

    @Override
    public String toString() {
        return "requestDTO{" + "requestID=" + id + ", user=" + user + ", requestDate=" + requestDate + ", requestStatus=" + requestStatus + ", requestSubstance=" + requestSubstance + ", status=" + status + '}';
    }
    
    public void setRequestSubstance(String requestSubstance) {
        this.requestSubstance = requestSubstance;
    }
    public int compareTo(requestDTO o) {
        if(this.id > o.id)
            return 1;
        else if(this.id < o.id)
            return -1;
        else return 0;
    }
    
}