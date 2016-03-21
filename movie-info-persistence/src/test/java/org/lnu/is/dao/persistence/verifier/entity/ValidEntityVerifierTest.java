package org.lnu.is.dao.persistence.verifier.entity;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lnu.is.dao.exception.EntityNotFoundException;
import org.lnu.is.dao.persistence.verifier.VerifierChainLink;
import org.lnu.is.domain.common.RowStatus;
import org.lnu.is.domain.film.Film;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ValidEntityVerifierTest {

	@Mock
	private VerifierChainLink<? super Object> nextVerifier;
	
	@InjectMocks
	private ValidEntityVerifier<Film> unit;
	
	@Test
	public void testVerify() throws Exception {
		// Given
		Film resource = new Film();

		// When
		unit.verify(resource);
		
		// Then
		verify(nextVerifier).verify(any());
	}
	
	@Test
	public void testVerifyWithoutNextVerifier() throws Exception {
		// Given
		unit.setNextVerifier(null);
		Film resource = new Film();

		// When
		unit.verify(resource);
		
		// Then
		verify(nextVerifier, times(0)).verify(any());
	}

}
