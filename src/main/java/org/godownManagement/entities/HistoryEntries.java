package org.godownManagement.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;

import java.sql.Date;

@Table(name = "History_Entries")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class HistoryEntries {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int historyId;

    String typeOfTransaction;
    Date date;

    @OneToOne
    @JoinColumn(name = "godownIdFrom")
    Godown godownFrom;

    @OneToOne
    @JoinColumn(name = "godownIdTo")
    Godown godownTo;

    @OneToOne
    @JoinColumn(name = "entry_id")
    Entry entry;
}
