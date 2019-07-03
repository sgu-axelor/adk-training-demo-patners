package com.axelor.partner.db.repo;

import java.util.Map;

import com.axelor.partner.db.Company;
import com.axelor.partner.db.Partner;

public class PartnerRepository extends AbstractPartnerRepository {

  @Override
  public Map<String, Object> populate(Map<String, Object> json, Map<String, Object> context) {
    int s = 0;
    if (!context.containsKey("json-enhance")) {
      return json;
    }
    try {
      Long id = (Long) json.get("id");
      Partner partner = find(id);
      if (partner.getCompany().size() > 0) {
        for (Company c : partner.getCompany()) {
          s += 1;
        }
      }
      json.put("seeCompany", s);

      if (partner.getAddresses().size() != 0) {
        json.put("address", partner.getAddresses().get(0));
      } else {
        json.put("address", null);
      }

      json.put("hasImage", partner.getImage() != null);
      json.put("emailAdd", partner.getEmail()!=null ? partner.getEmail().getEmailId():null);

    } catch (Exception e) {
    }

    return json;
  }

}
