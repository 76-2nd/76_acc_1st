package kr.co.seoulit.account.posting.ledger.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping("/findRangedJournalList2")
	public ArrayList<JournalEntity> findRangedJournalList(@RequestParam String fromDate,
											  @RequestParam String toDate) {

            ArrayList<JournalEntity> journalList = ledgerService.findRangedJournalList(fromDate, toDate);
  
            return journalList;
    }
    
    @RequestMapping("/findJournalDetailList2")
    public ArrayList<JournalDetailEntity> findJournalDetailList(@RequestParam String journalNo) {

            ArrayList<JournalDetailEntity> journalDetailList = ledgerService.findJournalDetailList(journalNo);

            return journalDetailList;
    }
}
