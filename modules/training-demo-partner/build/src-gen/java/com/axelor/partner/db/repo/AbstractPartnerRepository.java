package com.axelor.partner.db.repo;

import com.axelor.db.JpaRepository;
import com.axelor.partner.db.Partner;

public abstract class AbstractPartnerRepository extends JpaRepository<Partner> {

	public AbstractPartnerRepository() {
		super(Partner.class);
	}

}

