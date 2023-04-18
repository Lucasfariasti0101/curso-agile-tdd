package br.com.caelum.clines.api.users;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> createdBy(@RequestBody @Valid UserForm form) {

        Long id = userService.createUserBy(form);
        URI uri = URI.create("/users/").resolve(id.toString());

        return ResponseEntity.created(uri).build();

    }

    @GetMapping
    public List<UserView> list() {
       return userService.listAllUsers();
    }

    @GetMapping("/{id}")
    public UserView show(@PathVariable Long id) {
        return userService.showUserBy(id);
    }

}
