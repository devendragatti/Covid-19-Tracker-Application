package in.stackroute.covidapplication.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import in.stackroute.covidapplication.exceptions.SameUsernameExistsException;
import in.stackroute.covidapplication.exceptions.UserAlreadyExistException;
import in.stackroute.covidapplication.exceptions.UserNotFoundException;

import in.stackroute.covidapplication.model.User;
import in.stackroute.covidapplication.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    private User user;

    @InjectMocks
    private UserServiceImpl userServiceImpl;
    Optional<User> optional;

    @BeforeEach
    public void setUp() throws Exception {
        user = new User();
        MockitoAnnotations.openMocks(this);
        user.setUserName("roja");
        user.setPassword("roja123");
        user.setEmail("roja@gmail.com");
        optional = Optional.of(user);

    }

    @Test
    public void testSaveUserSuccess() throws UserAlreadyExistException {
        Mockito.when(userRepository.save(user)).thenReturn(user);
        User user1 = userServiceImpl.register(user);
        assertEquals(user.getId(), user1.getId());

    }

    @Test
    void testExistsUser() {
        Mockito.when(userRepository.findByuserName("roja")).thenReturn(optional);
        Mockito.when(userRepository.save(user)).thenReturn(user);
        assertThrows(UserAlreadyExistException.class,
                () -> userServiceImpl.register(user));
    }

    @Test
    void testExistsLogin() throws UserNotFoundException {
        Mockito.when(userRepository.findByuserNameAndPassword("roja", "roja123")).thenReturn(user);
        User fetchedUser = userServiceImpl.login("roja", "roja123");
        assertEquals("roja", fetchedUser.getUserName());
    }

    @Test
    void testForLoginNotExistUser() {
        Mockito.when(userRepository.save(user)).thenReturn(user);
        assertThrows(UserNotFoundException.class,
                () -> userServiceImpl.login("roja", "roja123"));
    }

    @Test
    public void testUpdateDetails() throws SameUsernameExistsException {
        User userUpdate = new User();
        userUpdate.setEmail("email");
        userUpdate.setUserName("username");
        Mockito.when(userRepository.findByuserName("roja")).thenReturn(optional);
        Mockito.when(userServiceImpl.updateDetails(userUpdate, "roja"))
                .thenReturn(user);
        User user2 = userServiceImpl.updateDetails(user, "roja");
        assertNotNull(user2);
        assertEquals("username", user2.getUserName());

    }

}
