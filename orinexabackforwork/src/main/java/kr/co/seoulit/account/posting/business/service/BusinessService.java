package kr.co.seoulit.account.posting.business.service;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.seoulit.account.posting.business.DTO.*;
import kr.co.seoulit.account.posting.business.Entity.JournalEntity;
import kr.co.seoulit.account.posting.business.Entity.JournalDetailEntity;
import kr.co.seoulit.account.posting.business.Entity.SlipEntity;

public interface BusinessService {

    public ArrayList<JournalDetailEntity> findJournalDetailList(String journalNo);

    public ArrayList<JournalDetailEntity> detailAccountList(String accountCode);

    public String modifyJournalDetail(JournalDetailEntity journalDetailEntity);

    public ArrayList<JournalEntity> findSingleJournalList(String slipNo);

    public void removeJournal(String journalNo);

    void modifyJournal(String slipNo, ArrayList<JournalEntity> journalBeanList);

    public ArrayList<SlipresDto> findRangedSlipList(HashMap<String, Object> params);

    public ArrayList<SlipEntity> findDisApprovalSlipList();

    public void registerSlip(SlipreqDto slipEntity, ArrayList<JournalreqDto> journalEntities);

    public void removeSlip(String slipNo);

    public String modifySlip(SlipreqDto slipEntity, ArrayList<JournalreqDto> journalEntities);

    public void modifyapproveSlip(ArrayList<SlipEntity> slipEntities);

    public ArrayList<SlipEntity> findSlipDataList(String slipDate);

    public HashMap<String, Object> findAccountingSettlementStatus(HashMap<String, Object> params);

    public ArrayList<SlipEntity> findSlip(String slipNo);

	ArrayList<JournalresDto> findRangedJournalList(String fromDate, String toDate);

// Nexacro
    public void addSlip(SlipEntity slipObj, ArrayList<JournalEntity> journalBeans, ArrayList<JournalDetailEntity> journalDetail);
}

