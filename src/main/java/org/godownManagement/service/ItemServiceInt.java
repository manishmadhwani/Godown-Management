package org.godownManagement.service;

import org.godownManagement.entities.Item;
import org.godownManagement.entities.User;

import java.util.List;

public interface ItemServiceInt {
    List<Item> addItems(List<Item> itemList, User user);

    boolean deleteItems(List<Item> items, User user);
}
