package quanghung.description;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import quanghung.utils.DBUtils;

public class DescriptionDAO {

//    private static final String GET_LIST_DESCRIPTION = "SELECT descriptionID, descriptionName FROM description";
    private static final String GET_LIST_DESCRIPTION_DISTINCT = "SELECT distinct descriptionName FROM description";
    private static final String GET_LIST_DESCRIPTION = "SELECT distinct descriptionID, descriptionName, cateID, status FROM description";
    private static final String GET_LIST_DESCRIPTION_BASED_ON_CATEID = "SELECT descriptionID, descriptionName, cateID, status FROM description WHERE cateID=?";
    private static final String DELETE_DESCRIPTION = "UPDATE description SET status=? WHERE descriptionID=?";
    private static final String UPDATE_DESCRIPTION = "UPDATE description SET descriptionName=? WHERE descriptionID=?";
    private static final String CREATE_DESCRIPTION = "INSERT INTO description(descriptionName,cateID,status) VALUES (?,?,?)";
    private static final String GET_DESCRIPTION_NAME = "SELECT descriptionName FROM description WHERE descriptionID=?";
    private static final String GET_LIST_DESCRIPTION_DETAIL_BY_ID = "SELECT detailID, descriptionID, detailName, status FROM descriptionDetail WHERE descriptionID=?";

    public String getDescriptionName(int descriptionID) throws SQLException {
        String descriptionName = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_DESCRIPTION_NAME);
                ptm.setInt(1, descriptionID);
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

    public boolean createDescription(DescriptionDTO description) throws SQLException, NamingException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE_DESCRIPTION);
                ptm.setString(1, description.getDescriptionName());
                ptm.setString(2, description.getCateID());
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

    public boolean deleteDescription(String descriptionID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_DESCRIPTION);
                ptm.setBoolean(1, false);
                ptm.setString(2, descriptionID);
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

    public List<DescriptionDTO> getListDescription(String cateID) throws SQLException {
        List<DescriptionDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        PreparedStatement ptm2 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_LIST_DESCRIPTION_BASED_ON_CATEID);
                ptm.setString(1, cateID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    boolean status = rs.getBoolean("status");
                    if (status) {
                        int descriptionID = rs.getInt("descriptionID");
                        String descriptionName = rs.getString("descriptionName");
                        ptm2 = conn.prepareStatement(GET_LIST_DESCRIPTION_DETAIL_BY_ID);
                        ptm2.setInt(1, descriptionID);
                        rs2 = ptm2.executeQuery();
                        while (rs2.next()) {
                            list.add(new DescriptionDTO(descriptionID, descriptionName, cateID, status));
                            break;
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

    public List<String> getListDescription() throws SQLException {
        List<String> list = new ArrayList<String>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_LIST_DESCRIPTION_DISTINCT);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String descriptionName = rs.getString("descriptionName");
                    list.add(descriptionName);
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

//    public Map<Integer, String> getListDescription() throws SQLException {
//        Map<Integer, String> description = new HashMap<>();
//        Connection conn = null;
//        PreparedStatement ptm = null;
//        ResultSet rs = null;
//        try {
//            conn = DBUtils.getConnection();
//            if (conn != null) {
//                ptm = conn.prepareStatement(GET_LIST_DESCRIPTION);
//                rs = ptm.executeQuery();
//                while (rs.next()) {
//                    int descriptionID = rs.getInt("descriptionID");
//                    String descriptionName = rs.getString("descriptionName");
//                    description.put(descriptionID, descriptionName);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (ptm != null) {
//                ptm.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        }
//        return description;
//    }
}