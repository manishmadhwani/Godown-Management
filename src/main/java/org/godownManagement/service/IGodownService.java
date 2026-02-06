package org.godownManagement.service;

import org.godownManagement.entities.Entry;
import org.godownManagement.entities.Godown;
import org.godownManagement.entities.User;

import java.util.List;

public interface IGodownService {

    boolean addGodown(Godown godown, User user);

    boolean deleteGodown(Godown godown, User user);

    Godown addEntries(List<Entry> entryList, Godown godown);

    Godown deleteEntries(List<Entry> entryList, Godown godown);

    Godown transferEntries(List<Entry> entryList, Godown godownFrom, Godown godownTo);

}
