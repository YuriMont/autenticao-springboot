package br.com.seguranca.autenticacao.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginFormModel {
    private String email;
    private String password;
}
