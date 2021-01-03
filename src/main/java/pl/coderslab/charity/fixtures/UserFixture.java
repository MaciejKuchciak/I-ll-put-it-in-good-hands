package pl.coderslab.charity.fixtures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.UserService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class UserFixture {

    private final UserService userService;
    private List<User> users = Arrays.asList(
            new User(null,"Monia200","monika.dab@hotmail.com","Monika","Dąb","123", LocalDateTime.of(2021,1,1,12,0,0), LocalDateTime.of(2020,1,1,12,0,0),true,null),
            new User(null,"Mirek19","miroslaw.zelent@interia.pl","Mirosław","Zelent","123",LocalDateTime.of(2021,1,1,12,0,0), LocalDateTime.of(2020,1,1,12,0,0),true,null),
            new User(null,"Maciek","maciek@onet.pl","Maciej","Kuchciak","admin",LocalDateTime.of(2021,1,1,12,0,0), LocalDateTime.of(2020,1,1,12,0,0),true,null),
            new User(null,"admin","admin@admin.pl","","","admin",LocalDateTime.of(2021,1,1,12,0,0), LocalDateTime.of(2020,1,1,12,0,0),true,null)
    );

    @Autowired
    public UserFixture(UserService userService) {
        this.userService = userService;
    }
    public void loadIntoDB() {
        for(User user : users){
            userService.add(user);
        }
    }
}
