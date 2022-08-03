package duonght.warehouse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import quanghung.utils.DBUtils;

public class WarehouseDAO {

    private static final String SEARCH_WAREHOUSE = "SELECT warehouseID, warehouseName, location, limitAmount, status FROM warehouse WHERE warehouseName like ?";
    private static final String CHECK_DUPLICATE = "SELECT * FROM warehouse WHERE warehouseName = ?";
    private static final String CHECK_STATUS = "SELECT * FROM category WHERE warehouseID=? and status = 1";
    private static final String RENEW_SEARCH_WAREHOUSE = "UPDATE warehouse SET status=? WHERE warehouseID=?";
    private static final String GET_LIST_WAREHOUSE = "SELECT warehouseID, warehouseName, location, limitAmount, status FROM warehouse WHERE status=1";
    private static final String DELETE_WAREHOUSE = "UPDATE warehouse SET status=? WHERE warehouseID=?";
    private static final String UPDATE_WAREHOUSE = "UPDATE warehouse SET warehouseName=?, location=?, limitAmount=? WHERE warehouseID=?";
    private static final String CREATE_WAREHOUSE = "INSERT INTO warehouse(warehouseName,location,limitAmount,status) VALUES (?,?,?,?)";
    private static final String GET_WAREHOUSE_ID = "SELECT warehouseID FROM warehouse WHERE warehouseName=?";
    private static final String CHECK_EXIST_DEVICES = "SELECT * FROM warehouse w JOIN device de ON w.warehouseID = de.warehouseID WHERE w.warehouseID = ?";

    public static int getWarehouseID(String warehouseName) throws SQLException {
        int warehouseID = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_WAREHOUSE_ID);
                ptm.setString(1, warehouseName);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    warehouseID = rs.getInt("warehouseID");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return warehouseID;
    }

    public static boolean checkExistDevices(int warehouseID) throws SQLException {
        int count = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_EXIST_DEVICES);
                ptm.setInt(1, warehouseID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    count++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return count > 0;
    }

    public static boolean checkDuplicate(String warehouseName) throws SQLException {
        int count = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DUPLICATE);
                ptm.setString(1, warehouseName);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    count++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return count > 0;
    }

    public static boolean createWarehouse(String warehouseName, String location, int limitAmount) throws SQLException, ClassNotFoundException, NamingException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE_WAREHOUSE);
                ptm.setString(1, warehouseName);
                ptm.setString(2, location);
                ptm.setInt(3, limitAmount);
                ptm.setBoolean(4, true);
                check = ptm.executeUpdate() > 0;
            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public static boolean updateWarehouse(WarehouseDTO warehouse) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_WAREHOUSE);
                ptm.setString(1, warehouse.getWarehouseName());
                ptm.setString(2, warehouse.getLocation());
                ptm.setInt(3, warehouse.getLimitAmount());
                ptm.setInt(4, warehouse.getWarehouseID());
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.toString();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public static boolean deleteWarehouse(int warehouseID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_WAREHOUSE);
                ptm.setBoolean(1, false);
                ptm.setInt(2, warehouseID);
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.toString();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public static ArrayList<WarehouseDTO> getWarehouse() throws SQLException {
        ArrayList<WarehouseDTO> warehouses = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                pstm = conn.prepareStatement(GET_LIST_WAREHOUSE);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    int warehouseID = rs.getInt("warehouseID");
                    String warehouseName = rs.getString("warehouseName");
                    String location = rs.getString("location");
                    int limitAmount = rs.getInt("limitAmount");
                    boolean status = rs.getBoolean("status");
                    WarehouseDTO warehouse = new WarehouseDTO(warehouseID, warehouseName, location, limitAmount, status);
                    warehouses.add(warehouse);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return warehouses;
    }

    public static List<WarehouseDTO> searchWarehouse(String search) throws SQLException {
        List<WarehouseDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_WAREHOUSE);
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    boolean status = rs.getBoolean("status");
                    if (status) {
                        int warehouseID = rs.getInt("warehouseID");
                        String warehouseName = rs.getString("warehouseName");
                        String location = rs.getString("location");
                        int limitAmount = rs.getInt("limitAmount");
                        list.add(new WarehouseDTO(warehouseID, warehouseName, location, limitAmount, status));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public static boolean checkStatus(int warehouseID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_STATUS);
                ptm.setInt(1, warehouseID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } catch (Exception e) {
            e.toString();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public static boolean renewWarehouse(int warehouseID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(RENEW_SEARCH_WAREHOUSE);
                ptm.setBoolean(1, true);
                ptm.setInt(2, warehouseID);
                check = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.toString();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
}
