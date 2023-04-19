package br.com.caelum.clines.api.users;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserFormMapperTest {

    private final String NAME = "Fulano da Silva";
    private final String EMAIL = "fulano3@email.com";
    private final String PASSWORD = "passwd";
    private final UserFormMapper mapper = new UserFormMapper();

    @Test
    void shouldConvertUserFormToUser() {
        var userForm = new UserForm(NAME, EMAIL, PASSWORD);
        var user = mapper.map(userForm);

        assertEquals(NAME, user.getName());
        assertEquals(EMAIL, user.getEmail());
        assertEquals(PASSWORD, user.getPassword());

    }
}
