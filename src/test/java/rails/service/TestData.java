package rails.service;

import rails.model.Network;

public class TestData {
	public static Network basicNetwork() {
		return Network.fromEncodedString("A#B#12;A#D#19;A#E#20;A#G#16;B#C#5;B#D#13;B#I#15;C#D#5;D#E#7;E#F#5;F#A#5;G#F#11;H#A#4;H#B#19;I#J#10;J#B#7;J#C#15");
	}
}
