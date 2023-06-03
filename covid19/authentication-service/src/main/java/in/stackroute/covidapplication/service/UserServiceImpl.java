package in.stackroute.covidapplication.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import in.stackroute.covidapplication.exceptions.PasswordMisMatchException;
import in.stackroute.covidapplication.exceptions.SameUsernameExistsException;
import in.stackroute.covidapplication.exceptions.UserAlreadyExistException;
import in.stackroute.covidapplication.exceptions.UserNotFoundException;
import in.stackroute.covidapplication.model.ChangePassword;
import in.stackroute.covidapplication.model.User;
import in.stackroute.covidapplication.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(User user) throws UserAlreadyExistException {
        Optional<User> existingUser = userRepository.findByuserName(user.getUserName());
        if (!existingUser.isEmpty()) {
            throw new UserAlreadyExistException();
        }
        return userRepository.save(user);
    }

    @Override
    public User login(String userName, String password) throws UserNotFoundException {
        User user = userRepository.findByuserNameAndPassword(userName, password);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;

    }

    @Override
    public User updateDetails(User user, String username) throws SameUsernameExistsException {
        Optional<User> user1 = userRepository.findByuserName(username);
        Optional<User> user3 = userRepository.findByuserName(user.getUserName());
        if (user3.isEmpty()) {
            User user2 = user1.get();
            user2.setEmail(user.getEmail());
            user2.setUserName(user.getUserName());
            return userRepository.save(user2);
        }
        throw new SameUsernameExistsException();
    }

    @Override
    public User updatePassword(ChangePassword changepassword, String username) throws PasswordMisMatchException {
        Optional<User> user1 = userRepository.findByuserName(username);
        User user2 = user1.get();
        if (user2.getPassword().equals(changepassword.getOldPassword())) {
            user2.setPassword(changepassword.getNewPassword());
            return userRepository.save(user2);
        }
        throw new PasswordMisMatchException();
    }

    @Override
    public User getUserByUserName(String username) {
        Optional<User> user = userRepository.findByuserName(username);
        User user1 = user.get();
        return user1;
    }

}
