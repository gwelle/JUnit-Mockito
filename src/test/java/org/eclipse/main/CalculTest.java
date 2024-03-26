package org.eclipse.main;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@Tag("CalculsTest")
@DisplayName("Tester la classe calcul")
@ExtendWith(LoggingExtension.class)
class CalculTest {

	private Calcul calcul;

	private Logger logger;

	public void setLogger(Logger logger) {
		this.logger = logger;
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
		calcul = new Calcul();
	}

	@AfterEach
	void tearDown() throws Exception {
		logger.info("Appel après chaque test");
		calcul = null;
	}

	@Nested
	@Tag("StringTest")
	@DisplayName("Vérifier des chaines de caractères")
	class StringTest {

		@Test
		@DisplayName("Vérifier si c'est null")
		void checkStringIsNullTest() {
			String valueIsNull = "value";
			assertThat(valueIsNull).isNotNull();
		}

		@Test
		@DisplayName("Vérifier si c'est non null")
		void checkStringIsNonNullTest() {
			String valueIsNotNull = "";
			assertThat(valueIsNotNull).isEmpty();
		}

	}

	@Test
	@DisplayName("Effectuer des Aditions")
	void addTest() {
		assertThat(calcul.add(2, 3)).isEqualTo(5);
		assertThat((calcul.add(-4, 3))).isBetween(-3, 0);
		assertThat((calcul.add(-4, 3))).isGreaterThanOrEqualTo(-2);
		assertThat((calcul.add(-4, 3))).isLessThanOrEqualTo(-1);
		assertThat((calcul.add(-4, 3))).isNegative();
		assertThat((calcul.add(4.5, 3))).isEqualTo(7.5);
		assertEquals(8, calcul.add(4.5, 3.5));
	}

	@Test
	@DisplayName("Effectuer des divisions")
	void divideTest() {
		assertThat(calcul.divide(9, 3)).isEqualTo(3);
	}

}
