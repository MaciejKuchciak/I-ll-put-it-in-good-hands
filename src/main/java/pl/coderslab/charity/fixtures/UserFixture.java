package pl.coderslab.charity.fixtures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.entity.UserRoles;
import pl.coderslab.charity.service.UserRolesService;
import pl.coderslab.charity.service.UserService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class UserFixture {

    private UserService userService;
    private UserRolesService userRolesService;
    private List<User> users = Arrays.asList(
            new User(null, "Monia200", "monika.dab@hotmail.com", "Monika", "Dąb", "123", LocalDateTime.of(2021, 1, 1, 13, 50, 50), LocalDateTime.of(2020, 1, 1, 12, 0, 0), true, null),
            new User(null, "Mirek19", "miroslaw.zelent@interia.pl", "Mirosław", "Zelent", "123", LocalDateTime.of(2021, 1, 1, 12, 0, 0), LocalDateTime.of(2020, 1, 1, 12, 0, 0), true, null),
            new User(null, "Maciek", "maciek@onet.pl", "Maciej", "Kuchciak", "admin", LocalDateTime.of(2021, 1, 1, 12, 0, 0), LocalDateTime.of(2020, 1, 1, 12, 0, 0), true, null),
            new User(null, "admin", "admin@admin.pl", "", "", "admin", LocalDateTime.of(2021, 1, 1, 12, 0, 0), LocalDateTime.of(2020, 1, 1, 12, 0, 0), true, null)
    );

    @Autowired
    public UserFixture(UserService userService, UserRolesService userRolesService) {
        this.userService = userService;
        this.userRolesService = userRolesService;
    }

    public void loadIntoDB() {

        List<UserRoles> userRolesList = userRolesService.getAllUserRoles();

        User user1 = users.get(0);
        User user2 = users.get(1);
        User user3 = users.get(2);
        User user4 = users.get(3);

        user1.setUserRoles(userRolesList.get(0));
        user2.setUserRoles(userRolesList.get(0));
        user3.setUserRoles(userRolesList.get(1));
        user4.setUserRoles(userRolesList.get(1));

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);
    }

}
