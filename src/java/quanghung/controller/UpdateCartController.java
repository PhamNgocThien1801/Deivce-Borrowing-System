/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package quanghung.controller;

import DLC.MessageSpecified;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import manhcuong.request.DeviceDAO;
import manhcuong.request.cartDTO;
import quanghung.device.DeviceDTO;

/**
 *
 * @author Admin
 */
public class UpdateCartController extends HttpServlet {

    private final String SUCCESS = "Cart.jsp";
    private final String ERROR = "Cart.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            int productID =Integer.parseInt(request.getParameter("deviceID"));
            DeviceDAO d_dao = new DeviceDAO();
            DeviceDTO items = d_dao.getDeviceByID(productID);
            int quantityUpdate = Integer.parseInt(request.getParameter("deviceQuantity"));
            cartDTO cart = (cartDTO) session.getAttribute("CART");
            
            if(quantityUpdate < items.getQuantity()){
                items.setQuantity(quantityUpdate);
                cart.update(productID, items);
                url = SUCCESS;
            }else{
                MessageSpecified error = new MessageSpecified(request.getParameter("productID"), "Invalid quantity", "Max at " + items.getQuantity());
                request.setAttribute("ERROR_MESSAGE", error);
                url = ERROR;
            }
        } catch (Exception e) {
            log("Error at RemoveItemCartController");
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