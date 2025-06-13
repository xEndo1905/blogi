package org.example.blogs.serwis;

import org.example.blogs.model.Post;
import org.example.blogs.model.Uzytkownik;
import org.example.blogs.repository.PostRepository;
import org.example.blogs.repository.UzytkownikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostSerwis {

    @Autowired
    private PostRepository postRepo;

    @Autowired
    private UzytkownikRepository uzytkownikRepo;

    public Page<Post> pobierzWszystkie(String tytul, Pageable pageable) {
        if (tytul == null || tytul.isBlank()) {
            return postRepo.findAll(pageable);
        } else {
            return postRepo.findByTytulContainingIgnoreCase(tytul, pageable);
        }
    }

    public Optional<Post> pobierzPoId(Long id) {
        return postRepo.findById(id);
    }

    public Post dodajPost(Post post, Authentication auth) {
        Uzytkownik autor = uzytkownikRepo.findByLogin(auth.getName()).orElseThrow();
        post.setAutor(autor);
        post.setDataUtworzenia(LocalDateTime.now());
        return postRepo.save(post);
    }

    public void usunPost(Long id, Authentication auth) {
        Post post = postRepo.findById(id).orElseThrow();
        if (!post.getAutor().getLogin().equals(auth.getName())) {
            throw new RuntimeException("Nie jesteś autorem posta");
        }
        postRepo.delete(post);
    }

    public Post edytujPost(Long id, Post nowyPost, Authentication auth) {
        Post post = postRepo.findById(id).orElseThrow();
        if (!post.getAutor().getLogin().equals(auth.getName())) {
            throw new RuntimeException("Nie jesteś autorem posta");
        }
        post.setTytul(nowyPost.getTytul());
        post.setTresc(nowyPost.getTresc());
        return postRepo.save(post);
    }
}
