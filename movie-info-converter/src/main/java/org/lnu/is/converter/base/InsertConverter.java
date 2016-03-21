package org.lnu.is.converter.base;

import java.util.Date;

import org.lnu.is.annotations.Converter;
import org.lnu.is.converter.AbstractConverter;
import org.lnu.is.domain.EntityModel;
import org.lnu.is.domain.common.RowStatus;
import org.lnu.is.resource.ApiResource;

/**
 * Class, that is used to make some convertation
 * when insert method (POST) is called.
 * @author ivanursul
 *
 * @param <S> resource type.
 * @param <T> class type.
 */
@Converter("insertConverter")
public class InsertConverter<S extends ApiResource, T extends EntityModel> extends AbstractConverter<S, T> {

	@Override
	public T convert(final S source, final T target) {
		
		target.setCreateDate(new Date());
		target.setUpdateDate(new Date());

		return target;
	}

	@Override
	public T convert(final S source) {
		throw new UnsupportedOperationException("Method not allowed");
	}

}
