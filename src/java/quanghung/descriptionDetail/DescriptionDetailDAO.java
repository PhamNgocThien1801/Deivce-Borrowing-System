package quanghung.descriptionDetail;

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

public class DescriptionDetailDAO {

    private static final String SEARCH_DESCRIPTION_DETAIL = "SELECT detailID, descriptionID, detailName, status FROM descriptionDetail WHERE detailName like ?";
    private static final String GET_LIST_DESCRIPTION_DETAIL_BY_ID = "SELECT detailID, descriptionID, detailName, status FROM descriptionDetail WHERE descriptionID=?";
    private static final String GET_LIST_DESCRIPTION_DETAIL_BY_NAME = "SELECT distinct detail.detailName \n"
            + "FROM descriptionDetail detail, description descript \n"
            + "WHERE descript.descriptionName = ? AND descript.descriptionID = detail.descriptionID";
    private static final String DELETE_DESCRIPTION_DETAIL = "UPDATE descriptionDetail SET status=? WHERE detailID=?";
    private static final String UPDATE_DESCRIPTION_DETAIL = "UPDATE descriptionDetail SET detailName=? WHERE detailID=?";
    private static final String CREATE_DESCRIPTION_DETAIL = "INSERT INTO descriptionDetail(descriptionID,detailName,status) VALUES (?,?,?)";
    private static final String GET_DESCRIPTION_NAME = "SELECT d.descriptionName FROM descriptionDetail dd, description d WHERE dd.detailID = ? AND d.descriptionID = dd.descriptionID";
    private static final String GET_LIST_DESCRIPTION_DETAIL_BASED_ON_DEVICE = "SELECT dc.descriptionID, dt.detailName\n"
            + "FROM device dv, description dc, descriptionDetail dt, device_description dd\n"
            + "WHERE dv.deviceID = dd.deviceID AND dd.detailID = dt.detailID AND dt.descriptionID = dc.descriptionID AND dv.deviceID=?";

    public Map<Integer, String> getListDescriptionDetailBasedOnDevice(int deviceID) throws SQLException {
        Map<Integer, String> list = new HashMap<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_LIST_DESCRIPTION_DETAIL_BASED_ON_DEVICE);
                ptm.setInt(1, deviceID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int descriptionID = rs.getInt("descriptionID");
                    String detailName = rs.getString("detailName");
                    list.put(descriptionID, detailName);
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

    public boolean createDescriptionDetail(DescriptionDetailDTO detail) throws SQLException, NamingException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE_DESCRIPTION_DETAIL);
                ptm.setInt(1, detail.getDescriptionID());
                ptm.setString(2, detail.getDetailName());
                ptm.setBoolean(3, true);
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

    public boolean deleteDescriptionDetail(String detailID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_DESCRIPTION_DETAIL);
                ptm.setBoolean(1, false);
                ptm.setString(2, detailID);
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

    public boolean updateDescriptionDetail(DescriptionDetailDTO detail) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_DESCRIPTION_DETAIL);
                ptm.setString(1, detail.getDetailName());
                ptm.setInt(2, detail.getDetailID());
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

    public List<DescriptionDetailDTO> getListDescriptionDetail(int descriptionID) throws SQLException {
        List<DescriptionDetailDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_LIST_DESCRIPTION_DETAIL_BY_ID);
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

    public List<String> getListDescriptionDetail(String descriptionName) throws SQLException {
        List<String> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_LIST_DESCRIPTION_DETAIL_BY_NAME);
                ptm.setString(1, descriptionName);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String detailName = rs.getString("detailName");
                    list.add(detailName);
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