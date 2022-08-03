/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package quanghung.controller;

import DLC.MessageSpecified;
import duonght.dao.AccountDao;
import duonght.dto.Account;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import manhcuong.request.requestDAO;
import manhcuong.request.requestDTO;

/**
 *
 * @author Admin
 */
public class LoadAllRequestManagement extends HttpServlet {

    private final String ALL_REQUEST = "viewAllRequestManagement.jsp";
    private final String PROCESSING_REQUEST = "requeststaff.jsp";
    private final String APPROVED_REQUEST = "approvedrequeststaff.jsp";
    private final String SUCCESSFULL_REQUEST = "successfulrequeststaff.jsp";
    private final String RETURNED_REQUEST = "returnedrequeststaff.jsp";
    private final String CANCEL_REQUEST = "cancelrequeststaff.jsp";
    private final String ERROR = "viewAllRequestManagement.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            if (action.equals("LoadAllRequestManager")) {
                requestDAO o_dao = new requestDAO();

                List<requestDTO> list = new ArrayList<>();
                /*for (Account user : users){
                list.addAll(o_dao.getOrderByUserID(user.getUserID(), true));
                list.addAll(o_dao.getOrderByUserID(user.getUserID(), false));
            }*/

                ///mod
                list.addAll(o_dao.getOrders(true));
                list.addAll(o_dao.getOrders(false));
                //end mod

                Collections.sort(list);
                if (!list.isEmpty()) {
                    request.setAttribute("LIST_REQUEST", list);
                    //request.setAttribute("LIST_USER", users);
                    url = ALL_REQUEST;
                } else {
                    MessageSpecified message = new MessageSpecified(null, "Empty", "Don't have any request!");
                    request.setAttribute("ERROR_MESSAGE", message);
                }
            } else if (action.equals("LoadProcessingRequest")) {
                requestDAO o_dao = new requestDAO();
                List<requestDTO> list = new ArrayList<>();
                list.addAll(o_dao.getRequestBaseOnStatusDetail(true, "Waiting..."));
                list.addAll(o_dao.getRequestBaseOnStatusDetail(false, "Waiting..."));
                Collections.sort(list);
                if (!list.isEmpty()) {
                    request.setAttribute("LIST_PROCESSING_REQUEST", list);
                    url = PROCESSING_REQUEST;
                } else {
                    MessageSpecified message = new MessageSpecified(null, "Empty", "Don't have any processing request!");
                    request.setAttribute("ERROR_MESSAGE", message);
                    url = PROCESSING_REQUEST;
                }

            } else if (action.equals("LoadApprovedRequest")) {
                requestDAO o_dao = new requestDAO();
                List<requestDTO> list = new ArrayList<>();
                list.addAll(o_dao.getRequestBaseOnDetailStatus(true, "approve"));
                list.addAll(o_dao.getRequestBaseOnDetailStatus(false, "approve"));
                Collections.sort(list);
                if (!list.isEmpty()) {
                    request.setAttribute("LIST_APPROVED_REQUEST", list);
                    url = APPROVED_REQUEST;
                } else {
                    MessageSpecified message = new MessageSpecified(null, "Empty", "Don't have any processing request!");
                    request.setAttribute("ERROR_MESSAGE", message);
                    url = APPROVED_REQUEST;
                }
            } else if (action.equals("LoadSuccessRequest")) {
                requestDAO o_dao = new requestDAO();
                List<requestDTO> list = new ArrayList<>();
                list.addAll(o_dao.getRequestBaseOnDetailStatus(true, "Received"));
                list.addAll(o_dao.getRequestBaseOnDetailStatus(false, "Received"));
                Collections.sort(list);
                if (!list.isEmpty()) {
                    request.setAttribute("LIST_SUCCESSFUL_REQUEST", list);
                    url = SUCCESSFULL_REQUEST;
                } else {
                    MessageSpecified message = new MessageSpecified(null, "Empty", "Don't have any returned request!");
                    request.setAttribute("ERROR_MESSAGE", message);
                    url = SUCCESSFULL_REQUEST;
                }
            } else if (action.equals("LoadReturnedRequest")) {
                requestDAO o_dao = new requestDAO();
                List<requestDTO> list = new ArrayList<>();
                list.addAll(o_dao.getRequestBaseOnStatusDetail(true, "Returned"));
                list.addAll(o_dao.getRequestBaseOnStatusDetail(false, "Returned"));
                Collections.sort(list);
                if (!list.isEmpty()) {
                    request.setAttribute("LIST_RETURNED_REQUEST", list);
                    url = RETURNED_REQUEST;
                } else {
                    MessageSpecified message = new MessageSpecified(null, "Empty", "Don't have any returned request!");
                    request.setAttribute("ERROR_MESSAGE", message);
                    url = RETURNED_REQUEST;
                }
            } else if (action.equals("LoadCanceledRequest")) {
                requestDAO o_dao = new requestDAO();
                List<requestDTO> list = new ArrayList<>();
                list.addAll(o_dao.getRequestBaseOnStatusDetail(true, "cancel"));
                list.addAll(o_dao.getRequestBaseOnStatusDetail(false, "cancel"));
                Collections.sort(list);
                if (!list.isEmpty()) {
                    request.setAttribute("LIST_CANCEL_REQUEST", list);
                    url = CANCEL_REQUEST;
                } else {
                    MessageSpecified message = new MessageSpecified(null, "Empty", "Don't have any processing request!");
                    request.setAttribute("ERROR_MESSAGE", message);
                    url = CANCEL_REQUEST;
                }
            } else if (action.equals("SearchRequestByUserInAllRequest")) {
                requestDAO o_dao = new requestDAO();
                String name = request.getParameter("SearchByUserName");
                List<requestDTO> list1 = new ArrayList<>();
                List<requestDTO> list2 = new ArrayList<>();
                list1.addAll(o_dao.getOrders(true));
                list1.addAll(o_dao.getOrders(false));
                if (name == null) {
                    request.setAttribute("LIST_REQUEST", list1);
                    url = ALL_REQUEST;
                } else {
                    for (int i = 0; i < list1.size(); i++) {
                        if (list1.get(i).getUser().getUserID().toLowerCase().contains(name)) {
                            list2.add(list1.get(i));
                        }
                    }
                    Collections.sort(list2);
                    if (!list2.isEmpty()) {
                        request.setAttribute("LIST_REQUEST", list2);
                        url = ALL_REQUEST;
                    } else {
                        MessageSpecified message = new MessageSpecified(null, "Empty", "Don't have any request!");
                        request.setAttribute("ERROR_MESSAGE", message);
                        url = ALL_REQUEST;
                    }
                }
            } else if (action.equals("SearchRequestByDateInAllRequest")) {
                requestDAO o_dao = new requestDAO();
                String Sdate1 = request.getParameter("SearchByDate1");
                String Sdate2 = request.getParameter("SearchByDate2");
                java.util.Date date1 = null;
                java.util.Date date2 = null;
                if(Sdate1.isEmpty() == false && Sdate2.isEmpty() == false){
                    date1 = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH).parse(Sdate1);
                    date2 = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH).parse(Sdate2);
                }else if(Sdate1.isEmpty() == true && Sdate2.isEmpty() == false){
                    date1 = null;
                    date2 = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH).parse(Sdate2);
                }else if(Sdate1.isEmpty() == false && Sdate2.isEmpty() == true){
                    date1 = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH).parse(Sdate1);
                    date2 = null;
                }
                
                
                //Date date1 = (Date) dateFormat.parse(request.getParameter("SearchByDate1"));
                //Date date2 = (Date) dateFormat.parse(request.getParameter("SearchByDate2"));
                List<requestDTO> list1 = new ArrayList<>();
                List<requestDTO> list2 = new ArrayList<>();
                list1.addAll(o_dao.getOrders(true));
                list1.addAll(o_dao.getOrders(false));
                if (date1 == null && date2 == null) {
                    request.setAttribute("LIST_REQUEST", list1);
                    url = ALL_REQUEST;
                } else {
                    if (date1 != null && date2 == null) {
                        for (int i = 0; i < list1.size(); i++) {
                            if (list1.get(i).getRequestDate().after(date1) || list1.get(i).getRequestDate().equals(date1)) {
                                list2.add(list1.get(i));
                            }
                        }
                    } else if (date1 == null && date2 != null) {
                        for (int i = 0; i < list1.size(); i++) {
                            if (list1.get(i).getRequestDate().before(date2) || list1.get(i).getRequestDate().equals(date2)) {
                                list2.add(list1.get(i));
                            }
                        }
                    } else if (date1 != null && date2 != null) {
                        for (int i = 0; i < list1.size(); i++) {
                            if (list1.get(i).getRequestDate().after(date1) && list1.get(i).getRequestDate().before(date2) || list1.get(i).getRequestDate().equals(date1) || list1.get(i).getRequestDate().equals(date2)) {
                                list2.add(list1.get(i));
                            }
                        }
                    }

                    Collections.sort(list2);
                    if (!list2.isEmpty()) {
                        request.setAttribute("LIST_REQUEST", list2);
                        url = ALL_REQUEST;
                    } else {
                        MessageSpecified message = new MessageSpecified(null, "Empty", "Don't have any request!");
                        request.setAttribute("ERROR_MESSAGE", message);
                        url = ALL_REQUEST;
                    }

                }

            }else if(action.equals("SearchRequestByUserInProcessRequest")){
                requestDAO o_dao = new requestDAO();
                String name = request.getParameter("SearchByUserName");
                List<requestDTO> list1 = new ArrayList<>();
                List<requestDTO> list2 = new ArrayList<>();
                //ADD HERE
                list1.addAll(o_dao.getRequestBaseOnStatusDetail(true, "Waiting..."));
                list1.addAll(o_dao.getRequestBaseOnStatusDetail(false, "Waiting..."));
                //ADD HERE
                if (name.isEmpty()) {
                    request.setAttribute("LIST_PROCESSING_REQUEST", list1);
                    url = PROCESSING_REQUEST;
                } else {
                    for (int i = 0; i < list1.size(); i++) {
                        if (list1.get(i).getUser().getUserID().toLowerCase().contains(name)) {
                            list2.add(list1.get(i));
                        }
                    }
                    Collections.sort(list2);
                    if (!list2.isEmpty()) {
                        request.setAttribute("LIST_PROCESSING_REQUEST", list2);
                        url = PROCESSING_REQUEST;
                    } else {
                        MessageSpecified message = new MessageSpecified(null, "Empty", "Don't have any request!");
                        request.setAttribute("ERROR_MESSAGE", message);
                        url = PROCESSING_REQUEST;
                    }
                }
            }else if(action.equals("SearchRequestByDateInProcessRequest")){
                requestDAO o_dao = new requestDAO();
                String Sdate1 = request.getParameter("SearchByDate1");
                String Sdate2 = request.getParameter("SearchByDate2");
                java.util.Date date1 = null;
                java.util.Date date2 = null;
                if(Sdate1.isEmpty() == false && Sdate2.isEmpty() == false){
                    date1 = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH).parse(Sdate1);
                    date2 = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH).parse(Sdate2);
                }else if(Sdate1.isEmpty() == true && Sdate2.isEmpty() == false){
                    date1 = null;
                    date2 = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH).parse(Sdate2);
                }else if(Sdate1.isEmpty() == false && Sdate2.isEmpty() == true){
                    date1 = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH).parse(Sdate1);
                    date2 = null;
                }
                
                
                
                List<requestDTO> list1 = new ArrayList<>();
                List<requestDTO> list2 = new ArrayList<>();
                //ADD HERE
                list1.addAll(o_dao.getRequestBaseOnStatusDetail(true, "Waiting..."));
                list1.addAll(o_dao.getRequestBaseOnStatusDetail(false, "Waiting..."));
                //ADD HERE
                if (date1 == null && date2 == null) {
                    request.setAttribute("LIST_PROCESSING_REQUEST", list1);
                    url = PROCESSING_REQUEST;
                } else {
                    if (date1 != null && date2 == null) {
                        for (int i = 0; i < list1.size(); i++) {
                            if (list1.get(i).getRequestDate().after(date1) || list1.get(i).getRequestDate().equals(date1)) {
                                list2.add(list1.get(i));
                            }
                        }
                    } else if (date1 == null && date2 != null) {
                        for (int i = 0; i < list1.size(); i++) {
                            if (list1.get(i).getRequestDate().before(date2) || list1.get(i).getRequestDate().equals(date2)) {
                                list2.add(list1.get(i));
                            }
                        }
                    } else if (date1 != null && date2 != null) {
                        for (int i = 0; i < list1.size(); i++) {
                            if (list1.get(i).getRequestDate().after(date1) && list1.get(i).getRequestDate().before(date2) || list1.get(i).getRequestDate().equals(date1) || list1.get(i).getRequestDate().equals(date2)) {
                                list2.add(list1.get(i));
                            }
                        }
                    }

                    Collections.sort(list2);
                    if (!list2.isEmpty()) {
                        request.setAttribute("LIST_PROCESSING_REQUEST", list2);
                        url = PROCESSING_REQUEST;
                    } else {
                        MessageSpecified message = new MessageSpecified(null, "Empty", "Don't have any request!");
                        request.setAttribute("ERROR_MESSAGE", message);
                        url = PROCESSING_REQUEST;
                    }

                }
            }else if(action.equals("SearchRequestByUserInApproveRequest")){
                requestDAO o_dao = new requestDAO();
                String name = request.getParameter("SearchByUserName");
                List<requestDTO> list1 = new ArrayList<>();
                List<requestDTO> list2 = new ArrayList<>();
                //ADD HERE
                list1.addAll(o_dao.getRequestBaseOnDetailStatus(true, "approve"));
                list1.addAll(o_dao.getRequestBaseOnDetailStatus(false, "approve"));
                //ADD HERE
                if (name.isEmpty()) {
                    request.setAttribute("LIST_APPROVED_REQUEST", list1);
                    url = APPROVED_REQUEST;
                } else {
                    for (int i = 0; i < list1.size(); i++) {
                        if (list1.get(i).getUser().getUserID().toLowerCase().contains(name)) {
                            list2.add(list1.get(i));
                        }
                    }
                    Collections.sort(list2);
                    if (!list2.isEmpty()) {
                        request.setAttribute("LIST_APPROVED_REQUEST", list2);
                        url = APPROVED_REQUEST;
                    } else {
                        MessageSpecified message = new MessageSpecified(null, "Empty", "Don't have any request!");
                        request.setAttribute("ERROR_MESSAGE", message);
                        url = APPROVED_REQUEST;
                    }
                }
            }else if(action.equals("SearchRequestByDateInApproveRequest")){
                requestDAO o_dao = new requestDAO();
                String Sdate1 = request.getParameter("SearchByDate1");
                String Sdate2 = request.getParameter("SearchByDate2");
                java.util.Date date1 = null;
                java.util.Date date2 = null;
                if(Sdate1.isEmpty() == false && Sdate2.isEmpty() == false){
                    date1 = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH).parse(Sdate1);
                    date2 = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH).parse(Sdate2);
                }else if(Sdate1.isEmpty() == true && Sdate2.isEmpty() == false){
                    date1 = null;
                    date2 = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH).parse(Sdate2);
                }else if(Sdate1.isEmpty() == false && Sdate2.isEmpty() == true){
                    date1 = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH).parse(Sdate1);
                    date2 = null;
                }
                
                
                
                List<requestDTO> list1 = new ArrayList<>();
                List<requestDTO> list2 = new ArrayList<>();
                //ADD HERE
                list1.addAll(o_dao.getRequestBaseOnDetailStatus(true, "approve"));
                list1.addAll(o_dao.getRequestBaseOnDetailStatus(false, "approve"));
                //ADD HERE
                if (date1 == null && date2 == null) {
                    request.setAttribute("LIST_APPROVED_REQUEST", list1);
                    url = APPROVED_REQUEST;
                } else {
                    if (date1 != null && date2 == null) {
                        for (int i = 0; i < list1.size(); i++) {
                            if (list1.get(i).getRequestDate().after(date1) || list1.get(i).getRequestDate().equals(date1)) {
                                list2.add(list1.get(i));
                            }
                        }
                    } else if (date1 == null && date2 != null) {
                        for (int i = 0; i < list1.size(); i++) {
                            if (list1.get(i).getRequestDate().before(date2) || list1.get(i).getRequestDate().equals(date2)) {
                                list2.add(list1.get(i));
                            }
                        }
                    } else if (date1 != null && date2 != null) {
                        for (int i = 0; i < list1.size(); i++) {
                            if (list1.get(i).getRequestDate().after(date1) && list1.get(i).getRequestDate().before(date2) || list1.get(i).getRequestDate().equals(date1) || list1.get(i).getRequestDate().equals(date2)) {
                                list2.add(list1.get(i));
                            }
                        }
                    }

                    Collections.sort(list2);
                    if (!list2.isEmpty()) {
                        request.setAttribute("LIST_APPROVED_REQUEST", list2);
                        url = APPROVED_REQUEST;
                    } else {
                        MessageSpecified message = new MessageSpecified(null, "Empty", "Don't have any request!");
                        request.setAttribute("ERROR_MESSAGE", message);
                        url = APPROVED_REQUEST;
                    }

                }
            }else if(action.equals("SearchRequestByUserInSuccessfulRequest")){
                requestDAO o_dao = new requestDAO();
                String name = request.getParameter("SearchByUserName");
                List<requestDTO> list1 = new ArrayList<>();
                List<requestDTO> list2 = new ArrayList<>();
                //ADD HERE
                list1.addAll(o_dao.getRequestBaseOnDetailStatus(true, "Received"));
                list1.addAll(o_dao.getRequestBaseOnDetailStatus(false, "Received"));
                //ADD HERE
                if (name.isEmpty()) {
                    request.setAttribute("LIST_SUCCESSFUL_REQUEST", list1);
                    url = SUCCESSFULL_REQUEST;
                } else {
                    for (int i = 0; i < list1.size(); i++) {
                        if (list1.get(i).getUser().getUserID().toLowerCase().contains(name)) {
                            list2.add(list1.get(i));
                        }
                    }
                    Collections.sort(list2);
                    if (!list2.isEmpty()) {
                        request.setAttribute("LIST_SUCCESSFUL_REQUEST", list2);
                        url = SUCCESSFULL_REQUEST;
                    } else {
                        MessageSpecified message = new MessageSpecified(null, "Empty", "Don't have any request!");
                        request.setAttribute("ERROR_MESSAGE", message);
                        url = SUCCESSFULL_REQUEST;
                    }
                }
            }else if(action.equals("SearchRequestByDateInSuccessfullRequest")){
                requestDAO o_dao = new requestDAO();
                String Sdate1 = request.getParameter("SearchByDate1");
                String Sdate2 = request.getParameter("SearchByDate2");
                java.util.Date date1 = null;
                java.util.Date date2 = null;
                if(Sdate1.isEmpty() == false && Sdate2.isEmpty() == false){
                    date1 = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH).parse(Sdate1);
                    date2 = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH).parse(Sdate2);
                }else if(Sdate1.isEmpty() == true && Sdate2.isEmpty() == false){
                    date1 = null;
                    date2 = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH).parse(Sdate2);
                }else if(Sdate1.isEmpty() == false && Sdate2.isEmpty() == true){
                    date1 = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH).parse(Sdate1);
                    date2 = null;
                }
                
                
                
                List<requestDTO> list1 = new ArrayList<>();
                List<requestDTO> list2 = new ArrayList<>();
                //ADD HERE
                list1.addAll(o_dao.getRequestBaseOnDetailStatus(true, "Received"));
                list1.addAll(o_dao.getRequestBaseOnDetailStatus(false, "Received"));
                //ADD HERE
                if (date1 == null && date2 == null) {
                    request.setAttribute("LIST_SUCCESSFUL_REQUEST", list1);
                    url = SUCCESSFULL_REQUEST;
                } else {
                    if (date1 != null && date2 == null) {
                        for (int i = 0; i < list1.size(); i++) {
                            if (list1.get(i).getRequestDate().after(date1) || list1.get(i).getRequestDate().equals(date1)) {
                                list2.add(list1.get(i));
                            }
                        }
                    } else if (date1 == null && date2 != null) {
                        for (int i = 0; i < list1.size(); i++) {
                            if (list1.get(i).getRequestDate().before(date2) || list1.get(i).getRequestDate().equals(date2)) {
                                list2.add(list1.get(i));
                            }
                        }
                    } else if (date1 != null && date2 != null) {
                        for (int i = 0; i < list1.size(); i++) {
                            if (list1.get(i).getRequestDate().after(date1) && list1.get(i).getRequestDate().before(date2) || list1.get(i).getRequestDate().equals(date1) || list1.get(i).getRequestDate().equals(date2)) {
                                list2.add(list1.get(i));
                            }
                        }
                    }

                    Collections.sort(list2);
                    if (!list2.isEmpty()) {
                        request.setAttribute("LIST_SUCCESSFUL_REQUEST", list2);
                        url = SUCCESSFULL_REQUEST;
                    } else {
                        MessageSpecified message = new MessageSpecified(null, "Empty", "Don't have any request!");
                        request.setAttribute("ERROR_MESSAGE", message);
                        url = SUCCESSFULL_REQUEST;
                    }

                }
            }else if(action.equals("SearchRequestByUserInReturnedRequest")){
                requestDAO o_dao = new requestDAO();
                String name = request.getParameter("SearchByUserName");
                List<requestDTO> list1 = new ArrayList<>();
                List<requestDTO> list2 = new ArrayList<>();
                //ADD HERE
                list1.addAll(o_dao.getRequestBaseOnStatusDetail(true, "Returned"));
                list1.addAll(o_dao.getRequestBaseOnDetailStatus(false, "Returned"));
                //ADD HERE
                if (name.isEmpty()) {
                    request.setAttribute("LIST_RETURNED_REQUEST", list1);
                    url = RETURNED_REQUEST;
                } else {
                    for (int i = 0; i < list1.size(); i++) {
                        if (list1.get(i).getUser().getUserID().toLowerCase().contains(name)) {
                            list2.add(list1.get(i));
                        }
                    }
                    Collections.sort(list2);
                    if (!list2.isEmpty()) {
                        request.setAttribute("LIST_RETURNED_REQUEST", list2);
                        url = RETURNED_REQUEST;
                    } else {
                        MessageSpecified message = new MessageSpecified(null, "Empty", "Don't have any request!");
                        request.setAttribute("ERROR_MESSAGE", message);
                         url = RETURNED_REQUEST;
                    }
                }
            }else if(action.equals("SearchRequestByDateInReturnedRequest")){
                requestDAO o_dao = new requestDAO();
                String Sdate1 = request.getParameter("SearchByDate1");
                String Sdate2 = request.getParameter("SearchByDate2");
                java.util.Date date1 = null;
                java.util.Date date2 = null;
                if(Sdate1.isEmpty() == false && Sdate2.isEmpty() == false){
                    date1 = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH).parse(Sdate1);
                    date2 = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH).parse(Sdate2);
                }else if(Sdate1.isEmpty() == true && Sdate2.isEmpty() == false){
                    date1 = null;
                    date2 = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH).parse(Sdate2);
                }else if(Sdate1.isEmpty() == false && Sdate2.isEmpty() == true){
                    date1 = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH).parse(Sdate1);
                    date2 = null;
                }
                
                
                
                List<requestDTO> list1 = new ArrayList<>();
                List<requestDTO> list2 = new ArrayList<>();
                //ADD HERE
                list1.addAll(o_dao.getRequestBaseOnStatusDetail(true, "Returned"));
                list1.addAll(o_dao.getRequestBaseOnStatusDetail(false, "Returned"));
                //ADD HERE
                if (date1 == null && date2 == null) {
                    request.setAttribute("LIST_RETURNED_REQUEST", list1);
                    url = RETURNED_REQUEST;
                } else {
                    if (date1 != null && date2 == null) {
                        for (int i = 0; i < list1.size(); i++) {
                            if (list1.get(i).getRequestDate().after(date1) || list1.get(i).getRequestDate().equals(date1)) {
                                list2.add(list1.get(i));
                            }
                        }
                    } else if (date1 == null && date2 != null) {
                        for (int i = 0; i < list1.size(); i++) {
                            if (list1.get(i).getRequestDate().before(date2) || list1.get(i).getRequestDate().equals(date2)) {
                                list2.add(list1.get(i));
                            }
                        }
                    } else if (date1 != null && date2 != null) {
                        for (int i = 0; i < list1.size(); i++) {
                            if (list1.get(i).getRequestDate().after(date1) && list1.get(i).getRequestDate().before(date2) || list1.get(i).getRequestDate().equals(date1) || list1.get(i).getRequestDate().equals(date2)) {
                                list2.add(list1.get(i));
                            }
                        }
                    }

                    Collections.sort(list2);
                    if (!list2.isEmpty()) {
                        request.setAttribute("LIST_RETURNED_REQUEST", list2);
                        url = RETURNED_REQUEST;
                    } else {
                        MessageSpecified message = new MessageSpecified(null, "Empty", "Don't have any request!");
                        request.setAttribute("ERROR_MESSAGE", message);
                        url = RETURNED_REQUEST;
                    }

                }
            }else if(action.equals("SearchRequestByUserInCancelRequest")){
                requestDAO o_dao = new requestDAO();
                String name = request.getParameter("SearchByUserName");
                List<requestDTO> list1 = new ArrayList<>();
                List<requestDTO> list2 = new ArrayList<>();
                //ADD HERE
                list1.addAll(o_dao.getRequestBaseOnStatusDetail(true, "cancel"));
                list1.addAll(o_dao.getRequestBaseOnStatusDetail(false, "cancel"));
                //ADD HERE
                if (name.isEmpty()) {
                    request.setAttribute("LIST_CANCEL_REQUEST", list1);
                    url = CANCEL_REQUEST;
                } else {
                    for (int i = 0; i < list1.size(); i++) {
                        if (list1.get(i).getUser().getUserID().toLowerCase().contains(name)) {
                            list2.add(list1.get(i));
                        }
                    }
                    Collections.sort(list2);
                    if (!list2.isEmpty()) {
                        request.setAttribute("LIST_CANCEL_REQUEST", list2);
                        url = CANCEL_REQUEST;
                    } else {
                        MessageSpecified message = new MessageSpecified(null, "Empty", "Don't have any request!");
                        request.setAttribute("ERROR_MESSAGE", message);
                        url = CANCEL_REQUEST;
                    }
                }
            }else if(action.equals("SearchRequestByDateInCancelRequest")){
                requestDAO o_dao = new requestDAO();
                String Sdate1 = request.getParameter("SearchByDate1");
                String Sdate2 = request.getParameter("SearchByDate2");
                java.util.Date date1 = null;
                java.util.Date date2 = null;
                if(Sdate1.isEmpty() == false && Sdate2.isEmpty() == false){
                    date1 = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH).parse(Sdate1);
                    date2 = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH).parse(Sdate2);
                }else if(Sdate1.isEmpty() == true && Sdate2.isEmpty() == false){
                    date1 = null;
                    date2 = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH).parse(Sdate2);
                }else if(Sdate1.isEmpty() == false && Sdate2.isEmpty() == true){
                    date1 = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH).parse(Sdate1);
                    date2 = null;
                }
                
                List<requestDTO> list1 = new ArrayList<>();
                List<requestDTO> list2 = new ArrayList<>();
                //ADD HERE
                list1.addAll(o_dao.getRequestBaseOnStatusDetail(true, "cancel"));
                list1.addAll(o_dao.getRequestBaseOnStatusDetail(true, "cancel"));
                //ADD HERE
                if (date1 == null && date2 == null) {
                    request.setAttribute("LIST_CANCEL_REQUEST", list1);
                    url = CANCEL_REQUEST;
                } else {
                    if (date1 != null && date2 == null) {
                        for (int i = 0; i < list1.size(); i++) {
                            if (list1.get(i).getRequestDate().after(date1) || list1.get(i).getRequestDate().equals(date1)) {
                                list2.add(list1.get(i));
                            }
                        }
                    } else if (date1 == null && date2 != null) {
                        for (int i = 0; i < list1.size(); i++) {
                            if (list1.get(i).getRequestDate().before(date2) || list1.get(i).getRequestDate().equals(date2)) {
                                list2.add(list1.get(i));
                            }
                        }
                    } else if (date1 != null && date2 != null) {
                        for (int i = 0; i < list1.size(); i++) {
                            if (list1.get(i).getRequestDate().after(date1) && list1.get(i).getRequestDate().before(date2) || list1.get(i).getRequestDate().equals(date1) || list1.get(i).getRequestDate().equals(date2)) {
                                list2.add(list1.get(i));
                            }
                        }
                    }

                    Collections.sort(list2);
                    if (!list2.isEmpty()) {
                        request.setAttribute("LIST_CANCEL_REQUEST", list2);
                        url = CANCEL_REQUEST;
                    } else {
                        MessageSpecified message = new MessageSpecified(null, "Empty", "Don't have any request!");
                        request.setAttribute("ERROR_MESSAGE", message);
                        url = CANCEL_REQUEST;
                    }

                }
            }

        } catch (Exception e) {
            log("Error at LoadOrderController: " + e.toString());
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