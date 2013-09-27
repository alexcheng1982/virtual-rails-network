package rails.web.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import rails.model.Network;

/**
 * Factory of <tt>Network</tt> objects.
 * @author alexcheng
 *
 */
@Configuration
public class NetworkFactory {
	@Bean
	public Network create() {
		return Network.fromEncodedString("A#B#12;A#D#19;A#E#20;A#G#16;B#C#5;B#D#13;B#I#15;C#D#5;D#E#7;E#F#5;F#A#5;G#F#11;H#A#4;H#B#19;I#J#10;J#B#7;J#C#15");
	}
}
