package it.intesys.codylab.controller;
import it.intesys.codylab.dto.User;
import it.intesys.codylab.dto.UserProfile;
import it.intesys.codylab.dto.WorkingHours;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.List;

@RestController
@RequestMapping("/users")
    public class UsersRestController {
    @GetMapping("/{userid}")
    public User getUser(@PathVariable Long userid) {
        return new User()
                .setId(userid)
                .setFirstName("Jack")
                .setLastName("Smith")
                .setEmail("jack.smith@gmail.com")
                .setProfile(UserProfile.PM)
                .setWorkingHours(WorkingHours.of(Duration.ofHours(8L)));


    }
}

