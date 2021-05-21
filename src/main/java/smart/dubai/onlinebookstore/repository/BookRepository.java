package smart.dubai.onlinebookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import smart.dubai.onlinebookstore.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	/**
	 * Method to find out book based on primary key
	 * @param bookId
	 * @return
	 */
	Book findByBookId(Long bookId);
	
}
