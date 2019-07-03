package com.axelor.partner.db.repo;

import com.axelor.db.JpaRepository;
import com.axelor.partner.db.One;

public class OneRepository extends JpaRepository<One> {

	public OneRepository() {
		super(One.class);
	}

}

