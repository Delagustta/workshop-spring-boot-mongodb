package com.gustavofunchal.workshopmongo.resources;

import com.gustavofunchal.workshopmongo.domain.Post;
import com.gustavofunchal.workshopmongo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

  private final PostService service;

  @Autowired
  public PostResource(PostService service) {
    this.service = service;
  }

  @GetMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Post> findById(@PathVariable String id) {
    Post obj = service.findById(id);
    return ResponseEntity.ok().body(obj);
  }
}
