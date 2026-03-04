package org.godownManagement.service;

import org.godownManagement.entities.Item;
import org.godownManagement.entities.User;
import org.godownManagement.exceptions.NoSuchUserExist;
import org.godownManagement.requestDtos.AddItemRequest;
import org.godownManagement.responseDtos.ItemResponse;

import java.util.List;

public interface ItemServiceInt {
    List<ItemResponse> addItems(List<AddItemRequest> itemList) throws NoSuchUserExist;

    boolean deleteItems(List<Item> items, User user);
}
