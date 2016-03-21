package org.lnu.is.facade.facade;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lnu.is.converter.Converter;
import org.lnu.is.dao.dao.DefaultDao;
import org.lnu.is.domain.film.Film;
import org.lnu.is.pagination.MultiplePagedSearch;
import org.lnu.is.pagination.OrderBy;
import org.lnu.is.pagination.PagedResult;
import org.lnu.is.resource.film.FilmResource;
import org.lnu.is.resource.search.PagedRequest;
import org.lnu.is.resource.search.PagedResultResource;
import org.lnu.is.service.DefaultService;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultFacadeTest {
	@Mock
	private Converter<FilmResource, Film> insertConverter;

	@Mock
	private Converter<FilmResource, Film> updateConverter;

	@Mock
	private Converter<FilmResource, Film> resourceConverter;

	@Mock
	private Converter<FilmResource, Film> resourceListConverter;

	@Mock
	private Converter<Film, FilmResource> entityConverter;

	@Mock
	private DefaultService<Film, Long, DefaultDao<Film, Long>> service;

	@Mock
	private Converter<PagedRequest<FilmResource>, MultiplePagedSearch<Film>> pagedRequestConverter;

	@Mock
	private Converter<Film, FilmResource> entityDetailsConverter;
	
	@Mock
	private Converter<PagedResult<Film>, PagedResultResource<FilmResource>> pagedResultConverter;

	private DefaultFacade<Film, FilmResource, DefaultService<Film, Long, DefaultDao<Film, Long>>, Long> unit = new DefaultFacade<Film, FilmResource, DefaultService<Film, Long, DefaultDao<Film, Long>>, Long>();
	
	@Before
	public void setup() {
		unit.setEntityConverter(entityConverter);
		unit.setEntityDetailsConverter(entityDetailsConverter);
		unit.setInsertConverter(insertConverter);
		unit.setPagedRequestConverter(pagedRequestConverter);
		unit.setPagedResultConverter(pagedResultConverter);
		unit.setResourceConverter(resourceConverter);
		unit.setService(service);
		unit.setUpdateConverter(updateConverter);
	}
	
	@Test
	public void testCreateEntity() throws Exception {
		// Given
		String name = "LightMan";

		FilmResource expected = new FilmResource();
		expected.setReleased(new Date());
		expected.setRuntime(new Date());
		expected.setTitle(name);

		Film film = new Film();
		film.setReleased(new Date());
		film.setRuntime(new Date());
		film.setTitle(name);

		// When
		when(resourceConverter.convert(any(FilmResource.class))).thenReturn(film);
		when(entityConverter.convert(any(Film.class))).thenReturn(expected);

		FilmResource actual = unit.createResource(expected);

		// Then
		verify(resourceConverter).convert(expected);
		verify(service).createEntity(film);
		verify(insertConverter).convert(expected, film);

		assertEquals(expected, actual);
	}

	@Test
	public void testUpdateEntity() throws Exception {
		// Given
		Long id = 1L;
		String name = "LightMan";
		String group = "developers";

		FilmResource expected = new FilmResource();
		expected.setReleased(new Date());
		expected.setRuntime(new Date());
		expected.setTitle(name);

		Film film = new Film();
		film.setReleased(new Date());
		film.setRuntime(new Date());
		film.setTitle(name);
		film.setCrtUserGroup(group);

		// When
		when(service.getEntity(anyLong())).thenReturn(film);

		unit.updateResource(id, expected);

		// Then
		verify(service).getEntity(id);
		verify(resourceConverter).convert(expected, film);
		verify(service).updateEntity(film);
		verify(updateConverter).convert(expected, film);
	}

	@Test
	public void testGetEntity() throws Exception {
		// Given
		Long id = 1L;
		String name = "fsd person";

		FilmResource expected = new FilmResource();
		expected.setReleased(new Date());
		expected.setRuntime(new Date());
		expected.setTitle(name);

		Film film = new Film();
		film.setReleased(new Date());
		film.setRuntime(new Date());
		film.setTitle(name);

		// When
		when(service.getEntity(anyLong())).thenReturn(film);
		when(entityConverter.convert(any(Film.class))).thenReturn(expected);
		FilmResource actual = unit.getResource(id);

		// Then
		verify(service).getEntity(id);
		verify(entityConverter).convert(film);
		verify(entityDetailsConverter).convert(film, expected);
		assertEquals(expected, actual);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		// Given
		Long id = 1L;
		String group = "developers";

		Film film = new Film();
		film.setId(id);
		film.setCrtUserGroup(group);

		// When
		when(service.getEntity(anyLong())).thenReturn(film);
		unit.removeResource(id);

		// Then
		verify(service).getEntity(id);
		verify(service).removeEntity(film);
	}

	@Test
	public void testGetEntities() throws Exception {
		// Given
		PagedRequest<FilmResource> pagedRequest = new PagedRequest<FilmResource>(new FilmResource(), 10, 10, Collections.<OrderBy>emptyList());
		List<FilmResource> resources = Collections.singletonList(new FilmResource());
		PagedResultResource<FilmResource> expectedPagedResultResource = new PagedResultResource<>(pagedRequest.getResource().getRootUri());
		expectedPagedResultResource.setResources(resources);

		int offset = 8;
		int limit = 3;
		int count = 100;

		MultiplePagedSearch<Film> pagedSearch = new MultiplePagedSearch<Film>(offset, limit, Collections.<String, Object> emptyMap(), Film.class);
		List<Film> entities = Arrays.asList(new Film());
		PagedResult<Film> pagedResult = new PagedResult<Film>(offset, limit, count, entities);

		// When
		when(pagedRequestConverter.convert(Matchers.<PagedRequest<FilmResource>> any())).thenReturn(pagedSearch);
		when(service.getEntities(Matchers.<MultiplePagedSearch<Film>> any())).thenReturn(pagedResult);
		when(entityConverter.convertAll(anyListOf(Film.class))).thenReturn(resources);

		PagedResultResource<FilmResource> actualFunnies = unit.getResources(pagedRequest);

		// Then
		verify(pagedRequestConverter).convert(pagedRequest);
		verify(service).getEntities(pagedSearch);
		verify(entityConverter).convertAll(entities);
		verify(pagedResultConverter).convert(pagedResult, expectedPagedResultResource);

		assertEquals(expectedPagedResultResource, actualFunnies);
	}
	
	@Test
	public void testEntityConverter() throws Exception {
		assertNotNull(unit.getEntityConverter());
	}
	
	@Test
	public void testEntityDetailsConverter() throws Exception {
		assertNotNull(unit.getEntityDetailsConverter());
	}
	
	@Test
	public void testInsertConverter() throws Exception {
		assertNotNull(unit.getInsertConverter());
	}
	
	@Test
	public void testPagedResultConverter() throws Exception {
		assertNotNull(unit.getPagedResultConverter());
	}
	
	@Test
	public void testResourceConverter() throws Exception {
		assertNotNull(unit.getResourceConverter());
	}
	
	@Test
	public void testService() throws Exception {
		assertNotNull(unit.getService());
	}
	
	@Test
	public void testPagedRequestConverter() throws Exception {
		assertNotNull(unit.getPagedRequestConverter());
	}

	@Test
	public void testPagedUpdateConverter() throws Exception {
		assertNotNull(unit.getUpdateConverter());
	}

}
