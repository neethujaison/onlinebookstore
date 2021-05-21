package smart.dubai.onlinebookstore.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import smart.dubai.onlinebookstore.entity.Book;
import smart.dubai.onlinebookstore.entity.Classification;
import smart.dubai.onlinebookstore.exception.ResourceNotFoundException;
import smart.dubai.onlinebookstore.service.BookService;
import smart.dubai.onlinebookstore.service.ClassificationService;

@RestController
@Slf4j
@RequestMapping("/books/")
public class OnlineBookStoreController {
	

	
	@Autowired
	BookService bookService;
	
	@Autowired
	ClassificationService classificationService;
	
	

	@PostMapping(value="/",produces="application/json", consumes="application/json")
	public Book addBook(@RequestBody Book book) {
		log.info("Book added");
		return bookService.saveBook(book);
	}
	/**
	 * Method to fetch Book
	 * @param bookId
	 * @return
	 */
	@GetMapping("/{bookId}")
	public Book getBook(@PathVariable("bookId") Long bookId) {
		log.info("Book fetched");
		return bookService.getBook(bookId);
	}
	
	@DeleteMapping("/{bookId}")
    public Map<String, Boolean> deleteBook(@PathVariable(value = "bookId") Long bookId)
        throws ResourceNotFoundException {
		Map<String, Boolean> response = new HashMap<>();
		Book book = getBook(bookId);
		if(null != book) {
			log.info("Book deleted");
			bookService.deleteBook(book);
		}else {
			throw new ResourceNotFoundException("Book Not found for this id :: " + bookId);
		}
		response.put("deleted", Boolean.TRUE);
		return response;
    }
	
	/**
	 * Method to update the Book details
	 * @param book
	 * @return
	 */
	@PutMapping(value="/{bookId}",produces = "application/json",consumes= "application/json")
	public ResponseEntity<Book> updateBook(@PathVariable(value = "bookId") Long bookId,@RequestBody Book inputBook) throws ResourceNotFoundException {
		log.info("Book updated");
		Book book = getBook(bookId);
		Book bookEntity =  null;
		if(null != book) {
			book.setAuthor(inputBook.getAuthor());
			book.setClassification_id(inputBook.getClassification_id());
			book.setDescription(inputBook.getDescription());
			book.setIsbn(inputBook.getIsbn());
			book.setName(inputBook.getName());
			book.setPrice(inputBook.getPrice());
			bookEntity = bookService.saveBook(book);
		}else {
			throw new ResourceNotFoundException("Book Not found for this id :: " + bookId);
		}
		return  ResponseEntity.ok(bookEntity);
	}
	
	/**
	 * Method to checkout Book
	 * @param bookId
	 * @return
	 */
	@GetMapping(value = {"/checkout"})
	public Double checkoutBook(@RequestParam(name = "bookIds",required=true) List<Long> bookIds, @RequestParam(name = "promotion",required=false) String promotion) {
		log.info("Book Checkout Operation");
		Double totalPrice = 0.0;
		for(Long bookId :bookIds){
			totalPrice = totalPrice + bookService.calculateBookPrice(bookId,promotion);
			log.info("Book ID:"+bookId+"Amount :" +totalPrice);
		}
		return totalPrice;
	}
	
	
	@PostMapping(value="/addClassification",produces="application/json", consumes="application/json")
	public Classification addClassification(@RequestBody Classification cat) {
		log.info("Classification added");
		return classificationService.saveClassification(cat);
	}
}
