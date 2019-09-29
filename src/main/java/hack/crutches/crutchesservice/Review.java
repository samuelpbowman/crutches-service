package hack.crutches.crutchesservice;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Class representing a single destination. The fields here correspond to
 * fields in our document database. All fields have been Lombok'ed for
 * simplicity's sake.
 *
 * @author Samuel P. Bowman
 */
@Data
@Document(collection = "reviews")
@SuppressWarnings("WeakerAccess")
public class Review {

	@Id
	private ObjectId _id;
	private String name;
	@Field("place_id")
	private String placeId;
	@Field("blind")
	private int visionAverage;
	@Field("blind_total")
	private int visionTotal;
	@Field("deaf")
	private int hearingAverage;
	@Field("deaf_total")
	private int hearingTotal;
	@Field("mute")
	private int speechAverage;
	@Field("mute_total")
	private int speechTotal;
	@Field("mobility")
	private int mobilityAverage;
	@Field("mobility_total")
	private int mobilityTotal;
	@Field("learning")
	private int learningAverage;
	@Field("learning_total")
	private int learningTotal;
	@JsonInclude()
	@Transient
	private double latitude;
	@JsonInclude()
	@Transient
	private double longitude;
}
