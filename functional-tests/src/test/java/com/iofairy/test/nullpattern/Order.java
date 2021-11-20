package com.iofairy.test.nullpattern;

/**
 * @author GG
 * @version 1.0
 */
public class Order {
    public String orderId;
    public double price;
    public User buyer;

    public Order(String orderId, double price, User buyer) {
        this.orderId = orderId;
        this.price = price;
        this.buyer = buyer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", price=" + price +
                ", buyer=" + buyer +
                '}';
    }
}
