package com.epam.chuikov.dto;

import java.util.Arrays;

public class FilterParameter {
    private String suggestedTitle;
    private String[] manufacturers_id;
    private String[] categories_id;
    private double fromPrice;
    private double toPrice;
    private int pageSize;
    private int pageNumber;
    private String sortMode;
    private boolean isEmpty;

    public FilterParameter() {
        pageNumber = 1;
        suggestedTitle = "";
        pageSize = 10;
        isEmpty = true;
    }

    public String getSuggestedTitle() {
        return suggestedTitle;
    }

    public void setSuggestedTitle(String suggestedTitle) {
        setNotEmpty();
        if(!suggestedTitle.equals("null")) {
            this.suggestedTitle = suggestedTitle;
        }
    }

    public String getSortMode() {
        return sortMode;
    }

    public void setSortMode(String sortMode) {
        setNotEmpty();
        this.sortMode = sortMode;
    }

    public String[] getCategories_id() {
        return categories_id;
    }

    public void setCategories_id(String[] categories_id) {
        setNotEmpty();
        this.categories_id = categories_id;
    }

    public String[] getManufacturers_id() {
        return manufacturers_id;
    }

    public void setManufacturers_id(String[] manufacturers_id) {
        setNotEmpty();
        this.manufacturers_id = manufacturers_id;
    }

    public double getFromPrice() {
        return fromPrice;
    }

    public void setFromPrice(double fromPrice) {
        setNotEmpty();
        this.fromPrice = fromPrice;
    }

    public double getToPrice() {
        return toPrice;
    }

    public void setToPrice(double toPrice) {
        setNotEmpty();
        this.toPrice = toPrice;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        setNotEmpty();
        this.pageNumber = pageNumber;
    }

    public void setPageSize(int pageSize) {
        setNotEmpty();
        if(pageSize <= 0){
            this.pageSize = 10;
        } else {
            this.pageSize = pageSize;
        }
    }
    public boolean isEmpty(){
        return isEmpty;
    }
    @Override
    public String toString() {
        return "FilterParameter{" +
                "suggestedTitle='" + suggestedTitle + '\'' +
                ", manufacturers_id=" + Arrays.toString(manufacturers_id) +
                ", categories_id=" + Arrays.toString(categories_id) +
                ", fromPrice=" + fromPrice +
                ", toPrice=" + toPrice +
                ", pageSize=" + pageSize +
                ", pageNumber=" + pageNumber +
                ", sortMode='" + sortMode + '\'' +
                '}';
    }

    private void setNotEmpty(){
        isEmpty = false;
    }
}
