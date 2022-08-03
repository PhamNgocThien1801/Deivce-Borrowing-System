package duonght.descriptionDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import quanghung.utils.DBUtils;

public class DescriptionDetailDAO {

    private static final String CHECK_DUPLICATE = "SELECT * FROM descriptionDetail WHERE detailName=? and descriptionID=?";
    private static final String CHECK_STATUS = "SELECT * FROM descriptionDetail WHERE detailName=? and descriptionID=? and status = 1";
    private static final String GET_LIST_DESCRIPTION_DETAIL = "SELECT detailID, descriptionID, detailName, status FROM descriptionDetail WHERE descriptionID=? and status = 1";
    private static final String GET_DESCRIPTION_DETAIL_ID = "SELECT detailID FROM descriptionDetail WHERE detailName=? and descriptionID=?";
    private static final String DELETE_DESCRIPTION_DETAIL = "UPDATE descriptionDetail SET status=? WHERE detailID=?";
    private static final String RENEW_DESCRIPTION_DETAIL = "UPDATE descriptionDetail SET status=? WHERE detailID=?";
    private static final String UPDATE_DESCRIPTION_DETAIL = "UPDATE descriptionDetail SET detailName=? WHERE detailID=?";
    private static final String CREATE_DESCRIPTION_DETAIL = "INSERT INTO descriptionDetail(descriptionID,detailName,status) VALUES (?,?,?)";
    private static final String GET_DESCRIPTION_NAME = "SELECT d.descriptionName FROM descriptionDetail dd, description d WHERE dd.detailID = ? AND d.descriptionID = dd.descriptionID";

    public String getDescriptionName(int detailID) throws SQLException {
        String descriptionName = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_DESCRIPTION_NAME);
                ptm.setInt(1, detailID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    descriptionName = rs.getString("descriptionName");
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
        return descriptionName;
    }

    public static int getDetailID(String detailName, int descriptionID) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_DESCRIPTION_DETAIL_ID);
                ptm.setString(1, detailName);
                ptm.setInt(2, descriptionID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    return rs.getInt("detailID");
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
        return 0;
    }

    public static boolean createDescriptionDetail(int descriptionID, String detailName) throws SQLException, NamingException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE_DESCRIPTION_DETAIL);
                ptm.setInt(1, descriptionID);
                ptm.setString(2, detailName);
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

    public static boolean deleteDescriptionDetail(int detailID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_DESCRIPTION_DETAIL);
                ptm.setBoolean(1, false);
                ptm.setInt(2, detailID);
                check = ptm.executeUpdate() > 0;
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
        return check;
    }
    
    public static boolean renewDescriptionDetail(int detailID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(RENEW_DESCRIPTION_DETAIL);
                ptm.setBoolean(1, true);
                ptm.setInt(2, detailID);
                check = ptm.executeUpdate() > 0;
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
        return check;
    }

    public static boolean updateDescriptionDetail(String detailName, int detailID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_DESCRIPTION_DETAIL);
                ptm.setString(1, detailName);
                ptm.setInt(2, detailID);
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

    public static ArrayList<DescriptionDetailDTO> getListDescriptionDetail(int descriptionID) throws SQLException {
        ArrayList<DescriptionDetailDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_LIST_DESCRIPTION_DETAIL);
                ptm.setInt(1, descriptionID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    boolean status = rs.getBoolean("status");
                    if (status) {
                        int detailID = rs.getInt("detailID");
                        String detailName = rs.getString("detailName");
                        list.add(new DescriptionDetailDTO(detailID, descriptionID, detailName, status));
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

    public static boolean checkDuplicate(String detailName, int descriptionID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DUPLICATE);
                ptm.setString(1, detailName);
                ptm.setInt(2, descriptionID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = true;
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
        return check;
    }

    public static boolean checkStatus(String detailName, int descriptionID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_STATUS);
                ptm.setString(1, detailName);
                ptm.setInt(2, descriptionID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = true;
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
        return check;
    }

}
