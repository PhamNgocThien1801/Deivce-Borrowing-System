/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package quanghung.controller;

import DLC.MessageSpecified;
import duonght.dto.Account;
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
public class AddToCartController extends HttpServlet {

    private final String SUCCESS1 = "Cart.jsp";
    private final String SUCCESS2 = "MainController?search=&action=HomeSearchDevice";
    private final String ERROR = "MainController?search=&action=HomeSearchDevice";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        
        try {
            String action = request.getParameter("action");
            int id = Integer.parseInt(request.getParameter("deviceID"));
            DeviceDAO dao = new DeviceDAO();
            HttpSession session = request.getSession();
            //update: change session name for login user
            Account loginUser = (Account) session.getAttribute("UserDB");
            //update: change session name for login user
            int quantityAddCart = Integer.parseInt(request.getParameter("quantityToCart"));
            
            DeviceDTO selected = dao.getDeviceByID(id);
            if(quantityAddCart > selected.getQuantity()){
                //selected number is greater than those in warehouse
                //warning
            } else {
                //add successfully
                cartDTO cart = (cartDTO) session.getAttribute("CART");
                if(cart == null){
                    cart = new cartDTO();
                }
                
                selected.setQuantity(quantityAddCart);
                cart.add(selected);
                session.setAttribute("CART", cart);
                if(action.equals("AddToCart")){
                    url = SUCCESS2;
                }else if(action.equals("RentNow")){
                    url = SUCCESS1;
                }
                
            }
        } catch (Exception e) {
            log("Error at CartController: " + e.toString());
            if (e.toString().contains("ull")) {
                MessageSpecified message = new MessageSpecified(null, "", " Out of stock!");
                request.setAttribute("ERROR_MESSAGE", message);
            }
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