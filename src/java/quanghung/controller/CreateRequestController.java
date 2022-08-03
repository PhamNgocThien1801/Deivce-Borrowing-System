/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package quanghung.controller;

import duonght.dao.AccountDao;
import duonght.dto.Account;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import manhcuong.request.DeviceDAO;
import manhcuong.request.cartDTO;
import manhcuong.request.requestDAO;
import quanghung.device.DeviceDTO;

/**
 *
 * @author Admin
 */
public class CreateRequestController extends HttpServlet {

    private final String SUCCESS = "MainController?action=LoadProcessRequest";
    private final String ERROR = "Cart.jsp";
    private final String LOGIN = "Login.jsp";
    private Account loginUser = null;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            Account loginUser = (Account) session.getAttribute("UserDB");
            int userDeposit = 0;
            int newquantity = 0;
            if (loginUser == null) {
                url = LOGIN;
            }
            cartDTO cart = (cartDTO) session.getAttribute("CART");

            List<DeviceDTO> itemsInCart = new ArrayList<DeviceDTO>(cart.getCart().values());
            DeviceDTO device = null;
            requestDAO dao = new requestDAO();
            DeviceDAO D_dao = new DeviceDAO();
            AccountDao A_dao = new AccountDao();
            List<Integer> borrowDate = new ArrayList<>();
            for (int i = 1; i <= itemsInCart.size(); i++) {
                String a = "borrowedDate" + String.valueOf(i);
                borrowDate.add(Integer.parseInt(request.getParameter(a)));
            }

            int check = 0;
            for (int i = 0; i < itemsInCart.size(); i++) {
                check += dao.createOrder(itemsInCart.get(i), loginUser, borrowDate.get(i), "Borrow Request");
                device = D_dao.getDeviceByID(itemsInCart.get(i).getDeviceID());
                newquantity = device.getQuantity() - itemsInCart.get(i).getQuantity();
                device.setQuantity(newquantity);
                boolean check2 = D_dao.updateDevice(newquantity, device.getDeviceID());
                
            }
            if (check > 0) {
                for (int i = 0; i < itemsInCart.size(); i++){
                    userDeposit = A_dao.updateDeposit(loginUser.getUserID(), loginUser.getDeposit());
                }
                loginUser.setDeposit(userDeposit);
                cart = null;
                session.setAttribute("CART", cart);
                
                url = SUCCESS;
            } else {
                url = ERROR;
                request.setAttribute("ERROR_MESSAGE", "Error occur in CreateOrder ");
                // xu?t ph?n này ra nhu nào tao v?n chua bi?t
            }
        } catch (Exception e) {
            log("Error at CreateRequestController: " + e.toString());
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
