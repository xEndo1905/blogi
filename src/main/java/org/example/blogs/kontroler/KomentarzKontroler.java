package org.example.blogs.kontroler;

import org.example.blogs.model.Komentarz;
import org.example.blogs.serwis.KomentarzSerwis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/komentarze")
public class KomentarzKontroler {

    @Autowired
    private KomentarzSerwis serwis;

    @PostMapping("/{postId}")
    public Komentarz dodaj(
            @PathVariable Long postId,
            @RequestBody Map<String, String> body,
            Authentication auth
    ) {
        return serwis.dodajKomentarz(postId, body.get("tresc"), auth);
    }

    @GetMapping("/{postId}")
    public List<Komentarz> dlaPosta(@PathVariable Long postId) {
        return serwis.pobierzKomentarzeDlaPosta(postId);
    }
}
