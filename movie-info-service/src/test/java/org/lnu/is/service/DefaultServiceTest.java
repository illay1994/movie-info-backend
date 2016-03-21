package org.lnu.is.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lnu.is.dao.dao.DefaultDao;
import org.lnu.is.domain.film.Film;
import org.lnu.is.extractor.ParametersExtractor;
import org.lnu.is.pagination.MultiplePagedSearch;
import org.lnu.is.pagination.PagedResult;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DefaultServiceTest {

	@Mock
	protected DefaultDao<Film, Long> defaultDao;
	
	@Mock
	protected ParametersExtractor<Film> parametersExtractor;
	
	@InjectMocks
	private DefaultService<Film, Long, DefaultDao<Film, Long>> unit;
	
	@Test
	public void testCreateEntity() throws Exception {
		// Given
		String name = "name";
		Film entity = new Film();
		entity.setTitle(name);

		// When
		unit.createEntity(entity);

		// Then
		verify(defaultDao).save(entity);
	}
	
	@Test
	public void testUpdateEntity() throws Exception {
		// Given
		String name = "name";
		Film entity = new Film();
		entity.setTitle(name);

		// When
		unit.updateEntity(entity);

		// Then
		verify(defaultDao).update(entity);
	}
	
	@Test
	public void testRemoveEntity() throws Exception {
		// Given
		String name = "name";
		Film entity = new Film();
		entity.setTitle(name);

		// When
		unit.removeEntity(entity);

		// Then
		verify(defaultDao).delete(entity);
	}
	
	@Test
	public void testgetEntity() throws Exception {
		// Given
		Long id = 1L;
		String name = "name";
		
		Film expected = new Film();
		expected.setId(id);
		expected.setTitle(name);
		
		
		// When
		when(defaultDao.getEntityById(anyLong())).thenReturn(expected);
		
		Film actual = unit.getEntity(id);
		
		// Then
		verify(defaultDao).getEntityById(id);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetEntities() throws Exception {
		// Given
		Integer offset = 0;
		Integer limit = 20;
		Film entity1 = new Film();
		Map<String, Object> parameters = Collections.<String, Object>singletonMap("some key", "some value");
		Class<Film> clazz = Film.class;
		MultiplePagedSearch<Film> search = new MultiplePagedSearch<Film>(offset, limit, null, clazz);
		search.setEntity(entity1);

		long count = 100;
		List<Film> entities = Arrays.asList(entity1);
		PagedResult<Film> expected = new PagedResult<Film>(offset, limit, count, entities);
		
		// When
		when(parametersExtractor.getParameters(any(Film.class))).thenReturn(parameters);
		when(defaultDao.getEntities(Matchers.<MultiplePagedSearch<Film>>any())).thenReturn(expected);
		
		PagedResult<Film> actual = unit.getEntities(search);

		// Then
		verify(parametersExtractor).getParameters(entity1);
		verify(defaultDao).getEntities(search);
		assertEquals(expected, actual);
	}
}
