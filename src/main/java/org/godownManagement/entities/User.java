package org.godownManagement.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Table(name = "USER")
@Data
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long userId;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    List<Godown> godowns;

    @Column(name = "userName", unique = true)
    String userName;

    Long contactNo;

    String password;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    List<Item> items;
}
