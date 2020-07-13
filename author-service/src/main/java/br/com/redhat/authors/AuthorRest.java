package br.com.redhat.authors;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
public class AuthorRest {

	@Autowired
	private AuthorService service;
	
	@GetMapping
	public ResponseEntity<List<Author>> all() {
		return new ResponseEntity<>(service.all(), HttpStatus.OK);
	}
	
	@GetMapping("/{name}")
	public ResponseEntity<Author> byName(@PathVariable("name") String name) {
		Author author = service.byName(name);
		if(Objects.isNull(author)) 
			return ResponseEntity.noContent().build();

		return new ResponseEntity<>(author, HttpStatus.OK);
	}
	
	// @GetMapping("/{id}")
	public ResponseEntity<Author> byId(@PathVariable("id") String id) {
		Author author = service.byId(id);
		if(Objects.isNull(author)) 
			return ResponseEntity.notFound().build();
		
		return new ResponseEntity<>(author, HttpStatus.OK);
	}
}
