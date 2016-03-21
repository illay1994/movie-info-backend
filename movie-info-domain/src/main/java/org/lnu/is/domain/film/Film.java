package org.lnu.is.domain.film;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.lnu.is.annotation.dbtable.OB;
import org.lnu.is.domain.Model;

/**
 * Person entity.
 * @author ivanursul
 *
 */
@OB
@Entity
@Table(name = "film")
public class Film extends Model {
	private static final long serialVersionUID = 1L;

	@Column(name = "title")
	private String title;

	@Column(name = "year")
	private Integer year;

	@Column(name = "released")
	private Date released;

	@Column(name = "runtime")
	private Date runtime;

	@Column(name = "language")
	private String language;

	@Column(name = "country")
	private String country;

	/**
	 * Default constructor.
	 */
	public Film() {
		super();
	}

	/**
	 * Default constructor with id.
	 *
	 * @param id
	 */
	public Film(final Long id) {
		super(id);
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getReleased() {
		return released;
	}

	public void setReleased(Date fatherName) {
		this.released = fatherName;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
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

		Film film = (Film) o;

		if (country != null ? !country.equals(film.country) : film.country != null) return false;
		if (released != null ? !released.equals(film.released) : film.released != null) return false;
		if (language != null ? !language.equals(film.language) : film.language != null) return false;
		if (runtime != null ? !runtime.equals(film.runtime) : film.runtime != null) return false;
		if (title != null ? !title.equals(film.title) : film.title != null) return false;
		if (year != null ? !year.equals(film.year) : film.year != null) return false;

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
		return "Film{" +
				"country='" + country + '\'' +
				", title='" + title + '\'' +
				", year=" + year +
				", released=" + released +
				", runtime=" + runtime +
				", language='" + language + '\'' +
				'}';
	}
}
