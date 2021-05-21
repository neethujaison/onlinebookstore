package smart.dubai.onlinebookstore.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import smart.dubai.onlinebookstore.entity.Book;
import smart.dubai.onlinebookstore.entity.Classification;
import smart.dubai.onlinebookstore.repository.BookRepository;

@Service
@Slf4j
public class BookService {
	
	
	@Autowired
	private BookRepository repo;
	@Autowired
	ClassificationService classificationService;
	
	
	private static enum PromotionDetails {
	    PROMO1("PROMO1",20.0),
	    PROMO2("PROMO2",30.0),
	    PROMO3("PROMO3",0.0);

	    public final String code;
	    public String getCode() {
			return code;
		}

		public Double getValue() {
			return value;
		}

		public final Double value;

	    private PromotionDetails(String code,Double value) {
	        this.code = code;
	        this.value = value;
	    }
	}
	/**
	 * Method to fetch the book
	 * @param bookId
	 * @return
	 */
	public Book getBook(Long bookId) {
		log.info("Inside getBook of BookService");
		return repo.findByBookId(bookId);
	}
	
	/**
	 *  Method to save the book
	 * @param book
	 * @return
	 */
	public Book saveBook(Book book) {
		log.info("Inside saveBook of BookService");
		return repo.save(book);
	}
	
	/**
	 *  Method to delete the book
	 *  @param book
	 */
	public void deleteBook(Book book) {
		 log.info("Inside deleteBook of BookService");
		 repo.delete(book);
	}
	
	/**
	 * Method to calculate price
	 * @param promotion
	 * @param totalPrice
	 * @return
	 */
	public Double calculateBookPrice(Long bookId ,String promotion) {
		Double totalPrice =0.0;
		Book book = getBook(bookId);
		if(null != book) {
			Classification details = classificationService.getClassificationDetails(book.getClassification_id());
			Double discount = (details.getDiscount()*book.getPrice())/100;
			totalPrice = book.getPrice()-discount;
			if(!StringUtils.isEmpty(promotion) ) {
				if(promotion.equalsIgnoreCase(details.getPromotion())) {
					Double promotionValue = getPromoValue(promotion);
					totalPrice = totalPrice - promotionValue;
				}	
			}
		}
		return totalPrice;
	}
	/**
	 * Method to fetch the promo value
	 * @param promotion
	 * @return
	 */
	private Double getPromoValue(String promotion) {
		Double promotionValue = 0.0;
			for (PromotionDetails p : PromotionDetails.values()) {
			    if (p.getCode().equalsIgnoreCase(promotion)) {
			    	promotionValue = p.getValue();
			    	log.info("promotionValue"+promotionValue);
			    }
		}
		return promotionValue;
	}
}
