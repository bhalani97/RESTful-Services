package com.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.jws.soap.SOAPBinding.Use;
import javax.validation.Valid;

import org.jboss.logging.Param;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class JpaResource {

	@Autowired
	private UserRepository userResource;

	@Autowired
	private  PostRepository postRepository;
	
	@GetMapping("/jpa/users")
	public List<User> retriveUsers() {
		return userResource.findAll();
	}

	@GetMapping("/jpa/users/{id}")
	public Resource<User> retriveUser(@PathVariable int id) {

		Optional<User> user = userResource.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("ID- " + id);
		}

		Resource<User> resource = new Resource<User>(user.get());
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retriveUsers());
		resource.add(linkTo.withRel("all-users"));
		return resource;
	}

	@DeleteMapping("/jpa/ users/{id}")
	public String deleteUser(@PathVariable int id) {

		userResource.deleteById(id);
		return "deleted";
	}

	@PostMapping("/jpa//users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userResource.save(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(uri).build();

	}

	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrivePost(@PathVariable int id) {
		Optional<User> user = userResource.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("id : " + id);
		}
		return user.get().getPost();

	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable int id,@RequestBody Post post) {
		Optional<User> userOptional = userResource.findById(id);
		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("id : " + post.getId());
		}
		User user = userOptional.get();
		post.setUser(user);
		postRepository.save(post);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId())
				.toUri();
		return ResponseEntity.created(uri).build();

	}

}
