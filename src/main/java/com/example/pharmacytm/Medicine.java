package com.example.pharmacytm;

public class Medicine
{
    private Integer med_id;
    private String med_brand, med_product, med_type, med_status, med_date;
    private double med_price;

    public Medicine(Integer _med_id, String _med_brand, String _med_date, String _med_type, String _med_status, String _med_product, double _med_price)
    {
        med_id = _med_id;
        med_brand = _med_brand;
        med_type = _med_type;
        med_date = _med_date;
        med_price = _med_price;
        med_product = _med_product;
        med_status = _med_status;
    }

    public String getMed_brand()
    {
        return med_brand;
    }

    public String getMed_product()
    {
        return med_product;
    }

    public double getMed_price()
    {
        return med_price;
    }

    public String getMed_status()
    {
        return med_status;
    }

    public Integer getMed_id()
    {
        return med_id;
    }

    public String getMed_date()
    {
        return med_date;
    }

    public String getMed_type()
    {
        return med_type;
    }

    @Override
    public String toString()
    {
        return  med_id + " " + med_brand + " " + med_product
                + " " + med_type + " " + med_price + " " + med_status + " " + med_date;
    }
}
