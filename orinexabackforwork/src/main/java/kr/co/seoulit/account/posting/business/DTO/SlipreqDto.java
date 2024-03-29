package kr.co.seoulit.account.posting.business.DTO;

import kr.co.seoulit.account.sys.base.to.BaseBean;
import kr.co.seoulit.account.sys.common.annotation.Dataset;
import lombok.Data;

@Data
@Dataset(name="gds_slip")
public class SlipreqDto extends BaseBean {
    private String slipNo;
    private String accountPeriodNo;
    private String deptCode;
    private String slipType;
    private String expenseReport;
    private String reportingEmpCode;
    private String reportingDate;
    private String approvalEmpCode;
    private String approvalDate;
    private String slipStatus;
}
