package kr.co.seoulit.account.posting.ledger.controller;

import java.util.ArrayList;

import com.nexacro.java.xapi.data.PlatformData;
import kr.co.seoulit.account.posting.ledger.dto.JournalDetailDtoRes;
import kr.co.seoulit.account.posting.ledger.dto.JournalDtoRes;
import kr.co.seoulit.account.sys.common.mapper.DatasetBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import kr.co.seoulit.account.posting.ledger.service.LedgerService;


@RestController
@RequestMapping("/posting")
public class JournalEntryController {
	
    @Autowired
    private LedgerService ledgerService;
    @Autowired
    private DatasetBeanMapper datasetBeanMapper;

    @RequestMapping("/findRangedJournalList2")
	public ArrayList<JournalDtoRes> findRangedJournalList(@RequestAttribute("reqData") PlatformData reqData,
                                                                 @RequestAttribute("resData") PlatformData resData) throws Exception {

        String fromDate = reqData.getVariable("startDate").getString();
        String toDate = reqData.getVariable("endDate").getString();

        ArrayList<JournalDtoRes> journalList = ledgerService.findRangedJournalList(fromDate, toDate);

        datasetBeanMapper.beansToDataset(resData, journalList, JournalDtoRes.class);

        return null;
    }




    @RequestMapping(value = "/findJournalDetailList2")
    public ArrayList<JournalDetailDtoRes> findJournalDetailList(@RequestAttribute("reqData") PlatformData reqData,
                                                  @RequestAttribute("resData")PlatformData resData) throws Exception {

        String journalNo = reqData.getVariable("journalNo").getString();

        ArrayList<JournalDetailDtoRes> journalDetailDtoRes = ledgerService.findJournalDetailList(journalNo);

        datasetBeanMapper.beansToDataset(resData, journalDetailDtoRes, JournalDetailDtoRes.class);

        return null;
    }
}
