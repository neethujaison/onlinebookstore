package smart.dubai.onlinebookstore.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import smart.dubai.onlinebookstore.entity.Book;
import smart.dubai.onlinebookstore.entity.Classification;
import smart.dubai.onlinebookstore.repository.BookRepository;

public class BookServiceTests {

	@InjectMocks
	BookService bookService;
	
	@Mock
	ClassificationService classificationService;
	
	@Mock
	BookRepository bookRepo;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);;
	}
	
	@Test
    public void getBookTest()
    {
		Book input = new Book();
		input.setBookId(1L);
		input.setName("Cdharles");
        when(bookRepo.findByBookId(1L)).thenReturn(input);
		Book book = bookService.getBook(1L);
		assertEquals("Cdharles", book.getName());
    }
	
	@Test
    public void saveBookTest()
    {
		Book input = new Book();
		input.setName("Cdharles");
		bookService.saveBook(input);
		verify(bookRepo,times(1)).save(input);
    }
	
	@Test
    public void deleteBookTest()
    {
		Book input = new Book();
		input.setName("Cdharles");
		bookService.deleteBook(input);
		verify(bookRepo,times(1)).delete(input);
    }
	
	@Test
    public void calculateBookPriceTest()
    {
		Book input = new Book();
		input.setBookId(1L);
		input.setName("Cdharles");
		input.setPrice(100.00);
		input.setClassification_id(1L);
		Classification cat = new Classification();
		cat.setClassificationId(1L);
		cat.setCategory("Comic");
		cat.setDiscount(10.0);
		cat.setPromotion("PROMO1");
		when(bookService.getBook(1L)).thenReturn(input);
	    when(classificationService.getClassificationDetails(1L)).thenReturn(cat);
        Double price= bookService.calculateBookPrice(1L,"PROMO1");
        Double exp= 70.0;
		assertEquals(exp, price);
    }
}
