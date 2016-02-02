import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class parseTimeToSecondsTest {
@Rule
public final ExpectedException exception = ExpectedException.none();

@Test
public void testFirstColon(){
	exception.expect(NumberFormatException.class);
	TimeParser.parseTimeToSeconds("");
}

@Test
public void testSecondColon(){
	exception.expect(NumberFormatException.class);
	TimeParser.parseTimeToSeconds(":");
}

@Test
public void testPM(){
	assertEquals(TimeParser.parseTimeToSeconds("2:12:12 pm"),51132);
}

@Test
public void test12AM(){
	assertEquals(TimeParser.parseTimeToSeconds("12:12:12 am"),732);
}

@Test
public void testRange(){
	exception.expect(IllegalArgumentException.class);
	TimeParser.parseTimeToSeconds("90:90:90");
}

@Test
public void testRangeMaxHours(){
	exception.expect(IllegalArgumentException.class);
	TimeParser.parseTimeToSeconds("90:00:00");
}

@Test
public void testRangeMinHours(){
	exception.expect(IllegalArgumentException.class);
	TimeParser.parseTimeToSeconds("-1:00:00");
}

@Test
public void testRangeMaxMins(){
	exception.expect(IllegalArgumentException.class);
	TimeParser.parseTimeToSeconds("00:90:00");
}

@Test
public void testRangeMinMins(){
	exception.expect(IllegalArgumentException.class);
	TimeParser.parseTimeToSeconds("00:-01:00");
}

@Test
public void testRangeMaxSecs(){
	exception.expect(IllegalArgumentException.class);
	TimeParser.parseTimeToSeconds("00:00:90");
}

@Test
public void testRangeMinSecs(){
	exception.expect(IllegalArgumentException.class);
	TimeParser.parseTimeToSeconds("00:00:-01");
}


@Test
public void testNormal(){
	assertEquals(TimeParser.parseTimeToSeconds("2:20:20"),8420);
}

}
