package com.gustavofunchal.workshopmongo.dto;

import com.gustavofunchal.workshopmongo.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class AuthorDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private String name;

	public AuthorDTO(User obj) {
		id = obj.getId();
		name = obj.getName();
	}

}
