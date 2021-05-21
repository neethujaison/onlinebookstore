package smart.dubai.onlinebookstore.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Entity
@Data
@Slf4j
@Table(name="Book")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookId;
	private String name;
	private String description;
	private String author;
	private Long classification_id;
	private Double price;
	private String isbn;
}
