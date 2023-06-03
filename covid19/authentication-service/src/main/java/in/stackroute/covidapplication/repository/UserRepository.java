package in.stackroute.covidapplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.stackroute.covidapplication.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByuserName(String userName);

    User findByuserNameAndPassword(String userName, String password);
}
