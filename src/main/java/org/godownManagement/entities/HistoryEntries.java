package org.godownManagement.entities;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.Generated;

import java.sql.Date;

@Table(name = "History_Entries")
public class HistoryEntries {
    @Id
    @Generated
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
