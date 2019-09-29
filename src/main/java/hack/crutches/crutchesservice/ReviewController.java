package hack.crutches.crutchesservice;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Rest Controller for Review objects. Currently, only get operations are supported,
 * as users adding/changing reviews is not a feature we decided to implement/
 *
 * @author Samuel P. Bowman
 */
@RestController
public class ReviewController {


	private MongoTemplate mongoTemplate;

	private ReviewRepository repository;
	private ReviewResourceAssembler assembler;
	private LocationDataFetcher fetcher;

	/**
	 * Default Constructor
	 * @param repository injected repository object
	 * @param assembler injected resource assembler object
	 */
	public ReviewController(ReviewRepository repository, ReviewResourceAssembler assembler,
							LocationDataFetcher fetcher, MongoTemplate mongoTemplate) {
		this.repository = repository;
		this.assembler = assembler;
		this.fetcher = fetcher;
		this.mongoTemplate = mongoTemplate;
	}

	/**
	 * Allows the caller to get one particular review by its ObjectId,
	 * throwing a RuntimeException if not found
	 * @param id the ObjectId to search for
	 * @return a HATEOAS resource representing the review
	 */
	@GetMapping("/reviews/{id}")
	public Resource<Review> one(@PathVariable ObjectId id) {
		Review review = repository.findBy_id(id)
				.orElseThrow(RuntimeException::new);
		review = fetcher.addLatLng(review);
		return assembler.toResource(review);
	}

	@GetMapping("/reviews")
	public Resource<Review> oneByPlaceId(@RequestParam String placeId) {
		Review reviews = repository.findByPlaceId(placeId).orElseThrow(ReviewNotFoundException::new);
		return assembler.toResource(reviews);
	}

	/*
	 * Returns all review objects
	 * @return a HATEOAS resource containing all reviews
	 *
	@GetMapping("/reviews")
	public Resources<Resource<Review>> all() {
		List<Resource<Review>> reviews = repository.findAll()
				.stream().map(assembler::toResource).collect(Collectors.toList());

		reviews.forEach(r -> fetcher.addLatLng(r.getContent()));
		return new Resources<>(reviews,
				linkTo(methodOn(ReviewController.class).all()).withSelfRel());
	}*/
}
