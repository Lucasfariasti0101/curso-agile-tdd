package br.com.caelum.clines.api.users;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserView {
    private Long id;
    private String name;
    private String email;
}
