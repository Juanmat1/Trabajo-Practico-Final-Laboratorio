package com.tp.tp_final_lab3.SingletonClasses;

import com.tp.tp_final_lab3.Models.Ticket;


public class SIngletonTicketClass {

    private static SIngletonTicketClass instancia;

    private static Ticket ticketData;

    private SIngletonTicketClass(){}

    public static SIngletonTicketClass getInstancia() {
        if (instancia == null) {
            instancia = new SIngletonTicketClass();
        }
        return instancia;
    }
    public void SetInfo(Ticket ticket)
    {
        ticketData = ticket;
    }
    public Ticket getInfo()
    {
        return ticketData;
    }
}
