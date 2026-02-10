package org.godownManagement.controller;

import jakarta.validation.Valid;
import org.godownManagement.exceptions.CityNotLoaded;
import org.godownManagement.exceptions.NoSuchUserExist;
import org.godownManagement.requestDtos.AddGodownRequest;
import org.godownManagement.requestDtos.UserRequest;
import org.godownManagement.responseDtos.GodownResponse;
import org.godownManagement.service.IGodownService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

import static org.godownManagement.constants.Constants.FAILED_TO_ADD_GODOWN;
import static org.godownManagement.constants.Constants.GODOWN_ADDED_SUCCESSFULLY;

@RestController
public class GodownController {

    Logger logger = LoggerFactory.getLogger(GodownController.class);

    @Autowired
    IGodownService iGodownService;

    @PostMapping("/addGodown")
    public ResponseEntity<String> addGodown(@RequestBody @Valid AddGodownRequest addGodownRequest) throws CityNotLoaded, NoSuchUserExist {
        logger.info("[addGodown] Request to add a godown from user :{}", addGodownRequest.getUserRequest().getContactNo());

        if (iGodownService.addGodown(addGodownRequest))
            return ResponseEntity.status(HttpStatus.CREATED).body(GODOWN_ADDED_SUCCESSFULLY);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FAILED_TO_ADD_GODOWN);
    }

    @PostMapping("/getAllGodowns")
    public ResponseEntity<List<GodownResponse>> getAllGodowns(@RequestBody @Valid UserRequest userRequest) throws NoSuchUserExist {
        logger.info("[getAllGodowns] Request to get all godowns for user :{}", userRequest.getContactNo());
        List<GodownResponse> godownResponses = iGodownService.getAllGodownsPerUser(userRequest);
        if (Objects.isNull(godownResponses) || godownResponses.size() == 0)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        return ResponseEntity.status(HttpStatus.OK).body(godownResponses);
    }
}
