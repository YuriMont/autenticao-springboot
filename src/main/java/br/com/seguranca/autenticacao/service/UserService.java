package br.com.seguranca.autenticacao.service;

import br.com.seguranca.autenticacao.config.JwtService;
import br.com.seguranca.autenticacao.model.LoginFormModel;
import br.com.seguranca.autenticacao.model.MessageModel;
import br.com.seguranca.autenticacao.model.UserModel;
import br.com.seguranca.autenticacao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository ur;

    @Autowired
    private MessageModel msg;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public ResponseEntity<List<UserModel>> listarTodos(){
        return ResponseEntity.ok(ur.findAll());
    }

    public ResponseEntity<?> registrar(UserModel user){
        if(!ur.findByEmail(user.getEmail()).isEmpty()){
            msg.setMessage("Email já cadastrado");
            return new ResponseEntity<MessageModel>(msg, HttpStatus.BAD_REQUEST);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(ur.save(user));
    }

    public ResponseEntity<?> logar(LoginFormModel login) {
        Optional<UserModel> user = ur.findByEmail(login.getEmail());

        if(user.isEmpty() || !passwordEncoder.matches(login.getPassword(), user.get().getPassword())){
            msg.setMessage("Email ou senha incorreto!");
            return new ResponseEntity<MessageModel>(msg, HttpStatus.BAD_REQUEST);
        }

        String token = jwtService.generateToken(user.get().getEmail());
        Map<String, String> jwt = new HashMap<String, String>();
        jwt.put("token", token);

        return ResponseEntity.ok(jwt);
    }
}
