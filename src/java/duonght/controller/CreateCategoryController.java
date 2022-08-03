package duonght.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import duonght.category.CategoryDAO;
import duonght.category.CategoryDTO;
import duonght.category.CategoryError;
import duonght.description.DescriptionDAO;
import quanghung.brand.BrandDAO;

public class CreateCategoryController extends HttpServlet {

    private static final String ERROR = "MainController?action=GetListCategory";
    private static final String SUCCESS = "MainController?action=GetListCategory";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        CategoryError categoryError = new CategoryError();
        try {
            boolean checkValidation = true;
            String cateID = request.getParameter("textCateID");
            String cateName = request.getParameter("textCateName");
            String description = request.getParameter("textDescription");
            String brandName = request.getParameter("textBrandName");
            boolean checkDuplicate = CategoryDAO.checkDuplicate(cateID);
            if (checkDuplicate) {
                boolean checkStatus = CategoryDAO.checkStatus(cateID);
                if (checkStatus) {
                    categoryError.setCateIDError("Duplicate CateID");
                    checkValidation = false;
                } else {
                    boolean check = true;
                    if (cateName.length() > 50) {
                        categoryError.setCateNameError("CateName must not exceed 50 characters");
                        check = false;
                    }
                    if (description.length() > 50) {
                        categoryError.setDescriptionName("Description must not exceed 50 characters");
                        check = false;
                    }
                    if (brandName.length() > 50) {
                        categoryError.setBrandName("Brand Name must not exceed 50 characters");
                        check = false;
                    }
                    if (check) {
                        boolean isRenew = CategoryDAO.renewCategory(cateID);
                        if (isRenew) {
                            CategoryDAO.updateCategory(cateName, cateID);
                            boolean isRenewDes = DescriptionDAO.renewDescription(description, cateID);
                            if (!isRenewDes) {
                                DescriptionDAO.createDescription(cateID, description);
                            }
                            boolean isRenewBrand = BrandDAO.renewBrand(brandName, cateID);
                            if (!isRenewBrand) {
                                BrandDAO.createBrand(brandName, cateID);
                            }
                            request.setAttribute("MESSAGE", "Insert Successfully! Now You Can Edit More!");
                            url = SUCCESS;
                        } else {
                            request.setAttribute("MESSAGE", "Ops! Something Wrong. Try Again!");
                            url = ERROR;
                        }
                    }
                }
            } else {
                if (cateID.length() > 10) {
                    categoryError.setCateIDError("CateID must not exceed 10 characters");
                    checkValidation = false;
                }
                if (cateName.length() > 50) {
                    categoryError.setCateNameError("CateName must not exceed 50 characters");
                    checkValidation = false;
                }
                if (description.length() > 50) {
                    categoryError.setDescriptionName("Description must not exceed 50 characters");
                    checkValidation = false;
                }
                if (brandName.length() > 50) {
                    categoryError.setBrandName("Brand Name must not exceed 50 characters");
                    checkValidation = false;
                }
                if (checkValidation) {
                    CategoryDTO category = new CategoryDTO(cateID, cateName, true);
                    boolean isCreate = CategoryDAO.createCategory(category, description, brandName);
                    if (isCreate) {
                        request.setAttribute("MESSAGE", "Insert Successfully! Now You Can Edit More!");
                    } else {
                        request.setAttribute("MESSAGE", "Ops! Something Wrong. Try again!");
                    }
                    url = SUCCESS;
                } else {
                    request.setAttribute("CateError", categoryError);
                    url = ERROR;
                }
            }
        } catch (Exception e) {
            if (e.toString().contains("duplicate")) {
                categoryError.getCateIDError("Duplicate Cate ID");
            }
            log("Error at Create Category Controller: " + e.toString());
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
