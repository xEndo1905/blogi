package org.example.blogs.repository;

import org.example.blogs.model.Komentarz;
import org.example.blogs.model.Post;
import org.example.blogs.model.Uzytkownik;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface KomentarzRepository extends JpaRepository<Komentarz, Long> {
    List<Komentarz> findByPost(Post post);
    Optional<Komentarz> findByPostAndAutor(Post post, Uzytkownik autor);
}
