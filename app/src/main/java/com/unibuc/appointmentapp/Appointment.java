package com.unibuc.appointmentapp;


public class Appointment {
    public Long id;

    public String name;

    public String phone;
    public String date;

    public Appointment(Long id, String name, String phone, String date) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.date = date;
    }

    public Appointment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
