package br.com.seguranca.autenticacao.service;

import br.com.seguranca.autenticacao.config.JwtService;
import br.com.seguranca.autenticacao.model.LoginFormModel;
import br.com.seguranca.autenticacao.model.UserModel;
import br.com.seguranca.autenticacao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository ur;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public ResponseEntity<List<UserModel>> listarTodos(){
        return ResponseEntity.ok(ur.findAll());
    }

    public ResponseEntity<UserModel> registrar(UserModel user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(ur.save(user));
    }

    public ResponseEntity<String> logar(LoginFormModel login) {
        Optional<UserModel> user = ur.findByEmail(login.getEmail());

        if(user.isEmpty() || !passwordEncoder.matches(login.getPassword(), user.get().getPassword())){
            return ResponseEntity.ok("Email ou senha incorreto!");
        }

        String token = jwtService.generateToken(user.get().getEmail());
        return ResponseEntity.ok(token);
    }
}
