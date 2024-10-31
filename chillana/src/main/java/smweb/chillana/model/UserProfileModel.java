package smweb.chillana.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_profile")
public class UserProfileModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int profileId;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserModel user;
    private String location;
    @Lob
    private byte[] profileImage;
    @Lob
    private byte[] backgroundImage;
    private String bio;

    public UserProfileModel(UserModel user, String location, byte[] profileImage, byte[] backgroundImage, String bio) {
        this.user = user;
        this.location = location;
        this.profileImage = profileImage;
        this.backgroundImage = backgroundImage;
        this.bio = bio;
    }


}
