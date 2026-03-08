package org.godownManagement.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Generated;

import java.util.List;

@Table(name = "GODOWNS")
@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Godown {
    @OneToMany(mappedBy = "godown")
    List<Entry> entries;

    @ManyToOne
    @JoinColumn(name = "ownerId")
    User owner;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int godownId;

    String name;
    String address;
    int valuation;

    @ManyToOne
    @JoinColumn(name = "cityId")
    City city;
}
