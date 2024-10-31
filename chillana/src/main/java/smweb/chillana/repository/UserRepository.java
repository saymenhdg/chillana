package smweb.chillana.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import smweb.chillana.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Integer> {
    UserModel findByUsername(String username);
}
