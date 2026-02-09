package org.godownManagement.service;

import org.godownManagement.entities.Godown;
import org.godownManagement.entities.User;
import org.godownManagement.exceptions.CityNotLoaded;
import org.godownManagement.exceptions.NoSuchUserExist;
import org.godownManagement.requestDtos.AddGodownRequest;

public interface IGodownService {

    boolean addGodown(AddGodownRequest addGodownRequest) throws NoSuchUserExist, CityNotLoaded;

    boolean deleteGodown(Godown godown, User user);
}
