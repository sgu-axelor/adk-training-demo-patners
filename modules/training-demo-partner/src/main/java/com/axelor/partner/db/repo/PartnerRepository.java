package com.axelor.partner.db.repo;

import java.util.Map;

import com.axelor.partner.db.Partner;

public class PartnerRepository extends AbstractPartnerRepository{

  @Override
  public Map<String, Object> populate(Map<String, Object> json, Map<String, Object> context) {
    if (!context.containsKey("json-enhance")) {
      return json;
    }
    try {
      Long id = (Long) json.get("id");
      Partner partner = find(id);
      System.err.println(partner.getAddresses().get(0));
      //System.err.println(partner.getEmail().getEmailId());
      json.put("address", partner.getAddresses().get(0));
      json.put("hasImage", partner.getImage() != null);
    } catch (Exception e) {
    }

    return json;
  }
  
}
