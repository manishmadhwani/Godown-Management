package org.godownManagement.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "USERS")
@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Column(name = "userName", unique = true)
    String userName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    long userId;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    List<Godown> godowns;

    @Column(name = "contactNo")
    String contactNo;

    @Column(name = "password")
    String password;
}
