package org.godownManagement.service;

import org.godownManagement.entities.Godown;
import org.godownManagement.entities.User;
import org.godownManagement.requestDtos.AddGodownRequest;

public interface IGodownService {

    boolean addGodown(AddGodownRequest addGodownRequest);

    boolean deleteGodown(Godown godown, User user);
}
