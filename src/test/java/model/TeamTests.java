package model;

import com.critchlow.footballdynasty.model.Team;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

public class TeamTests {

	@Test
	void equalsHashCodeContracts() {
		EqualsVerifier.forClass(Team.class).verify();
	}
}
