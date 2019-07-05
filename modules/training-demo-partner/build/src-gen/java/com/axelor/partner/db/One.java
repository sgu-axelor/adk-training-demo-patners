package com.axelor.partner.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.NameColumn;
import com.axelor.db.annotations.Widget;
import com.google.common.base.MoreObjects;

@Entity
@Cacheable
@Table(name = "PARTNER_ONE", indexes = { @Index(columnList = "period") })
public class One extends AuditableModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PARTNER_ONE_SEQ")
	@SequenceGenerator(name = "PARTNER_ONE_SEQ", sequenceName = "PARTNER_ONE_SEQ", allocationSize = 1)
	private Long id;

	@NameColumn
	private String period;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "one", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Two> two;

	@Widget(title = "Attributes")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "json")
	private String attrs;

	public One() {
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public List<Two> getTwo() {
		return two;
	}

	public void setTwo(List<Two> two) {
		this.two = two;
	}

	/**
	 * Add the given {@link Two} item to the {@code two}.
	 *
	 * <p>
	 * It sets {@code item.one = this} to ensure the proper relationship.
	 * </p>
	 *
	 * @param item
	 *            the item to add
	 */
	public void addTwo(Two item) {
		if (getTwo() == null) {
			setTwo(new ArrayList<>());
		}
		getTwo().add(item);
		item.setOne(this);
	}

	/**
	 * Remove the given {@link Two} item from the {@code two}.
	 *
 	 * @param item
	 *            the item to remove
	 */
	public void removeTwo(Two item) {
		if (getTwo() == null) {
			return;
		}
		getTwo().remove(item);
	}

	/**
	 * Clear the {@code two} collection.
	 *
	 * <p>
	 * If you have to query {@link Two} records in same transaction, make
	 * sure to call {@link javax.persistence.EntityManager#flush() } to avoid
	 * unexpected errors.
	 * </p>
	 */
	public void clearTwo() {
		if (getTwo() != null) {
			getTwo().clear();
		}
	}

	public String getAttrs() {
		return attrs;
	}

	public void setAttrs(String attrs) {
		this.attrs = attrs;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (this == obj) return true;
		if (!(obj instanceof One)) return false;

		final One other = (One) obj;
		if (this.getId() != null || other.getId() != null) {
			return Objects.equals(this.getId(), other.getId());
		}

		return false;
	}

	@Override
	public int hashCode() {
		return 31;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
			.add("id", getId())
			.add("period", getPeriod())
			.omitNullValues()
			.toString();
	}
}
