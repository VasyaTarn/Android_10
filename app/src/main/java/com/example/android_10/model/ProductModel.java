package com.example.android_10.model;

public class ProductModel {
    private int id;
    private String name;
    private double count;
    private int listId;
    private boolean checked;
    private int countType;

    public ProductModel(int id, String name, double count, int listId, boolean checked, int countType)
    {
        this.id = id;
        this.name = name;
        this.count = count;
        this.listId = listId;
        this.checked = checked;
        this.countType = countType;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getCount()
    {
        return count;
    }

    public void setCount(double count)
    {
        this.count = count;
    }

    public int getListId()
    {
        return listId;
    }

    public void setListId(int listId)
    {
        this.listId = listId;
    }

    public boolean isChecked()
    {
        return checked;
    }

    public void setChecked(boolean checked)
    {
        this.checked = checked;
    }

    public int getCountType()
    {
        return countType;
    }

    public void setCountType(int countType)
    {
        this.countType = countType;
    }
}
