package quanghung.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import quanghung.brand.BrandDAO;
import quanghung.category.CategoryDAO;
import quanghung.description.DescriptionDAO;
import quanghung.description.DescriptionDTO;
import quanghung.descriptionDetail.DescriptionDetailDAO;
import quanghung.device.DeviceDAO;
import quanghung.device_description.Device_DescriptionDAO;
import quanghung.warehouse.WarehouseDAO;

public class UpdateDeviceDetailCategoryController extends HttpServlet {

    private static final String ERROR = "updateCategoryDeviceInfo.jsp";
    private static final String SUCCESS = "MainController?search=&action=SearchDevice";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
                HttpSession session = request.getSession();
            DeviceDAO deviceDao = new DeviceDAO();
            WarehouseDAO warehouseDao = new WarehouseDAO();
            BrandDAO brandDao = new BrandDAO();
            CategoryDAO categoryDao = new CategoryDAO();
            DescriptionDAO descriptionDao = new DescriptionDAO();
            DescriptionDetailDAO detailDAO = new DescriptionDetailDAO();
            Device_DescriptionDAO device_descriptionDao = new Device_DescriptionDAO();
            String cateName = request.getParameter("cateName");
            String cateID = categoryDao.getCateID(cateName);
            int deviceID = Integer.parseInt(request.getParameter("deviceID"));
            List<DescriptionDTO> listDescription = descriptionDao.getListDescription(cateID);
            boolean delete = device_descriptionDao.deleteAllDevice_Description(deviceID);
            for (int i = 1; i <= listDescription.size(); i++) {
                String d = "detailID" + String.valueOf(i);
                String dd = request.getParameter(d);
                int detailID = Integer.parseInt(dd);
                boolean createDevice_Description = device_descriptionDao.createDevice_Description(deviceID, detailID);
            }
            boolean update = deviceDao.updateCategory(deviceID, cateID);
            if (update == true) {
                String success = "Update Category successfully";
                request.setAttribute("SUCCESS", success);
                url = SUCCESS;
            }

        } catch (Exception e) {
            log("Error at Create Device Controller: " + e.toString());
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