package Wilson.ProblemWilson.exceptions.responses;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourcesNotFoundException extends Exception{
    private static final long serialVersionUID = 1L;
    
    public ResourcesNotFoundException(String message){
        super(message);
    }
}

