package org.lnu.is.web.rest.controller.film;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lnu.is.facade.facade.Facade;
import org.lnu.is.pagination.OrderBy;
import org.lnu.is.resource.message.MessageResource;
import org.lnu.is.resource.message.MessageType;
import org.lnu.is.resource.film.FilmResource;
import org.lnu.is.resource.search.PagedRequest;
import org.lnu.is.resource.search.PagedResultResource;
import org.lnu.is.web.rest.controller.AbstractControllerTest;
import org.lnu.is.web.rest.controller.BaseController;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;

import java.nio.file.AccessDeniedException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class FilmControllerTest extends AbstractControllerTest {

	@Mock
	private Facade<FilmResource, Long> facade;
	
	@InjectMocks
	private FilmController unit;
	
	@Override
	protected BaseController getUnit() {
		return unit;
	}

    @Test
	public void testCreatePerson() throws Exception {
		// Given
    	String docNum = "docNum";
    	
    	FilmResource filmResource = new FilmResource();
    	filmResource.setReleased(new Date());
		filmResource.setTitle(docNum);
		
		// When
    	String request = getJson(filmResource, true);
		String response = getJson(filmResource, false);
    	
		when(facade.createResource(any(FilmResource.class))).thenReturn(filmResource);
		
    	// Then
		mockMvc.perform(post("/films")
				.contentType(MediaType.APPLICATION_JSON)
				.content(request))
				.andExpect(status().isCreated())
				.andExpect(content().string(response));
		
		verify(facade).createResource(filmResource);
	}
    
    @Test
	public void testUpdatePerson() throws Exception {
		// Given
    	Long id = 1L;
    	String docNum = "docNum";
    	
    	FilmResource filmResource = new FilmResource();
    	filmResource.setId(id);
    	filmResource.setRuntime(new Date());
		filmResource.setTitle(docNum);
		
		MessageResource responseResource = new MessageResource(MessageType.INFO);
		
		// When
    	String request = getJson(filmResource, true);
		String response = getJson(responseResource, false);
    	
		
    	// Then
		mockMvc.perform(put("/films/{id}", id)
				.contentType(MediaType.APPLICATION_JSON)
				.content(request))
				.andExpect(status().isOk())
				.andExpect(content().string(response));
		
		verify(facade).updateResource(id, filmResource);
	}
    
    @Test
	public void testGetResource() throws Exception {
		// Given
    	Long id = 1L;
    	String docNum = "docNum";
    	
    	FilmResource filmResource = new FilmResource();
    	filmResource.setId(id);
    	filmResource.setRuntime(new Date());
		filmResource.setTitle(docNum);

		// When
		String response = getJson(filmResource, false);
		
		when(facade.getResource(anyLong())).thenReturn(filmResource);
		
		// Then
    	mockMvc.perform(get("/films/{id}", id))
    		.andExpect(status().isOk())
    		.andExpect(content().string(response));
    	
    	verify(facade).getResource(id);
	}
    
    @Test
	public void testDeletePerson() throws Exception {
		// Given
    	Long id = 1L;
    	
		// When

		// Then
    	mockMvc.perform(delete("/films/{id}", id))
    		.andExpect(status().is(204));
    	
    	verify(facade).removeResource(id);
	}
    
    @Test
	public void testGetPersons() throws Exception {
		// Given
    	Long id = 1L;
    	String docNum = "docNum";
    	
    	FilmResource filmResource = new FilmResource();
    	filmResource.setId(id);
    	filmResource.setRuntime(new Date());
		filmResource.setTitle(docNum);

    	long count = 100;
    	int limit = 25;
    	Integer offset = 10;
    	String uri = "/films";
		List<FilmResource> entities = Arrays.asList(filmResource);
    	PagedResultResource<FilmResource> expectedResource = new PagedResultResource<>();
		expectedResource.setCount(count);
		expectedResource.setLimit(limit);
		expectedResource.setOffset(offset);
		expectedResource.setUri(uri);
		expectedResource.setResources(entities);
		
		PagedRequest<FilmResource> pagedRequest = new PagedRequest<FilmResource>(new FilmResource(), offset, limit, Collections.<OrderBy>emptyList());
		
		// When
		when(facade.getResources(Matchers.<PagedRequest<FilmResource>>any())).thenReturn(expectedResource);
    	String response = getJson(expectedResource, false);

		// Then
    	mockMvc.perform(get("/films")
    			.param("offset", String.valueOf(offset))
    			.param("limit", String.valueOf(limit)))
    		.andExpect(status().isOk())
    		.andExpect(content().string(response));
    	
		verify(facade).getResources(pagedRequest);
	}

	@Test(expected = AccessDeniedException.class)
	public void testGetResourceWithAccessDeniedException() throws Exception {
		// Given
		Long id = 1L;
		
		// When
		doThrow(AccessDeniedException.class).when(facade).getResource(anyLong());
		
		// Then
		mockMvc.perform(get("/films/{id}", id));
		
		verify(facade).getResource(id);
	}
}
