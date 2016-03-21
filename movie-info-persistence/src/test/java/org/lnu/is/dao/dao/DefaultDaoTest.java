package org.lnu.is.dao.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lnu.is.dao.builder.QueryBuilder;
import org.lnu.is.dao.persistence.PersistenceManager;
import org.lnu.is.domain.film.Film;
import org.lnu.is.pagination.MultiplePagedQuerySearch;
import org.lnu.is.pagination.MultiplePagedSearch;
import org.lnu.is.pagination.PagedResult;
import org.lnu.is.queries.Query;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DefaultDaoTest {

	@Mock
	private PersistenceManager<Film, Long> persistenceManager;
	
	@Mock
	private QueryBuilder<Film> queryBuilder;
	
	@InjectMocks
	private DefaultDao<Film, Long> unit;

	@Before
	public void setup() {
		unit.setEntityClass(Film.class);
	}
	
	@Test
	public void testFindById() throws Exception {
		// Given
		Long id = 1L;
		String name = "name";

		Film expected = new Film();
		expected.setId(id);
		expected.setTitle(name);
		
		// When
		when(persistenceManager.findById(Matchers.<Class<Film>>any(), anyLong())).thenReturn(expected);
		Film actual = unit.getEntityById(id);

		// Then
		verify(persistenceManager).findById(Film.class, id);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testSave() throws Exception {
		// Given
		Long id = 1L;
		String name = "name";

		Film entity = new Film();
		entity.setId(id);
		entity.setTitle(name);
		
		// When
		unit.save(entity);
		
		// Then
		verify(persistenceManager).create(entity);
	}
	
	@Test
	public void testUpdate() throws Exception {
		// Given
		Long id = 1L;
		String name = "name";

		Film entity = new Film();
		entity.setId(id);
		entity.setTitle(name);
		
		// When
		unit.update(entity);
		
		// Then
		verify(persistenceManager).update(entity);
	}
	
	@Test
	public void testDelete() throws Exception {
		// Given
		Long id = 1L;
		String name = "name";

		Film entity = new Film();
		entity.setId(id);
		entity.setTitle(name);
		
		// When
		unit.delete(entity);

		// Then
		verify(persistenceManager).remove(entity);
	}
	
	@Test
	public void testGetEntities() throws Exception {
		// Given
		Integer offset = 0;
		Integer limit = 20;
		Map<String, Object> parameters = Collections.emptyMap();
		Class<Film> clazz = Film.class;
		Film entity1 = new Film();
		
		MultiplePagedSearch<Film> pagedSearch = new MultiplePagedSearch<Film>(offset, limit, parameters, clazz);
		pagedSearch.setEntity(entity1);
		long count = 100;
		List<Film> entities = Arrays.asList(entity1);
		PagedResult<Film> expected = new PagedResult<Film>(offset, limit, count, entities);
		
		String querySql = "query Sql";
		Query<Film> queries = new Query<Film>(Film.class, querySql);
		MultiplePagedQuerySearch<Film> pagedQuerySearch = new MultiplePagedQuerySearch<Film>(queries, pagedSearch.getOffset(),
				pagedSearch.getLimit(), pagedSearch.getParameters(), Film.class);
		
		// When
		when(queryBuilder.build(Matchers.<MultiplePagedSearch<Film>>any())).thenReturn(querySql);
		when(persistenceManager.search(Matchers.<MultiplePagedQuerySearch<Film>>any())).thenReturn(expected);
		PagedResult<Film> actual = unit.getEntities(pagedSearch);

		// Then
		verify(queryBuilder).build(pagedSearch);
		verify(persistenceManager).search(pagedQuerySearch);
		assertEquals(expected, actual);
	}
}
