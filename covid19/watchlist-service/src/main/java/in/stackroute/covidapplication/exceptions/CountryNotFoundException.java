package in.stackroute.covidapplication.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Country not found.")

public class CountryNotFoundException extends Exception {

}
