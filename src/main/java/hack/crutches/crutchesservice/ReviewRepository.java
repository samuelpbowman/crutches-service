package hack.crutches.crutchesservice;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends MongoRepository<Review, ObjectId> {

	Optional<Review> findBy_id(ObjectId _id);

	@Query("{'place_id' : ?0}")
	Optional<Review> findByPlaceId(String placeId);

}
