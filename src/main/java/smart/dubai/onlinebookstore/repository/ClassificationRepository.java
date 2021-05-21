package smart.dubai.onlinebookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import smart.dubai.onlinebookstore.entity.Classification;
@Repository
public interface ClassificationRepository extends JpaRepository<Classification, Long>{

	public Classification findByClassificationId(Long classificationId);
	
}
