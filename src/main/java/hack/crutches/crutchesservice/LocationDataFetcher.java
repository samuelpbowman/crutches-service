package hack.crutches.crutchesservice;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
@SuppressWarnings("WeakerAccess")
public class LocationDataFetcher {

	private Gson gson;
	private GoogleAPIConfiguration googleAPIConfiguration;

	private static final String DETAIL_BASE_URL = "https://maps.googleapis.com/maps/api/place/details/json?";

	public LocationDataFetcher(Gson gson, GoogleAPIConfiguration googleAPIConfiguration) {
		this.gson = gson;
		this.googleAPIConfiguration = googleAPIConfiguration;
	}

	public Review addLatLng(Review review) {
		try {
			URL url = new URL(DETAIL_BASE_URL + "key=" + googleAPIConfiguration.getGoogleApiKey()
					+ "&place_id=" + review.getPlaceId());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");

			String response = this.generateResponse(conn);
			JsonObject data = gson.fromJson(response, JsonObject.class);
			JsonObject obj = data.getAsJsonObject("result").getAsJsonObject("geometry").getAsJsonObject("location");
			
			review.setLatitude(Double.parseDouble(obj.get("lat").toString()));
			review.setLongitude(Double.parseDouble(obj.get("lng").toString()));
		} catch(Exception e) {
			e.printStackTrace();
		}
		return review;
	}

	private String generateResponse(HttpURLConnection conn) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuilder response = new StringBuilder();

		while((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		return response.toString();
	}
}
