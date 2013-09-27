package rails.service;

import java.util.List;

import rails.model.Station;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

public class Utils {
	public static List<Station> asStations(String... names) {
		return Lists.transform(Lists.newArrayList(names), new Function<String, Station>() {
			public Station apply(String name) {
				return new Station(name);
			}
		});
	}
}
