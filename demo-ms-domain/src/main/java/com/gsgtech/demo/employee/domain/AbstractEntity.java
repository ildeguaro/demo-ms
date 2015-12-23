package com.gsgtech.demo.employee.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.MappedSuperclass;
import org.springframework.data.domain.Persistable;


/**
 * Clase abstracta que deben heredar todas las clases de entidades.
 * Implementa {@link #equals(Object)} y {@link #hashCode()} según su id
 * 
 * @author Jorge Guerrero
 * @param <PK> el primary-key de la entidad.
 */
@MappedSuperclass
public abstract class AbstractEntity<PK extends Serializable> implements Persistable<PK> {
	private static final long serialVersionUID = 1L;
	
	public abstract PK getId();

	/*
	 * Este método setter es protegido. 
	 * solo para uso en unit-tests. En los casos de entidades que
	 * requieran establecer el ID deben hacerlo vía constructor.
	 */
	protected abstract void setId(final PK id);

	public boolean isNew() {
		return null == getId();
	}

	@Override
	public String toString() {
		return getId().toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (null == obj) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (!getClass().equals(obj.getClass())) {
			return false;
		}
		AbstractEntity<?> that = (AbstractEntity<?>) obj;
		return null == this.getId() ? false : this.getId().equals(that.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}
}
