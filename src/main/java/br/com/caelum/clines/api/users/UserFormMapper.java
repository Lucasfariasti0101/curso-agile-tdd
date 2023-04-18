package br.com.caelum.clines.api.users;

import br.com.caelum.clines.shared.domain.User;
import br.com.caelum.clines.shared.infra.Mapper;
import org.springframework.stereotype.Component;

@Component
public class UserFormMapper implements Mapper<UserForm, User> {

    @Override
    public User map(UserForm source) {
        var name = source.getName();
        var email = source.getEmail();
        var password = source.getPassword();
        return new User(name, email, password);
    }
}
