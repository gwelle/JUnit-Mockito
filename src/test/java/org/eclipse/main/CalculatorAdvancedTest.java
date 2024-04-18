package org.eclipse.main;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@Tag("CalculatorAdvancedTest")
@ExtendWith(MockitoExtension.class)
class CalculatorAdvancedTest {

	private Calculator calculator;

	@Mock
	private CalculatorService calculatorService;
	int result = 0;
	Integer number0ne = 0;
	Integer numberTwo = 0;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		calculator = new Calculator(calculatorService);
	}

	@AfterEach
	void tearDown() throws Exception {
		result = 0;
		calculator = null;
		number0ne = 0;
		numberTwo = 0;
	}

	@Test
	@DisplayName("Add in int")
	void givenTwoIntegers_whenAdded_thenTheyShouldBeSummed() {

		// GIVEN => Parametre le mock
		when(calculatorService.add(2, 3)).thenReturn(5);

		// WHEN
		result = calculatorService.add(2, 3);

		// THEN
		// Vérifier que le service a bien appelé CalculatorService
		// avec les bon arguments
		verify(calculatorService).add(2, 3);
		assertThat(result).isEqualTo(5);
	}

	@Test
	@DisplayName("Add in double")
	void givenTwoIntegersDouble_whenAdded_thenTheyShouldBeSummed() {

		// GIVEN => Parametre le mock
		when(calculatorService.add(2.5, 3.5)).thenReturn((double) 6);

		// WHEN
		result = (int) calculatorService.add(2.5, 3.5);

		// THEN
		// Vérifier que le service a bien appelé CalculatorService
		// avec les bon arguments
		verify(calculatorService).add(Mockito.anyDouble(), Mockito.anyDouble());
		assertThat(result).isEqualTo(6);
	}

	@Test
	@DisplayName("Multiply in int")
	void givenTwoIntegers_whenMultiplied_thenTheyShouldBeSummed() {

		// GIVEN => Parametre le mock
		when(calculatorService.multiply(2, 3)).thenReturn(6);

		// WHEN
		result = calculatorService.multiply(2, 3);

		// THEN
		// Vérifier que le service a bien appelé CalculatorService
		// avec les bon arguments
		verify(calculatorService).multiply(2, 3);

		// Vérifier que multiply(2,3) a été appelée 1 fois
		verify(calculatorService, times(1)).multiply(2, 3);

		// Vérifier que multiply(2,3) a été appelée au moins 1 fois
		verify(calculatorService, atLeast(1)).multiply(2, 3);

		// Vérifier que multiply(2,3) a été appelée au plus 1 fois
		verify(calculatorService, atMost(1)).multiply(2, 3);

		assertThat(result).isEqualTo(6);
	}

	@Test
	@DisplayName("Divide")
	public void calculate_shouldThrowIllegalArgumentException_forADivisionBy0() {

		// GIVEN
		when(calculatorService.divide(1, 0)).thenThrow(new ArithmeticException());

		// WHEN
		assertThatThrownBy(() -> calculatorService.divide(1, 0)).isInstanceOf(ArithmeticException.class);

		// THEN
		verify(calculatorService, times(1)).divide(1, 0);
	}

	@Test
	@DisplayName("Substact")
	void givenTwoIntegers_whenSubstracted_thenTheyShouldBeSummed() {

		when(calculatorService.substract(Mockito.anyInt(), Mockito.anyInt())).thenAnswer((invocation) -> {
			number0ne = invocation.getArgument(0);
			numberTwo = invocation.getArgument(1);
			return number0ne - numberTwo;
		});
		assertThat(calculatorService.substract(3, 2)).isEqualTo(1);
	}

	@Test
	@DisplayName("Substact with argument captor")
	void givenTwoNumbers_whenSubstracted_thenTheyShouldBeSummed() {

		// GIVEN
		// Déclarer un capteur d’argument de type Integer
		ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);

		when(calculatorService.substract(Mockito.anyInt(), Mockito.anyInt())).thenAnswer(invocation -> {
			number0ne = invocation.getArgument(0);
			numberTwo = invocation.getArgument(1);
			return number0ne - numberTwo;
		});

		// WHEN
		result = calculatorService.substract(6, 3);

		// THEN
		// captor.capture() => permet de capturer les arguments de type entier.
		// captor.getAllValues() => retourne les arguments captures de type entier
		assertThat(result).isEqualTo(3);
		verify(calculatorService, times(1)).substract(captor.capture(), captor.capture());
		assertThat(captor.getAllValues().get(0)).isEqualTo(6);
		assertThat(captor.getAllValues().get(1)).isEqualTo(3);
	}
}