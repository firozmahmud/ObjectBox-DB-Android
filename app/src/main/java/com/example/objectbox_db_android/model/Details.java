package com.example.objectbox_db_android.model;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Details {

    @Id
    public long id;

    public String number;
    public String location;

    public Details(String number, String location) {
        this.number = number;
        this.location = location;
    }
}
