package in.stackroute.covidapplication.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "password mismatch")
public class PasswordMisMatchException extends Exception {

}
