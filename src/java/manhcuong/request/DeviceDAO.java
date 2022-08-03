/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manhcuong.request;

import static java.rmi.server.LogStream.log;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import quanghung.device.DeviceDTO;
import quanghung.utils.DBUtils;

/**
 *
 * @author Admin
 */
public class DeviceDAO {

    private static final String SEARCH_DEVICE = "SELECT deviceID, deviceName,url, deposit,d.warehouseID, d.brandID, quantity, d.cateID, w.warehouseName, c.cateName, b.brandName, d.status FROM device d, warehouse w, category c, Brand b  WHERE d.brandID = b.brandID AND d.warehouseID = w.warehouseID AND d.cateID = c.cateID AND deviceID =?";
    private static final String UPDATE_QUANTITY ="UPDATE device SET quantity=? WHERE deviceID=?";
    public DeviceDTO getDeviceByID(int deviceID) throws SQLException {
        DeviceDTO device = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_DEVICE);
                ptm.setInt(1, deviceID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    boolean status = rs.getBoolean("status");
                    String deviceName = rs.getString("deviceName");
                    String url = rs.getString("url");
                    int warehouseID = rs.getInt("warehouseID");
                    String warehouseName = rs.getString("warehouseName");
                    int brandID = rs.getInt("brandID");
                    String brandName = rs.getString("brandName");
                    int quantity = rs.getInt("quantity");
                    String cateID = rs.getString("cateID");
                    int deposit = rs.getInt("deposit");
                    String cateName = rs.getString("cateName");
                    device = new DeviceDTO(deviceID, deviceName, url, warehouseID, warehouseName, brandID, brandName, quantity, cateID, cateName, deposit, status);
                }
            }
        } catch (Exception e) {
            log("Error at getDeviceByID in DeviceDAO" + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, ptm, rs);
        }
        return device;
    }
    public boolean updateDevice(int quantity, int deviceID){
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if(conn != null){
                stm = conn.prepareStatement(UPDATE_QUANTITY);
                stm.setInt(1, quantity);
                stm.setInt(2, deviceID);
                check = stm.executeUpdate() > 0 ? true : false;
                
                
            }
        } catch (Exception e) {
            log("Error at getDeviceByID in DeviceDAO" + e.toString());
        }finally{
            DBUtils.closeQueryConnection(conn, stm, null);
        }
        return check;
    }
}