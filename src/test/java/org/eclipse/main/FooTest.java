package org.eclipse.main;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FooTest {

	private Foo foo;
	private MyClass myMock;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {

		// Création de la doublure de la classe MyClass avec L'objet foo
		myMock = mock(MyClass.class, "My mock");

		// Injection du mock dans l'objet foo
		foo = new Foo(myMock);
	}

	@AfterEach
	void tearDown() throws Exception {
		foo = null;
	}

	@Test
	void givenTwoNumbers_whenAdded_thenItShouldReturnOneString() {

		// Stubbing
		when(myMock.add(3, 4)).thenReturn(7);
		when(myMock.add(4, 4)).thenReturn(8);
		
		assertThat(myMock.add(3, 4)).isEqualTo(7);
		assertThat(myMock.add(4, 4)).isEqualTo(8);
	}
}
