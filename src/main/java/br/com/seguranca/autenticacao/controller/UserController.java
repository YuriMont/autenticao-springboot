package br.com.seguranca.autenticacao.controller;

import br.com.seguranca.autenticacao.model.LoginFormModel;
import br.com.seguranca.autenticacao.model.UserModel;
import br.com.seguranca.autenticacao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService us;

    @GetMapping("/listarTodos")
    public ResponseEntity<List<UserModel>> listarTodos() {
        return us.listarTodos();
    }

    @PostMapping("/registrar")
    public ResponseEntity<UserModel> registrar(@RequestBody UserModel user) {
        return us.registrar(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> logar(@RequestBody LoginFormModel login) {
        return us.logar(login);
    }
    }


