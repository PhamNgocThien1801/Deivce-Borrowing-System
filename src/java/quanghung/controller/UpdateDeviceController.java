package quanghung.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import quanghung.category.CategoryDAO;
import quanghung.description.DescriptionDAO;
import quanghung.description.DescriptionDTO;
import quanghung.device.DeviceDAO;
import quanghung.device.DeviceDTO;
import quanghung.device_description.Device_DescriptionDAO;
import quanghung.warehouse.WarehouseDAO;

public class UpdateDeviceController extends HttpServlet {

    private static final String ERROR = "MainController?search=&action=SearchDevice";
    private static final String SUCCESS = "MainController?search=&action=SearchDevice";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            CategoryDAO categoryDao = new CategoryDAO();
            int deviceID = Integer.parseInt(request.getParameter("deviceID"));
            String deviceName = request.getParameter("deviceName");
            int warehouseID = Integer.parseInt(request.getParameter("warehouseID"));
            int brandID = Integer.parseInt(request.getParameter("brandID"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            int currentQuantity = Integer.parseInt(request.getParameter("currentQuantity"));
            int deposit = Integer.parseInt(request.getParameter("deposit"));
            String cateName = request.getParameter("cateName");
            String cateID = categoryDao.getCateID(cateName);
            DeviceDAO deviceDao = new DeviceDAO();
            DescriptionDAO descriptionDao = new DescriptionDAO();
            Device_DescriptionDAO device_descriptionDao = new Device_DescriptionDAO();
            WarehouseDAO warehouseDao = new WarehouseDAO();
            List<DescriptionDTO> listDescription = descriptionDao.getListDescription(cateID);
            for (int i = 1; i <= listDescription.size(); i++) {
                String d = "detailID" + String.valueOf(i);
                String dd = request.getParameter(d);
                int detailID = Integer.parseInt(dd);
                int currentDetailID = Integer.parseInt(request.getParameter("currentDetailID" + String.valueOf(i)));
                boolean createDevice_Description = device_descriptionDao.updateDevice_Description(currentDetailID, deviceID, detailID);
            }
            boolean updateLimitAmount = false;
            int limitAmount = warehouseDao.getLimitAmount(warehouseID);
            String warehouseName = warehouseDao.getWarehouseName(warehouseID);
            if (quantity > limitAmount) {
                String error = "The Limit Amount of " + warehouseName +" is " + String.valueOf(limitAmount) + ". Cannot update " + quantity + " devices"  + " to this warehouse";
                request.setAttribute("ERROR_QUANTITY", error);
            } else {
                if (currentQuantity < quantity) {
                    updateLimitAmount = warehouseDao.subtractionLimitAmount(quantity - currentQuantity, warehouseID);
                } else {
                    updateLimitAmount = warehouseDao.addtionLimitAmount(currentQuantity - quantity, warehouseID);
                }
                boolean check = deviceDao.updateDevice(deviceID, deviceName, warehouseID, brandID, quantity, cateID, deposit);
                if (check && updateLimitAmount) {
                    request.setAttribute("SUCCESS", "Update Device Successfully");
                    url = SUCCESS;
                }
            }

        } catch (Exception e) {
            log("Error at UpdateProductController: " + e.toString());
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