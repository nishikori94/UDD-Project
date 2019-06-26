package com.techprimers.elastic.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techprimers.elastic.dto.CartDTO;
import com.techprimers.elastic.model.Article;
import com.techprimers.elastic.model.Magazine;
import com.techprimers.elastic.model.User;
import com.techprimers.elastic.service.ArticleService;
import com.techprimers.elastic.service.MagazineService;
import com.techprimers.elastic.service.UserService;

@RestController
@RequestMapping(value = "/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private MagazineService magazineService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Collection<User>> getUsers() {

		ArrayList<User> users = (ArrayList<User>) userService.findAll();
		if (users != null) {
			return new ResponseEntity<Collection<User>>(users, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}


	@RequestMapping(method = RequestMethod.POST)
	private ResponseEntity<User> saveUser(@Valid @RequestBody User user) {

		User savedUser = userService.save(user);

		return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/getCart", method=RequestMethod.GET)
	public ResponseEntity<CartDTO> getCartContent() {
		User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		Set<Article> articles = user.getArticles();
		Set<Magazine> magazines =user.getMagazines();

		CartDTO cart = new CartDTO();
		cart.setArticles(articles);
		cart.setMagazines(magazines);
		System.out.println();
		return new ResponseEntity<>(cart,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/addArticleToCart/{id}", method=RequestMethod.PUT)
	public ResponseEntity<?> addArticleToCart(@PathVariable Long id) {
		Article article = articleService.findOne(id);
		User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		user.getArticles().add(article);
		userService.save(user);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/addMagazineToCart/{id}", method=RequestMethod.PUT)
	public ResponseEntity<?> addMagazineToCart(@PathVariable String id) {
		Magazine magazine = magazineService.findOne(id);
		User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		user.getMagazines().add(magazine);
		userService.save(user);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

}
