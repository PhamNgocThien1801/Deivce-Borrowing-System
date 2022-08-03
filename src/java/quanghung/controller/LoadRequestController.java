/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package quanghung.controller;

import DLC.MessageSpecified;
import duonght.dto.Account;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import manhcuong.request.requestDAO;
import manhcuong.request.requestDTO;


/**
 *
 * @author Admin
 */
public class LoadRequestController extends HttpServlet {

    private final String ERROR = "user.jsp";
    private final String PROCESSING_REQUEST = "user.jsp";
    private final String APPROVE_REQUEST = "userApproved.jsp";
    private final String SUCCESS_REQUEST = "userSuccessful.jsp";
    private final String RETURNED_REQUEST = "userReturned.jsp";
    private final String CANCEL_REQUEST = "userCancel.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String url = ERROR;
        Account acc = (Account)request.getSession().getAttribute("UserDB");
        
        try {
            String action = request.getParameter("action");
            if (action.equals("LoadProcessRequest")) {
                requestDAO dao = new requestDAO();
                List<requestDTO> list = new ArrayList<>();
                list.addAll(dao.getRequestBaseOnStatusDetailAnduser(true, "Waiting...", acc));
                Collections.sort(list);
                if(!list.isEmpty()){
                    request.setAttribute("LIST_PROCESSING_REQUEST_USER", list);
                    url = PROCESSING_REQUEST;
                }else{
                    MessageSpecified message = new MessageSpecified(null, "Empty", "Don't have any processing request!");
                    request.setAttribute("ERROR_MESSAGE", message);
                    url = PROCESSING_REQUEST;
                }
                } else if (action.equals("LoadApproveRequest")) {
                requestDAO dao = new requestDAO();
                List<requestDTO> list = new ArrayList<>();
                list.addAll(dao.getRequestBaseOnDetailStatusAndUser(true, "approve", acc));
                Collections.sort(list);
                if(!list.isEmpty()){
                    request.setAttribute("LIST_APPROVED_REQUEST_USER", list);
                    url = APPROVE_REQUEST;
                }else{
                    MessageSpecified message = new MessageSpecified(null, "Empty", "Don't have any approve request!");
                    request.setAttribute("ERROR_MESSAGE", message);
                    url = APPROVE_REQUEST;
                }

            } else if (action.equals("LoadSuccessfulRequest")) {
                requestDAO dao = new requestDAO();
                List<requestDTO> list = new ArrayList<>();
                list.addAll(dao.getRequestBaseOnDetailStatusAndUser(true, "Received", acc));
                Collections.sort(list);
                if(!list.isEmpty()){
                    request.setAttribute("LIST_SUCCESSFUL_REQUEST_USER", list);
                    url = SUCCESS_REQUEST;
                }else{
                    MessageSpecified message = new MessageSpecified(null, "Empty", "Don't have any  received request!");
                    request.setAttribute("ERROR_MESSAGE", message);
                    url = SUCCESS_REQUEST;
                }
            } else if (action.equals("LoadReturnRequest")) {
                requestDAO dao = new requestDAO();
                List<requestDTO> list = new ArrayList<>();
                list.addAll(dao.getRequestBaseOnStatusDetailAnduser(true, "returned", acc));
                Collections.sort(list);
                if(!list.isEmpty()){
                    request.setAttribute("LIST_RETURNED_REQUEST_USER", list);
                    url = RETURNED_REQUEST;
                }else{
                    MessageSpecified message = new MessageSpecified(null, "Empty", "Don't have any returned request!");
                    request.setAttribute("ERROR_MESSAGE", message);
                    url = RETURNED_REQUEST;
                }
            } else if (action.equals("LoadCancelUserRequest")) {
                requestDAO dao = new requestDAO();
                List<requestDTO> list = new ArrayList<>();
                list.addAll(dao.getRequestBaseOnStatusDetailAnduser(true, "cancel", acc));
                Collections.sort(list);
                if(!list.isEmpty()){
                    request.setAttribute("LIST_CANCEL_REQUEST_USER", list);
                    url = CANCEL_REQUEST;
                }else{
                    MessageSpecified message = new MessageSpecified(null, "Empty", "Don't have any cancel request!");
                    request.setAttribute("ERROR_MESSAGE", message);
                    url = CANCEL_REQUEST;
                }
            }
        } catch (Exception e) {
            log("Error at LoadRequestController: " + e.toString());
        } finally {
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