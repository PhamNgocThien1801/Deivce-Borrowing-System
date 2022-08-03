/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duonght.controller;

import duonght.dao.AccountDao;
import duonght.dto.Account;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Trung Duong
 */
public class UpdateProfileServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String userName = request.getParameter("userName");
            String userPhone = request.getParameter("userPhone");
            String userID = request.getParameter("userID");
            boolean checkValidation = true;
            if (userName.length() < 10) {
                checkValidation = false;
                request.setAttribute("errorName", "Name Should Greater Than 10 Character!");
            } else if (userName.length() > 50) {
                checkValidation = false;
                request.setAttribute("errorName", "Name Should Less Than 50 Character!");
            }
            if (userPhone.length() != 10) {
                checkValidation = false;
                request.setAttribute("errorPhone", "Phone Must Equal 10 Numbers!");
            }
            if (checkValidation) {
                boolean isUpdate = AccountDao.UpdateProfile(userID, userName, userPhone);
                if (isUpdate) {
                    request.setAttribute("MESSAGE", "Update Profile Successfully");
                    HttpSession session = request.getSession();
                    Account acc = AccountDao.searchAccountUpdate(userID);
                    session.setAttribute("AdminDB", acc);
                } else {
                    request.setAttribute("MESSAGE", "Opps! "
                            + " Something Wrong. Try again!");
                }
            }
            request.getRequestDispatcher("myprofile.jsp").forward(request, response);
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
