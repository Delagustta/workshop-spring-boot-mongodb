package com.gustavofunchal.workshopmongo.config;

import com.gustavofunchal.workshopmongo.domain.Post;
import com.gustavofunchal.workshopmongo.domain.User;
import com.gustavofunchal.workshopmongo.dto.AuthorDTO;
import com.gustavofunchal.workshopmongo.dto.CommentDTO;
import com.gustavofunchal.workshopmongo.repositories.PostRepository;
import com.gustavofunchal.workshopmongo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import static java.util.Collections.singletonList;

@Configuration
public class Instantiation implements CommandLineRunner {

  private UserRepository userRepository;
  private PostRepository postRepository;

  @Autowired
  public Instantiation(UserRepository userRepository, PostRepository postRepository) {
    this.userRepository = userRepository;
    this.postRepository = postRepository;
  }

  @Override
  public void run(String... args) throws Exception {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

    userRepository.deleteAll();
    postRepository.deleteAll();

    User maria = new User(null, "Maria Brown", "maria@gmail.com");
    User alex = new User(null, "Alex Green", "alex@gmail.com");
    User bob = new User(null, "Bob Grey", "bob@gmail.com");

    userRepository.saveAll(Arrays.asList(maria, alex, bob));

    Post post1 =
        new Post(
            null,
            sdf.parse("21/03/2018"),
            "Partiu viagem",
            "Vou viajar para SP, abraços.",
            new AuthorDTO(maria));

    Post post2 =
        new Post(
            null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));

    CommentDTO c1 =
        new CommentDTO("Boa viagem, mano!", sdf.parse("21/03/2021"), new AuthorDTO(alex));

    CommentDTO c2 = new CommentDTO("Aproveite!", sdf.parse("22/03/2021"), new AuthorDTO(bob));

    CommentDTO c3 =
        new CommentDTO("Tenha um otimo dia!", sdf.parse("23/03/2021"), new AuthorDTO(alex));

    post1.getComments().addAll(Arrays.asList(c1, c2));
    post2.getComments().addAll(singletonList(c3));

    postRepository.saveAll(Arrays.asList(post1, post2));

    maria.getPosts().addAll(Arrays.asList(post1, post2));
    userRepository.save(maria);
  }
}
