package dtos;

import com.critchlow.footballdynasty.dtos.StatisticDto;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Test;

public class StatisticDtoTests {

	@Test
	public void equalsHashCodeContracts(){
		EqualsVerifier.forClass(StatisticDto.class)
				.suppress(Warning.NONFINAL_FIELDS)
				.verify();
	}
}
