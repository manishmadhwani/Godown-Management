package org.godownManagement.controller;

import org.godownManagement.exceptions.NoSuchUserExist;
import org.godownManagement.requestDtos.AddItemRequest;
import org.godownManagement.responseDtos.ItemResponse;
import org.godownManagement.service.ItemServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    ItemServiceInt itemServiceInt;

    @PostMapping("/addItems")
    ResponseEntity<List<ItemResponse>> addItems(@RequestBody List<AddItemRequest> itemRequests) throws NoSuchUserExist {
        List<ItemResponse> itemResponses = itemServiceInt.addItems(itemRequests);
        return ResponseEntity.status(HttpStatus.CREATED).body(itemResponses);
    }
}
