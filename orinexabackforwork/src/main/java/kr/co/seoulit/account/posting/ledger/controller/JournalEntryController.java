package kr.co.seoulit.account.posting.ledger.controller;

import java.util.ArrayList;

import com.nexacro.java.xapi.data.PlatformData;
import kr.co.seoulit.account.posting.business.DTO.JournalDetailresDto;
import kr.co.seoulit.account.sys.common.mapper.DatasetBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.seoulit.account.posting.business.Entity.JournalEntity;
import kr.co.seoulit.account.posting.business.Entity.JournalDetailEntity;
import kr.co.seoulit.account.posting.ledger.service.LedgerService;

@RestController
@RequestMapping("/posting")
public class JournalEntryController {
	
    @Autowired
    private LedgerService ledgerService;
    @Autowired
    private DatasetBeanMapper datasetBeanMapper;

    @RequestMapping("/findRangedJournalList2")
	public ArrayList<JournalEntity> findRangedJournalList(@RequestParam String fromDate,
											  @RequestParam String toDate) {

            ArrayList<JournalEntity> journalList = ledgerService.findRangedJournalList(fromDate, toDate);
  
            return journalList;
    }

    @RequestMapping("/findJournalDetailList2")
    public ArrayList<JournalDetailEntity> findJournalDetailList(@RequestParam String journalNo, @RequestAttribute("resData") PlatformData resData) throws Exception {

        ArrayList<JournalDetailresDto> journalDetailList = ledgerService.findJournalDetailList(journalNo);
        datasetBeanMapper.beansToDataset(resData, journalDetailList, JournalDetailresDto.class);

        return null;
    }
}
