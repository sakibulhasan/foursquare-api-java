package ca.opax.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class ApiRepository {
	
	@Value("${foursquare.client.id:na}")
	private String CLIENT_ID;

	@Value("${foursquare.client.secret:na}")
	private String CLIENT_SECRET;
	
	@Value("${foursquare.version:20170501}")
	private String VERSION;
	
	@Value("${foursquare.mode:foursquare}")
	private String MODE;
	
	@Value("${foursquare.api.url:https://api.foursquare.com/v2}")
	private String API_URL;
	

	public <T> T fetchData(String uri, Map<String, String> parameters, Class<T> responseType) {
		
		if (CLIENT_ID.equals("na") || CLIENT_SECRET.equals("na")) {
			throw new RuntimeException("You need to provide foursquare clientId and clientSecret");
		}
		
		List<String> params = new ArrayList<>();
		
		for (Map.Entry<String, String> entry : parameters.entrySet()) {
		    params.add(entry.getKey() + "=" + entry.getValue());
		}
		
		params.add("client_id=" + CLIENT_ID);
		params.add("client_secret=" + CLIENT_SECRET);
		params.add("v=" + VERSION);
		params.add("m=" + MODE);
		
		String callingUrl = API_URL + uri + "?" + String.join("&", params);
		
		RestTemplate restTemplate = new RestTemplate();
		
		return restTemplate.getForObject(callingUrl, responseType);
	}
	
}
