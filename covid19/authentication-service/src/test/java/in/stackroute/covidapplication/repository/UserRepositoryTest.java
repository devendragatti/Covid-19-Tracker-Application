package in.stackroute.covidapplication.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import in.stackroute.covidapplication.model.User;

import org.mockito.MockitoAnnotations;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setUserName("rose");
        user.setPassword("rose123");
        user.setEmail("rose@gmail.com");
    }

    @AfterEach
    public void tearDown() throws Exception {
        userRepository.deleteAll();
    }

    @Test
    public void testRegisterUserSuccess() {
        userRepository.save(user);
        User fetchUser = userRepository.findById(user.getId()).get();
        assertThat(user.getId(), is(fetchUser.getId()));
    }

    @Test
    public void testLoginUserSuccess() {
        userRepository.save(user);
        User fetchUser = userRepository.findById(user.getId()).get();
        String name = fetchUser.getUserName();
        assertEquals("rose", name);

    }

    @Test
    public void testFindByUserNameMethod() {
        userRepository.save(user);
        User fetchUser = userRepository.findByuserName(user.getUserName()).get();
        assertEquals(user.getId(), fetchUser.getId());
    }

    @Test
    public void testFindByUserIdAndPasswordMethod() {
        userRepository.save(user);
        User fetchUser = userRepository.findByuserNameAndPassword(user.getUserName(), user.getPassword());
        assertEquals(user.getId(), fetchUser.getId());
    }
}