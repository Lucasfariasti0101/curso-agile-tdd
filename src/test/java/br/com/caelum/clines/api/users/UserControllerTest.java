package br.com.caelum.clines.api.users;

import br.com.caelum.clines.shared.domain.User;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestPropertySource(properties = {"DB_NAME=clines_test", "spring.jpa.hibernate.ddlAuto:create-drop"})
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
@Transactional
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestEntityManager entityManager;

    private final Gson gson = new Gson();

    @Test
    void shouldReturn404WhenNotExistUserByCode() throws Exception {
        mockMvc.perform(get("/users/555"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturn201WhenSuccessfullyInCreatedBy() throws Exception {
        var userForm = new UserForm("Fulano2", "fulano3@email.com", "passwd");

        String userFormJson = gson.toJson(userForm);

        mockMvc.perform(post("/users/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userFormJson))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldReturn409WhenExistsUserWithEmailInCreatedBy() throws Exception {
        var userToSaved = new User("Fulano", "fulano3@email.com", "passwd");
        entityManager.persist(userToSaved);

        var userForm = new UserForm("Fulano2", "fulano3@email.com", "passwd");

        String userFormJson = gson.toJson(userForm);

        mockMvc.perform(post("/users/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userFormJson))
                .andExpect(status().isConflict());
    }

    @Test
    void shouldReturnAnUserByCode() throws Exception {
        var user = new User("Fulano", "fulano3@email.com", "passwd");

        entityManager.persist(user);

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andDo(log())
                .andExpect(jsonPath("$.name", equalTo(user.getName())))
                .andExpect(jsonPath("$.email", equalTo(user.getEmail())));
    }

}
