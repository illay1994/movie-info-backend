package org.lnu.is.web.rest.processor.resolver.resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lnu.is.resource.film.FilmResource;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.MethodParameter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultResourceParameterResolverTest {

	private DefaultResourceParameterResolver unit = new DefaultResourceParameterResolver();
	
	@Mock
	private MethodParameter param;
	
	@Mock
	private ParameterizedType type;
	
	private Class<FilmResource> clzType = FilmResource.class;

	private Type[] types = new Type[] { clzType };
	
	@Test
	public void testGetResource() throws Exception {
		// Given
		Map<String, Object> parameters = new HashMap<>();
		Integer resident = 1;
		parameters.put("year", resident);

		FilmResource expected = new FilmResource();
		expected.setYear(resident);

		// When
		when(param.getGenericParameterType()).thenReturn(type);
		when(type.getActualTypeArguments()).thenReturn(types);
		
		Object actual = unit.getResource(param, parameters);

		// Then
		verify(param).getGenericParameterType();
		verify(type).getActualTypeArguments();
		assertEquals(expected, actual);
	}

	@Test
	public void testGetResourceWithMultipleFields() throws Exception {
		// Given
		String id = "1";
		String name = "Ivan";
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("title", name);

		FilmResource expected = new FilmResource();
		expected.setTitle(name);

		// When
		when(param.getGenericParameterType()).thenReturn(type);
		when(type.getActualTypeArguments()).thenReturn(types);

		Object actual = unit.getResource(param, parameters);

		// Then
		verify(param).getGenericParameterType();
		verify(type).getActualTypeArguments();
		assertEquals(expected, actual);
	}

	@Test
	public void testGetResourceWithMultipleFieldsId() throws Exception {
		// Given
		String id = "1";
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("id", id);

		FilmResource expected = new FilmResource();
		expected.setId(Long.valueOf(id));

		// When
		when(param.getGenericParameterType()).thenReturn(type);
		when(type.getActualTypeArguments()).thenReturn(types);

		Object actual = unit.getResource(param, parameters);

		// Then
		verify(param).getGenericParameterType();
		verify(type).getActualTypeArguments();
		assertEquals(expected, actual);
	}

	@Test
	public void testGetResourceWithMultipleFieldsAndReadyParameters() throws Exception {
		// Given
		String id = "1";
		String name = "ivan";
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("title", name);

		FilmResource expected = new FilmResource();
		expected.setTitle(name);

		// When
		when(param.getGenericParameterType()).thenReturn(type);
		when(type.getActualTypeArguments()).thenReturn(types);

		Object actual = unit.getResource(param, parameters);

		// Then
		verify(param).getGenericParameterType();
		verify(type).getActualTypeArguments();
		assertEquals(expected, actual);
	}
}
