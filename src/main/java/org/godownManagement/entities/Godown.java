package org.godownManagement.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.Generated;

import java.util.List;

@Table(name = "GODOWNS")
@Data
@Builder
public class Godown {
    @ManyToOne
    @JoinColumn(name = "owner_id")
    User owner;

    @Id
    @Generated
    int godownId;

    String name;
    String address;
    int valuation;

    @ManyToOne
    @JoinColumn(name = "cityName")
    City city;

    @OneToMany(mappedBy = "godown")
    List<Entry> entries;

    public Godown(String name, String address, int valuation, City city, List<Entry> entries) {
        this.name = name;
        this.address = address;
        this.valuation = valuation;
        this.city = city;
        this.entries = entries;
    }
}
