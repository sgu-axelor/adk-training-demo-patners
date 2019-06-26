package com.axelor.partner.db.repo;

import com.axelor.db.JpaRepository;
import com.axelor.db.Query;
import com.axelor.partner.db.Company;

public class CompanyRepository extends JpaRepository<Company> {

	public CompanyRepository() {
		super(Company.class);
	}

	public Company findByName(String name) {
		return Query.of(Company.class)
				.filter("self.name = :name")
				.bind("name", name)
				.fetchOne();
	}

}

