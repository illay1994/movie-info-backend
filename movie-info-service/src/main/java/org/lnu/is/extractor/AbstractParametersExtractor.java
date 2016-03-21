package org.lnu.is.extractor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.lnu.is.dao.dao.Dao;
import org.lnu.is.domain.EntityModel;
import org.lnu.is.domain.common.RowStatus;
import org.lnu.is.security.service.SessionService;
import org.springframework.beans.factory.annotation.Value;

/**
 * Abstract parameters extractor with some operations.
 * @author ivanursul
 *
 * @param <T> Entity.
 */
public abstract class AbstractParametersExtractor<T> implements ParametersExtractor<T> {

	@Resource(name = "sessionService")
	private SessionService sessionService;

	@Value("${active.filtering}")
	private Boolean active;
	
	@Value("${security.filtering}")
	private Boolean security;
	
	@Override
	public Map<String, Object> getParameters(final T entity) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		if (active) {
			parameters.put("status", RowStatus.ACTIVE);
		}

		if (security) {
			List<String> groups = sessionService.getGroups();
			parameters.put("userGroups", groups);
		}
		
		return getParameters(entity, parameters);
	}

	/**
	 * Template method for method above(getParameters(entity).
	 * @param entity entity.
	 * @param parameters parameters.
	 * @return map.
	 */
	protected abstract Map<String, Object> getParameters(T entity, Map<String, Object> parameters);

	/**
	 * 
	 * Method for foreign key parameters.
	 * 
	 * @param entity entity.
	 * @param dao dao 
	 * @param parameterName parameterName
	 * @param parameters parameters
	 */
	protected <E extends EntityModel> void addParameter(final E entity,
																		 final Dao<E, Long> dao,
																		 final String parameterName,
																		 final Map<String, Object> parameters) {
		
		if (entity != null) {
			E dbEntity = dao.getEntityById(entity.getId());
			parameters.put(parameterName, dbEntity);
		}
	}

	/**
	 * Method for adding to parameters list of entities.
	 * @param entities entities.
	 * @param dao dao.
	 * @param parameterName parameters name
	 * @param parameters parameters
	 * @param <E> E
	 */
	protected <E extends EntityModel> void addParameter(final List<E> entities, final Dao<E, Long> dao,
																		 final String parameterName,
																		 final Map<String, Object> parameters) {

		if (entities != null) {
			List<E> dbEntities = entities.stream()
					.map(e -> dao.getEntityById(e.getId()))
					.collect(Collectors.toList());

			parameters.put(parameterName, dbEntities);
		}
	}
	
	/**
	 * Method for simple fields.
	 * @param entity entity
	 * @param parameterName parameterName
	 * @param parameters parameters
	 */
	protected void addParameter(final Object entity, final String parameterName, final Map<String, Object> parameters) {
		
		if (entity != null) {
			parameters.put(parameterName, entity);
		}
		
	}

	/**
	 * Method for simple fields.
	 * @param entity entity
	 * @param parameterName parameterName
	 * @param parameters parameters
	 */
	protected void addParameter(final List<?> entity, final String parameterName, final Map<String, Object> parameters) {
		
		if (entity != null && !entity.isEmpty()) {
			parameters.put(parameterName, entity);
		}
		
	}

	public void setSessionService(final SessionService sessionService) {
		this.sessionService = sessionService;
	}

	public void setActive(final Boolean active) {
		this.active = active;
	}

	public void setSecurity(final Boolean security) {
		this.security = security;
	}
	
}
