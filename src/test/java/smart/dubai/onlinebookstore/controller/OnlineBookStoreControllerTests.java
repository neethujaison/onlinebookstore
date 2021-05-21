package smart.dubai.onlinebookstore.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import smart.dubai.onlinebookstore.entity.Book;
import smart.dubai.onlinebookstore.entity.Classification;
import smart.dubai.onlinebookstore.service.BookService;
import smart.dubai.onlinebookstore.service.ClassificationService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = OnlineBookStoreController.class)
public class OnlineBookStoreControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BookService bookService;
	
	@MockBean
	private ClassificationService catService;

	@Test
	public void testGetBook() throws Exception {
		Book book = new Book();
		book.setBookId(1L);
		book.setName("Cdharles");
		Mockito.when(bookService.getBook(Mockito.anyLong())).thenReturn(book);

		String URI = "/books/1";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(book);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
	}

	@Test
	public void testAddBook() throws Exception {
		Book book = new Book();
		book.setBookId(1L);
		book.setName("Charles");
		String inputInJson = this.mapToJson(book);
		String URI = "/books/";
		Mockito.when(bookService.saveBook(Mockito.any(Book.class))).thenReturn(book);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(URI)
				.accept(MediaType.APPLICATION_JSON).content(inputInJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		String outputInJson = response.getContentAsString();
		
		assertThat(outputInJson).isEqualTo(inputInJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void testDeleteBook() throws Exception {
		Book book = new Book();
		book.setBookId(3L);
		book.setName("Cdharles");
		String URI = "/books/3";
		Mockito.when(bookService.getBook(3L)).thenReturn(book);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(URI).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	
	@Test
	public void testDeleteBookExceptionTestCase() throws Exception {
		Book book = new Book();
		book.setBookId(3L);
		book.setName("Cdharles");
		String URI = "/books/9";
		Mockito.when(bookService.getBook(9L)).thenReturn(null);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(URI).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println(result.getResponse().getStatus());
		assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResponse().getStatus());
	}
	@Test
	public void testUpdateBook() throws Exception {
		Book book = new Book();
		book.setName("xxx");
		book.setBookId(1L);
		String inputInJson = this.mapToJson(book);
		String URI = "/books/1";
		Mockito.when(bookService.getBook(1L)).thenReturn(book);
		Mockito.when(bookService.saveBook(Mockito.any(Book.class))).thenReturn(book);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put(URI)
				.accept(MediaType.APPLICATION_JSON).content(inputInJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		String outputInJson = response.getContentAsString();
		
		assertThat(outputInJson).isEqualTo(inputInJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}


	
	@Test
	public void testCheckoutBook() throws Exception {
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
		String URI = "/books/checkout?bookIds=1&promotion=PROMO1";
		Mockito.when(bookService.getBook(1L)).thenReturn(input);
		Mockito.when(catService.getClassificationDetails(1L)).thenReturn(cat);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		String outputInJson = result.getResponse().getContentAsString();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertNotNull(outputInJson);
	}
	/**
	 * Method to Map an Object into a JSON String.
	 * 
	 * @param object
	 * @return
	 * @throws JsonProcessingException
	 */
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
}
