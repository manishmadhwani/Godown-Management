package org.godownManagement.serviceImpl;

import org.godownManagement.controller.LoginController;
import org.godownManagement.entities.Item;
import org.godownManagement.entities.User;
import org.godownManagement.exceptions.NoSuchUserExist;
import org.godownManagement.repository.ItemRepository;
import org.godownManagement.repository.UserRespository;
import org.godownManagement.requestDtos.AddItemRequest;
import org.godownManagement.responseDtos.ItemResponse;
import org.godownManagement.service.ItemServiceInt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.godownManagement.constants.ExceptionConstant.NO_SUCH_USER_EXIST;

@Service
public class ItemService implements ItemServiceInt {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    UserRespository userRespository;

    Logger logger = LoggerFactory.getLogger(ItemService.class);

    @Override
    public List<ItemResponse> addItems(List<AddItemRequest> itemList) throws NoSuchUserExist {
         return createItems(itemList);
    }

    private List<ItemResponse> createItems(List<AddItemRequest> addItemRequests) throws NoSuchUserExist {
        List<ItemResponse> itemResponses= new ArrayList<>();

        for (AddItemRequest addItemRequest : addItemRequests) {
            Optional<User> user = userRespository
                    .getUserByContactNo(addItemRequest.getUserRequest().getContactNo());
            if (user.isEmpty()) throw new NoSuchUserExist(NO_SUCH_USER_EXIST);
            Item item = Item.builder().comodity(addItemRequest.getComodity())
                    .markaName(addItemRequest.getMarka()).packing(addItemRequest.getPacking())
                    .addressFrom(addItemRequest.getAddressFrom()).owner(user.get())
                    .build();
            Item item_Saved_In_Database= itemRepository.save(item);
            logger.info("[createItems] Created an entry in database of item with item Id :{}", item_Saved_In_Database.getItemId());
            ItemResponse itemResponse= ItemResponse.builder()
                    .comodity(item_Saved_In_Database.getComodity())
                    .markaName(item_Saved_In_Database.getMarkaName())
                    .packing(item_Saved_In_Database.getPacking())
                    .addressFrom(item_Saved_In_Database.getAddressFrom())
                    .build();
            itemResponses.add(itemResponse);
        }
        return itemResponses;
    }

    @Override
    public boolean deleteItems(List<Item> items, User user) {
        return false;
    }
}
