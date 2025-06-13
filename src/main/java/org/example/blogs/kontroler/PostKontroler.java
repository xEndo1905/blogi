package org.example.blogs.kontroler;

import org.example.blogs.model.Post;
import org.example.blogs.serwis.PostSerwis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostKontroler {

    @Autowired
    private PostSerwis serwis;

    @GetMapping
    public Page<Post> wszystkie(
            @RequestParam(required = false) String tytul,
            Pageable pageable
    ) {
        return serwis.pobierzWszystkie(tytul, pageable);
    }


    @GetMapping("/{id}")
    public Post jeden(@PathVariable Long id) {
        return serwis.pobierzPoId(id).orElseThrow();
    }

    @PostMapping
    public Post dodaj(@RequestBody Post post, Authentication auth) {
        return serwis.dodajPost(post, auth);
    }

    @PutMapping("/{id}")
    public Post edytuj(@PathVariable Long id, @RequestBody Post post, Authentication auth) {
        return serwis.edytujPost(id, post, auth);
    }

    @DeleteMapping("/{id}")
    public void usun(@PathVariable Long id, Authentication auth) {
        serwis.usunPost(id, auth);
    }
}
