package quanghung.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import quanghung.brand.BrandDAO;
import quanghung.category.CategoryDAO;
import quanghung.description.DescriptionDAO;
import quanghung.description.DescriptionDTO;
import quanghung.descriptionDetail.DescriptionDetailDAO;
import quanghung.descriptionDetail.DescriptionDetailDTO;
import quanghung.device.DeviceDAO;
import quanghung.device.DeviceError;
import quanghung.warehouse.WarehouseDAO;

public class InputDeviceInfoController extends HttpServlet {

    private static final String ERROR = "createDevice.jsp";
    private static final String SUCCESS = "createDeviceInfo.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        DeviceError deviceError = new DeviceError();
        try {
            boolean checkValidation = true;
            HttpSession session = request.getSession();
            CategoryDAO categoryDao = new CategoryDAO();
            DeviceDAO deviceDao = new DeviceDAO();
            DescriptionDAO descriptionDao = new DescriptionDAO();
            DescriptionDetailDAO descriptionDetailDao = new DescriptionDetailDAO();
            WarehouseDAO warehouseDao = new WarehouseDAO();
            BrandDAO brandDao = new BrandDAO();
            String cateID = request.getParameter("cateID");
            String deviceName = request.getParameter("deviceName");
            String warehouseName = request.getParameter("warehouseName");
            String cateName = categoryDao.getCateName(cateID);
            int warehouseID = warehouseDao.getWarehouseID(warehouseName);
            boolean checkDuplicate = deviceDao.checkDuplicate(deviceName, warehouseID);
            if (checkDuplicate) {
                deviceError.setDeviceNameError("Duplicate Device Name in " + warehouseName);
                checkValidation = false;
            }
            if (checkValidation) {
                Map<Integer, String> listBrand = brandDao.getListBrandBasedOnCateID(cateID);
                List<DescriptionDTO> listDescription = descriptionDao.getListDescription(cateID);
                for (DescriptionDTO l : listDescription) {
                    List<DescriptionDetailDTO> listDescriptionDetail = descriptionDetailDao.getListDescriptionDetail(l.getDescriptionID());
                    session.setAttribute(l.getDescriptionName(), listDescriptionDetail);
                }
                session.setAttribute("CATE_NAME", cateName);
                session.setAttribute("DEVICE_NAME", deviceName);
                session.setAttribute("WAREHOUSE_NAME", warehouseName);
                session.setAttribute("DESCRIPTION_LIST", listDescription);
                session.setAttribute("BRAND_LIST", listBrand);
                url = SUCCESS;
            } else {
                url = ERROR;
                request.setAttribute("DEVICE_ERROR", deviceError);
            }

        } catch (Exception e) {
            if (e.toString().contains("duplicate")) {
                deviceError.setDeviceNameError("Duplicate Device Name");
            }
            request.setAttribute("DEVICE_ERROR", deviceError);
            log("Error at InputDeviceInfoController: " + e.toString());
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