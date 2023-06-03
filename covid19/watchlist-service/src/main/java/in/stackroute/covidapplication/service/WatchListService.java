package in.stackroute.covidapplication.service;

import java.util.List;

import in.stackroute.covidapplication.exceptions.CountryAlreadyExistsException;
import in.stackroute.covidapplication.exceptions.CountryNotFoundException;
import in.stackroute.covidapplication.model.Covid;
import in.stackroute.covidapplication.model.User;

public interface WatchListService {
    public User saveCountryToWatchList(Covid covid, String username) throws CountryAlreadyExistsException;

    public User deleteCountryFromWatchList(String trackId, String username) throws CountryNotFoundException;

    public List<Covid> getCountryList(String username) throws Exception;

}
