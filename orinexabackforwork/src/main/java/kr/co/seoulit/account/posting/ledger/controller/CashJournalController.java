package kr.co.seoulit.account.posting.ledger.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.nexacro.java.xapi.data.PlatformData;

import kr.co.seoulit.account.posting.ledger.service.LedgerService;
import kr.co.seoulit.account.posting.ledger.dto.CashJournalBean;
import kr.co.seoulit.account.sys.common.mapper.DatasetBeanMapper;

@RestController
@RequestMapping("/posting")
public class CashJournalController {

	@Autowired
    private LedgerService ledgerService;
	@Autowired
    private DatasetBeanMapper datasetBeanMapper;


    ModelAndView mav = null;
    ModelMap map = new ModelMap();

    //현금출납장 조회
    @RequestMapping("/cashjournal")
    public ArrayList<CashJournalBean> handleRequestInternal(@RequestAttribute("reqData") PlatformData reqData,
                                                            @RequestAttribute("resData") PlatformData resData) throws Exception{
    	String account=reqData.getVariable("account").getString();
    	String fromDate=reqData.getVariable("startDate").getString();
    	String toDate=reqData.getVariable("endDate").getString();
    	System.out.println(account+"@@@@@@@@@@@@@@@@@@");
    	System.out.println(fromDate+"@@@@@@@@@@@@@@@@@@");
    	System.out.println(toDate+"@@@@@@@@@@@@@@@@@@");

        ArrayList<CashJournalBean> cashJournalList = ledgerService.findCashJournal(fromDate, toDate, account);
        datasetBeanMapper.beansToDataset(resData, cashJournalList, CashJournalBean.class);
        return null;
    }
}
