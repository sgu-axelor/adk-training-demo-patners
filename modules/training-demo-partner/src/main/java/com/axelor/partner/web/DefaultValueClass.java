package com.axelor.partner.web;

import com.axelor.partner.db.Email;
import com.axelor.partner.db.Partner;
import com.axelor.partner.service.Service;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.google.inject.Inject;

public class DefaultValueClass {

  @Inject Service service; 
  
  public void setDefaultEmail(ActionRequest req, ActionResponse res) {
    Partner partner = req.getContext().asType(Partner.class);
    if(partner.getEmail()==null) {
      Email email = new Email();
      email.setEmailId(new String(partner.getFirstName()+"."+partner.getLastName()+"@gmail.com"));
      res.setAttr("email", "value",email);
    }
  }
  
  public void show(ActionRequest req, ActionResponse res) {
    Partner partner = req.getContext().asType(Partner.class);
    service.showPatner(partner);
  }
  
  
}
