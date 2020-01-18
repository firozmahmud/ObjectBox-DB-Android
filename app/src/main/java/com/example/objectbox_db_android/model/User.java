package com.example.objectbox_db_android.model;
import java.util.Date;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToOne;


@Entity
public class User {

    @Id
    public long id;
    public String name;
    public String profession;
    public Date createdAt;
    public boolean isSelected;


    public ToOne<Details> details;

}
