package in.stackroute.covidapplication.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.stackroute.covidapplication.model.Covid;
import in.stackroute.covidapplication.model.User;
import in.stackroute.covidapplication.exceptions.CountryAlreadyExistsException;
import in.stackroute.covidapplication.exceptions.CountryNotFoundException;
import in.stackroute.covidapplication.service.WatchListService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST,
        RequestMethod.DELETE })
@RequestMapping("/api/v1/watchlist")
public class WatchListController {
    // private ResponseEntity<?> responseEntity;
    private WatchListService watchListService;

    public WatchListController(WatchListService watchListService) {
        this.watchListService = watchListService;
    }

    @PostMapping("/{username}")
    public ResponseEntity<User> addCountryToWatchList(@RequestBody Covid ccovid, @PathVariable String username)
            throws CountryAlreadyExistsException {

        // if (username != null) {
        try {
            User user1 = watchListService.saveCountryToWatchList(ccovid, username);
            // if (user1.getUsername() != null) {
            // responseEntity = new ResponseEntity<User>(user1, HttpStatus.CREATED);
            return ResponseEntity.ok(user1);
            // }
            // else {
            // System.out.println("Useername not found...mayebe its null");
            // }

        } catch (CountryAlreadyExistsException e) {
            throw new CountryAlreadyExistsException();
        }
        /*
         * catch (Exception e) {
         * responseEntity = new ResponseEntity<>(e.getMessage(),
         * HttpStatus.INTERNAL_SERVER_ERROR);
         * 
         * }
         */
        // return responseEntity;
        // return null;

    }
    // else {
    // return null;
    // }

    @DeleteMapping("/{username}/{country}")
    public ResponseEntity<User> deleteFromList(@PathVariable String country, @PathVariable String username)
            throws CountryNotFoundException {

        try {
            User user1 = watchListService.deleteCountryFromWatchList(country, username);
            // responseEntity = new ResponseEntity<User>(user1, HttpStatus.OK);
            return ResponseEntity.ok(user1);
        } catch (CountryNotFoundException e) {
            throw new CountryNotFoundException();
        } // catch (Exception e) {
          // responseEntity = new ResponseEntity<>(e.getMessage(),
          // HttpStatus.INTERNAL_SERVER_ERROR);

        // }
        // return responseEntity;

    }

    @GetMapping("/{username}")
    public ResponseEntity<List<Covid>> getCountryList(@PathVariable String username) throws CountryNotFoundException {

        try {
            List<Covid> countryList = watchListService.getCountryList(username);
            // responseEntity = new ResponseEntity<>(countryList, HttpStatus.OK);
            return ResponseEntity.ok(countryList);
        } catch (Exception e) {
            throw new CountryNotFoundException();
        }
        // return responseEntity;

    }
}
