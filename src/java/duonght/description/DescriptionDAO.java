package duonght.description;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import quanghung.utils.DBUtils;

public class DescriptionDAO {

    private static final String GET_LIST_DESCRIPTION = "SELECT descriptionID, descriptionName, cateID, status FROM description WHERE cateID=?";
    private static final String DELETE_DESCRIPTION = "UPDATE description SET status=? WHERE descriptionID=?";
    private static final String RENEW_DESCRIPTION = "UPDATE description SET status=? WHERE descriptionName=? and cateID=?";
    private static final String UPDATE_DESCRIPTION = "UPDATE description SET descriptionName=? WHERE descriptionID=?";
    private static final String CREATE_DESCRIPTION = "INSERT INTO description(descriptionName,cateID,status) VALUES (?,?,?)";
    private static final String CHECK_DUPLICATE = "SELECT * FROM description WHERE descriptionName=? and cateID=?";
    private static final String CHECK_STATUS = "SELECT * FROM description WHERE descriptionName=? and cateID=? and status = 1";
    private static final String CHECK_QUANTITY = "SELECT COUNT(descriptionID) as 'Quantity' FROM description WHERE cateID=? and status=1";

    public static int checkQuantity(String cateID) throws SQLException {
        int quantity = 0;
        Connection cn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                ptm = cn.prepareStatement(CHECK_QUANTITY);
                ptm.setString(1, cateID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    quantity = rs.getInt("Quantity");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return quantity;
    }
    
    public static boolean createDescription(String cateID, String descriptionName) throws SQLException, NamingException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE_DESCRIPTION);
                ptm.setString(1, descriptionName);
                ptm.setString(2, cateID);
                ptm.setBoolean(3, true);
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

    public static boolean deleteDescription(int descriptionID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_DESCRIPTION);
                ptm.setBoolean(1, false);
                ptm.setInt(2, descriptionID);
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

    public static boolean renewDescription(String descriptionName, String cateID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(RENEW_DESCRIPTION);
                ptm.setBoolean(1, true);
                ptm.setString(2, descriptionName);
                ptm.setString(3, cateID);
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
    
    public static boolean checkDuplicate(String descriptionName, String cateID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DUPLICATE);
                ptm.setString(1, descriptionName);
                ptm.setString(2, cateID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
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
    
    public static boolean checkStatus(String descriptionName, String CateID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_STATUS);
                ptm.setString(1, descriptionName);
                ptm.setString(2, CateID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
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

    public boolean updateCategory(DescriptionDTO description) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_DESCRIPTION);
                ptm.setString(1, description.getDescriptionName());
                ptm.setInt(2, description.getDescriptionID());
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

    public static ArrayList<DescriptionDTO> getListDescription(String cateID) throws SQLException {
        ArrayList<DescriptionDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_LIST_DESCRIPTION);
                ptm.setString(1, cateID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    boolean status = rs.getBoolean("status");
                    if (status) {
                        int descriptionID = rs.getInt("descriptionID");
                        String descriptionName = rs.getString("descriptionName");
                        list.add(new DescriptionDTO(descriptionID, descriptionName, cateID, status));
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
}
