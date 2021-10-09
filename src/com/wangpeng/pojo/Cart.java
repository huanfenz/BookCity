package com.wangpeng.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 购物车类
 */
public class Cart {
    private Integer count = 0; //商品数量
    private BigDecimal price = new BigDecimal(0);  //总商品金额
    // linkedHashMap是有序的HashMap，能够记录插入的顺序
    private Map<Integer, CartItem> items = new LinkedHashMap<>();   //商品项信息

    public Cart() {
    }

    public Cart(Integer count, BigDecimal price, Map<Integer, CartItem> items) {
        this.count = count;
        this.price = price;
        this.items = items;
    }

    /**
     * 	根据item对象添加一个商品项
     * @param item
     */
    public void addItem(CartItem item) {
        //如果items中有该商品，则数量++，更新总价，否则添加item，数量=1。
        CartItem cartItem = items.get(item.getId());

        if(cartItem == null) {  //cart里没有该item
            //把该item添加进map
            items.put(item.getId(), item);
        } else {    //cart里有该item
            // 更新该商品的数量
            int cartCount = cartItem.getCount() + item.getCount();
            cartItem.setCount(cartCount);
            // 更新该商品的总价格
            cartItem.setTotalPrice(cartItem.getTotalPrice().add(item.getPrice()));
        }
        //更新购物车的count和price
        this.count += item.getCount();
        this.price = this.price.add(item.getPrice());
    }

    /**
     * 根据item对象修改数量
     * @param id
     * @param count
     */
    public void updateItem(int id, int count) {
        //获取item
        CartItem cartItem = items.get(id);
        //获取数量差值
        int difCount = count - cartItem.getCount();
        //更新item的count
        cartItem.setCount(count);

        //获取item的totalPrice的差值
        BigDecimal difTotalPrice = cartItem.getPrice()
                .multiply(new BigDecimal(count))
                .subtract(cartItem.getTotalPrice());
        //更新商品项总价格
        cartItem.setTotalPrice(cartItem.getTotalPrice().add(difTotalPrice));

        //更新总商品数量
        this.count += difCount;
        this.price = this.price.add(difTotalPrice);
    }

    /**
     * 	根据id删除一个商品项
     * @param id
     */
    public void delete(Integer id) {
        //获取该item
        CartItem item = items.get(id);
        //总数量和总金额更新
        count -= item.getCount();
        price = price.subtract(item.getTotalPrice());
        //把它从map中删掉
        items.remove(id);
    }

    /**
     * 清空商品项
     */
    public void clear() {
        //总数量和总金额更新
        count = 0;
        price = new BigDecimal(0);
        items.clear();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "count=" + count +
                ", price=" + price +
                ", items=" + items +
                '}';
    }
}
