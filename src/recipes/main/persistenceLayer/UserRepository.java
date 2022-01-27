package recipes.main.persistenceLayer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import recipes.main.businessLayer.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findUserByEmail(String email);
}
