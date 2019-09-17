package tutorial.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFound extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ProductNotFound(String message) {
		super(message);
	}

	public static ProductNotFound noHoses() {
		return new ProductNotFound("no hoses");
	}

	public static ProductNotFound noHooka() {
		return new ProductNotFound("no hooka");
	}

	public static ProductNotFound noCoals() {
		return new ProductNotFound("no coals");
	}
}
