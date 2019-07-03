package com.axelor.partner.db.repo;

import com.axelor.db.JpaRepository;
import com.axelor.partner.db.Two;

public class TwoRepository extends JpaRepository<Two> {

	public TwoRepository() {
		super(Two.class);
	}

}

