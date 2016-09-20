package com.epam.chuikov.entity;


public class ProductOrder {
    private Integer id;
    private final Product product;
    private final Order order;
    private final int amount;
    private final double factPrice;

    public ProductOrder(Product product, Order order, int amount, double factPrice) {
        this.product = product;
        this.order = order;
        this.amount = amount;
        this.factPrice = factPrice;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public Order getOrder() {
        return order;
    }

    public int getAmount() {
        return amount;
    }

    public double getFactPrice() {
        return factPrice;
    }

    @Override
    public String toString() {
        return "ProductOrder{" +
                "id=" + id +
                ", product=" + product +
                ", order=" + order +
                ", amount=" + amount +
                ", factPrice=" + factPrice +
                '}';
    }
}
