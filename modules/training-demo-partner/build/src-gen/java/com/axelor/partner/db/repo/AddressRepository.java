package com.axelor.partner.db.repo;

import com.axelor.db.JpaRepository;
import com.axelor.partner.db.Address;

public class AddressRepository extends JpaRepository<Address> {

	public AddressRepository() {
		super(Address.class);
	}

}

