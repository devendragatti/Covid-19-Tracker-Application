package in.stackroute.covidapplication.service;

import in.stackroute.covidapplication.exceptions.PasswordMisMatchException;
import in.stackroute.covidapplication.exceptions.SameUsernameExistsException;
import in.stackroute.covidapplication.exceptions.UserAlreadyExistException;
import in.stackroute.covidapplication.exceptions.UserNotFoundException;
import in.stackroute.covidapplication.model.ChangePassword;
import in.stackroute.covidapplication.model.User;

public interface UserService {
    User register(User user) throws UserAlreadyExistException;

    User login(String userName, String password) throws UserNotFoundException;

    User updateDetails(User user, String userName) throws SameUsernameExistsException;

    public User updatePassword(ChangePassword changepassword, String username)
            throws PasswordMisMatchException;

    User getUserByUserName(String username);
}
