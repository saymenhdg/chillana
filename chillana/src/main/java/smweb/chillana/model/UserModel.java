package smweb.chillana.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "User_info")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String age;
    private String username;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private UserProfileModel userProfileModel;

    public UserModel(String username,String password){
        this.username = username;
        this.password = password;
    }
}
