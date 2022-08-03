/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manhcuong.request;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import quanghung.device.DeviceDTO;

/**
 *
 * @author Admin
 */
public class cartDao {
    public cartDTO getCartFromPreviousOrder(int requestID) {
         List<DeviceDTO> list = null;
         cartDTO cart = null;
         requestDAO o_dao = new requestDAO();
         list = o_dao.convertOrderToItems(requestID);
         if (!list.isEmpty()){
             cart = new cartDTO();
             for (DeviceDTO deviceDTO : list) {
                cart.add(deviceDTO);
            }
         }
         return cart;
         
     }
    public List<DeviceDTO> convertCartToList(cartDTO cart) {
         List<DeviceDTO> list = new ArrayList<>();
         for (Map.Entry<Integer, DeviceDTO> item : cart.getCart().entrySet()){
             int key = item.getKey();
             DeviceDTO value = item.getValue();
             list.add(value);
         }
         return list;
    }
}
