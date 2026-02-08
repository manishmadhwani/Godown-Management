package org.godownManagement.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;

@Table(name = "ITEMS")
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long itemId;

    String comodity;
    String markaName;
    int packing;
    String addressFrom;

    @ManyToOne
    @JoinColumn(name = "owner_Id")
    User owner;

    public Item(String comodity, String markaName, int packing, String addressFrom) {
        this.comodity = comodity;
        this.markaName = markaName;
        this.packing = packing;
        this.addressFrom = addressFrom;
    }
}
