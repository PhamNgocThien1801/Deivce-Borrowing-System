package quanghung.brand;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.naming.NamingException;
import quanghung.utils.DBUtils;

public class BrandDAO {

    private static final String SEARCH_BRAND = "SELECT brandName, cateID, status FROM brand WHERE brandName like ?";
    private static final String GET_LIST_BRAND_BASED_ON_CATEGORY_ID = "SELECT brandID, brandName, cateID, status FROM brand WHERE cateID=?";
    private static final String GET_LIST_BRAND_BASED_ON_BRAND_ID = "SELECT brandID, brandName, cateID, status FROM brand WHERE brandID=?";
    private static final String GET_LIST_BRAND = "SELECT brandID, brandName, cateID, status FROM brand WHERE status=1";
    private static final String DELETE_BRAND = "UPDATE Brand SET status=? WHERE brandName=? and cateID=?";
    private static final String UPDATE_BRAND = "UPDATE Brand SET brandName=? WHERE brandName=? and cateID=?";
    private static final String CREATE_BRAND = "INSERT INTO Brand(brandName,cateID,status) VALUES (?,?,?)";
    private static final String GET_BRAND_ID = "SELECT brandID from Brand WHERE brandName=?";
    private static final String RENEW_BRAND = "UPDATE Brand SET status = ? WHERE cateID = ? and brandName = ?";
    private static final String CHECK_DUPLICATE = "SELECT * FROM Brand WHERE brandName=? and cateID=?";
    private static final String CHECK_STATUS = "SELECT * FROM Brand WHERE brandName=? and cateID=? and status = 1";
    private static final String CHECK_QUANTITY = "SELECT COUNT(brandID) as 'Quantity' FROM Brand WHERE cateID=? and status=1";

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

    public static boolean checkStatus(String brandName, String CateID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_STATUS);
                ptm.setString(1, brandName);
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

    public static boolean checkDuplicate(String brandName, String cateID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DUPLICATE);
                ptm.setString(1, brandName);
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

    public static ArrayList<BrandDTO> getListBrand(String cateID) throws SQLException {
        ArrayList<BrandDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_LIST_BRAND_BASED_ON_CATEGORY_ID);
                ptm.setString(1, cateID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    boolean status = rs.getBoolean("status");
                    if (status) {
                        int brandID = rs.getInt("brandID");
                        String brandName = rs.getString("brandName");
                        list.add(new BrandDTO(brandID, cateID, brandName, status));
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

    public static boolean renewBrand(String brandName, String cateID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(RENEW_BRAND);
                ptm.setBoolean(1, true);
                ptm.setString(2, cateID);
                ptm.setString(3, brandName);
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

    public static int getBrandID(String brandName) throws SQLException {
        int brandID = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_BRAND_ID);
                ptm.setString(1, brandName);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    brandID = rs.getInt("brandID");
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
        return brandID;
    }

    public static boolean createBrand(String brandName, String cateID) throws SQLException, NamingException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE_BRAND);
                ptm.setString(1, brandName);
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

    public static boolean deleteBrand(String brandName, String cateID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_BRAND);
                ptm.setBoolean(1, false);
                ptm.setString(2, brandName);
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

    public static boolean updateBrand(String brandName, String textBrand, String cateID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_BRAND);
                ptm.setString(1, textBrand);
                ptm.setString(2, brandName);
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

    public Map<Integer, String> getListBrandBasedOnCateID(String cateID) throws SQLException {
        Map<Integer, String> listBrand = new HashMap<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_LIST_BRAND_BASED_ON_CATEGORY_ID);
                ptm.setString(1, cateID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int brandID = rs.getInt("brandID");
                    String brandName = rs.getString("brandName");
                    listBrand.put(brandID, brandName);
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
        return listBrand;
    }

    public Map<Integer, String> getListBrand() throws SQLException {
        Map<Integer, String> listBrand = new HashMap<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_LIST_BRAND);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int brandID = rs.getInt("brandID");
                    String brandName = rs.getString("brandName");
                    listBrand.put(brandID, brandName);
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
        return listBrand;
    }

    public Map<Integer, String> getListBrandBasedOnBrandID(int brandID) throws SQLException {
        Map<Integer, String> listBrand = new HashMap<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_LIST_BRAND_BASED_ON_CATEGORY_ID);
                ptm.setInt(1, brandID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String brandName = rs.getString("brandName");
                    listBrand.put(brandID, brandName);
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
        return listBrand;
    }
}
