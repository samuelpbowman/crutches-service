package hack.crutches.crutchesservice;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Class which assembles Review objects into HATEOAS resources. These resources are
 * then marshalled into linked JSON, which can be neatly handled by the mobile app.
 *
 * @author Samuel P. Bowman
 */
@Component
public class ReviewResourceAssembler implements ResourceAssembler<Review, Resource<Review>> {

	/**
	 * @inheritDoc
	 */
	@Override
	public Resource<Review> toResource(Review destination) {
		return new Resource<>(destination,
				linkTo(methodOn(ReviewController.class).one(destination.get_id())).withSelfRel()
		);
	}
}
