package smweb.chillana.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import smweb.chillana.model.UserModel;
import smweb.chillana.model.UserProfileModel;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfileModel, Integer> {
    Optional<UserProfileModel> findByUser(UserModel user);
}
