package org.lnu.is.resource.film;

import java.text.MessageFormat;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.lnu.is.annotation.CrudableResource;
import org.lnu.is.resource.ApiResource;

/**
 * Resource for person.
 * @author ivanursul
 *
 */
@CrudableResource
public class FilmResource extends ApiResource {

	@NotNull(message = "Field required")
	private String title;

	@NotNull(message = "Field required")
	@Min(value = 0, message = "Minimal value is 0")
	private Integer year;

	@NotNull(message = "Field required")
	private Date released;

	@NotNull(message = "Field required")
	private Date runtime;

	@NotNull(message = "Field required")
	private String language;

	@NotNull(message = "Field required")
	private String country;

	@Override
	public String getUri() {
		return MessageFormat.format("/films/{0}", getId());
	}
	
	@Override
	public String getRootUri() {
		return "/films";
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Date getReleased() {
		return released;
	}

	public void setReleased(Date released) {
		this.released = released;
	}

	public Date getRuntime() {
		return runtime;
	}

	public void setRuntime(Date runtime) {
		this.runtime = runtime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		FilmResource that = (FilmResource) o;

		if (country != null ? !country.equals(that.country) : that.country != null) return false;
		if (language != null ? !language.equals(that.language) : that.language != null) return false;
		if (released != null ? !released.equals(that.released) : that.released != null) return false;
		if (runtime != null ? !runtime.equals(that.runtime) : that.runtime != null) return false;
		if (title != null ? !title.equals(that.title) : that.title != null) return false;
		if (year != null ? !year.equals(that.year) : that.year != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (title != null ? title.hashCode() : 0);
		result = 31 * result + (year != null ? year.hashCode() : 0);
		result = 31 * result + (released != null ? released.hashCode() : 0);
		result = 31 * result + (runtime != null ? runtime.hashCode() : 0);
		result = 31 * result + (language != null ? language.hashCode() : 0);
		result = 31 * result + (country != null ? country.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "FilmResource{" +
				"country='" + country + '\'' +
				", title='" + title + '\'' +
				", year=" + year +
				", released=" + released +
				", runtime=" + runtime +
				", language='" + language + '\'' +
				'}';
	}
}
