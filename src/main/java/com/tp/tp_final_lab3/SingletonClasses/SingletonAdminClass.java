package com.tp.tp_final_lab3.SingletonClasses;

import com.tp.tp_final_lab3.Models.Admin;

public class SingletonAdminClass {

    private static SingletonAdminClass instancia;

    private static Admin adminData;

    private SingletonAdminClass(){}

    public static SingletonAdminClass getInstancia() {
        if (instancia == null) {
            instancia = new SingletonAdminClass();
        }
        return instancia;
    }

    public void SetInfo(Admin admin)
    {
        adminData = admin;
    }
    public Admin getInfo()
    {
        return adminData;
    }

}
