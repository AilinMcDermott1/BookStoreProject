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
import org.springframework.web.bind.annotation.*;

import com.ailin.bookstore.facade.OrderServiceFacadeImpl;
import com.ailin.bookstore.model.*;
import com.ailin.bookstore.prototype.*;
import com.ailin.bookstore.repository.*;
import com.ailin.bookstore.service.*;
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
	
	@Autowired
	private PurchaseHistoryRepository purchaseHistoryRepository; 
    
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

		
		User userBuilder=new User();
		System.out.println("User details: "+userBuilder);

		
		return "login";
	}

	@RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
	public String welcome() {

		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName(); // Authentication for 
		User user = userRepository.findByUsername(username);

		String result;

		if(user.getUsername().equals("bookstore_Admin")) {
			result = "admin";
		}

		else{
			result = ("welcome");
		}

		return result;


	}

	@RequestMapping(value = "/addBookPage", method = RequestMethod.GET)
	public String addBookPage(@Valid Book book, BindingResult bookResult, Model model) {

		return "addBook";
	}

	@RequestMapping(value = "/addBook", method = RequestMethod.POST)
	public String addBook(Book book, @RequestParam("author") String author, 
			@RequestParam("title") String title,
			@RequestParam("genre") String genre,
			@RequestParam("price") double price 
/*			 @RequestParam("image") String image
*/			 ) {

		bookRepository.save(book);

		return "welcome";

	}
	
    @RequestMapping(value="/searchfunction", method=RequestMethod.GET)
    @ResponseBody
    public List<Book> searchbooks()
    {
    	   
    	List<Book> searchresults = bookRepository.findAll();	   
    	return searchresults;   
    }

	@RequestMapping(value = "/viewallbooks", method = RequestMethod.GET)
	public String bookList(@Valid Book book, Model model) {
		List<Book> listOfBooks = new ArrayList<>();
		listOfBooks = bookRepository.findAll();

		model.addAttribute("listOfBooks", listOfBooks);

		return "listOfBooks";
	}
	
    @RequestMapping(value = "/adminviewbook", method = RequestMethod.GET)
    public String adminbookList(@Valid Book book, Model model) {
    	List<Book> booksList = new ArrayList<>();
    	booksList = bookRepository.findAll();
    	
    	model.addAttribute("booksList", booksList);
    	
    	return "adminView";
    }

	@RequestMapping(value = " /addToCart/{bookId}", method=RequestMethod.GET)
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

		user.saveBookToShoppingCart(book);
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

	@RequestMapping(value="payment", method=RequestMethod.GET)
	public String paymentPage() {

		return "payPage";
	}

	@RequestMapping(value="payDetails", method=RequestMethod.GET)
	@ResponseBody
	public String payment(@RequestParam("shipping") String shipping,
			@RequestParam("creditcard") String creditcard,
			@RequestParam("expirydate") String expirydate,
			@RequestParam("carddetails") int carddetails,
			@RequestParam("cvv") int cvv) {


		System.out.println("Shipping Address: " + shipping + "\n" +
				"Credit Card Type: " + creditcard + "\n" +
				"Expiry Date: " + expirydate + "\n" +
				"Card Number: " + carddetails + "\n" +
				"CVV: " + cvv + "\n");
		
		User user = new User(); 
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName(); // Authentication for 
		user = userRepository.findByUsername(username);

		user.setShipping_address(shipping);

        List<Book> shoppingcart = user.getShoppingCart();
        
        
        for (Book book: shoppingcart) {
        	OrderFulfillmentController controller = new OrderFulfillmentController(); 
        	controller.facade = new OrderServiceFacadeImpl();
        	controller.orderProduct(book.getId());
	        int newQuantity = book.getQuantity() - 1;
        	book.setQuantity(newQuantity);
        	boolean result = controller.orderFulfilled;
        	System.out.println(result);
        	
        }
        
        System.out.println("");


		CloneFactory userCloner = new CloneFactory(); 
		
		User userClone = (User) userCloner.getClone(user); 
		
        PurchaseHistory purchaseHistory = new PurchaseHistory();
		
		
		System.out.println("");
		
		 List<String> purchaseHistoryList = new ArrayList<String>();
	        
	        
	        for(Book book: shoppingcart) {
	        	String booktitle = book.getTitle();
	        	purchaseHistoryList.add(booktitle);
	        }
	        
	        user.clearShoppingCart();
	        System.out.println("Cleared");
	        purchaseHistory.setUsername(userClone.getUsername());
	        purchaseHistory.setShipping_address(userClone.getShipping_address());
	        purchaseHistory.setPurchaseHistory(purchaseHistoryList);
	        System.out.println(purchaseHistoryList);
	        
	        userRepository.save(user); 
	        purchaseHistoryRepository.save(purchaseHistory); 
	        
	        System.out.println("User 1");
	        System.out.println("User : " + System.identityHashCode(System.identityHashCode(user)));
	        System.out.println("Username : " + user.getUsername());
	        System.out.println("Shopping Cart" + user.getShoppingCart());
	        System.out.println("=======================================");
	        System.out.println("User : " + System.identityHashCode(System.identityHashCode(userClone)));
	        System.out.println("User 2: " + userClone.getUsername());
	        System.out.println("Username : " + userClone.getShoppingCart());

		return "payConfirmation";
	}



	@RequestMapping(value="paymentpage", method=RequestMethod.GET)
	public String confirmPaymentPage(Model model) {



		return "payConfirmation";
	}
	
    @RequestMapping(value="/customerorders", method=RequestMethod.GET)
    public String customerOrderPage(Model model) {
    	
    	Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName(); // Authentication for 
        User user = userRepository.findByUsername(username);
        
        model.addAttribute("user", user);
    	
    	return "payConfirmation";
    }

	@RequestMapping(value = " /checkout", method=RequestMethod.GET)
	public String checkout(Model model) {

		// This gets the currently logged in user
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName(); // Authentication for 
		User user = userRepository.findByUsername(username);

		List<Book> checkout = user.getShoppingCart();
		double total = 0;
		double price;


		for(Book b: checkout) {
			price = b.getPrice();
			total += price;
		}

		model.addAttribute("total", total);

		return "checkout";
	}


}

