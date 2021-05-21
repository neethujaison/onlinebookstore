package smart.dubai.onlinebookstore.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import smart.dubai.onlinebookstore.entity.Classification;
import smart.dubai.onlinebookstore.repository.ClassificationRepository;

public class ClassificationServiceTests {

	@InjectMocks
	ClassificationService service;
	
	@Mock
	ClassificationRepository repo;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);;
	}
	
	@Test
    public void getClassificationTest()
    {
		Classification input = new Classification();
		input.setClassificationId(1L);
		input.setCategory("Comics");
        when(repo.findByClassificationId(1L)).thenReturn(input);
        Classification cat = service.getClassificationDetails(1L);
		assertEquals("Comics", cat.getCategory());
    }
}
