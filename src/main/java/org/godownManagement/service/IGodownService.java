package org.godownManagement.service;

import org.godownManagement.entities.Entry;
import org.godownManagement.entities.Godown;
import org.godownManagement.entities.User;

import java.util.List;

public interface IGodownService {

    boolean addGodown(Godown godown, User user);

    boolean deleteGodown(Godown godown, User user);
}
