package org.example.blogs.serwis;

import org.example.blogs.model.Uzytkownik;
import org.example.blogs.repository.UzytkownikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UzytkownikSerwis implements UserDetailsService {

    @Autowired
    private UzytkownikRepository repo;

    @Autowired
    private PasswordEncoder encoder;

    public void zarejestruj(String login, String haslo) {
        Uzytkownik u = new Uzytkownik();
        u.setLogin(login);
        u.setHaslo(encoder.encode(haslo));
        repo.save(u);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Uzytkownik u = repo.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Nie znaleziono użytkownika"));

        return User.builder()
                .username(u.getLogin())
                .password(u.getHaslo())
                .roles("USER")
                .build();
    }
}
