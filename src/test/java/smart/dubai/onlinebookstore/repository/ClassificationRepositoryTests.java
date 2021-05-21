package smart.dubai.onlinebookstore.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import junit.framework.Assert;
import smart.dubai.onlinebookstore.entity.Book;
import smart.dubai.onlinebookstore.entity.Classification;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ClassificationRepositoryTests {
	 @Autowired
	 ClassificationRepository repo;

	    @Test
	    public void findByClassificationIdTest() throws Exception {
	    	Classification cat = new Classification();
			cat.setClassificationId(1L);
			cat.setCategory("Comic");
			cat.setDiscount(10.0);
			cat.setPromotion("PROMO1");
	    	Classification category = repo.findByClassificationId(1L);
	    	Assert.assertNotNull(category);
	    }
}
