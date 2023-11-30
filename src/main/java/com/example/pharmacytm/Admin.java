package com.example.pharmacytm;

public class Admin
{
    private final int id;
    private final String name, password, username;

    public Admin(int id, String name, String password, String username)
    {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }
}
