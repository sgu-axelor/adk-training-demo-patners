package com.axelor.partner.controller;

import com.axelor.app.AxelorModule;
import com.axelor.partner.service.Service;
import com.axelor.partner.service.ServiceImpl;

public class ControllerGuice extends AxelorModule{

  @Override
  protected void configure() {
    bind(Service.class).to(ServiceImpl.class);
    
  }

}
