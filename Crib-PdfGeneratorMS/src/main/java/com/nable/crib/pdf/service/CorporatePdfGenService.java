/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 16, 2022 - 12:31:00 AM
 *  ***************************************
 */

package com.nable.crib.pdf.service;

import static org.thymeleaf.templatemode.TemplateMode.HTML;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;
import com.nable.crib.pdf.bean.crib.CorporateCribObject;
import com.nable.crib.pdf.bean.score.CorporateScoreObject;
import com.nable.crib.pdf.response.PdfResponse;
import com.nable.crib.pdf.response.Status;
import com.nable.crib.pdf.util.GetCorporateCribData;

@Service("CorporatePdfGenService")
public class CorporatePdfGenService {
	
	static Logger log = LoggerFactory.getLogger(CorporatePdfGenService.class);
	
	@Autowired
	GetCorporateCribData getCorpCribData;
	
	public PdfResponse get(Long requestDetailId, boolean scoreRequired) throws Exception {
		
		PdfResponse pdfResponse = new PdfResponse();
		
		CorporateCribObject corpCribObj = getCorpCribData.getCorporateCribDataByDetailId(requestDetailId);			
		CorporateScoreObject corpScoreObj = new CorporateScoreObject();
		if(scoreRequired) {
			//GetConsumerScoreData consScore = new GetConsumerScoreData();
			//consScoreObj = consScore.getConsumerScoreDataByDetailId(conn, requestDetailId);
		}
				
		Context context = getCorporateContext(corpCribObj, scoreRequired, corpScoreObj);
        String html = loadAndFillTemplate(context, "corp_crib_report_pdf_template");
		
        pdfResponse.setPdf(renderPdf(html));
		pdfResponse.setStatus(Status.SUCCESS.getStatus());
		pdfResponse.setStatusCode(Status.SUCCESS.getStatusCode());
		pdfResponse.setMessage("Get Corporate CRIB Report PDF Successful");
		
		return pdfResponse;		
	}
		
	private String renderPdf(String html) throws IOException, DocumentException {

        ByteArrayOutputStream pdfStream = new ByteArrayOutputStream();
        ITextRenderer renderer = new ITextRenderer(20f * 4f / 3f, 20);
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(pdfStream);
        pdfStream.close();
        
        byte[] encoded = Base64.encodeBase64(pdfStream.toByteArray());
		String encodedString = new String(encoded);
		
        return encodedString;
    }
	
	private Context getCorporateContext(CorporateCribObject cribData, Boolean scoreRequired, CorporateScoreObject scoreData) {
        Context context = new Context();
        context.setVariable("requestMain", cribData.getRequestMain());
        context.setVariable("requestMaster", cribData.getRequestMaster());
        context.setVariable("requestDetail", cribData.getRequestDetail());
        context.setVariable("firmographicDetails", cribData.getFirmographicDetails());
        context.setVariable("mailingAddresses", cribData.getMailingAddresses());
        context.setVariable("permanentAddresses", cribData.getPermanentAddresses());
        context.setVariable("reportedNames", cribData.getReportedNames());
        context.setVariable("relationshipDetails", cribData.getRelationshipDetails());
        context.setVariable("settledCFDetails", cribData.getSettledCFDetails());
        context.setVariable("settledCFSummary", cribData.getSettledCFSummary());
        context.setVariable("lendingInstInquiries", cribData.getLendingInstInquiries());
        context.setVariable("inquiriesBySubject", cribData.getInquiriesBySubject());
        context.setVariable("creditFacilityDetails", cribData.getCreditFacilityDetails());
        context.setVariable("cFForLast24Months", cribData.getCFForLast24Months());
        context.setVariable("disputeDetails", cribData.getDisputeDetails());
        context.setVariable("potAndCurrLiabilities", cribData.getPotAndCurrLiabilities());
        context.setVariable("cFOfGlanceStatus", cribData.getCFOfGlanceStatus());
        context.setVariable("dishonChequeSummary", cribData.getDishonChequeSummary());
        context.setVariable("dishonChequeDetails", cribData.getDishonChequeDetails());
        context.setVariable("econActivityHistory", cribData.getEconActivityHistory());
        context.setVariable("catalogueDescription", cribData.getCatalogueDescription());
        context.setVariable("pastMonths", cribData.getLast24Months());
	    context.setVariable("pastYears", cribData.getLast5Years());
	    
	    context.setVariable("scoreRequired", scoreRequired);
	    context.setVariable("scoreGeneralDetail", scoreData.getScoreGeneralDetails()); 
	    context.setVariable("cribScore", scoreData.getCribScore()); 
	    context.setVariable("scoreObservations", scoreData.getScoreObservations());
               
        return context;
    }
	
	private String loadAndFillTemplate(Context context, String template) {
    	
    	ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setCheckExistence(true);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        return templateEngine.process(template, context);             
    }

}
