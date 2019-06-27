package com.axelor.partner.web;

import com.axelor.partner.db.Email;
import com.axelor.partner.db.Partner;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;

public class DefaultValueClass {

  public void setDefaultEmail(ActionRequest req, ActionResponse res) {
    Partner partner = req.getContext().asType(Partner.class);
    if(partner.getEmail()==null) {
      Email email = new Email();
      email.setEmailId(new String(partner.getFirstName()+"."+partner.getLastName()+"@gmail.com"));
      res.setAttr("email", "value",email);
    }
  }
}
