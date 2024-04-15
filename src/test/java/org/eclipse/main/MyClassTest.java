package org.eclipse.main;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class MyClassTest {

	List mockedList;
	MyClass myClass;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {

		// Une doublure est créée à partir de l'interface List.
		mockedList = mock(List.class);

		/*
		 * La classe à tester, avec l'attribut List mockedList on remplace la liste par
		 * une doublure.
		 */
		myClass = new MyClass(mockedList);
	}

	@AfterEach
	void tearDown() throws Exception {
		myClass = null;
	}

	@Test
	void givenOneList_whenRetrieveTheFirstElement_thenItShouldReturnOneString() {

		//Stubbing
		when(mockedList.get(Mockito.anyInt())).thenReturn("toto");
		
		//Utilisation des doublures
		assertThat(mockedList.get(10)).isEqualTo("toto");

		/* 
		 Interrogation de la doublure => Vérifier qu'une a bien été appelé 
		 avec les bons paramètres
		 */
		verify(mockedList).get(10);
	}
}