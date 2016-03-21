package org.lnu.is.converter.film;

import org.lnu.is.annotations.Converter;
import org.lnu.is.converter.AbstractConverter;
import org.lnu.is.domain.film.Film;
import org.lnu.is.resource.film.FilmResource;

/**
 * Person resource converter.
 * @author ivanursul
 *
 */
@Converter("filmResourceConverter")
public class FilmResourceConverter extends AbstractConverter<FilmResource, Film> {

	@Override
	public Film convert(final FilmResource source, final Film target) {

		target.setId(source.getId());
		target.setCountry(source.getCountry());
		target.setLanguage(source.getLanguage());
		target.setReleased(source.getReleased());
		target.setRuntime(source.getRuntime());
		target.setTitle(source.getTitle());
		target.setYear(source.getYear());
		
		return target;
	}

	@Override
	public Film convert(final FilmResource source) {
		return convert(source, new Film());
	}

}
