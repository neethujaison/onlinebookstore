package smart.dubai.onlinebookstore.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import smart.dubai.onlinebookstore.entity.Book;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTests {

    @Autowired
    BookRepository repo;

    @Test
    public void findByBookIdTest() throws Exception {
    	Book book = repo.findByBookId(1L);
    }
}