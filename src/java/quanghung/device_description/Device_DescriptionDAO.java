package quanghung.device_description;

import quanghung.description.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import quanghung.utils.DBUtils;

public class Device_DescriptionDAO {

    private static final String SEARCH_DESCRIPTION = "SELECT descriptionID, descriptionName, cateID, status FROM description WHERE descriptionName like ?";
    private static final String GET_LIST_DESCRIPTION = "SELECT descriptionID, descriptionName, cateID, status FROM description";
    private static final String DELETE_DESCRIPTION = "UPDATE description SET status=? WHERE descriptionID=?";
    private static final String UPDATE_DEVICE_DESCRIPTION = "UPDATE device_description SET detailID=? FROM device_description d1, descriptionDetail d2 WHERE d1.detailID = d2.detailID AND d1.detailID=? AND d1.deviceID=?";
    private static final String CREATE_DEVICE_DESCRIPTION = "INSERT INTO device_description(deviceID,detailID) VALUES (?,?)";
    private static final String DELETE_ALL_DEVICE_DESCRIPTION = "DELETE FROM device_description WHERE deviceID = ?";

    public boolean createDevice_Description(int deviceID, int detailID) throws SQLException, NamingException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE_DEVICE_DESCRIPTION);
                ptm.setInt(1, deviceID);
                ptm.setInt(2, detailID);
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

    public boolean updateDevice_Description(int currentDetailID, int deviceID, int detailID) throws SQLException, NamingException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_DEVICE_DESCRIPTION);
                ptm.setInt(1, detailID);
                ptm.setInt(2, currentDetailID);
                ptm.setInt(3, deviceID);
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

    public boolean deleteAllDevice_Description(int deviceID) throws SQLException, NamingException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_ALL_DEVICE_DESCRIPTION);
                ptm.setInt(1, deviceID);
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
//
////    public boolean updateCategory(DescriptionDTO description) throws SQLException {
//        boolean check = false;
//        Connection conn = null;
//        PreparedStatement ptm = null;
//        try {
//            conn = DBUtils.getConnection();
//            if (conn != null) {
//                ptm = conn.prepareStatement(UPDATE_DESCRIPTION);
//                ptm.setString(1, description.getDescriptionName());
//                ptm.setInt(2, description.getDescriptionID());
//                check = ptm.executeUpdate() > 0 ? true : false;
//            }
//        } catch (Exception e) {
//            e.toString();
//        } finally {
//            if (ptm != null) {
//                ptm.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        }
//        return check;
//    }

    public List<DescriptionDTO> getListDescription(String search) throws SQLException {
        List<DescriptionDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                if (search != "") {
                    ptm = conn.prepareStatement(SEARCH_DESCRIPTION);
                    ptm.setString(1, "%" + search + "%");
                    rs = ptm.executeQuery();
                    while (rs.next()) {
                        boolean status = rs.getBoolean("status");
                        if (status) {
                            int descriptionID = rs.getInt("descriptionID");
                            String descriptionName = rs.getString("descriptionName");
                            String cateID = rs.getString("cateID");
                            list.add(new DescriptionDTO(descriptionID, descriptionName, cateID, status));
                        }
                    }
                } else {
                    ptm = conn.prepareStatement(GET_LIST_DESCRIPTION);
                    rs = ptm.executeQuery();
                    while (rs.next()) {
                        boolean status = rs.getBoolean("status");
                        if (status) {
                            int descriptionID = rs.getInt("descriptionID");
                            String descriptionName = rs.getString("descriptionName");
                            String cateID = rs.getString("cateID");
                            list.add(new DescriptionDTO(descriptionID, descriptionName, cateID, status));
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