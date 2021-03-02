package com.gustavofunchal.workshopmongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gustavofunchal.workshopmongo.domain.Post;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    List<Post> findByTitleContaining(String text);
    List<Post> findByTitleContainingIgnoreCase(String text);
}
