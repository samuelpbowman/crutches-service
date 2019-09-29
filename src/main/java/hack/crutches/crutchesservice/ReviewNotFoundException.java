package hack.crutches.crutchesservice;

public class ReviewNotFoundException extends RuntimeException {

	ReviewNotFoundException() {
		super("Could not find review!");
	}
}
