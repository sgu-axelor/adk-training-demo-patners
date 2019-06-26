package com.axelor.partner.db.repo;

import com.axelor.db.JpaRepository;
import com.axelor.db.Query;
import com.axelor.partner.db.Catagory;

public class CatagoryRepository extends JpaRepository<Catagory> {

	public CatagoryRepository() {
		super(Catagory.class);
	}

	public Catagory findByCode(String code) {
		return Query.of(Catagory.class)
				.filter("self.code = :code")
				.bind("code", code)
				.fetchOne();
	}

	public Catagory findByName(String name) {
		return Query.of(Catagory.class)
				.filter("self.name = :name")
				.bind("name", name)
				.fetchOne();
	}

}

