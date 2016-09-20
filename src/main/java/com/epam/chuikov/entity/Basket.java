package com.epam.chuikov.entity;



import java.util.HashMap;
import java.util.Map;


public class Basket {
    private Map<Product, Integer> bagContainer;

    public Basket() {
        this.bagContainer = new HashMap<>();
    }

    public void add(Product product, Integer amount){
        bagContainer.put(product, amount);
    }

    public void remove(Product product){
        bagContainer.remove(product);
    }

    public Map<Product, Integer> getAll(){
        return bagContainer;
    }

    public double getTotalPrice() {
        double total = 0;
        for(Map.Entry<Product, Integer> pair : bagContainer.entrySet()){
            total += (pair.getKey().getPrice() * pair.getValue());
        }
        return total;
    }

    public void clear(){
        bagContainer = new HashMap<>();
    }

    public int getGoodAmount(){
        return bagContainer.size();
    }

    public int size() {
        int size = 0;
        for(Map.Entry<Product, Integer> pair : bagContainer.entrySet()){
            size += pair.getValue();
        }
        return size;
    }
}
