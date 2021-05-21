package smart.dubai.onlinebookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import smart.dubai.onlinebookstore.entity.Classification;
import smart.dubai.onlinebookstore.repository.ClassificationRepository;

@Slf4j
@Service
public class ClassificationService {
	@Autowired
	private ClassificationRepository repo;

	/**
	 * @param classificationId
	 * @return
	 */
	public Classification getClassificationDetails(Long classificationId) {
		return repo.findByClassificationId(classificationId);
	}

	/**
	 * Method to save the Classification
	 * 
	 * @param cat
	 * @return
	 */
	public Classification saveClassification(Classification cat) {
		log.info("Inside saveClassification of ClassificationService");
		return repo.save(cat);
	}
}
