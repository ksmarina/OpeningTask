package ru.opening.rest.services;

import io.restassured.response.Response;
import ru.opening.rest.businessObjects.NewUserAnswer;
import ru.opening.rest.businessObjects.UserFromList;

import java.util.ArrayList;
import java.util.List;

public class UserCheckService {

    public static boolean isUserValid(UserFromList user) {
        boolean isValid = true;
        if (user.getId() == null || user.getId().isEmpty()) {
            isValid = false;
        } else if (user.getEmail() == null || user.getEmail().isEmpty()) {
            isValid = false;
        }
        if (user.getFirstName() == null || user.getFirstName().isEmpty()) {
            isValid = false;
        }
        if (user.getLastName() == null || user.getLastName().isEmpty()) {
            isValid = false;
        }
        if (user.getAvatar() == null || user.getAvatar().isEmpty()) {
            isValid = false;
        }
        return isValid;
    }

    public static List<UserFromList> getUsersList(Response usersJson) {
        List<UserFromList> users = new ArrayList<UserFromList>();
        Integer numberOfUsers = usersJson.path("per_page");
        for (int i = 0; i < numberOfUsers; i++) {
            UserFromList user = new UserFromList();
            user.setId(usersJson.path("data.id[" + i + "]").toString());
            user.setEmail(usersJson.path("data.email[" + i + "]"));
            user.setFirstName(usersJson.path("data.first_name[" + i + "]"));
            user.setLastName(usersJson.path("data.last_name[" + i + "]"));
            user.setAvatar(usersJson.path("data.avatar[" + i + "]"));
            users.add(user);
        }
        return users;
    }

    public static NewUserAnswer getNewUserAnswer(Response response) {
        NewUserAnswer answer = new NewUserAnswer(response.path("name.value"), response.path("job.value"), response.path("id").toString(), response.path("createdAt").toString());
        return answer;
    }
}
