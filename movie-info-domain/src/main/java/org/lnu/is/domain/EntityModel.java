package org.lnu.is.domain;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.lnu.is.domain.common.RowStatus;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * Base model.
 * @author ivanursul
 *
 */
@MappedSuperclass
public abstract class EntityModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "crtuser")
	private String crtUser;

	@Column(name = "crtusergroup")
	private String crtUserGroup;

	@Column(name = "create_date")
	private Date createDate;

	@Column(name = "update_date")
	private Date updateDate;

	/**
	 * Default constructor with no parameters.
	 */
	public EntityModel() {
		super();
	}

	/**
	 * Constructor with id.
	 * @param id id.
	 */
	public EntityModel(final Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getCrtUser() {
		return crtUser;
	}

	public void setCrtUser(final String crtUser) {
		this.crtUser = crtUser;
	}

	public String getCrtUserGroup() {
		return crtUserGroup;
	}

	public void setCrtUserGroup(final String crtUserGroup) {
		this.crtUserGroup = crtUserGroup;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(final Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(final Date updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((crtUser == null) ? 0 : crtUser.hashCode());
		result = prime * result
				+ ((crtUserGroup == null) ? 0 : crtUserGroup.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((updateDate == null) ? 0 : updateDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		EntityModel other = (EntityModel) obj;
		if (createDate == null) {
			if (other.createDate != null) {
				return false;
			}
		} else if (!createDate.equals(other.createDate)) {
			return false;
		}
		if (crtUser == null) {
			if (other.crtUser != null) {
				return false;
			}
		} else if (!crtUser.equals(other.crtUser)) {
			return false;
		}
		if (crtUserGroup == null) {
			if (other.crtUserGroup != null) {
				return false;
			}
		} else if (!crtUserGroup.equals(other.crtUserGroup)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (updateDate == null) {
			if (other.updateDate != null) {
				return false;
			}
		} else if (!updateDate.equals(other.updateDate)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Model [id=" + id + ", crtUser=" + crtUser + ", crtUserGroup="
				+ crtUserGroup + ", createDate=" + createDate + ", updateDate="
				+ updateDate + "]";
	}

}
