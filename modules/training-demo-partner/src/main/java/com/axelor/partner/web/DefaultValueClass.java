package com.axelor.partner.web;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.axelor.partner.db.Email;
import com.axelor.partner.db.One;
import com.axelor.partner.db.Partner;
import com.axelor.partner.db.Two;
import com.axelor.partner.db.repo.EmailRepository;
import com.axelor.partner.service.Service;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;

public class DefaultValueClass {

  @Inject
  Service service;
  @Inject
  EmailRepository emailrepo;

  public void setDefaultEmail(ActionRequest req, ActionResponse res) {
    Partner partner = req.getContext().asType(Partner.class);
    if (partner.getEmail() == null) {
      Email email = new Email();
      email.setEmailId(
          new String(partner.getFirstName() + "." + partner.getLastName() + "@gmail.com"));

      res.setAttr("email", "value", email);
    }
  }


  public void setCompanyAssociationsCount(ActionRequest req, ActionResponse res) {
    Partner partner = req.getContext().asType(Partner.class);
    if (partner.getCompany().size() > 0)
      res.setAttr("companyassociations", "value", partner.getCompany().size());
  }

  public Partner savePartner(Object bean, Map context) {
    Partner partner = (Partner) bean;
    HashMap<?, ?> email = (HashMap<?, ?>) context.get("email");
    Email e = emailrepo.all().filter("self.emailId= '" + email.get("emailId") + "'").fetchOne();

    if (e == null) e = new Email((String) email.get("emailId"));
    else
      e = null;
    partner.setEmail(e);

    return partner;
  }
  
  public void showMinPeriod(ActionRequest req, ActionResponse res) {
    Two two =  req.getContext().asType(Two.class);
  }

  /*
   * public Partner savePartner(Object bean, Map context) { Partner partner = (Partner) bean; Email
   * e = emailrepo.all().filter("self.emailId= '" + context.get("email") + "'").fetchOne();
   * 
   * if (e == null) e = new Email((String) context.get("email")); else e = null;
   * partner.setEmail(e);
   * 
   * return partner; }
   */

}
