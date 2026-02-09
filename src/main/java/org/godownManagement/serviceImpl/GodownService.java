package org.godownManagement.serviceImpl;

import org.godownManagement.entities.City;
import org.godownManagement.entities.Godown;
import org.godownManagement.entities.User;
import org.godownManagement.exceptions.CityNotLoaded;
import org.godownManagement.exceptions.NoSuchUserExist;
import org.godownManagement.repository.CityRepository;
import org.godownManagement.repository.GodownRepository;
import org.godownManagement.repository.UserRespository;
import org.godownManagement.requestDtos.AddGodownRequest;
import org.godownManagement.requestDtos.CityRequest;
import org.godownManagement.requestDtos.UserRequest;
import org.godownManagement.service.IGodownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

import static org.godownManagement.constants.Constants.NULL_ENTRIES;
import static org.godownManagement.constants.Constants.VALUATION;
import static org.godownManagement.constants.ExceptionConstant.CITY_NOT_LOADED_IN_DATABASE;
import static org.godownManagement.constants.ExceptionConstant.NO_SUCH_USER_EXIST;

@Service
public class GodownService implements IGodownService {

    @Autowired
    CityRepository cityRepository;

    @Autowired
    UserRespository userRespository;

    @Autowired
    GodownRepository godownRepository;

    @Override
    public boolean addGodown(AddGodownRequest addGodownRequest) throws NoSuchUserExist, CityNotLoaded {
        CityRequest cityRequest = addGodownRequest.getCityRequest();
        UserRequest userRequest = addGodownRequest.getUserRequest();

        Optional<User> user = userRespository.getUserByContactNo(userRequest.getContactNo());
        if (user.isEmpty())  throw new NoSuchUserExist(NO_SUCH_USER_EXIST);

        City city = cityRepository.getCityByCityNameAndStateName(cityRequest.getCity(), cityRequest.getState());
        if (Objects.isNull(city))
            throw new CityNotLoaded(CITY_NOT_LOADED_IN_DATABASE);

        Godown godown = Godown.builder()
                .name(addGodownRequest.getName())
                .address(addGodownRequest.getAddress())
                .owner(user.get()).city(city)
                .valuation(VALUATION).entries(NULL_ENTRIES)
                .build();
        godownRepository.save(godown);
        return true;
    }

    @Override
    public boolean deleteGodown(Godown godown, User user) {
        return false;
    }
}
