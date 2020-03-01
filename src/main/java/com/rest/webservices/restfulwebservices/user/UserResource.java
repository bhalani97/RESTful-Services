package com.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

	@Autowired
	private UserDaoService daoService;

	@GetMapping("users")
	public List<User> retriveUsers() {
		return daoService.findAll();
	}

	@GetMapping("users/{id}")
	public Resource<User> retriveUser(@PathVariable int id) {

		User user = daoService.findOne(id);
		if (user == null) {
			throw new UserNotFoundException("ID- " + id);
		}

		Resource<User> resource = new Resource<User>(user);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retriveUsers());
		resource.add(linkTo.withRel("all-users"));
		return resource;
	}

	@DeleteMapping("users/{id}")
	public User deleteUser(@PathVariable int id) {

		User user = daoService.deleteById(id);
		if (user == null) {
			throw new UserNotFoundException("ID- " + id);
		}
		return user;
	}

	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = daoService.save(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(uri).build();

	}

}
