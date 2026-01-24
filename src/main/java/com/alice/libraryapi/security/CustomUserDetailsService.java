package com.alice.libraryapi.security;

import com.alice.libraryapi.model.Usuario;
import com.alice.libraryapi.service.UsuarioService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
@Getter
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioService service;


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = service.obterPorLogin(login);

        if(usuario == null) {
            throw new UsernameNotFoundException("Usuario não encontrado");
        }


        return User.builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .roles(usuario.getRoles().toArray(new String[usuario.getRoles().size()]))
                .build();
    }
}
