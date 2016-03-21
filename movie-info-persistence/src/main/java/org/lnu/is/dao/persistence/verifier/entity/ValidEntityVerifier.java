package org.lnu.is.dao.persistence.verifier.entity;

import org.lnu.is.dao.exception.EntityNotFoundException;
import org.lnu.is.dao.persistence.verifier.DefaultVerifierChainLink;
import org.lnu.is.domain.EntityModel;
import org.lnu.is.domain.common.RowStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Current verifier check  if entity is valid.
 * @author ivanursul
 *
 * @param <T> entity
 */
public class ValidEntityVerifier<T extends EntityModel> extends DefaultVerifierChainLink<T> {
	private static final Logger LOG = LoggerFactory.getLogger(ValidEntityVerifier.class);
	
	@Override
	protected void verifyLink(final T entity) {

	}

}
