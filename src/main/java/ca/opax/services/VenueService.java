package ca.opax.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.opax.dto.VenueListDTO;
import ca.opax.model.Venue;
import ca.opax.repository.ApiRepository;

@Service
public class VenueService {
	
	@Autowired
	private ApiRepository api;

	public List<Venue> findVenuesByLocation (double latitude, double longitude) {
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("ll", latitude + "," + longitude);
		
		VenueListDTO dto = api.fetchData("/venues", params, VenueListDTO.class);
		
		return null;
	}
	
}
