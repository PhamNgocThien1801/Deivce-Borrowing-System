package duonght.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author duong
 */
public class MainController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String LOGIN = "Login";
    private static final String LOGIN_CONTROLLER = "LoginController";
    private static final String GET_LIST_CATEGORY = "GetListCategory";
    private static final String GET_LIST_CATEGORY_CONTROLLER = "GetListCategoryController";
    private static final String GET_LIST_WAREHOUSE = "GetListWarehouse";
    private static final String GET_LIST_WAREHOUSE_CONTROLLER = "GetListWarehouseController";
    private static final String GET_LIST_DESCRIPTION = "GetListDescription";
    private static final String GET_LIST_DESCRIPTION_CONTROLLER = "GetListDescriptionController";
    private static final String CREATE_WAREHOUSE = "createWarehouse";
    private static final String CREATE_WAREHOUSE_CONTROLLER = "CreateWarehouseController";
    private static final String DELETE_WAREHOUSE = "DeleteWarehouse";
    private static final String DELETE_WAREHOUSE_CONTROLLER = "DeleteWarehouseController";
    private static final String UPDATE_WAREHOUSE = "UpdateWarehouse";
    private static final String UPDATE_WAREHOUSE_CONTROLLER = "UpdateWarehouseController";
    private static final String CREATE_CATEGORY = "CreateCategory";
    private static final String CREATE_CATEGORY_CONTROLLER = "CreateCategoryController";
    private static final String DELETE_CATEGORY = "DeleteCategory";
    private static final String DELETE_CATEGORY_CONTROLLER = "DeleteCategoryController";
    private static final String UPDATE_CATEGORY = "UpdateCategory";
    private static final String UPDATE_CATEGORY_CONTROLLER = "UpdateCategoryController";
    private static final String SEARCH_CATEGORY = "SearchCategory";
    private static final String SEARCH_CATEGORY_CONTROLLER = "SearchCategoryController";
    private static final String CREATE_DESCRIPTION = "CreateDescription";
    private static final String CREATE_DESCRIPTION_CONTROLLER = "CreateDescriptionController";
    private static final String DELETE_DESCRIPTION = "DeleteDescription";
    private static final String DELETE_DESCRIPTION_CONTROLLER = "DeleteDescriptionController";
    private static final String CREATE_DETAIL = "CreateDetail";
    private static final String CREATE_DETAIL_CONTROLLER = "CreateDetailController";
    private static final String UPDATE_DETAIL = "UpdateDetail";
    private static final String UPDATE_DETAIL_CONTROLLER = "UpdateDetailController";
    private static final String DELETE_DETAIL = "DeleteDetail";
    private static final String DELETE_DETAIL_CONTROLLER = "DeleteDetailController";
    private static final String SEARCH_WAREHOUSE = "SearchWarehouse";
    private static final String SEARCH_WAREHOUSE_CONTROLLER = "SearchWarehouseController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");

            if (CREATE_WAREHOUSE.equals(action)) {
                url = CREATE_WAREHOUSE_CONTROLLER;
            } else if (DELETE_WAREHOUSE.equals(action)) {
                url = DELETE_WAREHOUSE_CONTROLLER;
            } else if (UPDATE_WAREHOUSE.equals(action)) {
                url = UPDATE_WAREHOUSE_CONTROLLER;
            } else if (SEARCH_WAREHOUSE.equals(action)) {
                url = SEARCH_WAREHOUSE_CONTROLLER;
            } else if (GET_LIST_CATEGORY.equals(action)) {
                url = GET_LIST_CATEGORY_CONTROLLER;
            } else if (CREATE_CATEGORY.equals(action)) {
                url = CREATE_CATEGORY_CONTROLLER;
            } else if (UPDATE_CATEGORY.equals(action)) {
                url = UPDATE_CATEGORY_CONTROLLER;
            } else if (DELETE_CATEGORY.equals(action)) {
                url = DELETE_CATEGORY_CONTROLLER;
            } else if (GET_LIST_WAREHOUSE.equals(action)) {
                url = GET_LIST_WAREHOUSE_CONTROLLER;
            } else if (GET_LIST_DESCRIPTION.equals(action)) {
                url = GET_LIST_DESCRIPTION_CONTROLLER;
            } else if (CREATE_DESCRIPTION.equals(action)) {
                url = CREATE_DESCRIPTION_CONTROLLER;
            } else if (CREATE_DETAIL.equals(action)) {
                url = CREATE_DETAIL_CONTROLLER;
            } else if (UPDATE_DETAIL.equals(action)) {
                url = UPDATE_DETAIL_CONTROLLER;
            } else if (DELETE_DETAIL.equals(action)) {
                url = DELETE_DETAIL_CONTROLLER;
            } else if (DELETE_DESCRIPTION.endsWith(action)) {
                url = DELETE_DESCRIPTION_CONTROLLER;
            } else if (SEARCH_CATEGORY.equals(action)) {
                url = SEARCH_CATEGORY_CONTROLLER;
            }
        } catch (Exception e) {
            log("Error at MainController: " + e.toString());
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
