package Wilson.ProblemWilson.exceptions.responses;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author UTP
 */
@ResponseStatus(HttpStatus.OK)
public class Ok extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public Ok(String exception) {
        super(exception);
    }

}
