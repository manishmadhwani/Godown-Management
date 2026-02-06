package org.godownManagement.service;

import org.godownManagement.entities.Entry;
import org.godownManagement.entities.Godown;

import java.util.List;

public interface IEntryService {

    Godown addEntries(List<Entry> entryList, Godown godown);

    Godown deleteEntries(List<Entry> entryList, Godown godown);

    Godown transferEntries(List<Entry> entryList, Godown godownFrom, Godown godownTo);

}
