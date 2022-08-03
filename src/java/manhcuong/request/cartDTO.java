/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manhcuong.request;

import java.util.HashMap;
import java.util.Map;
import quanghung.device.DeviceDTO;

/**
 *
 * @author Admin
 */
public class cartDTO {
    private Map<Integer, DeviceDTO> cart;
    public cartDTO(){
        
    }

    public cartDTO(Map<Integer, DeviceDTO> cart) {
        this.cart = cart;
    }

    public Map<Integer, DeviceDTO> getCart() {
        return cart;
    }

    public void setCart(Map<Integer, DeviceDTO> cart) {
        this.cart = cart;
    }
    public boolean add(DeviceDTO item){
     boolean newItem = true;
     if(this.cart == null){
         this.cart = new HashMap<>();
     }
     if(this.cart.containsKey(item.getDeviceID())){
         newItem = false;
         int currentQuantity =(int) this.cart.get(item.getDeviceID()).getQuantity();
         item.setQuantity(currentQuantity +item.getQuantity());
     }
     cart.put(item.getDeviceID(), item);
     return newItem;
    }
    public void delete(int id) {
        if (this.cart == null) {
            return;
        }
        if (this.cart.containsKey(id)) {
            this.cart.remove(id);
        }
    }
    public void update(int id, DeviceDTO item) {
        if (this.cart == null) {
            return;
        }
        if (this.cart.containsKey(id)) {
            this.cart.replace(id, item);
        }
    }

}