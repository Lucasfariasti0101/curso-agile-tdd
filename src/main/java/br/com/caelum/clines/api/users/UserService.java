package br.com.caelum.clines.api.users;

import br.com.caelum.clines.shared.domain.User;
import br.com.caelum.clines.shared.exceptions.ResourceAlreadyExistsException;
import br.com.caelum.clines.shared.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserFormMapper formMapper;
    private final UserViewMapper viewMapper;

    @Transactional
    public Long createUserBy(UserForm form) {
        userRepository.findByEmail(form.getEmail()).ifPresent(user -> {
            throw new ResourceAlreadyExistsException("The email is already being used");
        });

        User user = formMapper.map(form);

        User userSaved = userRepository.save(user);

        return userSaved.getId();

    }

    public List<UserView> listAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(viewMapper::map)
                .collect(Collectors.toList());

    }


    public UserView showUserBy(Long id) {
        return userRepository.findById(id)
                .map(viewMapper::map)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

    }
}
