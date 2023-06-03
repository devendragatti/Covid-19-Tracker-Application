package in.stackroute.covidapplication.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import in.stackroute.covidapplication.exceptions.CountryAlreadyExistsException;
import in.stackroute.covidapplication.exceptions.CountryNotFoundException;
import in.stackroute.covidapplication.model.Covid;
import in.stackroute.covidapplication.model.User;
import in.stackroute.covidapplication.respository.WatchListRepository;

@Service
public class WatchListImpl implements WatchListService {

    private WatchListRepository watchListRepository;

    public WatchListImpl(WatchListRepository watchListRepository) {
        this.watchListRepository = watchListRepository;
    }

    @Override
    public User saveCountryToWatchList(Covid covid, String username) throws CountryAlreadyExistsException {
        User user1 = watchListRepository.findByusername(username);
        // User user1 = user2.get();
        if (user1 == null) {
            user1 = new User(username, new ArrayList<Covid>());
        }
        List<Covid> covidList = user1.getCountryList();

        if (covidList != null) {
            for (Covid p : covidList) {

                if (p.getCountry().equals(covid.getCountry())) {
                    throw new CountryAlreadyExistsException();
                }
            }

            covidList.add(covid);
            user1.setCountryList(covidList);
            watchListRepository.save(user1);
        }

        else {
            covidList = new ArrayList<>();
            covidList.add(covid);

            user1.setCountryList(covidList);
            watchListRepository.save(user1);
        }
        return user1;

    }

    @Override
    public User deleteCountryFromWatchList(String country, String username) throws CountryNotFoundException {
        User user2 = watchListRepository.findByusername(username);
        // User user1 = user2.get();
        int indexnum = 0;
        List<Covid> covidList = user2.getCountryList();
        boolean status = false;
        if (covidList != null && covidList.size() > 0) {
            for (Covid t : covidList) {
                indexnum++;
                if (t.getCountry().equals(country)) {
                    status = true;
                    covidList.remove(indexnum - 1);
                    user2.setCountryList(covidList);
                    watchListRepository.save(user2);
                    break;
                }
            }
            if (status == false) {
                throw new CountryNotFoundException();
            }
        }
        return user2;
    }

    @Override
    public List<Covid> getCountryList(String username) throws Exception {
        User user1 = watchListRepository.findByusername(username);
        return user1.getCountryList();
    }

}
