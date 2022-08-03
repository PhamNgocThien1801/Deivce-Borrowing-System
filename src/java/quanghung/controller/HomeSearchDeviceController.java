/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanghung.controller;

import java.io.IOException;
import java.io.PrintWriter;
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

public class HomeSearchDeviceController extends HttpServlet {

    private static final String ERROR = "devicePage.jsp";
    private static final String SUCCESS = "devicePage.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            String search = request.getParameter("search");
            String value = request.getParameter("value");
            String filter = String.valueOf(request.getParameter("filter"));
            if (search != null) {
                filter = search;
            }
            request.setAttribute("FILTER", search);
            DeviceDAO deviceDAO = new DeviceDAO();
            CategoryDAO categoryDAO = new CategoryDAO();
            BrandDAO brandDao = new BrandDAO();
            WarehouseDAO warehouseDAO = new WarehouseDAO();
            DescriptionDAO descriptionDAO = new DescriptionDAO();
            DescriptionDetailDAO detailDAO = new DescriptionDetailDAO();
            List<DeviceDTO> deviceList = deviceDAO.getListDeviceByName(filter);

            Map<String, String> categoryList = categoryDAO.getCategory();
            Map<Integer, String> brandList = brandDao.getListBrand();
            List<String> descriptionList = descriptionDAO.getListDescription();
            Map<Integer, String> warehouseList = warehouseDAO.getWarehouse();
            for (Map.Entry<String, String> category : categoryList.entrySet()) {
                if (filter.equals(category.getKey()) && value.equals(category.getValue())) {
                    deviceList = deviceDAO.getListDeviceByCateID(category.getKey());
                    break;
                }
            }
//            for (Map.Entry<String, String> category : categoryList.entrySet()) {
//                if (filter.equals(category.getKey()) && value.equals(category.getValue())) {
//                    List<DeviceDTO> list = deviceDAO.getListDeviceByCateID(category.getKey());
//                    List<DeviceDTO> a = new ArrayList<>();
//                    if (a != null) {
//                        for (int i = 0; i < deviceList.size(); i++) {
//                            for (int j = 0; i < list.size(); j++) {
//                                if (deviceList.get(i).getDeviceID() == list.get(j).getDeviceID()) {
//                                    a.add(deviceList.get(i));
//                                }
//                            }
//                        }
//                        deviceList = a;
//                    }
//
//                    request.setAttribute("SEARCH", category.getValue());
//                    break;
//                }
//            }
            for (Map.Entry<Integer, String> brand : brandList.entrySet()) {
                if (filter.equals(String.valueOf(brand.getKey())) && value.equals(brand.getValue())) {
                    deviceList = deviceDAO.getListDeviceByBrandID(brand.getKey());
                    break;
                }
            }
            for (Map.Entry<Integer, String> warehouse : warehouseList.entrySet()) {
                if (filter.equals(String.valueOf(warehouse.getKey())) && value.equals(warehouse.getValue())) {
                    deviceList = deviceDAO.getListDeviceByWarehouseID(warehouse.getKey());
                    break;
                }
            }
            if (deviceList.size() != 0) {
                for (int i = 0; i < descriptionList.size(); i++) {
                    List<String> listDescriptionDetail = detailDAO.getListDescriptionDetail(descriptionList.get(i));
                    session.setAttribute(descriptionList.get(i), listDescriptionDetail);
                }

                session.setAttribute("LIST_DESCRIPTION", descriptionList);
                session.setAttribute("LIST_DEVICE", deviceList);
                session.setAttribute("LIST_BRAND", brandList);
                session.setAttribute("LIST_CATEGORY", categoryList);
                session.setAttribute("LIST_WAREHOUSE", warehouseList);
                url = SUCCESS;
            } else {
                request.setAttribute("ERROR", "No Result");
            }
        } catch (Exception e) {
            System.out.println("" + e.toString());
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