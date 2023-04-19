package br.com.caelum.clines.api.users;

import br.com.caelum.clines.shared.domain.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class UserViewMapperTest {

    private final String NAME = "Fulano da Silva";
    private final String EMAIL = "fulano2@emal.com";
    private final String PASSWORD = "passwd";
    private final UserViewMapper mapper = new UserViewMapper();


    @Test
    void shouldConvertUserModelToUserModelView() {

        User user = new User(NAME, EMAIL, PASSWORD);

        UserView userView = mapper.map(user);

        assertEquals(NAME, userView.getName());
        assertEquals(EMAIL, userView.getEmail());

    }


}
