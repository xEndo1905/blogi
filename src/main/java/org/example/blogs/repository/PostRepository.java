package org.example.blogs.repository;

import org.example.blogs.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByTytulContainingIgnoreCase(String tytul, Pageable pageable);
}
