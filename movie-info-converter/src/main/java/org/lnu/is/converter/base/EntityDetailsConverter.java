package org.lnu.is.converter.base;

import org.lnu.is.annotations.Converter;
import org.lnu.is.converter.AbstractConverter;
import org.lnu.is.domain.Model;
import org.lnu.is.resource.ApiResource;

/**
 * Entity details converter.
 * @author ivanursul
 *
 * @param <ENTITY> entity.
 * @param <RESOURCE> resource.
 */
@Converter("entityDetailsConverter")
public class EntityDetailsConverter<ENTITY extends Model, RESOURCE extends ApiResource> extends AbstractConverter<ENTITY, RESOURCE> {

	@Override
	public RESOURCE convert(final ENTITY source, final RESOURCE target) {
		
		target.setCreateDate(source.getCreateDate());
		target.setCrtUser(source.getCrtUser());
		target.setCrtUserGroup(source.getCrtUserGroup());
		target.setUapp(source.getUapp());
		target.setUid(source.getUid());
		target.setUpdateDate(source.getUpdateDate());
		target.setUtid(source.getUtid());
		
		return target;
	}

	@Override
	public RESOURCE convert(final ENTITY source) {
		throw new UnsupportedOperationException("Method not allowed");
	}

}
