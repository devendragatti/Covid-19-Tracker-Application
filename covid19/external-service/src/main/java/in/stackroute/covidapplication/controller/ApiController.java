package in.stackroute.covidapplication.controller;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET })
@RequestMapping("/api/v1/countries")

public class ApiController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/country")
    public ResponseEntity<String> getCountry(@RequestParam(name = "page", required = true) int page,

            @RequestParam(name = "infected", required = false) String infected,
            @RequestParam(name = "tested", required = false) String tested,
            @RequestParam(name = "recovered", required = false) String recovered,
            @RequestParam(name = "deceased", required = false) String deceased,
            @RequestParam(name = "country", required = false) String country) {
        String queryappend = "";
        if (!NumberUtils.isDigits(infected)) {
            queryappend = queryappend + "&infected=" + infected;
        }
        if (!NumberUtils.isDigits(tested)) {
            queryappend = queryappend + "&tested=" + tested;
        }
        if (!NumberUtils.isDigits(recovered)) {
            queryappend = queryappend + "&recovered=" + recovered;
        }
        if (!NumberUtils.isDigits(deceased)) {
            queryappend = queryappend + "&deceased=" + deceased;
        }
        if (!NumberUtils.isDigits(country)) {
            queryappend = queryappend + "&country=" + country;
        }
        System.out.println(queryappend);

        return restTemplate.getForEntity(
                "https://api.apify.com/v2/key-value-stores/tVaYRsPHLjNdNBu7S/records/LATEST?disableRedirect=true?page="
                        + page + "" + queryappend,
                String.class);
    }

}
