package quanghung.warehouse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import quanghung.utils.DBUtils;

public class WarehouseDAO {

    private static final String SEARCH_WAREHOUSE = "SELECT warehouseName, location, limitAmount, status FROM warehouse WHERE warehouseName like ?";
    private static final String GET_LIST_WAREHOUSE = "SELECT warehouseID, warehouseName, location, limitAmount, status FROM warehouse WHERE status=1";
    private static final String DELETE_WAREHOUSE = "UPDATE warehouse SET status=? WHERE warehouseID=?";
    private static final String UPDATE_WAREHOUSE = "UPDATE warehouse SET warehouseName=?, location=?, limitAmount=? WHERE warehouseID=?";
    private static final String CREATE_WAREHOUSE = "INSERT INTO warehouse(warehouseName,location,limitAmount,status) VALUES (?,?,?,?)";
    private static final String GET_WAREHOUSE_ID = "SELECT warehouseID FROM warehouse WHERE warehouseName=?";
    private static final String GET_WAREHOUSE_NAME = "SELECT warehouseName FROM warehouse WHERE warehouseID=?";
    private static final String GET_LIMIT_AMOUNT = "SELECT limitAmount FROM warehouse WHERE warehouseID=?";
    private static final String SUBTRACTION_LIMIT_AMOUNT = "UPDATE warehouse SET limitAmount=limitAmount-? WHERE warehouseID=?";
    private static final String ADDITION_LIMIT_AMOUNT = "UPDATE warehouse SET limitAmount=limitAmount+? WHERE warehouseID=?";

    public String getWarehouseName(int warehouseID) throws SQLException {
        String warehouseName = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_WAREHOUSE_NAME);
                ptm.setInt(1, warehouseID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    warehouseName = rs.getString("warehouseName");
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
        return warehouseName;
    }

    public boolean subtractionLimitAmount(int quantity, int warehouseID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SUBTRACTION_LIMIT_AMOUNT);
                ptm.setInt(1, quantity);
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

    public boolean addtionLimitAmount(int quantity, int warehouseID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ADDITION_LIMIT_AMOUNT);
                ptm.setInt(1, quantity);
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

    public int getLimitAmount(int warehouseID) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        int limitAmount = 0;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_LIMIT_AMOUNT);
                ptm.setInt(1, warehouseID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    limitAmount = rs.getInt("limitAmount");
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
        return limitAmount;
    }

    public int getWarehouseID(String warehouseName) throws SQLException {
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

    public boolean createWarehouse(WarehouseDTO warehouse) throws SQLException, ClassNotFoundException, NamingException {
        boolean check = false;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE_WAREHOUSE);
                ptm.setString(1, warehouse.getWarehouseName());
                ptm.setString(2, warehouse.getLocation());
                ptm.setInt(3, warehouse.getLimitAmount());
                ptm.setBoolean(4, true);
                check = ptm.executeUpdate() > 0 ? true : false;
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

    public boolean updateWarehouse(WarehouseDTO warehouse) throws SQLException {
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

    public boolean deleteWarehouse(String warehouseID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_WAREHOUSE);
                ptm.setBoolean(1, false);
                ptm.setString(2, warehouseID);
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

    public Map<Integer, String> getWarehouse() throws SQLException {
        Map<Integer, String> warehouse = new HashMap<>();
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
                    warehouse.put(warehouseID, warehouseName);
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
        return warehouse;
    }

    public List<WarehouseDTO> searchWarehouse(String search) throws SQLException {
        List<WarehouseDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                if (search != "") {
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
                } else {
                    ptm = conn.prepareStatement(GET_LIST_WAREHOUSE);
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
            }
        } catch (Exception e) {
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
}