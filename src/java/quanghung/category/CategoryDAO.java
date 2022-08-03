package quanghung.category;

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

public class CategoryDAO {

//    private static final String FIND = "SELECT productID, productName, imageUrl, price, quantity, categoryID, importDate,usingDate, status FROM tblProduct WHERE categoryID=?";
    private static final String SEARCH_CATEGORY = "SELECT cateID, cateName, status FROM category WHERE cateName like ?";
    private static final String GET_LIST_CATEGORY = "SELECT cateID, cateName, status FROM category WHERE status=1";
    private static final String GET_CATE_NAME = "SELECT cateName FROM category WHERE cateID=?";
    private static final String GET_CATE_ID = "SELECT cateID FROM category WHERE cateName=?";
    private static final String DELETE_CATEGORY = "UPDATE category SET status=? WHERE cateID=?";
    private static final String UPDATE_CATEGORY = "UPDATE category SET cateName=? WHERE cateID=?";
    private static final String CHECK_DUPLICATE = "SELECT cateName FROM category WHERE cateID=?";
    private static final String CREATE_CATEGORY = "INSERT INTO category(cateID,cateName,status) VALUES (?,?,?)";
    private static final String GET_LIST_DESCRIPTION_BASED_ON_CATEID = "SELECT descriptionID, descriptionName, cateID, status FROM description WHERE cateID=?";
    private static final String GET_LIST_DESCRIPTION_DETAIL_BY_ID = "SELECT detailID, descriptionID, detailName, status FROM descriptionDetail WHERE descriptionID=?";

    public String getCateID(String cateName) throws SQLException {
        String cateID = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_CATE_ID);
                ptm.setString(1, cateName);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    cateID = rs.getString("cateID");
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
        return cateID;
    }

    public String getCateName(String cateID) throws SQLException {
        String cateName = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_CATE_NAME);
                ptm.setString(1, cateID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    cateName = rs.getString("cateName");
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
        return cateName;
    }

    public Map<String, String> getCategory() throws SQLException {
        Map<String, String> category = new HashMap<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        PreparedStatement ptm2 = null;
        PreparedStatement ptm3 = null;

        ResultSet rs = null;
        ResultSet rs2 = null;
        ResultSet rs3 = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_LIST_CATEGORY);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String cateID = rs.getString("cateID");
                    String cateName = rs.getString("cateName");
                    ptm2 = conn.prepareStatement(GET_LIST_DESCRIPTION_BASED_ON_CATEID);
                    ptm2.setString(1, cateID);
                    rs2 = ptm2.executeQuery();
                    while (rs2.next()) {
                        int descriptionID = rs2.getInt("descriptionID");
                        ptm3 = conn.prepareStatement(GET_LIST_DESCRIPTION_DETAIL_BY_ID);
                        ptm3.setInt(1, descriptionID);
                        rs3 = ptm3.executeQuery();
                        while (rs3.next()) {
                            category.put(cateID, cateName);
                            break;
                        }
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
        return category;
    }

    public boolean createCategory(CategoryDTO category) throws SQLException, NamingException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE_CATEGORY);
                ptm.setString(1, category.getCateID());
                ptm.setString(2, category.getCateName());
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

    public boolean checkDuplicate(String cateID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DUPLICATE);
                ptm.setString(1, cateID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = true;
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
        return check;
    }

    public boolean deleteCategory(String cateID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_CATEGORY);
                ptm.setBoolean(1, false);
                ptm.setString(2, cateID);
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

    public boolean updateCategory(CategoryDTO category) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_CATEGORY);
                ptm.setString(1, category.getCateName());
                ptm.setString(2, category.getCateID());
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

    public List<CategoryDTO> getListCategory(String search) throws SQLException {
        List<CategoryDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                if (search != "") {
                    ptm = conn.prepareStatement(SEARCH_CATEGORY);
                    ptm.setString(1, "%" + search + "%");
                    rs = ptm.executeQuery();
                    while (rs.next()) {
                        boolean status = rs.getBoolean("status");
                        if (status) {
                            String cateID = rs.getString("cateID");
                            String cateName = rs.getString("cateName");
                            list.add(new CategoryDTO(cateID, cateName, status));
                        }
                    }
                } else {
                    ptm = conn.prepareStatement(GET_LIST_CATEGORY);
                    rs = ptm.executeQuery();
                    while (rs.next()) {
                        boolean status = rs.getBoolean("status");
                        if (status) {
                            String cateID = rs.getString("cateID");
                            String cateName = rs.getString("cateName");
                            list.add(new CategoryDTO(cateID, cateName, status));
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
