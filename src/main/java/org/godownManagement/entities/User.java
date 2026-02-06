package org.godownManagement.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.Generated;

import java.util.List;

@Table(name = "USER")
@Data
@Builder
public class User {
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    List<Godown> godowns;

    @Column(name = "username", unique = true)
    String userName;

    @Id
    Long contactNo;
    String password;

    @Generated
    long userId;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    List<Item> items;

    public User(String userName, String password, long contactNo, List<Godown> godowns, List<Item> items) {
        this.userName = userName;
        this.password = password;
        this.contactNo = contactNo;
        this.godowns = godowns;
        this.items = items;
    }
}
