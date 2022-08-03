package quanghung.controller;

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
    private static final String LOGOUT = "Logout";
    private static final String LOGOUT_CONTROLLER = "LogoutServlet";
    private static final String GET_LIST = "GetList";
    private static final String GET_LIST_CONTROLLER = "GetListController";
    private static final String INPUT_DEVICE_INFORMATION = "InputDeviceInfo";
    private static final String INPUT_DEVICE_INFORMATION_CONTROLLER = "InputDeviceInfoController";
    private static final String CREATE_DEVICE = "CreateDevice";
    private static final String CREATE_DEVICE_CONTROLLER = "CreateDeviceController";
    private static final String SEARCH_DEVICE = "SearchDevice";
    private static final String SEARCH_DEVICE_CONTROLLER = "SearchDeviceController";
    private static final String DELETE_DEVICE = "DeleteDevice";
    private static final String DELETE_DEVICE_CONTROLLER = "DeleteDeviceController";
    private static final String UPDATE_DEVICE = "UpdateDevice";
    private static final String UPDATE_DEVICE_CONTROLLER = "UpdateDeviceController";
    private static final String UPDATE_PROFILE = "UpdateProfile";
    private static final String UPDATE_PROFILE_CONTROLLER = "UpdateProfileServlet";
    private static final String UPDATE_DEVICE_DETAIL = "UpdateDeviceDetail";
    private static final String UPDATE_DEVICE_DETAIL_CONTROLLER = "UpdateDeviceDetailController";
    private static final String FIND_PRODUCT = "FindProduct";
    private static final String FIND_PRODUCT_CONTROLLER = "FindProductController";
    private static final String VIEW_ALL_REQUEST_MANAGER = "LoadAllRequestManager";
    private static final String VIEW_ALL_REQUEST_MANAGER_CONTROLLER = "LoadAllRequestManagement";
    private static final String VIEW_PROCESSING_REQUEST_MANAGER = "LoadProcessingRequest";
    private static final String VIEW_APPROVE_REQUEST_MANAGER = "LoadApprovedRequest";
    private static final String VIEW_SUCCESSFUL_REQUEST_MANAGER = "LoadSuccessRequest";
    private static final String VIEW_RETURNED_REQUEST_MANAGER = "LoadReturnedRequest";
    private static final String VIEW_CANCEL_REQUEST_MANAGER = "LoadCanceledRequest";
    private static final String CREATE_WAREHOUSE = "createWarehouse";
    private static final String CREATE_WAREHOUSE_CONTROLLER = "CreateWarehouseController";
    private static final String GET_LIST_WAREHOUSE = "GetListWarehouse";
    private static final String GET_LIST_WAREHOUSE_CONTROLLER = "GetListWarehouseController";
    private static final String DELETE_WAREHOUSE = "DeleteWarehouse";
    private static final String DELETE_WAREHOUSE_CONTROLLER = "DeleteWarehouseController";
    private static final String UPDATE_WAREHOUSE = "UpdateWarehouse";
    private static final String UPDATE_WAREHOUSE_CONTROLLER = "UpdateWarehouseController";
    private static final String SEARCH_WAREHOUSE = "SearchWarehouse";
    private static final String SEARCH_WAREHOUSE_CONTROLLER = "SearchWarehouseController";
    private static final String CREATE_CATEGORY = "CreateCategory";
    private static final String CREATE_CATEGORY_CONTROLLER = "CreateCategoryController";
    private static final String GET_LIST_CATEGORY = "GetListCategory";
    private static final String GET_LIST_CATEGORY_CONTROLLER = "GetListCategoryController";
    private static final String DELETE_CATEGORY = "DeleteCategory";
    private static final String DELETE_CATEGORY_CONTROLLER = "DeleteCategoryController";
    private static final String UPDATE_CATEGORY = "UpdateCategory";
    private static final String UPDATE_CATEGORY_CONTROLLER = "UpdateCategoryController";
    private static final String SEARCH_CATEGORY = "SearchCategory";
    private static final String SEARCH_CATEGORY_CONTROLLER = "SearchCategoryController";
    private static final String CREATE_DESCRIPTION = "CreateDescription";
    private static final String CREATE_DESCRIPTION_CONTROLLER = "CreateDescriptionController";
    private static final String GET_LIST_DESCRIPTION = "GetListDescription";
    private static final String GET_LIST_DESCRIPTION_CONTROLLER = "GetListDescriptionController";
    private static final String DELETE_DESCRIPTION = "DeleteDescription";
    private static final String DELETE_DESCRIPTION_CONTROLLER = "DeleteDescriptionController";
    private static final String CREATE_DETAIL = "CreateDetail";
    private static final String CREATE_DETAIL_CONTROLLER = "CreateDetailController";
    private static final String UPDATE_DETAIL = "UpdateDetail";
    private static final String UPDATE_DETAIL_CONTROLLER = "UpdateDetailController";
    private static final String DELETE_DETAIL = "DeleteDetail";
    private static final String DELETE_DETAIL_CONTROLLER = "DeleteDetailController";
    private static final String UPDATE_STATUS_ACCOUNT = "UpdateAccountStatus";
    private static final String UPDATE_STATUS_ACCOUNT_CONTROLLER = "UpdateAccountStatusServlet";
    private static final String SEARCH_ACCOUNT = "SearchByID";
    private static final String SEARCH_ACCOUNT_CONTROLLER = "SearchByIDServlet";
    private static final String HOME_SEARCH_DEVICE = "HomeSearchDevice";
    private static final String HOME_SEARCH_DEVICE_CONTROLLER = "HomeSearchDeviceController";
    private static final String DEVICE_DETAIL = "Detail";
    private static final String DEVICE_DETAIL_CONTROLLER = "DetailController";
    private static final String UPDATE_IMG = "UpdateImg";
    private static final String UPDATE_IMG_CONTROLLER = "UpdateImgController";
    private static final String OPEN_UPDATE_IMG_PAGE = "OpenUpdateImgPage";
    private static final String OPEN_UPDATE_IMG_PAGE_CONTROLLER = "OpenUpdateImgPageController";
    private static final String FILTER_DEVICE = "FilterDevice";
    private static final String FILTER_DEVICE_CONTROLLER = "FilterDeviceController";
    private static final String UPDATE_REQUEST_APPORVE = "UpdateRequestApproved";
    private static final String UPDATE_REQUESTCANCEL = "UpdateRequestCancel";
    private static final String UPDATE_REQUEST_SUCCESS = "UpdateRequestSuccess";
    private static final String UPDATE_REQUEST_RETURN = "UpdateRequestReturn";
    private static final String UPDATE_REQUEST_CONTROLLER = "UpdateRequestController";
    private static final String LOAD_PROCESSING_REQUEST = "LoadProcessRequest";
    private static final String LOAD_PROCESSING_REQUEST_CONTROLLER = "LoadRequestController";
    private static final String LOAD_APPROVE_REQUEST = "LoadApproveRequest";
    private static final String LOAD_SUCCESS_REQUEST = "LoadSuccessfulRequest";
    private static final String LOAD_RETURNED_REQUEST = "LoadReturnRequest";
    private static final String LOAD_CANCEL_REQUEST = "LoadCancelUserRequest";
    private static final String CREATE_REQUEST = "CreateRequest";
    private static final String CREATE_REQUEST_CONTROLLER = "CreateRequestController";
    private static final String UPDATE_CART = "UpdateCart";
    private static final String DELETE_ITEM_IN_CART = "DeleteItemInCart";
    private static final String UPDATE_CART_CONTROLLER = "UpdateCartController";
    private static final String DELETE_ITEM_IN_CART_CONTROLLER = "DeleteItemInCartController";
    private static final String ADD_TO_CART = "AddToCart";
    private static final String ADD_TO_CART_CONTROLLER = "AddToCartController";
    private static final String SEARCH_REQUEST_BY_NAME_IN_ALL = "SearchRequestByUserInAllRequest";
    private static final String SEARCH_REQUEST_BY_DATE_IN_ALL = "SearchRequestByDateInAllRequest";
    private static final String SEARCH_REQUEST_BY_NAME_IN_PROCESS = "SearchRequestByUserInProcessRequest";
    private static final String SEARCH_REQUEST_BY_DATE_IN_PROCESS = "SearchRequestByDateInProcessRequest";
    private static final String SEARCH_REQUEST_BY_NAME_IN_APPROVE = "SearchRequestByUserInApproveRequest";
    private static final String SEARCH_REQUEST_BY_DATE_IN_APPROVE = "SearchRequestByDateInApproveRequest";
    private static final String SEARCH_REQUEST_BY_NAME_IN_SUCCESS = "SearchRequestByUserInSuccessfulRequest";
    private static final String SEARCH_REQUEST_BY_DATE_IN_SUCCESS = "SearchRequestByDateInSuccessfullRequest";
    private static final String SEARCH_REQUEST_BY_NAME_IN_RETURN = "SearchRequestByUserInReturnedRequest";
    private static final String SEARCH_REQUEST_BY_DATE_IN_RETURN = "SearchRequestByDateInReturnedRequest";
    private static final String SEARCH_REQUEST_BY_NAME_IN_CANCEL = "SearchRequestByUserInCancelRequest";
    private static final String SEARCH_REQUEST_BY_DATE_IN_CANCEL = "SearchRequestByDateInCancelRequest";
    private static final String EXTEND_REQUEST = "ExtendRequest";
    private static final String EXTEND_STATUS_CONTROLLER = "ExtendRequestController";
    private static final String CREATE_BRAND = "CreateBrand";
    private static final String CREATE_BRAND_CONTROLLER = "CreateBrandController";
    private static final String UPDATE_BRAND = "UpdateBrand";
    private static final String UPDATE_BRAND_CONTROLLER = "UpdateBrandController";
    private static final String DELETE_BRAND = "DeleteBrand";
    private static final String DELETE_BRAND_CONTROLLER = "DeleteBrandController";
    private static final String UPDATE_DEVICE_CATEGORY = "UpdateDeviceCategory";
    private static final String UPDATE_DEVICE_CATEGORY_CONTROLLER = "UpdateDeviceCategoryController";
    private static final String UPDATE_CATEGORY_DEVICE_INFO = "UpdateCategoryDeviceInfo";
    private static final String UPDATE_CATEGORY_DEVICE_INFO_CONTROLLER = "InputCategoryDeviceInfoController";
    private static final String UPDATE_DEVICE_DETAIL_CATEGORY = "UpdateDeviceDetailCategory";
    private static final String UPDATE_DEVICE_DETAIL_CATEGORY_CONTROLLER = "UpdateDeviceDetailCategoryController";
    private static final String RENT_NOW = "RentNow";
    private static final String AUTO_UPDATE_EXTEND = "AutoUpdateExtend";
    private static final String AUTO_UPDATE_EXTEND_CONTROLLER ="AutoUpdateExtend";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");

            if (CREATE_DEVICE.equals(action)) {
                url = CREATE_DEVICE_CONTROLLER;
            } else if (SEARCH_DEVICE.equals(action)) {
                url = SEARCH_DEVICE_CONTROLLER;
            } else if (DELETE_DEVICE.equals(action)) {
                url = DELETE_DEVICE_CONTROLLER;
            } else if (UPDATE_DEVICE.equals(action)) {
                url = UPDATE_DEVICE_CONTROLLER;
            } else if (FIND_PRODUCT.equals(action)) {
                url = FIND_PRODUCT_CONTROLLER;
            } else if (GET_LIST.equals(action)) {
                url = GET_LIST_CONTROLLER;
            } else if (INPUT_DEVICE_INFORMATION.equals(action)) {
                url = INPUT_DEVICE_INFORMATION_CONTROLLER;
            } else if (UPDATE_DEVICE_DETAIL.equals(action)) {
                url = UPDATE_DEVICE_DETAIL_CONTROLLER;
            } else if (action.equals(VIEW_ALL_REQUEST_MANAGER)) {
                url = VIEW_ALL_REQUEST_MANAGER_CONTROLLER;
            } else if (action.equals(VIEW_PROCESSING_REQUEST_MANAGER)) {
                url = VIEW_ALL_REQUEST_MANAGER_CONTROLLER;
            } else if (action.equals(VIEW_APPROVE_REQUEST_MANAGER)) {
                url = VIEW_ALL_REQUEST_MANAGER_CONTROLLER;
            } else if (action.equals(VIEW_SUCCESSFUL_REQUEST_MANAGER)) {
                url = VIEW_ALL_REQUEST_MANAGER_CONTROLLER;
            } else if (action.equals(VIEW_RETURNED_REQUEST_MANAGER)) {
                url = VIEW_ALL_REQUEST_MANAGER_CONTROLLER;
            } else if (action.equals(VIEW_CANCEL_REQUEST_MANAGER)) {
                url = VIEW_ALL_REQUEST_MANAGER_CONTROLLER;
            } else if (UPDATE_PROFILE.equals(action)) {
                url = UPDATE_PROFILE_CONTROLLER;
            } else if (LOGOUT.equals(action)) {
                url = LOGOUT_CONTROLLER;
            } else if (CREATE_WAREHOUSE.equals(action)) {
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
            } else if (CREATE_BRAND.equals(action)) {
                url = CREATE_BRAND_CONTROLLER;
            } else if (UPDATE_BRAND.equals(action)) {
                url = UPDATE_BRAND_CONTROLLER;
            } else if (DELETE_BRAND.equals(action)) {
                url = DELETE_BRAND_CONTROLLER;
            } else if (DELETE_DESCRIPTION.endsWith(action)) {
                url = DELETE_DESCRIPTION_CONTROLLER;
            } else if (SEARCH_CATEGORY.equals(action)) {
                url = SEARCH_CATEGORY_CONTROLLER;
            } else if (SEARCH_ACCOUNT.equals(action)) {
                url = SEARCH_ACCOUNT_CONTROLLER;
            } else if (UPDATE_STATUS_ACCOUNT.equals(action)) {
                url = UPDATE_STATUS_ACCOUNT_CONTROLLER;
            } else if (HOME_SEARCH_DEVICE.equals(action)) {
                url = HOME_SEARCH_DEVICE_CONTROLLER;
            } else if (DEVICE_DETAIL.equals(action)) {
                url = DEVICE_DETAIL_CONTROLLER;
            } else if (UPDATE_IMG.equals(action)) {
                url = UPDATE_IMG_CONTROLLER;
            } else if (OPEN_UPDATE_IMG_PAGE.equals(action)) {
                url = OPEN_UPDATE_IMG_PAGE_CONTROLLER;
            } else if (FILTER_DEVICE.equals(action)) {
                url = FILTER_DEVICE_CONTROLLER;
            } else if (UPDATE_REQUEST_APPORVE.equals(action)) {
                url = UPDATE_REQUEST_CONTROLLER;
            } else if (UPDATE_REQUESTCANCEL.equals(action)) {
                url = UPDATE_REQUEST_CONTROLLER;
            } else if (UPDATE_REQUEST_SUCCESS.equals(action)) {
                url = UPDATE_REQUEST_CONTROLLER;
            } else if (UPDATE_REQUEST_RETURN.equals(action)) {
                url = UPDATE_REQUEST_CONTROLLER;
            } else if (LOAD_PROCESSING_REQUEST.equals(action)) {
                url = LOAD_PROCESSING_REQUEST_CONTROLLER;
            } else if (LOAD_APPROVE_REQUEST.equals(action)) {
                url = LOAD_PROCESSING_REQUEST_CONTROLLER;
            } else if (LOAD_SUCCESS_REQUEST.equals(action)) {
                url = LOAD_PROCESSING_REQUEST_CONTROLLER;
            } else if (LOAD_RETURNED_REQUEST.equals(action)) {
                url = LOAD_PROCESSING_REQUEST_CONTROLLER;
            } else if (LOAD_CANCEL_REQUEST.equals(action)) {
                url = LOAD_PROCESSING_REQUEST_CONTROLLER;
            } else if (UPDATE_DEVICE_CATEGORY.equals(action)) {
                url = UPDATE_DEVICE_CATEGORY_CONTROLLER;
            } else if (UPDATE_CATEGORY_DEVICE_INFO.equals(action)) {
                url = UPDATE_CATEGORY_DEVICE_INFO_CONTROLLER;
            } else if (UPDATE_DEVICE_DETAIL_CATEGORY.equals(action)) {
                url = UPDATE_DEVICE_DETAIL_CATEGORY_CONTROLLER;
            } else if (CREATE_REQUEST.equals(action)) {
                url = CREATE_REQUEST_CONTROLLER;
            } else if (UPDATE_CART.equals(action)) {
                url = UPDATE_CART_CONTROLLER;
            } else if (DELETE_ITEM_IN_CART.equals(action)) {
                url = DELETE_ITEM_IN_CART_CONTROLLER;
            } else if (ADD_TO_CART.equals(action)) {
                url = ADD_TO_CART_CONTROLLER;
            } else if (SEARCH_REQUEST_BY_NAME_IN_ALL.equals(action)) {
                url = VIEW_ALL_REQUEST_MANAGER_CONTROLLER;
            } else if (SEARCH_REQUEST_BY_DATE_IN_ALL.equals(action)) {
                url = VIEW_ALL_REQUEST_MANAGER_CONTROLLER;
            } else if (SEARCH_REQUEST_BY_NAME_IN_PROCESS.equals(action)) {
                url = VIEW_ALL_REQUEST_MANAGER_CONTROLLER;
            } else if (SEARCH_REQUEST_BY_DATE_IN_PROCESS.equals(action)) {
                url = VIEW_ALL_REQUEST_MANAGER_CONTROLLER;
            } else if (SEARCH_REQUEST_BY_NAME_IN_APPROVE.equals(action)) {
                url = VIEW_ALL_REQUEST_MANAGER_CONTROLLER;
            } else if (SEARCH_REQUEST_BY_DATE_IN_APPROVE.equals(action)) {
                url = VIEW_ALL_REQUEST_MANAGER_CONTROLLER;
            } else if (SEARCH_REQUEST_BY_NAME_IN_SUCCESS.equals(action)) {
                url = VIEW_ALL_REQUEST_MANAGER_CONTROLLER;
            } else if (SEARCH_REQUEST_BY_DATE_IN_SUCCESS.equals(action)) {
                url = VIEW_ALL_REQUEST_MANAGER_CONTROLLER;
            } else if (SEARCH_REQUEST_BY_NAME_IN_RETURN.equals(action)) {
                url = VIEW_ALL_REQUEST_MANAGER_CONTROLLER;
            } else if (SEARCH_REQUEST_BY_DATE_IN_RETURN.equals(action)) {
                url = VIEW_ALL_REQUEST_MANAGER_CONTROLLER;
            } else if (SEARCH_REQUEST_BY_NAME_IN_CANCEL.equals(action)) {
                url = VIEW_ALL_REQUEST_MANAGER_CONTROLLER;
            } else if (SEARCH_REQUEST_BY_DATE_IN_CANCEL.equals(action)) {
                url = VIEW_ALL_REQUEST_MANAGER_CONTROLLER;
            } else if (EXTEND_REQUEST.equals(action)) {
                url = EXTEND_STATUS_CONTROLLER;
            }else if(action.equals(RENT_NOW)){
                url = ADD_TO_CART_CONTROLLER;
            }else if(action.equals(AUTO_UPDATE_EXTEND)){
                url =AUTO_UPDATE_EXTEND_CONTROLLER;
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
