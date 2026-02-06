package org.godownManagement.entities;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.Generated;

@Table(name = "ITEMS")
@Data
public class Item {
    @Id
    @Generated
    String itemId;

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
