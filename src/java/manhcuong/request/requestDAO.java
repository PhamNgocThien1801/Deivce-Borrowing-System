/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manhcuong.request;

import DLC.Extension;
import com.sun.org.apache.xerces.internal.impl.Constants;
import duonght.dao.AccountDao;
import duonght.dto.Account;
import static java.rmi.server.LogStream.log;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import quanghung.device.DeviceDAO;
import quanghung.device.DeviceDTO;
import quanghung.utils.DBUtils;

/**
 *
 * @author Admin
 */
public class requestDAO {

    private static final String CREATE_ORDER = "INSERT INTO request(userID, requestDate, requestStatus, substance,status) VALUES(?,?,?,?,?)";
    private static final String CREATE_ORDER_DETAIL = "INSERT INTO requestDetail(requestID, deviceID, quantity, borrowDate, expiredDate, detailStatus,status) VALUES(?,?,?,?,?,?,?)";
    private static final String GET_ORDER = "SELECT requestID, requestDate, requestStatus,substance  FROM request WHERE statusID = true AND userID = ? AND status = ? ";
    private static final String GET_PREVIOUS_ORDER = "SELECT requestID FROM request WHERE userID = ? AND status = ?";
    private static final String CONVERT_ORDER = "SELECT o.deviceID, d.deviceName, d.cateID, o.quantity, d.warehouseID, d.brandID, o.status "
            + "FROM requestDetail o,  device d, category c,  WHERE requestID = ? AND o.deviceID = d.deviceID ";
    private static final String GET_LIST_REQUEST = "SELECT requestID, requestDate, requestStatus,substance, userID  FROM request WHERE status = ?";
    private static final String GET_REQUEST_DETAIL = "SELECT d.detailID, d.deviceID, d.quantity, d.borrowDate, d.expiredDate, d.detailStatus, d.status "
            + "FROM requestDetail d, request r WHERE d.requestID = ? AND d.requestID = r.requestID";
    private final String GET_REQUEST_BASE_ON = "SELECT requestID, requestDate,substance, userID  FROM request WHERE status = ? AND requestStatus =?";
    private final String GET_REQUEST_BASE_ON_DETAIL = "SELECT r.requestID, r.requestDate,r.substance, r.userID, r.requestStatus FROM request r, requestDetail d WHERE r.status = ? AND d.detailStatus = ? AND r.requestID = d.requestID";
    private final String UPDATE_REQUEST_STATUS = "UPDATE request SET requestStatus = ? WHERE requestID = ?";
    private final String UPDATE_REQUEST_DETAIL_STATUS = "UPDATE requestDetail SET detailStatus = ? WHERE detailID =?";
    private final String CREATE_RETURN = "INSERT INTO returned (userID, requestID, deviceID, quantity, returnDate, status) VALUES (?,?,?,?,?,?)";
    private final String GET_REQUEST_BASE_ON_USER = "SELECT requestID, requestDate,substance, userID  FROM request WHERE status = ? AND requestStatus =? AND userID = ?";
    private final String GET_REQUEST_BASE_ON_DETAIL_AND_USER = "SELECT r.requestID, r.requestDate,r.substance, r.userID, r.requestStatus FROM request r, requestDetail d WHERE r.status = ? AND d.detailStatus = ? AND r.requestID = d.requestID AND r.userID = ?";
    private static final String GET_REQUEST_BY_ID = "SELECT  requestDate, requestStatus,substance,userID, status  FROM request WHERE  requestID =? ";
    private static final String CREATED_EXTEND = "INSERT INTO message (messageID, requestID, message, extendDate) VALUES (?,?,?,?)";
    private final String UPDATE_REQUEST_EXPIRED_DATE = "UPDATE requestDetail SET expiredDate = ? WHERE detailID = ?";
    private final String UPDATE_REQUEST_BORROW_DATE = "UPDATE requestDetail SET borrowDate = ?, expiredDate = ? WHERE detailID = ?";
    private final String GET_EXTEND = "SELECT messageID, requestID, message, extendDate FROM message WHERE messageID = ?";
    private final String GET_EXTEND_REQUEST = "SELECT requestID, requestDate,substance, userID, requestStatus, status FROM request WHERE substance = ?";

    public int createOrder(DeviceDTO items, Account user, int borrowDate, String substance) throws SQLException {
        int check = -1;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(CREATE_ORDER, Statement.RETURN_GENERATED_KEYS);
                stm.setString(1, user.getUserID());
                stm.setDate(2, new Date(System.currentTimeMillis()));
                stm.setString(3, "Waiting...");
                stm.setString(4, substance);
                stm.setBoolean(5, true);
                if (stm.executeUpdate() > 0) {
                    rs = stm.getGeneratedKeys();
                    if (rs.next()) {
                        int requestID = rs.getInt(1);
                        createOrderDetails(items, requestID, borrowDate);
                        check = requestID;
                    }
                }
            }
        } catch (Exception e) {
            log("Error at checkOutOrder in OrderDAO" + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, rs);

        }
        return check;
    }

    public boolean createOrderDetails(DeviceDTO items, int requestID, int borrowDate) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        Extension dlc = new Extension();
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(CREATE_ORDER_DETAIL);

                stm.setInt(1, requestID);
                stm.setInt(2, items.getDeviceID());
                stm.setInt(3, items.getQuantity());
                stm.setDate(4, null);
                stm.setDate(5, dlc.AddDate(borrowDate));
                stm.setString(6, "Waiting...");
                stm.setBoolean(7, true);
                check = stm.executeUpdate() > 0 ? true : false;

            }
        } catch (Exception e) {
            log("Error at createOrderDetails in OrderDAO" + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, null);
        }
        return check;
    }

    public List<requestDTO> getOrderByUserID(String userID, boolean status) {
        List<requestDTO> request = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_ORDER);
                stm.setString(1, userID);
                stm.setBoolean(2, true);
                rs = stm.executeQuery();
                request = new ArrayList<>();
                while (rs.next()) {
                    int requestID = rs.getInt("requestID");
                    Date requestDate = rs.getDate("requestDate");
                    String requestStatus = rs.getString("requestStatus");
                    String substance = rs.getString("substance");
                    Account user = new AccountDao().searchAccountUpdate(userID);
                    requestDetailDTO requestDetail = getRequestDetailByRequestID(requestID);
                    if (substance.equals("Extend Request")) {
                        ExtendDTO extend = getExtend(requestID);
                        request.add(new requestDTO(requestID, user, requestDate, requestStatus, substance, requestDetail, extend, status));

                    } else {
                        request.add(new requestDTO(requestID, user, requestDate, requestStatus, substance, requestDetail, status));
                    }
                }

            }
        } catch (Exception e) {
            log("Error at getOrder in OrderDAO" + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, rs);
        }
        return request;
    }

    public int getPreviousOrder(Account loginUser) {
        int check = -1;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_PREVIOUS_ORDER);
                stm.setString(1, loginUser.getUserID());
                stm.setBoolean(2, true);
                rs = stm.executeQuery();
                if (rs.next()) {
                    check = rs.getInt("orderID");
                }

            }
        } catch (Exception e) {
            log("Error at getPreviousOrder in OrderDAO" + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, rs);
        }
        return check;
    }

    public List<DeviceDTO> convertOrderToItems(int requestID) {
        List<DeviceDTO> list = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(CONVERT_ORDER);
                stm.setInt(1, requestID);
                rs = stm.executeQuery();
                list = new ArrayList<>();
                while (rs.next()) {
                    boolean status = rs.getBoolean("status");
                    int deviceID = rs.getInt("deviceID");
                    String deviceName = rs.getString("deviceName");
                    String url = rs.getString("url");
                    int warehouseID = rs.getInt("warehouseID");
                    String warehouseName = rs.getString("warehouseName");
                    int brandID = rs.getInt("brandID");
                    String brandName = rs.getString("brandName");
                    int quantity = rs.getInt("quantity");
                    String cateID = rs.getString("cateID");
                    int deposit = rs.getInt("deposit");
                    String cateName = rs.getString("cateName");
                    list.add(new DeviceDTO(deviceID, deviceName, url, warehouseID, warehouseName, brandID, brandName, quantity, cateID, cateName, deposit, status));
                }
            }
        } catch (Exception e) {
            log("Error at getOrder in OrderDAO" + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, rs);
        }
        return list;
    }

    public List<requestDTO> getOrders(boolean status) {
        List<requestDTO> request = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                //SELECT requestID, requestDate, requestStatus,substance, status, userID  FROM request WHERE status = true
                stm = conn.prepareStatement(GET_LIST_REQUEST);
                stm.setBoolean(1, status);
                rs = stm.executeQuery();
                request = new ArrayList<>();
                while (rs.next()) {
                    int requestID = rs.getInt("requestID");
                    Date requestDate = rs.getDate("requestDate");
                    String requestStatus = rs.getString("requestStatus");
                    String substance = rs.getString("substance");
                    String user_id = rs.getString("userID");
                    Account user = new AccountDao().searchAccountUpdate(user_id);
                    requestDetailDTO requestDetail = getRequestDetailByRequestID(requestID);
                    if (substance.equals("Extend Request")) {
                        ExtendDTO extend = getExtend(requestID);
                        request.add(new requestDTO(requestID, user, requestDate, requestStatus, substance, requestDetail, extend, status));

                    } else {
                        request.add(new requestDTO(requestID, user, requestDate, requestStatus, substance, requestDetail, status));
                    }
                }
            }
        } catch (Exception e) {
            log("Error at getOrder in OrderDAO" + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, rs);
        }
        return request;
    }

    public requestDetailDTO getRequestDetailByRequestID(int requestID) {
        requestDetailDTO requestDetail = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_REQUEST_DETAIL);
                //SELECT d.detailID, d.deviceID, d.quantity, d.borrowDate, d.expiredDate, d.detailStatus, d.status "
                //+ "FROM requestDetail d, request r WHERE d.requestID = ? AND d.requestID = r.requestID
                stm.setInt(1, requestID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int detailID = rs.getInt("detailID");
                    int deviceID = rs.getInt("deviceID");
                    int quantity = rs.getInt("quantity");
                    Date borrowDate = rs.getDate("borrowDate");
                    Date expiredDate = rs.getDate("expiredDate");
                    String detailStatus = rs.getString("detailStatus");
                    boolean status = rs.getBoolean("status");

                    DeviceDTO device = new manhcuong.request.DeviceDAO().getDeviceByID(deviceID);
                    requestDetail = new requestDetailDTO(detailID, requestID, device, quantity, borrowDate, expiredDate, detailStatus, status);
                }
            }

        } catch (Exception e) {
            log("Error at getRequestDetail in requestDAO" + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, rs);
        }
        return requestDetail;
    }

    public List<requestDTO> getRequestBaseOnStatusDetail(boolean status, String requestStatus) {
        List<requestDTO> request = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_REQUEST_BASE_ON);
                stm.setBoolean(1, status);
                stm.setString(2, requestStatus);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int requestID = rs.getInt("requestID");
                    Date requestDate = rs.getDate("requestDate");
                    String substance = rs.getString("substance");
                    String user_id = rs.getString("userID");
                    Account user = new AccountDao().searchAccountUpdate(user_id);
                    requestDetailDTO requestDetail = getRequestDetailByRequestID(requestID);
                    if (substance.equals("Extend Request")) {
                        ExtendDTO extend = getExtend(requestID);
                        request.add(new requestDTO(requestID, user, requestDate, requestStatus, substance, requestDetail, extend, status));

                    } else {
                        request.add(new requestDTO(requestID, user, requestDate, requestStatus, substance, requestDetail, status));
                    }

                }

            }
        } catch (Exception e) {
            log("Error at getProcessingRequest in requestDAO" + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, rs);
        }
        return request;
    }

    public List<requestDTO> getRequestBaseOnDetailStatus(boolean status, String detailStatus) {
        List<requestDTO> request = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_REQUEST_BASE_ON_DETAIL);
                stm.setBoolean(1, status);
                stm.setString(2, detailStatus);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int requestID = rs.getInt("requestID");
                    Date requestDate = rs.getDate("requestDate");
                    String substance = rs.getString("substance");
                    String requestStatus = rs.getString("requestStatus");
                    String user_id = rs.getString("userID");
                    Account user = new AccountDao().searchAccountUpdate(user_id);
                    requestDetailDTO requestDetail = getRequestDetailByRequestID(requestID);
                    if (substance.equals("Extend Request")) {
                        ExtendDTO extend = getExtend(requestID);
                        request.add(new requestDTO(requestID, user, requestDate, requestStatus, substance, requestDetail, extend, status));

                    } else {
                        request.add(new requestDTO(requestID, user, requestDate, requestStatus, substance, requestDetail, status));
                    }

                }

            }
        } catch (Exception e) {
            log("Error at getProcessingRequest in requestDAO" + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, rs);
        }
        return request;
    }

    public boolean updateRequestStatus(int requestID, String requestStatusNew) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(UPDATE_REQUEST_STATUS);
                //UPDATE request SET requestStatus = ?  AND requestID = ?
                stm.setString(1, requestStatusNew);
                stm.setInt(2, requestID);
                check = stm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            log("Error at updateRequestStatus in requestDAO: " + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, null);
        }
        return check;
    }

    public boolean updateDetailStatus(int deatailID, String deatailStatus) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        //UPDATE requestDetail SET detailStatus = ? AND borrowDate = ?WHERE detailID =?
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(UPDATE_REQUEST_DETAIL_STATUS);
                stm.setString(1, deatailStatus);

                stm.setInt(2, deatailID);
                check = stm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            log("Error at updateDetailStatus in requestDAO: " + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, null);
        }
        return check;
    }

    public int createReturned(DeviceDTO items, Account user, int borrowDate) throws SQLException {
        int check = -1;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(CREATE_RETURN, Statement.RETURN_GENERATED_KEYS);
                //INSERT INTO returned (userID, requestID, deviceID, quantity, returnDate, status) (?,?,?,?,?,?)
                stm.setString(1, user.getUserID());
                stm.setDate(2, new Date(System.currentTimeMillis()));
                stm.setString(3, "Waiting...");
                stm.setBoolean(4, true);
                if (stm.executeUpdate() > 0) {
                    rs = stm.getGeneratedKeys();
                    if (rs.next()) {
                        int requestID = rs.getInt(1);
                        createOrderDetails(items, requestID, borrowDate);
                        check = requestID;
                    }
                }
            }
        } catch (Exception e) {
            log("Error at checkOutOrder in OrderDAO" + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, rs);

        }
        return check;
    }

    public List<requestDTO> getRequestBaseOnStatusDetailAnduser(boolean status, String requestStatus, Account user) {
        List<requestDTO> request = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_REQUEST_BASE_ON_USER);
                //SELECT requestID, requestDate,substance, userID  FROM request WHERE status = ? AND requestStatus =? AND userID = ?
                stm.setBoolean(1, status);
                stm.setString(2, requestStatus);
                stm.setString(3, user.getUserID());

                rs = stm.executeQuery();
                while (rs.next()) {
                    int requestID = rs.getInt("requestID");
                    Date requestDate = rs.getDate("requestDate");
                    String substance = rs.getString("substance");

                    requestDetailDTO requestDetail = getRequestDetailByRequestID(requestID);
                    if (substance.equals("Extend Request")) {
                        ExtendDTO extend = getExtend(requestID);
                        request.add(new requestDTO(requestID, user, requestDate, requestStatus, substance, requestDetail, extend, status));

                    } else {
                        request.add(new requestDTO(requestID, user, requestDate, requestStatus, substance, requestDetail, status));
                    }

                }

            }
        } catch (Exception e) {
            log("Error at getProcessingRequest in requestDAO" + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, rs);
        }
        return request;
    }

    public List<requestDTO> getRequestBaseOnDetailStatusAndUser(boolean status, String detailStatus, Account user) {
        List<requestDTO> request = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_REQUEST_BASE_ON_DETAIL_AND_USER);
                //SELECT r.requestID, r.requestDate,r.substance, r.userID, r.requestStatus FROM request r, 
                //requestDetail d WHERE r.status = ? AND d.detailStatus = ? AND r.requestID = d.requestID AND r.userID = ?
                stm.setBoolean(1, status);
                stm.setString(2, detailStatus);
                stm.setString(3, user.getUserID());
                rs = stm.executeQuery();
                while (rs.next()) {
                    int requestID = rs.getInt("requestID");
                    Date requestDate = rs.getDate("requestDate");
                    String substance = rs.getString("substance");
                    String requestStatus = rs.getString("requestStatus");
                    requestDetailDTO requestDetail = getRequestDetailByRequestID(requestID);
                    if (substance.equals("Extend Request")) {
                        ExtendDTO extend = getExtend(requestID);
                        request.add(new requestDTO(requestID, user, requestDate, requestStatus, substance, requestDetail, extend, status));

                    } else {
                        request.add(new requestDTO(requestID, user, requestDate, requestStatus, substance, requestDetail, status));
                    }

                }

            }
        } catch (Exception e) {
            log("Error at getProcessingRequest in requestDAO" + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, rs);
        }
        return request;
    }

    public boolean creatReturnRequest(requestDTO request) {
        //INSERT INTO returned (userID, requestID, deviceID, quantity, returnDate, status) VALUES (?,?,?,?,?,?)
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(CREATE_RETURN);
                stm.setString(1, request.getUser().getUserID());
                stm.setInt(2, request.getId());
                stm.setInt(3, request.getRequestDetail().getDevice().getDeviceID());
                stm.setInt(4, request.getRequestDetail().getQuantity());
                stm.setDate(5, new Date(System.currentTimeMillis()));
                stm.setBoolean(6, true);
                check = stm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            log("Error at CreateReturnRequest in requestDAO" + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, null);
        }
        return check;
    }

    public requestDTO getRequestByID(int requestID) {
        //SELECT  requestDate, requestStatus,substance,userID, status  FROM request WHERE  requestID =?
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        requestDTO request = null;
        requestDetailDTO requestDetail = null;
        Account account = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_REQUEST_BY_ID);

                stm.setInt(1, requestID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    Date requestDate = rs.getDate("requestDate");
                    String requestStatus = rs.getString("requestStatus");
                    String substance = rs.getString("substance");
                    String userID = rs.getString("userID");
                    boolean status = rs.getBoolean("status");
                    requestDetail = getRequestDetailByRequestID(requestID);
                    AccountDao dao = new AccountDao();
                    account = dao.searchAccountUpdate(userID);
                    if (substance.equals("Extend Request")) {
                        ExtendDTO extend = getExtend(requestID);

                        request = new requestDTO(requestID, account, requestDate, requestStatus, substance, requestDetail, extend, status);

                    } else {
                        request = new requestDTO(requestID, account, requestDate, requestStatus, substance, requestDetail, status);
                    }
                }
            }

        } catch (Exception e) {
            log("Error at getRequestDetail in requestDAO" + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, rs);
        }
        return request;
    }

    public boolean creatExtendRequest(int newID, int requestID, String message, int extendDate) {
        //INSERT INTO message (messageID, requestID, message, extendDate) VALUES (?,?,?,?)
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(CREATED_EXTEND);
                stm.setInt(1, newID);
                stm.setInt(2, requestID);
                stm.setString(3, message);
                stm.setInt(4, extendDate);
                check = stm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            log("Error at CreateReturnRequest in requestDAO" + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, null);
        }
        return check;
    }

    

    public boolean updateRequestBorrowDate(int detailID, Date borrowDate, Date NewExpiredDate) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        Extension dlc = new Extension();
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(UPDATE_REQUEST_BORROW_DATE);
                //UPDATE request SET requestStatus = ?  AND requestID = ?
                stm.setDate(1, borrowDate);
                stm.setDate(2,NewExpiredDate);
                stm.setInt(3, detailID);
                check = stm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            log("Error at updateRequestStatus in requestDAO: " + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, null);
        }
        return check;
    }

    public ExtendDTO getExtend(int requestID) {
        //SELECT messageID, requestID, message, extendDate FROM message WHERE messageID = ?
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ExtendDTO extend = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_EXTEND);

                stm.setInt(1, requestID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int messageID = rs.getInt("messageID");
                    int request = rs.getInt("requestID");
                    String message = rs.getString("message");
                    int extendDate = rs.getInt("extendDate");
                    extend = new ExtendDTO(messageID, request, message, extendDate);
                }
            }

        } catch (Exception e) {
            log("Error at getRequestDetail in requestDAO" + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, rs);
        }
        return extend;
    }

    public List<requestDTO> getExtendRequest(String requestSubstance) {
        List<requestDTO> request = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_EXTEND_REQUEST);
                stm.setString(1, requestSubstance);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int requestID = rs.getInt("requestID");
                    Date requestDate = rs.getDate("requestDate");
                    String substance = rs.getString("substance");
                    String requestStatus = rs.getString("requestStatus");
                    String user_id = rs.getString("userID");
                    boolean status = rs.getBoolean("status");
                    Account user = new AccountDao().searchAccountUpdate(user_id);
                    requestDetailDTO requestDetail = getRequestDetailByRequestID(requestID);
                    ExtendDTO extend = getExtend(requestID);
                    request.add(new requestDTO(requestID, user, requestDate, requestStatus, substance, requestDetail, extend, status));
                }

            }
        } catch (Exception e) {
            log("Error at getProcessingRequest in requestDAO" + e.toString());
        } finally {
            DBUtils.closeQueryConnection(conn, stm, rs);
        }
        return request;
    }
    

}
