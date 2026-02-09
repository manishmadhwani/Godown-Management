package org.godownManagement.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;

import java.util.List;

@Table(name = "GODOWNS")
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Godown {
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

    @OneToMany(mappedBy = "godown")
    List<Entry> entries;
}
