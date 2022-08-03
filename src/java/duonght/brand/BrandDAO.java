/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duonght.brand;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import quanghung.utils.DBUtils;

/**
 *
 * @author Trung Duong
 */
public class BrandDAO {

    private static final String RENEW_BRAND = "UPDATE Brand SET status = ? WHERE cateID = ? and brandName = ?";

    public static boolean renewDescription(String brandName, String cateID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(RENEW_BRAND);
                ptm.setBoolean(1, true);
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
}
