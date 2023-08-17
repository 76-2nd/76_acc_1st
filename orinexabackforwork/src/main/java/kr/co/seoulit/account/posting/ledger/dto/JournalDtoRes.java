package kr.co.seoulit.account.posting.ledger.dto;

import kr.co.seoulit.account.posting.business.DTO.JournalDetailresDto;
import kr.co.seoulit.account.sys.base.to.BaseBean;
import kr.co.seoulit.account.sys.common.annotation.Dataset;
import lombok.Data;

import java.util.List;

@Data
@Dataset(name="gds_journal")
public class JournalDtoRes extends BaseBean {

    private String journalNo;
    private String slipNo;
    private String balanceDivision;
    private String accountInnerCode;
    private String accountName;
    private String leftDebtorPrice;
    private String rightCreditsPrice;
    private List<JournalDetailresDto> journalDetailList;
}
