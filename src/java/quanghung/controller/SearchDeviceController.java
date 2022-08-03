package quanghung.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import quanghung.device.DeviceDTO;
import quanghung.warehouse.WarehouseDAO;

public class SearchDeviceController extends HttpServlet {

    private static final String ERROR = "deviceManagement.jsp";
    private static final String SUCCESS = "deviceManagement.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            String search = String.valueOf(request.getParameter("search"));
            String filter = String.valueOf(request.getParameter("filter"));
            String value = request.getParameter("value");
            request.setAttribute("SEARCH", search);
            DeviceDAO deviceDAO = new DeviceDAO();
            DescriptionDAO descriptionDAO = new DescriptionDAO();
            DescriptionDetailDAO descriptionDetailDAO = new DescriptionDetailDAO();
            CategoryDAO categoryDAO = new CategoryDAO();
            BrandDAO brandDao = new BrandDAO();
            WarehouseDAO warehouseDAO = new WarehouseDAO();
            Map<String, String> categoryList = categoryDAO.getCategory();
            Map<Integer, String> brandList = brandDao.getListBrand();
            Map<Integer, String> warehouseList = warehouseDAO.getWarehouse();
            categoryList.entrySet();
            brandList.entrySet();
            warehouseList.entrySet();
            List<DeviceDTO> deviceList;
            deviceList = deviceDAO.getListDeviceByName(search);
            for (Map.Entry<String, String> category : categoryList.entrySet()) {
                if (filter.equals(category.getKey()) && value.equals(category.getValue())) {
                    deviceList = deviceDAO.getListDeviceByCateID(category.getKey());
                    request.setAttribute("SEARCH", category.getValue());
                    break;
                }
            }
            for (Map.Entry<Integer, String> brand : brandList.entrySet()) {
                if (filter.equals(String.valueOf(brand.getKey())) && value.equals(brand.getValue())) {
                    deviceList = deviceDAO.getListDeviceByBrandID(brand.getKey());
                    request.setAttribute("SEARCH", brand.getValue());
                    break;
                }
            }
            for (Map.Entry<Integer, String> warehouse : warehouseList.entrySet()) {
                if (filter.equals(String.valueOf(warehouse.getKey())) && value.equals(warehouse.getValue())) {
                    deviceList = deviceDAO.getListDeviceByWarehouseID(warehouse.getKey());
                    request.setAttribute("SEARCH", warehouse.getValue());
                    break;
                }
            }
            if (deviceList.size() != 0) {
                List<Map<Integer, String>> brandListBasedOnCategory = new ArrayList<>();
                Map<Integer, String> detailList = null;
                List<List<Map.Entry<Integer, String>>> detailListBasedOnDevice = new ArrayList<>();
                for (int i = 0; i < deviceList.size(); i++) {
                    String cateID = deviceList.get(i).getCateID();
                    Map<Integer, String> brandBasedOnCategory = brandDao.getListBrandBasedOnCateID(cateID);
                    List<DescriptionDTO> listDescription = descriptionDAO.getListDescription(cateID);
                    detailList = descriptionDetailDAO.getListDescriptionDetailBasedOnDevice(deviceList.get(i).getDeviceID());
                    List<Map.Entry<Integer, String>> detailBasedOnDevice = new ArrayList<>(detailList.entrySet());
                    for (DescriptionDTO l : listDescription) {
                        List<DescriptionDetailDTO> listDescriptionDetail = descriptionDetailDAO.getListDescriptionDetail(l.getDescriptionID());
                        request.setAttribute(l.getDescriptionName(), listDescriptionDetail);
                    }
                    String ld = "LIST_DESCRIPTION" + String.valueOf(i + 1);
                    request.setAttribute(cateID, listDescription);
                    brandListBasedOnCategory.add(brandBasedOnCategory);
                    detailListBasedOnDevice.add(detailBasedOnDevice);
                }
                session.setAttribute("LIST_DETAIL", detailListBasedOnDevice);
                request.setAttribute("LIST_BRAND_BASED_ON_CATEGORY", brandListBasedOnCategory);
                if (deviceList.size() > 0) {
                    url = SUCCESS;
                    session.setAttribute("LIST_BRAND", brandList);
                    session.setAttribute("LIST_WAREHOUSE", warehouseList);
                    session.setAttribute("LIST_CATEGORY", categoryList);
                    session.setAttribute("LIST_WAREHOUSE_FILTER", warehouseList);
                    request.setAttribute("LIST_DEVICE", deviceList);
                }
            } else {
                request.setAttribute("ERROR", "No Result");
            }
        } catch (Exception e) {
            log("Error at SearchDeviceController: " + e.toString());
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