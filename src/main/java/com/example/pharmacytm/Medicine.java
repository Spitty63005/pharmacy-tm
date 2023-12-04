package com.example.pharmacytm;

public class Medicine
{
    private Integer med_id;
    private String med_brand, med_product, med_type, med_status, med_date;
    private double med_price;

    public Medicine(Integer med_id, String med_brand, String med_date, String med_type, String med_status, String med_product, double med_price)
    {
        this.med_id = med_id;
        this.med_brand = med_brand;
        this.med_type = med_type;
        this.med_date = med_date;
        this.med_price = med_price;
        this.med_product = med_product;
        this.med_status = med_status;
    }

    public void setMed_brand(String med_brand)
    {
        this.med_brand = med_brand;
    }

    public String getMed_brand()
    {
        return med_brand;
    }
}
