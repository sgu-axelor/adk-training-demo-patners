package com.axelor.partner.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.HashKey;
import com.axelor.db.annotations.NameColumn;
import com.axelor.db.annotations.Widget;
import com.google.common.base.MoreObjects;

@Entity
@Cacheable
@Table(name = "PARTNER_TWO")
public class Two extends AuditableModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PARTNER_TWO_SEQ")
	@SequenceGenerator(name = "PARTNER_TWO_SEQ", sequenceName = "PARTNER_TWO_SEQ", allocationSize = 1)
	private Long id;

	@HashKey
	@NameColumn
	@NotNull
	@Column(unique = true)
	private String nametwo;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "two", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<One> one;

	@Widget(title = "Attributes")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "json")
	private String attrs;

	public Two() {
	}

	public Two(String nametwo) {
		this.nametwo = nametwo;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getNametwo() {
		return nametwo;
	}

	public void setNametwo(String nametwo) {
		this.nametwo = nametwo;
	}

	public List<One> getOne() {
		return one;
	}

	public void setOne(List<One> one) {
		this.one = one;
	}

	/**
	 * Add the given {@link One} item to the {@code one}.
	 *
	 * <p>
	 * It sets {@code item.two = this} to ensure the proper relationship.
	 * </p>
	 *
	 * @param item
	 *            the item to add
	 */
	public void addOne(One item) {
		if (getOne() == null) {
			setOne(new ArrayList<>());
		}
		getOne().add(item);
		item.setTwo(this);
	}

	/**
	 * Remove the given {@link One} item from the {@code one}.
	 *
 	 * @param item
	 *            the item to remove
	 */
	public void removeOne(One item) {
		if (getOne() == null) {
			return;
		}
		getOne().remove(item);
	}

	/**
	 * Clear the {@code one} collection.
	 *
	 * <p>
	 * If you have to query {@link One} records in same transaction, make
	 * sure to call {@link javax.persistence.EntityManager#flush() } to avoid
	 * unexpected errors.
	 * </p>
	 */
	public void clearOne() {
		if (getOne() != null) {
			getOne().clear();
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
		if (!(obj instanceof Two)) return false;

		final Two other = (Two) obj;
		if (this.getId() != null || other.getId() != null) {
			return Objects.equals(this.getId(), other.getId());
		}

		if (!Objects.equals(getNametwo(), other.getNametwo())) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hash(84524, this.getNametwo());
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
			.add("id", getId())
			.add("nametwo", getNametwo())
			.omitNullValues()
			.toString();
	}
}
