package org.lnu.is.converter.film;

import org.lnu.is.annotations.Converter;
import org.lnu.is.converter.AbstractConverter;
import org.lnu.is.domain.film.Film;
import org.lnu.is.resource.film.FilmResource;

/**
 * Film converter.
 * @author illay
 *
 */
@Converter("filmConverter")
public class FilmConverter extends AbstractConverter<Film, FilmResource> {

	@Override
	public FilmResource convert(final Film source, final FilmResource target) {
		
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
	public FilmResource convert(final Film source) {
		return convert(source, new FilmResource());
	}
}
