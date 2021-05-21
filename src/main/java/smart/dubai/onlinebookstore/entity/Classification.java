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
@Table(name="Classification")
public class Classification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long classificationId;
	private String category;
	private Double discount;
	private String promotion;
}
