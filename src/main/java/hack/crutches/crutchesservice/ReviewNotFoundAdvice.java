package hack.crutches.crutchesservice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ReviewNotFoundAdvice {

	@ResponseBody
	@ExceptionHandler(ReviewNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String reviewNotFoundHandler(ReviewNotFoundException r) {
		return r.getMessage();
	}
}
