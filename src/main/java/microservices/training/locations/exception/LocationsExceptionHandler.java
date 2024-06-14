package microservices.training.locations.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class LocationsExceptionHandler {

    @ExceptionHandler(LocationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ProblemDetail handleNotFoundException(LocationNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                e.getMessage()
        );
        problemDetail.setType(URI.create("employees/not-found"));
        problemDetail.setTitle("Not found");
        problemDetail.setProperty("id", UUID.randomUUID().toString());
        return problemDetail;
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ProblemDetail handleNotValidException(MethodArgumentNotValidException exception) {
        List<Violation> violations =
                exception.getBindingResult().getFieldErrors()
                        .stream()
                        .map(fe -> new Violation(fe.getField(), fe.getDefaultMessage()))
                        .toList();

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                exception.getMessage()
        );
        problemDetail.setType(URI.create("locations/not-valid"));
        problemDetail.setTitle("Validation error");
        problemDetail.setProperty("violations", violations);

        return problemDetail;

    }
}
