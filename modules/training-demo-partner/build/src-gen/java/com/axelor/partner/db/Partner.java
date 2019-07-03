package com.axelor.partner.db;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.NameColumn;
import com.axelor.db.annotations.VirtualColumn;
import com.axelor.db.annotations.Widget;
import com.google.common.base.MoreObjects;

@Entity
@Cacheable
@DynamicInsert
@DynamicUpdate
@Table(name = "PARTNER_PARTNER", indexes = { @Index(columnList = "fullName"), @Index(columnList = "email") })
public class Partner extends AuditableModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PARTNER_PARTNER_SEQ")
	@SequenceGenerator(name = "PARTNER_PARTNER_SEQ", sequenceName = "PARTNER_PARTNER_SEQ", allocationSize = 1)
	private Long id;

	@NotNull
	private String firstName;

	@NotNull
	private String lastName;

	@Widget(selection = "agreementPeriod-selection-list")
	private Integer agreementPeriod = 0;

	@Widget(search = { "firstName", "lastName" })
	@NameColumn
	@VirtualColumn
	@Access(AccessType.PROPERTY)
	private String fullName;

	@Widget(image = true)
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] image;

	@Widget(title = "Partnered On", help = "Date on which Patner is registered")
	@NotNull
	private LocalDate startdate;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "partner", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Address> addresses;

	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Email email;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<Company> company;

	private Integer companyassociations = 0;

	@Widget(title = "Attributes")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "json")
	private String attrs;

	public Partner() {
	}

	public Partner(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAgreementPeriod() {
		return agreementPeriod == null ? 0 : agreementPeriod;
	}

	public void setAgreementPeriod(Integer agreementPeriod) {
		this.agreementPeriod = agreementPeriod;
	}

	public String getFullName() {
		try {
			fullName = computeFullName();
		} catch (NullPointerException e) {
			Logger logger = LoggerFactory.getLogger(getClass());
			logger.error("NPE in function field: getFullName()", e);
		}
		return fullName;
	}

	protected String computeFullName() {
		if (firstName == null && lastName == null)
		  return null;
		return firstName + " " + lastName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	/**
	 * Date on which Patner is registered
	 *
	 * @return the property value
	 */
	public LocalDate getStartdate() {
		return startdate;
	}

	public void setStartdate(LocalDate startdate) {
		this.startdate = startdate;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	/**
	 * Add the given {@link Address} item to the {@code addresses}.
	 *
	 * <p>
	 * It sets {@code item.partner = this} to ensure the proper relationship.
	 * </p>
	 *
	 * @param item
	 *            the item to add
	 */
	public void addAddress(Address item) {
		if (getAddresses() == null) {
			setAddresses(new ArrayList<>());
		}
		getAddresses().add(item);
		item.setPartner(this);
	}

	/**
	 * Remove the given {@link Address} item from the {@code addresses}.
	 *
 	 * @param item
	 *            the item to remove
	 */
	public void removeAddress(Address item) {
		if (getAddresses() == null) {
			return;
		}
		getAddresses().remove(item);
	}

	/**
	 * Clear the {@code addresses} collection.
	 *
	 * <p>
	 * If you have to query {@link Address} records in same transaction, make
	 * sure to call {@link javax.persistence.EntityManager#flush() } to avoid
	 * unexpected errors.
	 * </p>
	 */
	public void clearAddresses() {
		if (getAddresses() != null) {
			getAddresses().clear();
		}
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public Set<Company> getCompany() {
		return company;
	}

	public void setCompany(Set<Company> company) {
		this.company = company;
	}

	/**
	 * Add the given {@link Company} item to the {@code company}.
	 *
	 * @param item
	 *            the item to add
	 */
	public void addCompany(Company item) {
		if (getCompany() == null) {
			setCompany(new HashSet<>());
		}
		getCompany().add(item);
	}

	/**
	 * Remove the given {@link Company} item from the {@code company}.
	 *
 	 * @param item
	 *            the item to remove
	 */
	public void removeCompany(Company item) {
		if (getCompany() == null) {
			return;
		}
		getCompany().remove(item);
	}

	/**
	 * Clear the {@code company} collection.
	 *
	 */
	public void clearCompany() {
		if (getCompany() != null) {
			getCompany().clear();
		}
	}

	public Integer getCompanyassociations() {
		return companyassociations == null ? 0 : companyassociations;
	}

	public void setCompanyassociations(Integer companyassociations) {
		this.companyassociations = companyassociations;
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
		if (!(obj instanceof Partner)) return false;

		final Partner other = (Partner) obj;
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
			.add("firstName", getFirstName())
			.add("lastName", getLastName())
			.add("agreementPeriod", getAgreementPeriod())
			.add("startdate", getStartdate())
			.add("companyassociations", getCompanyassociations())
			.omitNullValues()
			.toString();
	}
}
