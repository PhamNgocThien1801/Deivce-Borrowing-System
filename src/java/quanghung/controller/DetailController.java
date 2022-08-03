/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanghung.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import quanghung.description.DescriptionDAO;
import quanghung.descriptionDetail.DescriptionDetailDAO;
import quanghung.device.DeviceDTO;

/**
 *
 * @author duong
 */
public class DetailController extends HttpServlet {

    private static final String ERROR = "MainController?search=&action=HomeSearchDevice";
    private static final String SUCCESS = "deviceDetail.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            int deviceID = Integer.parseInt(request.getParameter("deviceID"));
            String deviceName = request.getParameter("deviceName");
            String image = request.getParameter("url");
            String warehouseName = request.getParameter("warehouseName");
            int warehouseID = Integer.parseInt(request.getParameter("warehouseID"));;
            int brandID = Integer.parseInt(request.getParameter("brandID"));;
            String cateID = request.getParameter("cateID");;
            String cateName = request.getParameter("cateName");;
            String brandName = request.getParameter("brandName");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            int deposit = Integer.parseInt(request.getParameter("deposit"));
            DeviceDTO device = new DeviceDTO(deviceID, deviceName, image, warehouseID, warehouseName, brandID, brandName, quantity, cateID, cateName, deposit, true);
            DescriptionDAO descriptionDAO = new DescriptionDAO();
            DescriptionDetailDAO detailDAO = new DescriptionDetailDAO();
            Map<Integer, String> detailList = detailDAO.getListDescriptionDetailBasedOnDevice(deviceID);
            detailList.entrySet();
            List<String> descriptionList = new ArrayList<String>();
            for (Map.Entry<Integer, String> detail : detailList.entrySet()) {
                descriptionList.add(String.valueOf(descriptionDAO.getDescriptionName(detail.getKey())));
            }
            url = SUCCESS;
            request.setAttribute("DEVICE", device);
            request.setAttribute("DESCRIPTION_LIST", descriptionList);
            request.setAttribute("DETAIL_LIST", detailList);
        } catch (Exception e) {
            System.out.println("");
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