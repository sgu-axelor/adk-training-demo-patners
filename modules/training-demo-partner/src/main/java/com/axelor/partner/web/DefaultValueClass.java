package com.axelor.partner.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.eclipse.birt.core.exception.BirtException;

import com.axelor.app.AppSettings;
import com.axelor.partner.db.Company;
import com.axelor.partner.db.Email;
import com.axelor.partner.db.Partner;
import com.axelor.partner.db.Two;
import com.axelor.partner.db.repo.CompanyRepository;
import com.axelor.partner.service.Service;
import com.axelor.report.ReportGenerator;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.opencsv.CSVWriter;

public class DefaultValueClass {

	@javax.inject.Inject
	Service service;
	/*
	 * @Inject EmailRepository emailrepo;
	 */

	@javax.inject.Inject
	CompanyRepository companyrepo;
	@javax.inject.Inject
	private ReportGenerator generator;

	public void setDefaultEmail(ActionRequest req, ActionResponse res) {
		Partner partner = req.getContext().asType(Partner.class);
		if (partner.getEmail() == null) {
			Email email =  new Email();
			email.setEmailId(new String(partner.getFirstName() + "." + partner.getLastName() + "@gmail.com"));
			res.setAttr("email", "value", email);
		}

	}

	public void setCompanyAssociationsCount(ActionRequest req, ActionResponse res) {
		Partner partner = req.getContext().asType(Partner.class);
		if (partner.getCompany() != null)
			res.setAttr("companyassociations", "value", partner.getCompany().size());
	}

	/*
	 * public Partner savePartnerImportXml(Object bean, Map context) { Partner
	 * partner = (Partner) bean; System.err.println("Partner: "+partner);
	 * System.out.println(); System.err.println("Context: "+context); HashMap<?, ?>
	 * email = (HashMap<?, ?>) context.get("email"); Email e =
	 * emailrepo.all().filter("self.emailId= '" + email.get("emailId") +
	 * "'").fetchOne();
	 * 
	 * if (e == null) e = new Email((String) email.get("emailId")); else e = null;
	 * partner.setEmail(e);
	 * 
	 * return partner; }
	 */
	public void showMinPeriod(ActionRequest req, ActionResponse res) {
		Two two = req.getContext().asType(Two.class);
	}

	/*
	 * public Partner savePartnerImportCsv(Object bean, Map context) { Partner
	 * partner = (Partner) bean; Email e = emailrepo.all().filter("self.emailId= '"
	 * + context.get("email") + "'").fetchOne();
	 * 
	 * if (e == null) e = new Email((String) context.get("email")); else e = null;
	 * partner.setEmail(e);
	 * 
	 * return partner; }
	 */

	public void exportPatner(ActionRequest req, ActionResponse res) {
		Partner partner = req.getContext().asType(Partner.class);
		System.err.println("incoming");
		String filepath = "/home/axelor/Projects/training/ADK/training-demo/src/main/resources/data-demo/"
				+ partner.getFirstName() + ".csv";
		File file = new File(filepath);
		try {

			CSVWriter writer = new CSVWriter(new FileWriter(file));

			String[] header = { "firstName", "lastName", "email", "startdate", "agreementperiod", "company" };
			writer.writeNext(header);
			String company = "";
			for (Company c : partner.getCompany()) {
				company += c.getName() + "|";
			}

			String[] data = { partner.getFirstName(), partner.getLastName(), partner.getEmail().getEmailId(),
					String.valueOf(partner.getStartdate()), String.valueOf(partner.getAgreementPeriod()), company };
			writer.writeNext(data);
			System.err.println("writting");
			System.err.println(filepath);
			writer.close();
		} catch (Exception e) {
			System.err.println(e);
		}

	}

	/*
	 * public void showCompanyReport(String design, Map<String, Object> params)
	 * throws IOException, BirtException {
	 * 
	 * OutputStream output = new FileOutputStream("Company");
	 * 
	 * try { generator.generate(output, design, ".pdf", params,
	 * Locale.getDefault()); } finally { output.close(); } }
	 */

	public void showCompanyReport(ActionRequest req, ActionResponse res) throws IOException, BirtException {
		OutputStream output = new FileOutputStream("Company.pdf");
		Map<String, Object> params = new HashMap();
		params.put("ids", 1);

		try {
			generator.generate(output, "company.rptdesign", ".pdf", params, Locale.getDefault());
		} finally {
			output.close();
		}
	}
	
	public void setReportParams(ActionRequest req, ActionResponse res) {
		String attachmentPath = AppSettings.get().getPath("file.upload.dir", "");
		if (attachmentPath.charAt(attachmentPath.length()-1)=='/')
			req.getContext().put("Attachments", attachmentPath);
		else 
			req.getContext().put("Attachments", attachmentPath+"/");
		req.getContext().put("locale", Locale.getDefault());
	}
}
