package org.lnu.is.dao.persistence.enhancers.group;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lnu.is.dao.persistence.enhancers.Enhancer;
import org.lnu.is.domain.group.Group;
import org.lnu.is.domain.film.Film;
import org.lnu.is.security.service.SessionService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DefaultGroupEnhancerTest {

	@Mock
	private Enhancer<? super Object> nextEnhancer;
	
	@Mock
	private SessionService sessionService;
	
	@InjectMocks
	private DefaultGroupEnhancer<Film> unit;

	@Test
	public void testEnhance() throws Exception {
		// Given
		Film entity = new Film();
		Group group = new Group();
		group.setTitle("group1");

		// When
		when(sessionService.getDefaultGroup()).thenReturn(group);
		unit.enhance(entity);

		// Then
		verify(nextEnhancer).enhance(any());
		verify(sessionService).getDefaultGroup();
		assertEquals(group.getTitle(), entity.getCrtUserGroup());
	}
	
	@Test
	public void testEnhanceWithoutNextEnhancer() throws Exception {
		// Given
		unit.setNextEnhancer(null);
		Film entity = new Film();
		Group group = new Group();
		group.setTitle("group1");
		
		// When
		when(sessionService.getDefaultGroup()).thenReturn(group);
		unit.enhance(entity);
		
		// Then
		verify(nextEnhancer, times(0)).enhance(any());
		verify(sessionService).getDefaultGroup();
		assertEquals(group.getTitle(), entity.getCrtUserGroup());
	}
}
