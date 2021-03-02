package com.gustavofunchal.workshopmongo.services;

import com.gustavofunchal.workshopmongo.domain.Post;
import com.gustavofunchal.workshopmongo.domain.User;
import com.gustavofunchal.workshopmongo.dto.UserDTO;
import com.gustavofunchal.workshopmongo.repositories.PostRepository;
import com.gustavofunchal.workshopmongo.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

  private PostRepository repository;

  @Autowired
  public PostService(PostRepository repository) {
    this.repository = repository;
  }

  public List<Post> findAll() {
    return repository.findAll();
  }

  public Post findById(String id) {
    return repository
        .findById(id)
        .orElseThrow(() -> new ObjectNotFoundException("Document not found"));
  }

  public List<Post> findByTitle(String text, boolean ignoreCase) {
    if (ignoreCase) {
      return repository.findByTitleContainingIgnoreCase(text);
    }
    return repository.findByTitleContaining(text);
  }

  public List<Post> searchTitle(String text){
    return repository.searchTitle(text);
  }
}
