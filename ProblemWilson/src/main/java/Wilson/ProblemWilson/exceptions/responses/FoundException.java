package Wilson.ProblemWilson.exceptions.responses;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FOUND)
public class FoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FoundException(String exception) {
		super(exception);
	}

}
