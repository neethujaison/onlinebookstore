package smart.dubai.onlinebookstore.exception;

import java.util.Date;

import lombok.Data;

@Data
public class ErrorResponse {
	private String message;
	private String uri;
	
	public ErrorResponse( String message, String uri) {
        super();
        this.message = message;
        this.uri = uri;
    }
}
