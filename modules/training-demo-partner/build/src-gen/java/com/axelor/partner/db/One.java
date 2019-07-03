package com.axelor.partner.db;

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
import javax.persistence.Index;
import javax.persistence.ManyToOne;
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
@Table(name = "PARTNER_ONE", indexes = { @Index(columnList = "two") })
public class One extends AuditableModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PARTNER_ONE_SEQ")
	@SequenceGenerator(name = "PARTNER_ONE_SEQ", sequenceName = "PARTNER_ONE_SEQ", allocationSize = 1)
	private Long id;

	@HashKey
	@NameColumn
	@NotNull
	@Column(unique = true)
	private String nameone;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Two two;

	@Widget(title = "Attributes")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "json")
	private String attrs;

	public One() {
	}

	public One(String nameone) {
		this.nameone = nameone;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getNameone() {
		return nameone;
	}

	public void setNameone(String nameone) {
		this.nameone = nameone;
	}

	public Two getTwo() {
		return two;
	}

	public void setTwo(Two two) {
		this.two = two;
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

		if (!Objects.equals(getNameone(), other.getNameone())) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hash(79430, this.getNameone());
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
			.add("id", getId())
			.add("nameone", getNameone())
			.omitNullValues()
			.toString();
	}
}
