package org.godownManagement.serviceImpl;

import org.godownManagement.entities.*;
import org.godownManagement.exceptions.CityNotLoaded;
import org.godownManagement.exceptions.NoSuchUserExist;
import org.godownManagement.repository.*;
import org.godownManagement.requestDtos.AddEntryRequestToGodown;
import org.godownManagement.requestDtos.AddGodownRequest;
import org.godownManagement.requestDtos.CityRequest;
import org.godownManagement.requestDtos.UserRequest;
import org.godownManagement.responseDtos.EntryResponse;
import org.godownManagement.responseDtos.GodownResponse;
import org.godownManagement.responseDtos.ItemResponse;
import org.godownManagement.service.IGodownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;

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

    @Autowired
    EntriesRepository entriesRepository;

    @Autowired
    ItemRepository itemRepository;

    static GodownResponse createGodownResponseOutOfGodown(Godown godown) {
        List<EntryResponse> entryResponses = new ArrayList<>();
        for (Entry entry : godown.getEntries()) {
            Item item = entry.getItem();
            ItemResponse itemResponse = ItemResponse.builder().comodity(item.getComodity()).markaName(item.getMarkaName()).packing(item.getPacking()).addressFrom(item.getAddressFrom()).build();
            EntryResponse entryResponse = EntryResponse.builder().noOfSacks(entry.getNoOfSacks()).entryDate(entry.getEntryDate()).entryValuation(entry.getEntryValuation()).itemResponse(itemResponse).costPerSack(entry.getCostPerSack()).build();
            entryResponses.add(entryResponse);
        }
        return GodownResponse.builder().entryResponses(entryResponses).godownId(godown.getGodownId()).godownName(godown.getName()).godownAddress(godown.getAddress()).valuation(godown.getValuation()).build();
    }

    @Override
    public boolean addGodown(AddGodownRequest addGodownRequest) throws NoSuchUserExist, CityNotLoaded {
        CityRequest cityRequest = addGodownRequest.getCityRequest();
        UserRequest userRequest = addGodownRequest.getUserRequest();
        Optional<User> user = userRespository.getUserByContactNo(userRequest.getContactNo());
        if (user.isEmpty()) throw new NoSuchUserExist(NO_SUCH_USER_EXIST);
        City city = cityRepository.getCityByCityNameAndStateName(cityRequest.getCity(), cityRequest.getState());
        if (Objects.isNull(city)) throw new CityNotLoaded(CITY_NOT_LOADED_IN_DATABASE);
        Godown godown = Godown.builder().name(addGodownRequest.getName()).address(addGodownRequest.getAddress()).owner(user.get()).city(city).valuation(VALUATION).entries(NULL_ENTRIES).build();
        Godown dbGodown = godownRepository.save(godown);

        return true;
    }

    @Override
    public boolean deleteGodown(Godown godown, User user) {
        return false;
    }

    @Override
    public List<GodownResponse> getAllGodownsPerUser(UserRequest userRequest) throws NoSuchUserExist {
        Optional<User> user = userRespository.getUserByContactNo(userRequest.getContactNo());
        if (user.isEmpty()) throw new NoSuchUserExist(NO_SUCH_USER_EXIST);
        List<Godown> godowns = godownRepository.getAllGodownsPerUser(userRequest.getContactNo());
        Collections.sort(godowns, (godown1, godown2) -> {
            return godown1.getName().compareTo(godown2.getName());
        });
        return getGodownResponseFromGodown(godowns);
    }

    private List<GodownResponse> getGodownResponseFromGodown(List<Godown> godowns) {
        List<GodownResponse> godownResponses = new ArrayList<>();
        for (Godown godown : godowns) {
            List<EntryResponse> entryResponses = new ArrayList<>();
            for (Entry entry : godown.getEntries()) {
                Item item = entry.getItem();
                ItemResponse itemResponse = ItemResponse.builder().comodity(item.getComodity()).markaName(item.getMarkaName()).packing(item.getPacking()).addressFrom(item.getAddressFrom()).build();
                EntryResponse entryResponse = EntryResponse.builder().noOfSacks(entry.getNoOfSacks()).entryDate(entry.getEntryDate()).entryValuation(entry.getEntryValuation()).itemResponse(itemResponse).build();
                entryResponses.add(entryResponse);
            }
            GodownResponse godownResponse = GodownResponse.builder().entryResponses(entryResponses).godownId(godown.getGodownId()).godownName(godown.getName()).godownAddress(godown.getAddress()).valuation(godown.getValuation()).build();
            godownResponses.add(godownResponse);
        }
        return godownResponses;
    }

    @Override
    public GodownResponse addEntriesToGodown(int godownId, List<AddEntryRequestToGodown> addEntryRequestToGodowns) {
        Godown godown = godownRepository.getGodownById(godownId);
        for (AddEntryRequestToGodown addEntryRequestToGodown : addEntryRequestToGodowns) {
            Item item = itemRepository.getItemByMarkaAndComodityAndPacking(addEntryRequestToGodown.getComodity(), addEntryRequestToGodown.getType(), addEntryRequestToGodown.getMarkaName(), addEntryRequestToGodown.getPacking());

            Entry entry = Entry.builder()
                    .entryDate(new Date(System.currentTimeMillis())).godown(godown)
                    .entryValuation(addEntryRequestToGodown.getNoOfSacks() * addEntryRequestToGodown.getCostPerSack())
                    .noOfSacks(addEntryRequestToGodown.getNoOfSacks())
                    .costPerSack(addEntryRequestToGodown.getCostPerSack())
                    .item(item).build();
            entry = entriesRepository.save(entry);

            godown.getEntries().add(entry);

            int currentGodownValuation = godown.getValuation();
            godown.setValuation(currentGodownValuation + entry.getEntryValuation());
        }
        Godown newGodown = godownRepository.save(godown);
        return createGodownResponseOutOfGodown(newGodown);
    }
}