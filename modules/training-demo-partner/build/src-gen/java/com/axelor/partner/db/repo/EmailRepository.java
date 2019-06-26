package com.axelor.partner.db.repo;

import com.axelor.db.JpaRepository;
import com.axelor.partner.db.Email;

public class EmailRepository extends JpaRepository<Email> {

	public EmailRepository() {
		super(Email.class);
	}

}

