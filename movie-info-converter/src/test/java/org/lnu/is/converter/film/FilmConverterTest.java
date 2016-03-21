package org.lnu.is.converter.film;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.lnu.is.domain.film.Film;
import org.lnu.is.resource.film.FilmResource;

public class FilmConverterTest {

	private FilmConverter unit = new FilmConverter();
	
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
		
		Film source = new Film();
		source.setId(id);

		FilmResource expected = new FilmResource();
		expected.setId(id);

		// When
		FilmResource actual = unit.convert(source);

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
		
		Film source = new Film();
		source.setId(id);

		FilmResource expected = new FilmResource();
		expected.setId(id);

		// When
		FilmResource actual = unit.convert(source);
		
		// Then
		assertEquals(expected, actual);
	}
}
