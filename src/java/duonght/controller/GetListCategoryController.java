/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duonght.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import duonght.category.CategoryDAO;
import duonght.category.CategoryDTO;
import duonght.description.DescriptionDAO;
import duonght.description.DescriptionDTO;
import duonght.descriptionDetail.DescriptionDetailDAO;
import duonght.descriptionDetail.DescriptionDetailDTO;
import quanghung.brand.BrandDAO;
import quanghung.brand.BrandDTO;

/**
 *
 * @author Trung Duong
 */
public class GetListCategoryController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "categoryManagement.jsp";

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            ArrayList<CategoryDTO> categories = new ArrayList<>();
            ArrayList<DescriptionDTO> descriptions = new ArrayList<>();
            ArrayList<DescriptionDetailDTO> descriptionDetails = new ArrayList<>();
            ArrayList<BrandDTO> brands = new ArrayList<>();
            categories = CategoryDAO.getCategory();
            if (categories != null) {
                request.setAttribute("categories", categories);
                url = SUCCESS;
                for (CategoryDTO category : categories) {
                    descriptions = DescriptionDAO.getListDescription(category.getCateID());
                    brands = BrandDAO.getListBrand(category.getCateID());
                    request.setAttribute("brand"+category.getCateID(), brands);
                    request.setAttribute("des"+category.getCateID(), descriptions);
                    for (DescriptionDTO description : descriptions) {
                        descriptionDetails = DescriptionDetailDAO.getListDescriptionDetail(description.getDescriptionID());
                        request.setAttribute(description.getDescriptionID()+description.getDescriptionName(), descriptionDetails);
                    }
                }
            }
            
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {
            log("Error at Get List Category Controller: " + e.toString());
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(GetListCategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(GetListCategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
