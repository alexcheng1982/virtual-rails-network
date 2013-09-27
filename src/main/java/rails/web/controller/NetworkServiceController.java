package rails.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import rails.model.Network;
import rails.model.Station;
import rails.service.DistanceCalculation;
import rails.service.JourneyPlanning;
import rails.service.ShortestRoute;
import rails.service.ShortestRoute.ShortestRouteResult;

import com.google.common.base.Splitter;

/**
 * Web service controller for network related services
 * @author alexcheng
 *
 */
@Controller
@RequestMapping("service")
public class NetworkServiceController {
	@Autowired 
	private Network network;
	
	@RequestMapping("calculateDistance")
	@ResponseBody
	public int calculateDistance(@RequestParam("stations") String stations) {
		List<String> stationNames = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(stations);
		return new DistanceCalculation(network).calculate(stationNames.toArray(new String[0]));
	}
	
	@RequestMapping("planJourney/all")
	@ResponseBody
	public List<List<Station>> planJourney(@RequestParam("start") String start, @RequestParam("end") String end) {
		return new JourneyPlanning(network).plan(start, end);
	}
	
	@RequestMapping("planJourney/max")
	@ResponseBody
	public List<List<Station>> planJourneyWithMaxStops(@RequestParam("start") String start, @RequestParam("end") String end, @RequestParam("stops") int stops) {
		return new JourneyPlanning(network).planWithMaxStops(start, end, stops);
	}
	
	@RequestMapping("planJourney/exact")
	@ResponseBody
	public List<List<Station>> planJourneyWithExactStops(@RequestParam("start") String start, @RequestParam("end") String end, @RequestParam("stops") int stops) {
		return new JourneyPlanning(network).planWithExactStops(start, end, stops);
	}
	
	@RequestMapping("calculateShortestRoute")
	@ResponseBody
	public ShortestRouteResult calculateShortestRoute(@RequestParam("start") String start, @RequestParam("end") String end) {
		return new ShortestRoute(network).calculate(start, end);
	}
}
