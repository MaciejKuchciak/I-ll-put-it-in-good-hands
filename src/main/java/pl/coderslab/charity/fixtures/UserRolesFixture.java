package pl.coderslab.charity.fixtures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.entity.UserRoles;
import pl.coderslab.charity.service.UserRolesService;

import java.util.Arrays;
import java.util.List;

@Component
public class UserRolesFixture {

    private UserRolesService userRolesService;
    private List<UserRoles> userRolesList = Arrays.asList(
            new UserRoles(null,"ROLE_USER","User with access only to content"),
            new UserRoles(null,"ROLE_ADMIN","User with access to content and configuration panel")
    );

    @Autowired
    public UserRolesFixture(UserRolesService userRolesService) {
        this.userRolesService = userRolesService;
    }

    public void loadIntoDB() {
        for(UserRoles userRoles : userRolesList){
            userRolesService.addUserRole(userRoles);
        }
    }

}
