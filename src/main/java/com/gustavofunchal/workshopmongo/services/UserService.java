package com.gustavofunchal.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustavofunchal.workshopmongo.domain.User;
import com.gustavofunchal.workshopmongo.repositories.UserRepository;
import com.gustavofunchal.workshopmongo.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {

	private UserRepository repository;

	@Autowired
	public UserService(UserRepository repository) {
		this.repository = repository;
	}

	public List<User> findAll() {
		return repository.findAll();
	}

	public User findById(String id) {
		return repository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Document not found"));
	}
}
