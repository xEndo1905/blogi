package org.example.blogs.serwis;

import org.example.blogs.model.Komentarz;
import org.example.blogs.model.Post;
import org.example.blogs.model.Uzytkownik;
import org.example.blogs.repository.KomentarzRepository;
import org.example.blogs.repository.PostRepository;
import org.example.blogs.repository.UzytkownikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class KomentarzSerwis {

    @Autowired
    private KomentarzRepository komentarzRepo;

    @Autowired
    private PostRepository postRepo;

    @Autowired
    private UzytkownikRepository uzytkownikRepo;

    public Komentarz dodajKomentarz(Long postId, String tresc, Authentication auth) {
        Uzytkownik autor = uzytkownikRepo.findByLogin(auth.getName()).orElseThrow();
        Post post = postRepo.findById(postId).orElseThrow();

        boolean istnieje = komentarzRepo.findByPostAndAutor(post, autor).isPresent();
        if (istnieje) {
            throw new RuntimeException("Już dodałeś komentarz do tego posta.");
        }

        Komentarz komentarz = new Komentarz();
        komentarz.setAutor(autor);
        komentarz.setPost(post);
        komentarz.setTresc(tresc);
        komentarz.setDataDodania(LocalDateTime.now());

        return komentarzRepo.save(komentarz);
    }

    public List<Komentarz> pobierzKomentarzeDlaPosta(Long postId) {
        Post post = postRepo.findById(postId).orElseThrow();
        return komentarzRepo.findByPost(post);
    }
}
