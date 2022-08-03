/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package quanghung.controller;

import DLC.Extension;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import manhcuong.request.DeviceDAO;
import manhcuong.request.requestDAO;
import manhcuong.request.requestDTO;
import quanghung.device.DeviceDTO;

/**
 *
 * @author Admin
 */
public class UpdateRequestController extends HttpServlet {

    
     private final String ERROR = "viewAllRequestManagement.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            if (action.equals("UpdateRequestApproved")){
                int requestID = Integer.parseInt(request.getParameter("requestID"));
                int detailID = Integer.parseInt(request.getParameter("detailID"));
                requestDAO dao = new requestDAO();
                //checkUpdateDate = dao.updateRequestExpiredDateForExtend(detail.getDetailID(), extendDate);
                boolean checkUpdateRequest = dao.updateRequestStatus(requestID, "approve");
                boolean checkUpdateRequestDetail = dao.updateDetailStatus(detailID, "approve");
                if(checkUpdateRequest== true && checkUpdateRequestDetail == true){
                    url="MainController?action=LoadProcessingRequest";
                }else{
                    url ="MainController?action=LoadProcessingRequest";
                }
                request.setAttribute("LIST_PROCESSING_REQUEST", dao.getRequestBaseOnStatusDetail(true, "Waiting"));
                
            }else if(action.equals("UpdateRequestCancel")){
                int requestID = Integer.parseInt(request.getParameter("requestID"));
                int detailID = Integer.parseInt(request.getParameter("detailID"));
                int newQuantity = 0;
                requestDAO dao = new requestDAO();
                DeviceDAO D_dao = new DeviceDAO();
                requestDTO request1 = dao.getRequestByID(requestID);
                
                DeviceDTO device = D_dao.getDeviceByID(request1.getRequestDetail().getDevice().getDeviceID());
                newQuantity = device.getQuantity() + request1.getRequestDetail().getQuantity();
                boolean checkUpdateRequest= dao.updateRequestStatus(requestID, "cancel");
                boolean checkUpdateDetail = dao.updateDetailStatus(detailID, "cancel");
                
                if(checkUpdateDetail == true && checkUpdateRequest == true){
                    boolean check2 = D_dao.updateDevice(newQuantity, device.getDeviceID());
                    if(check2 == true){
                        device.setQuantity(newQuantity);
                    }
                    if(request.getParameter("cancel").equals("requeststaff.jsp")){
                        
                        url ="MainController?action=LoadProcessingRequest";
                    }
                    
                }else{
                    if(request.getParameter("cancel").equals("requeststaff.jsp")){
                        url = "MainController?action=LoadProcessRequest";
                    }else if(request.getParameter("cancel").equals("approvedrequeststaff.jsp")){
                        url = "MainController?action=LoadApproveRequest";
                    }else if(request.getParameter("cancel").equals("successfulrequeststaff.jsp")){
                        url = "MainController?action=LoadSuccessfulRequest";
                    }
                    
                }
            }else if(action.equals("UpdateRequestSuccess")){
                int detailID = Integer.parseInt(request.getParameter("detailID"));
                int requestID = Integer.parseInt(request.getParameter("requestID"));
                requestDAO dao = new requestDAO();
                requestDTO request1 = dao.getRequestByID(requestID);
                boolean checkUpdateDetail = dao.updateDetailStatus(detailID, "Received");
                Extension ex = new Extension();
                
                long diffDate = ex.DiffDate(request1.getRequestDate(), request1.getRequestDetail().getExpiredDate());
                Date newExpiredDate = ex.AddDate(diffDate);
                boolean checkUpdateDate = dao.updateRequestBorrowDate(detailID, new Date(System.currentTimeMillis()), newExpiredDate);
                List<requestDTO> list1 = new ArrayList<>();
                List<requestDTO> list2 = new ArrayList<>();
                list1.addAll(dao.getRequestBaseOnDetailStatus(true, "approve"));
                for(int i = 0; i < list1.size(); i++){
                    if(!list1.get(i).getRequestStatus().equalsIgnoreCase("Waiting...")){
                        list2.add(list1.get(i));
                    }
                }
                if(checkUpdateDetail == true && checkUpdateDate == true){
                    url = "MainController?action=LoadApprovedRequest";
                }else{
                    url = "MainController?action=LoadApprovedRequest";
                }
                request.setAttribute("LIST_APPROVED_REQUEST", list2);
            }else if(action.equals("UpdateRequestReturn")){
                int newQuantity = 0;
                int requestID = Integer.parseInt(request.getParameter("requestID"));
                int detailID = Integer.parseInt(request.getParameter("detailID"));
                DeviceDAO D_dao = new DeviceDAO();
                requestDAO dao = new requestDAO();
                requestDTO request1 = dao.getRequestByID(requestID);
                DeviceDTO device = D_dao.getDeviceByID(request1.getRequestDetail().getDevice().getDeviceID());
                newQuantity = device.getQuantity() + request1.getRequestDetail().getQuantity();
                
                boolean checkUpdateRequest= dao.updateRequestStatus(requestID, "Returned");
                boolean checkUpdateDetail = dao.updateDetailStatus(detailID, "Returned");
                boolean checkCreateReturn = dao.creatReturnRequest(request1);
                if(checkCreateReturn == true && checkUpdateDetail == true && checkUpdateRequest == true){
                    boolean check2 = D_dao.updateDevice(newQuantity, device.getDeviceID());
                    if(check2 == true){
                        device.setQuantity(newQuantity);
                    }
                    url = "MainController?action=LoadSuccessRequest";
                }
                
                
            }
            
        } catch (Exception e) {
            log("Error at UpdateRequestController: " + e.toString());
        }finally{
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}