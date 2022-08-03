/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanghung.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
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
import quanghung.descriptionDetail.DescriptionDetailDAO;
import quanghung.device.DeviceDAO;
import quanghung.device.DeviceDTO;
import quanghung.warehouse.WarehouseDAO;

public class FilterDeviceController extends HttpServlet {

    private static final String ERROR = "devicePage2.jsp";
    private static final String SUCCESS = "devicePage2.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            DeviceDAO deviceDAO = new DeviceDAO();
            CategoryDAO categoryDAO = new CategoryDAO();
            BrandDAO brandDao = new BrandDAO();
            WarehouseDAO warehouseDAO = new WarehouseDAO();
            DescriptionDAO descriptionDAO = new DescriptionDAO();
            DescriptionDetailDAO detailDAO = new DescriptionDetailDAO();
            List<DeviceDTO> deviceList = deviceDAO.getListDeviceByName("");
            Map<String, String> categoryList = categoryDAO.getCategory();
            Map<Integer, String> brandList = brandDao.getListBrand();
            List<String> descriptionList = descriptionDAO.getListDescription();
            Map<Integer, String> warehouseList = warehouseDAO.getWarehouse();
            String filterBrand = request.getParameter("filterBrand");
            String filterWarehouse = request.getParameter("filterWarehouse");
            String filterCategory = request.getParameter("filterCategory");
            Map<String, String> filterDetail = new HashMap<>();
            boolean check = true;
            for (int i = 1; i <= descriptionList.size(); i++) {
                String des = "desName" + String.valueOf(i);
                String detail = "detailName" + String.valueOf(i);
                String dess = request.getParameter(des);
                String detaill = request.getParameter(detail);
                if (detaill != "") {
                    filterDetail.put(dess, detaill);
                }
            }
            filterDetail.entrySet();
            for (Map.Entry<String, String> detail : filterDetail.entrySet()) {
                if (detail.getValue() != "") {
                    List<DeviceDTO> a = new ArrayList<>();
                    for (int i = 0; i < deviceList.size(); i++) {
                        if (deviceDAO.getExactlyDetailName(detail.getValue(), detail.getKey(), deviceList.get(i).getDeviceID()).equals(detail.getValue())) {
                            a.add(deviceList.get(i));
                        }
                    }
                    if (a.size() != 0) {
                        deviceList = a;
                        check = true;
                    } else {
                        check = false;
                    }
                }
            }
            if (filterBrand != "") {
                List<DeviceDTO> a = new ArrayList<>();
                for (int i = 0; i < deviceList.size(); i++) {
                    if (deviceList.get(i).getBrandID() == Integer.parseInt(filterBrand)) {
                        a.add(deviceList.get(i));
                    }
                }
                if (a.size() != 0) {
                    deviceList = a;
                    check = true;
                } else {
                    check = false;
                }
            }
            if (filterWarehouse != "") {
                List<DeviceDTO> a = new ArrayList<>();
                for (int i = 0; i < deviceList.size(); i++) {
                    if (deviceList.get(i).getWarehouseID() == Integer.parseInt(filterWarehouse)) {
                        a.add(deviceList.get(i));
                    }
                }
                if (a.size() != 0) {
                    deviceList = a;
                    check = true;
                } else {
                    check = false;
                }
            }
            if (filterCategory != "") {
                List<DeviceDTO> a = new ArrayList<>();
                for (int i = 0; i < deviceList.size(); i++) {
                    if (deviceList.get(i).getWarehouseID() == Integer.parseInt(filterCategory)) {
                        a.add(deviceList.get(i));
                    }
                }
                if (a.size() != 0) {
                    deviceList = a;
                    check = true;
                } else {
                    check = false;
                }
            }
            if (check == false) {
                deviceList.clear();
            }
            if (deviceList.size() != 0) {
                for (int i = 0; i < descriptionList.size(); i++) {
                    List<String> listDescriptionDetail = detailDAO.getListDescriptionDetail(descriptionList.get(i));
                    session.setAttribute(descriptionList.get(i), listDescriptionDetail);
                }

                session.setAttribute("LIST_DESCRIPTION", descriptionList);
                request.setAttribute("LIST_DEVICE_FILTER", deviceList);
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