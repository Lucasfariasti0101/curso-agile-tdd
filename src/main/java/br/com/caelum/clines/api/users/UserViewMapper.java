package br.com.caelum.clines.api.users;

import br.com.caelum.clines.shared.domain.User;
import br.com.caelum.clines.shared.infra.Mapper;
import org.springframework.stereotype.Component;

@Component
public class UserViewMapper implements Mapper<User, UserView> {
    @Override
    public UserView map(User source) {
        var id = source.getId();
        var name = source.getName();
        var email = source.getEmail();
        return new UserView(id, name, email);
    }
}
