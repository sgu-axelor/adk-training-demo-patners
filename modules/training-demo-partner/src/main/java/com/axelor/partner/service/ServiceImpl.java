package com.axelor.partner.service;

import com.axelor.partner.db.Partner;

public class ServiceImpl implements Service{

  @Override
  public void showPatner(Partner partner) {
    System.err.println(partner);
    
  }
  
}
