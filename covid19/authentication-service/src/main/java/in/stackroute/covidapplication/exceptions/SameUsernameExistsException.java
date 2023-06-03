package in.stackroute.covidapplication.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "same username exist already")
public class SameUsernameExistsException extends Exception {

}
