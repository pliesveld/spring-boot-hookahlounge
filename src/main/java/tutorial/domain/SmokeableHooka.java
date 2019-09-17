package tutorial.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class SmokeableHooka {
	
	public SmokeableHooka(final Hooka hooka) {
		this.hooka = hooka;
	}

	private Hooka hooka;


}
