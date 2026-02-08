package org.godownManagement.controller;

import org.godownManagement.requestDtos.AddGodownRequest;
import org.godownManagement.service.IGodownService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.godownManagement.constants.Constants.FAILED_TO_ADD_GODOWN;
import static org.godownManagement.constants.Constants.GODOWN_ADDED_SUCCESSFULLY;

@RestController
public class GodownController {

    Logger logger = LoggerFactory.getLogger(GodownController.class);

    @Autowired
    IGodownService iGodownService;

    @PostMapping("/addGodown")
    public ResponseEntity<String> addGodown(@RequestBody AddGodownRequest addGodownRequest) {
        logger.info("[addGodown] Request to add a godown from user :{}", addGodownRequest.getUserRequest().getContactNo());
        if (iGodownService.addGodown(addGodownRequest))
            return ResponseEntity.status(HttpStatus.CREATED).body(GODOWN_ADDED_SUCCESSFULLY);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FAILED_TO_ADD_GODOWN);
    }
}
