/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 15, 2022 - 4:33:06 PM
 *  ***************************************
 */

package com.nable.crib.comn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nable.crib.comn.model.audit.Auditable;

import lombok.Data;

@Data
@Entity
@Table(name = "CRIB_REQUEST_DETAIL")
public class RequestDetail extends Auditable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
    private Long id;
	
	@Column(name = "REQUEST_MASTER_ID")
    private Long requestMasterId;
	
	@Column(name = "SEARCH_VALUE", length = 50)
    private String searchValue;
	
	@Column(name = "IS_CRIB_AVAILABLE", columnDefinition = "SMALLINT")
	protected Short isCribAvailable;
	
	@Column(name = "IS_HIT", columnDefinition = "SMALLINT")
	protected Short isHit;
	
	@Column(name = "SOURCE_NAME", length = 20)
    private String sourceName;
	
	@Column(name = "REPORT_PATH", length = 100)
    private String reportPath;
	
	@Column(name = "PER_HIT_COST", precision=10, scale=2)
	private Double perHitCost;
	
	@Column(name = "QUERY_TYPE", length = 20)
    private String queryType;
	
	@Column(name = "QUERY_REFERENCE_NO", length = 100)
    private String queryReferenceNo;
	
	@Column(name = "QUERY_BUREAU_ID", length = 100)
    private String queryBureauId;
	
	@Column(name = "BUREAU_REFERENCE_NO", length = 50)
    private String bureauReferenceNo;
	
	@Column(name = "BULK_OFFLINE_REQUEST_NO", length = 50)
    private String bulkOfflineRequestNo;
	
	@Column(name = "BULK_OFFLINE_RESPONSE_FILE", length = 50)
    private String bulkOfflineResponseFile;
	
	@Column(name = "BULK_OFFLINE_TICKET_ID", length = 50)
    private String bulkOfflineTicketId;
	
	@Column(name = "REPORT_ID")
    private Long reportId;
	
	@Column(name = "STATUS_CODE", length = 50)
    private String statusCode;
	
	@Column(name = "STATUS", length = 255)
    private String status;
	
	@Column(name = "STATUS_DESCRIPTION", length = 255)
    private String statusDescription;
	
	@Column(name = "ERROR_CODE", length = 50)
    private String errorCode;
	
	@Column(name = "ERROR", length = 255)
    private String error;
	
	@Column(name = "BULK_OFFLINE_STATUS", length = 50)
    private String bulkOfflineStatus;
	
	@Column(name = "ANALYTICAL_REPORT_STATUS", length = 50)
    private String analyticalReportStatus;
	
	@Column(name = "IS_SCORE_AVAILABLE", columnDefinition = "SMALLINT")
	protected Short isScoreAvailable;
	
	@Column(name = "IS_ANALYTICAL_REPORT_AVAILABLE", columnDefinition = "SMALLINT")
	protected Short isAnalyticalReportAvailable;
	
	@Column(name = "SCORE_PER_HIT_COST", precision=10, scale=2)
	private Double scorePerHitCost;
	
}
