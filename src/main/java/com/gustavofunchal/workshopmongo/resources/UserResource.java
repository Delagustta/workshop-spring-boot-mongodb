package com.gustavofunchal.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gustavofunchal.workshopmongo.domain.User;
import com.gustavofunchal.workshopmongo.dto.UserDTO;
import com.gustavofunchal.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	private UserService service;

	@Autowired
	public UserResource(UserService service) {
		this.service = service;
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<UserDTO>> findAll() {
		List<UserDTO> listDto = service.findAll()
				.stream()
				.map(user -> new UserDTO(user))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		User obj = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(obj));
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDto) {
		User obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

}
