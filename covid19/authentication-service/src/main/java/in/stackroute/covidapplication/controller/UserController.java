package in.stackroute.covidapplication.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.stackroute.covidapplication.dto.UserDto;
import in.stackroute.covidapplication.exceptions.PasswordMisMatchException;
import in.stackroute.covidapplication.exceptions.SameUsernameExistsException;
import in.stackroute.covidapplication.exceptions.UserAlreadyExistException;
import in.stackroute.covidapplication.exceptions.UserNotFoundException;
import in.stackroute.covidapplication.model.ChangePassword;
import in.stackroute.covidapplication.model.User;
import in.stackroute.covidapplication.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.PUT, RequestMethod.POST })
@RequestMapping("/api/v1/users")
public class UserController {
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserDto userDto) throws UserAlreadyExistException {
        User user = modelMapper.map(userDto, User.class);
        try {
            User user1 = userService.register(user);
            return ResponseEntity.ok(user1);

        } catch (Exception e) {

            throw new UserAlreadyExistException();
        }

    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody UserDto userDto) throws UserNotFoundException {
        Map<String, String> map = new HashMap<>();
        User user = modelMapper.map(userDto, User.class);
        String jwtToken = "";
        try {
            User user2 = userService.login(user.getUserName(), user.getPassword());
            if (user2.getUserName().equals(user.getUserName())) {
                jwtToken = getToken(user.getUserName(), user.getPassword());
                map.put("message", "login successfull");
                map.put("token", jwtToken);

            }
            return ResponseEntity.ok(map);

        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        }

    }

    public String getToken(String username, String password) throws UserNotFoundException {
        if (username == null || password == null) {
            throw new UserNotFoundException();
        }
        String jwtToken = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 30000))
                .signWith(SignatureAlgorithm.HS256, "secretkey").compact();

        return jwtToken;

    }

    @PutMapping("/update/{userName}")
    public ResponseEntity<User> updateDetails(@RequestBody UserDto userDto, @PathVariable String userName)
            throws SameUsernameExistsException {
        User user = modelMapper.map(userDto, User.class);

        try {
            User user1 = userService.updateDetails(user, userName);
            return ResponseEntity.ok(user1);

        } catch (SameUsernameExistsException e) {
            throw new SameUsernameExistsException();
        }

    }

    @PutMapping("/changepassword/{userName}")
    public ResponseEntity<User> updatePassword(@RequestBody ChangePassword changepassword,
            @PathVariable String userName) throws PasswordMisMatchException {
        try {
            User user1 = userService.updatePassword(changepassword, userName);
            return ResponseEntity.ok(user1);
        } catch (PasswordMisMatchException e) {
            throw new PasswordMisMatchException();
        }

    }
}
