package in.stackroute.covidapplication.respository;

import org.springframework.data.mongodb.repository.MongoRepository;

import in.stackroute.covidapplication.model.User;

public interface WatchListRepository extends MongoRepository<User, String> {
    public User findByusername(String username);

}
