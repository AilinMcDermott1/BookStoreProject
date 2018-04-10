package com.ailin.bookstore.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ailin.bookstore.model.Book;
import com.ailin.bookstore.model.User;
import com.ailin.bookstore.repository.*;
import com.ailin.bookstore.service.SecurityService;
import com.ailin.bookstore.service.UserService;
import com.ailin.bookstore.validator.UserValidator;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registration(Model model) {
		model.addAttribute("userForm", new User());

		return "registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) 
	{
		userValidator.validate(userForm, bindingResult);

		if (bindingResult.hasErrors()) {
			return "registration";
		}

		userService.save(userForm);

		securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

		return "redirect:/welcome";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("error", "Your username and password is invalid.");

		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully.");

		return "login";
	}

	@RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
	public String welcome(Model model) {
		return "welcome";
	}

	@RequestMapping(value = "/addBookPage", method = RequestMethod.GET)
	public String addBookPage(@Valid Book book, BindingResult bookResult, Model model) {

		return "addBook";
	}

	@RequestMapping(value = "/addBook", method = RequestMethod.POST)
	public String addBook(Book book, @RequestParam("author") String author, 
			@RequestParam("title") String title,
			@RequestParam("genre") String genre,
			@RequestParam("price") double price) {

		bookRepository.save(book);

		return "welcome";

	}

	@RequestMapping(value = "/viewallbooks", method = RequestMethod.GET)
	public String bookList(@Valid Book book, Model model) {
		List<Book> listOfBooks = new ArrayList<>();
		listOfBooks = bookRepository.findAll();

		model.addAttribute("listOfBooks", listOfBooks);

		return "listOfBooks";
	}

	@RequestMapping(value = " /shoppingcart/{bookId}", method=RequestMethod.GET)
	public String shoppingCart(@PathVariable("bookId")Long id, Model model) {

		// This gets the currently logged in user
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName(); 
		User user = userRepository.findByUsername(username);

		System.out.println(username);

		//Retrieve book
		Book book = bookRepository.findById(id);
		String title = book.getTitle();

		System.out.println(username + "saved" + "Book: " + title);

		user.saveBookToCart(book);
		userRepository.save(user);

		return "addToCart";
	}

	@RequestMapping(value = "/viewShoppingCart", method = RequestMethod.GET)
	public String viewShoppingCart(@Valid Book book, Model model) {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName(); // Authentication for 
		User user = userRepository.findByUsername(username);

		List<Book> shoppingCart = user.getShoppingCart();

		model.addAttribute("shoppingCart", shoppingCart);

		return "shoppingCart";

	}
	
    @RequestMapping(value = " /deletebook/{bookId}", method=RequestMethod.GET)
    public String removeBookFromShoppingCart(@PathVariable("bookId")Long id, Model model) {
    	
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName(); // Authentication for 
        User user = userRepository.findByUsername(username);
        
        
        Book book = bookRepository.findById(id);
        
        user.removeBookFromShoppingCart(book);
        List<Book> shoppingCart = user.getShoppingCart();
        userRepository.save(user);
        
        String title = book.getTitle();
        System.out.println(username + "removed" + "Book: " + title);
        
        model.addAttribute("shoppingCart", shoppingCart);
        
		return "deletedBook";
    }


}
