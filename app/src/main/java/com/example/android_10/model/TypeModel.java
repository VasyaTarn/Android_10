package com.example.android_10.model;

public class TypeModel {
    private int id;
    private String label;
    private String rule;

    public TypeModel(int id, String label, String rule)
    {
        this.id = id;
        this.label = label;
        this.rule = rule;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public String getRule()
    {
        return rule;
    }

    public void setRule(String rule)
    {
        this.rule = rule;
    }
}
