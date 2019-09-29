package hack.crutches.crutchesservice;

import java.util.Optional;

public interface CustomReviewRepository {

	Optional<Review> getWherePathId(String pathId);
}
