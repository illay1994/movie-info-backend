package org.lnu.is.converter.film;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.lnu.is.domain.film.Film;
import org.lnu.is.resource.film.FilmResource;

public class FilmResourceConverterTest {

	private FilmResourceConverter unit = new FilmResourceConverter();
	
	@Test
	public void testConvert() throws Exception {
		// Given
		Date begDate = new Date();
		String birthPlace = "fsd";
		String docNum = "gfdsf";
		String docSeries = "fdafdsfs";
		Date endDate = new Date();
		String fatherName = " fdsfsdfsd";
		String firstName = " gfdsgsd";
		Long id = 1L;
		String identifier = "fdsfds";
		Integer isHostel = 1;
		Integer isMilitary = 0;
		String name = "fdsfds";
		String photo = "fdsdfsdfsd";
		Integer resident = 1;
		String surname = "fdfds";

		Long parentId = 5L;
		Film parent = new Film();
		parent.setId(parentId);

		Film expected = new Film();
		expected.setId(id);

		FilmResource source = new FilmResource();
		source.setId(id);

		// When
		Film actual = unit.convert(source);

		// Then
		assertEquals(expected, actual);
	}
	
	@Test
	public void testConvertWithNoRelations() throws Exception {
		// Given
		Date begDate = new Date();
		String birthPlace = "fsd";
		String docNum = "gfdsf";
		String docSeries = "fdafdsfs";
		Date endDate = new Date();
		String fatherName = " fdsfsdfsd";
		String firstName = " gfdsgsd";
		Long id = 1L;
		String identifier = "fdsfds";
		Integer isHostel = 1;
		Integer isMilitary = 0;
		String name = "fdsfds";
		String photo = "fdsdfsdfsd";
		Integer resident = 1;
		String surname = "fdfds";
		
		Film expected = new Film();
		expected.setId(id);

		FilmResource source = new FilmResource();
		source.setId(id);

		// When
		Film actual = unit.convert(source);
		
		// Then
		assertEquals(expected, actual);
	}
}
