package org.example.blogs.kontroler;

import org.example.blogs.serwis.UzytkownikSerwis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UzytkownikKontroler {

    @Autowired
    private UzytkownikSerwis serwis;

    @PostMapping("/register")
    public String zarejestruj(@RequestParam String login, @RequestParam String haslo) {
        serwis.zarejestruj(login, haslo);
        return "Użytkownik zarejestrowany!";
    }

    @GetMapping("/test")
    public String test() {
        return "Dostęp do chronionej strony OK!";
    }
}
