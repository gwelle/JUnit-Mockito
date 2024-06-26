package org.eclipse.main;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.logging.Logger;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Tag("CalculsTest")
@DisplayName("Tester la classe calcul")
class CalculatorTest {

	private Calculator calcul;
	private String valueString;
	private Logger logger;

	public CalculatorTest() {
		logger = Logger.getLogger(CalculatorTest.class.getName());
	}

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("Appel avant tous les tests");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("Appel après tous les tests");
	}

	@BeforeEach
	void setUp() throws Exception {
		logger.info("Appel avant chaque test");
		calcul = new Calculator();

	}

	@AfterEach
	void tearDown() throws Exception {
		logger.info("Appel après chaque test");
		calcul = null;
		valueString = null;
	}

	@Nested
	@Tag("StringTest")
	@DisplayName("Vérifier des chaines de caractères")
	class StringTest {

		@Test
		@DisplayName("Vérifier si c'est non null")
		void checkStringIsNullTest() {
			valueString = "test";
			assertThat(valueString).isNotNull();
		}

		@Disabled("This test is disabled because I'm not satisfied with the result")
		@Test
		@DisplayName("Vérifier si c'est null")
		void checkStringIsNonNullTest() {
			valueString = "";
			assertThat(valueString).isNull();
		}

	}

	@Test
	@Order(1)
	@DisplayName("Effectuer des Aditions")
	void givenTwoIntegers_whenAdded_thenTheyShouldBeSummed() {
		assertThat(calcul.add(2, 3)).isEqualTo(5);
		assertThat(calcul.add(-4, 3)).isBetween(-3, 0);
		assertThat(calcul.add(-4, 3)).isGreaterThanOrEqualTo(-2);
		assertThat(calcul.add(-4, 3)).isNegative();
		assertThat(calcul.add(4.5, 3)).isEqualTo(7.5);
		assertThat(calcul.add(4.5, 3.5)).isEqualTo(8);
	}

	@Test
	@Order(4)
	@DisplayName("Effectuer des divisions")
	void givenTwoPostiveIntegers_whenDivided_thenTheyShouldBeSummed() {
		assertThat(calcul.divide(9, 3)).isEqualTo(3);
	}

	@RepeatedTest(value = 3, name = RepeatedTest.SHORT_DISPLAY_NAME)
	@Order(3)
	@DisplayName("Effectuer des multiplications")
	void givenTwoPostiveIntegers_whenMultiplied_thenTheyShouldBeSummed() {
		assertThat(calcul.multiply(5, 3)).isBetween(10, 20);
	}

	@ParameterizedTest
	@Order(2)
	@ValueSource(ints = { 2, 3 })
	@DisplayName("Effectuer des soustractions")
	void givenTwoPostiveIntegers_whenSubstracted_thenTheyShouldBeSummed(int value) {
		assertThat(calcul.substract(value, 3)).isNotEqualTo(-1);
	}

}
