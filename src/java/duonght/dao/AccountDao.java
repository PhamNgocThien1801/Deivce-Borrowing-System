/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duonght.dao;

import duonght.dto.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import quanghung.utils.DBUtils;

/**
 *
 * @author Trung Duong
 */
public class AccountDao {

    public static int updateDeposit(String userID, int deposit) {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        PreparedStatement pst2 = null;
        int rs2 = 0;
        boolean check = false;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String url = "SELECT deposit FROM Accounts\n"
                        + "WHERE userID = ?";
                pst = cn.prepareStatement(url);
                pst.setString(1, userID);
                rs = pst.executeQuery();
                if (rs.next()) {
                    deposit += rs.getInt("deposit");
                    String url2 = "UPDATE Accounts SET deposit = ? \n"
                            + "WHERE userID = ?";
                    pst2 = cn.prepareStatement(url2);
                    pst2.setInt(1, deposit);
                    pst2.setString(2, userID);
                    rs2 = pst2.executeUpdate();
                    if (rs2 > 0) {
                        check = true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deposit;
    }

    public static ArrayList<Account> getAllUser() {
        ArrayList<Account> users = new ArrayList<>();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String url = "SELECT userID, userName, mail, phone, position, deposit, roleID, status FROM Accounts";
                st = cn.createStatement();
                rs = st.executeQuery(url);
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String userName = rs.getString("userName");
                    String mail = rs.getString("mail");
                    String phone = rs.getString("phone");
                    String position = rs.getString("position");
                    String roleID = rs.getString("roleID");
                    int deposit = rs.getInt("deposit");
                    int status = rs.getInt("status");
                    Account user = new Account(userID, userName, mail, phone, position, deposit, roleID, status);
                    users.add(user);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public static Account checkLogin(String email) {
        Account acc = null;
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String url = "SELECT userID, userName, mail, phone, position, deposit, roleID, status "
                        + "FROM Accounts "
                        + "WHERE mail = ? and status = 1";
                pst = cn.prepareStatement(url);
                pst.setString(1, email);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String userID = rs.getString("userID");
                    String userName = rs.getString("userName");
                    String mail = rs.getString("mail");
                    String phone = rs.getString("phone");
                    String position = rs.getString("position");
                    String roleID = rs.getString("roleID");
                    int deposit = rs.getInt("deposit");
                    int status = rs.getInt("status");
                    acc = new Account(userID, userName, mail, phone, position, deposit, roleID, status);
                }
                System.out.println("Come here!");
                System.out.println(acc.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return acc;
    }

    public static ArrayList<Account> searchAccountByID(String ID) {
        ArrayList<Account> accounts = new ArrayList<>();
        //buoc 1: mo ket noi
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String url = "SELECT userID, userName, mail, phone, position, deposit, roleID, status FROM Accounts \n"
                        + "WHERE userID like ?";
                pst = cn.prepareStatement(url);
                pst.setString(1, "%" + ID + "%");
                rs = pst.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String userName = rs.getString("userName");
                    String mail = rs.getString("mail");
                    String phone = rs.getString("phone");
                    String position = rs.getString("position");
                    String roleID = rs.getString("roleID");
                    int deposit = rs.getInt("deposit");
                    int status = rs.getInt("status");
                    accounts.add(new Account(userID, userName, mail, phone, position, deposit, roleID, status));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return accounts;
    }

    public static Account searchAccountUpdate(String userID) {
        Account acc = null;
        //buoc 1: mo ket noi
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String url = "Select userID, userName, mail, phone, position, deposit, roleID, status "
                        + "from Accounts "
                        + "where userID = ?";
                pst = cn.prepareStatement(url);
                pst.setString(1, userID);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String userName = rs.getString("userName");
                    String mail = rs.getString("mail");
                    String phone = rs.getString("phone");
                    String position = rs.getString("position");
                    String roleID = rs.getString("roleID");
                    int deposit = rs.getInt("deposit");
                    int status = rs.getInt("status");
                    acc = new Account(userID, userName, mail, phone, position, deposit, roleID, status);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return acc;
    }

    public static boolean UpdateAccountStatus(String userID, int status) {
        Connection cn = null;
        PreparedStatement pst = null;
        int rs = 0;
        try {
            cn = DBUtils.getConnection();
            if ((cn != null)) {
                String url = "UPDATE Accounts SET status = ?\n"
                        + "WHERE userID = ?";
                pst = cn.prepareStatement(url);
                pst.setString(2, userID);
                if (status == 1) {
                    pst.setInt(1, 0);
                } else {
                    pst.setInt(1, 1);
                }
                rs = pst.executeUpdate();
                if (rs > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean UpdateProfile(String userID, String userName, String userPhone) {
        Connection cn = null;
        PreparedStatement pst = null;
        int rs = 0;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String url = "UPDATE Accounts "
                        + "SET userName = ?, phone = ?\n"
                        + "WHERE userID = ?";
                pst = cn.prepareStatement(url);
                pst.setString(1, userName);
                pst.setString(2, userPhone);
                pst.setString(3, userID);
                rs = pst.executeUpdate();
                if (rs > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
