package mx.com.warache.utils;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

@DisplayName("Testing math utils")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MathUtilsTest {

	MathUtils mathUtils;

	@BeforeAll
	void initAll() {
		System.out.println("Initializing tests...");
	}

	@BeforeEach
	void init(TestInfo testInfo, TestReporter testReporter) {
		mathUtils = new MathUtils();
		testReporter.publishEntry("Running " + testInfo.getDisplayName() + " with tags " + testInfo.getTags());
	}

	@AfterEach
	void cleanUp() {
		System.out.println("Cleaning up...");
	}

	@AfterAll
	void cleanUpAll() {
		System.out.println("Cleaning up tests...");
	}

	@Nested
	@DisplayName("Testing addition methods bunch")
	@Tag("Math")
	class AddTest {
		@Test
		@EnabledOnOs(OS.WINDOWS)
		@DisplayName("Testing add method for positives")
		void testAddPositives() {
			assertEquals(10, mathUtils.add(7, 3), () -> "Method to compute an addition has failed...");
		}

		@Test
		@EnabledOnOs(OS.WINDOWS)
		@DisplayName("Testing add method for negatives")
		void testAddNegatives() {
			assertEquals(-11, mathUtils.add(-7, -3), () -> "Method to compute an addition has failed...");
		}
	}

	@RepeatedTest(8)
	@Tag("Circle")
	void testCircleArea(RepetitionInfo repInfo) {

		if (repInfo.getCurrentRepetition() % 2 == 0) {
			System.out.println("Skipping test because repetition is even...");
			return;
		}
		boolean even = ((int) (Math.random() * 10) % 2) == 0;
		System.out.println(even);
		assumeTrue(even, "El número no es par");
		;
		assertEquals(12.566370614359172, mathUtils.computeCircleArea(2),
				"Method to compute a circle area has failed...");
//		System.out.println("The test case has completed...");

	}

	@Test
	@Tag("Math")
	void testDivide() {
		assertThrows(ArithmeticException.class, () -> mathUtils.divide(5, 0),
				"Method to compute a division has failed...");
	}

	@Test
	@Tag("Math")
	void testMultiply() {
		assertAll(() -> assertEquals(4, mathUtils.multiply(2, 2)), () -> assertEquals(6, mathUtils.multiply(2, 3)),
				() -> assertEquals(12.566370614359172, mathUtils.computeCircleArea(2)),
				() -> assertEquals(100, mathUtils.multiply(20, 4)));
	}

}
