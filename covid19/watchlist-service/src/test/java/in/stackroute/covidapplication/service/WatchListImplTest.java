package in.stackroute.covidapplication.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import in.stackroute.covidapplication.exceptions.CountryAlreadyExistsException;
import in.stackroute.covidapplication.exceptions.CountryNotFoundException;
import in.stackroute.covidapplication.model.Covid;
import in.stackroute.covidapplication.model.User;

public class WatchListImplTest {
    private User user;
    private Covid covid, covid1;
    List<Covid> list;

    @BeforeEach
    public void setUp() {
        user = new User();
        covid = new Covid();
        covid.setCountry("india");
        covid.setRecovered("172817281");
        covid.setDeceased("6677777");
        covid.setInfected("88999");
        user.setUsername("pallavi");
        list = new ArrayList<>();
        list.add(covid);
        user.setCountryList(list);
    }

    @AfterEach
    public void tearDown() {
        covid = null;
        user = null;
    }

    @Test
    public void testSaveWatchListForUser() throws CountryAlreadyExistsException {
        WatchListImpl service = mock(WatchListImpl.class);
        when(service.saveCountryToWatchList(covid, "pallavi")).thenReturn(user);
        User dummy = service.saveCountryToWatchList(covid, "pallavi");
        assertNotNull(dummy);
    }

    @Test
    public void testViewWatchListForUser() throws Exception {
        WatchListImpl service = mock(WatchListImpl.class);
        covid1 = new Covid();
        covid1.setCountry("USA");
        covid1.setRecovered("172817281");
        covid1.setDeceased("6677777");
        covid1.setInfected("88999");
        list.add(covid);
        user.setCountryList(list);
        when(service.getCountryList("pallavi")).thenReturn(list);
        List<Covid> list1 = service.getCountryList("pallavi");
        assertNotNull(list1);
        assertEquals(list1.size(), list.size());

    }

    @Test
    public void testDeleteWatchListForUser() throws CountryNotFoundException {
        WatchListImpl service = mock(WatchListImpl.class);
        when(service.deleteCountryFromWatchList("india", "pallavi")).thenReturn(null);
        User dummy = service.deleteCountryFromWatchList("india", "pallavi");
        assertNull(dummy);

    }
}
