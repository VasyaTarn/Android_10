package com.example.android_10.model;

public class ListModel {
    private int id;
    private String name;
    private long date;
    private String description;

    public ListModel(int id, String name, long date, String description)
    {
        this.id = id;
        this.name = name;
        this.date = date;
        this.description = description;
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

    public long getDate()
    {
        return date;
    }

    public void setDate(long date)
    {
        this.date = date;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
