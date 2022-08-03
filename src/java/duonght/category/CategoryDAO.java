package duonght.category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import quanghung.utils.DBUtils;

public class CategoryDAO {

    private static final String SEARCH_CATEGORY = "SELECT cateID, cateName, status FROM category WHERE cateName like ?";
    private static final String GET_LIST_CATEGORY = "SELECT cateID, cateName, status FROM category WHERE status=1";
    private static final String GET_CATE_NAME = "SELECT cateName FROM category WHERE cateID=?";
    private static final String GET_CATE_ID = "SELECT cateID FROM category WHERE cateName=?";
    private static final String DELETE_CATEGORY = "UPDATE category SET status=? WHERE cateID=?";
    private static final String RENEW_CATEGORY = "UPDATE category SET status=? WHERE cateID=?";
    private static final String UPDATE_CATEGORY = "UPDATE category SET cateName=? WHERE cateID=?";
    private static final String CHECK_DUPLICATE = "SELECT cateName FROM category WHERE cateID=?";
    private static final String CHECK_EXIST_BRAND = "SELECT * FROM Brand WHERE cateID=? and status = 1";
    private static final String CHECK_EXIST_DEVICE = "SELECT * FROM device WHERE cateID=? and status = 1";
    private static final String CHECK_EXIST_DESCRIPTION = "SELECT * FROM description WHERE cateID=? and status = 1";
    private static final String CHECK_STATUS = "SELECT * FROM category WHERE cateID=? and status = 1";
    private static final String CREATE_CATEGORY = "INSERT INTO category(cateID,cateName,status) VALUES (?,?,?)";
    private static final String CREATE_DESCRIPTION = "INSERT INTO description(descriptionName,cateID,status) VALUES (?,?,?)";
    private static final String CREATE_BRAND = "INSERT INTO brand(brandName,cateID,status) VALUES (?,?,?)";

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

    public static ArrayList<CategoryDTO> getCategory() throws SQLException {
        ArrayList<CategoryDTO> category = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_LIST_CATEGORY);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String cateID = rs.getString("cateID");
                    String cateName = rs.getString("cateName");
                    category.add(new CategoryDTO(cateID, cateName, true));
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

    public static boolean createCategory(CategoryDTO category, String descriptionName, String brandName) throws SQLException, ClassNotFoundException, NamingException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        PreparedStatement ptm2 = null;
        PreparedStatement ptm3 = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE_CATEGORY);
                ptm.setString(1, category.getCateID());
                ptm.setString(2, category.getCateName());
                ptm.setBoolean(3, true);
                if (ptm.executeUpdate() > 0) {
                    ptm2 = conn.prepareStatement(CREATE_DESCRIPTION);
                    ptm2.setString(1, descriptionName);
                    ptm2.setString(2, category.getCateID());
                    ptm2.setBoolean(3, true);
                    if (ptm2.executeUpdate() > 0) {
                        ptm3 = conn.prepareStatement(CREATE_BRAND);
                        ptm3.setString(1, brandName);
                        ptm3.setString(2, category.getCateID());
                        ptm3.setBoolean(3, true);
                        check = ptm3.executeUpdate() > 0;
                    }
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

    public static boolean checkDuplicate(String cateID) throws SQLException {
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
        return check;
    }

    public static boolean checkExistBrand(String cateID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_EXIST_BRAND);
                ptm.setString(1, cateID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = true;
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
        return check;
    }

    public static boolean checkExistDevice(String cateID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_EXIST_DEVICE);
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

    public static boolean checkExistDescription(String cateID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_EXIST_DESCRIPTION);
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

    public static boolean checkStatus(String cateID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_STATUS);
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

    public static boolean deleteCategory(String cateID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_CATEGORY);
                ptm.setBoolean(1, false);
                ptm.setString(2, cateID);
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

    public static boolean renewCategory(String cateID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(RENEW_CATEGORY);
                ptm.setBoolean(1, true);
                ptm.setString(2, cateID);
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

    public static boolean updateCategory(String cateName, String cateID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_CATEGORY);
                ptm.setString(1, cateName);
                ptm.setString(2, cateID);
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

    public static ArrayList<CategoryDTO> searchCategory(String search) throws SQLException {
        ArrayList<CategoryDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
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
