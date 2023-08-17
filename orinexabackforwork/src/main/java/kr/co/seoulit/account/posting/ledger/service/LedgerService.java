package kr.co.seoulit.account.posting.ledger.service;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.seoulit.account.posting.business.DTO.JournalDetailresDto;
import kr.co.seoulit.account.posting.business.Entity.JournalEntity;
import kr.co.seoulit.account.posting.business.Entity.JournalDetailEntity;
import kr.co.seoulit.account.posting.ledger.dto.*;

public interface LedgerService {

    //현금출납장 조회
	ArrayList<CashJournalBean> findCashJournal(String fromDate, String toDate, String account);

    ArrayList<CashJournalBean> findTotalCashJournal(HashMap<String, String> map);

    ArrayList<JournalEntity> findRangedJournalList(String fromDate, String toDate);

    ArrayList<JournalDetailresDto> findJournalDetailList(String journalNo);

    ArrayList<AssetResDto> findAssetList();

    ArrayList<AssetItemResDto> findAssetItemList(String parentsCode);

    ArrayList<DeptResDto> findDeptList();

    void assetStorage(AssetItemReqDto assetItemReqDto);

    void removeAssetItem(String assetItemCode);

    ArrayList<CustomerLedgerBean> findCustomerLedger(String fromDate, String toDate);

    ArrayList<CustomerLedgerDetailBean> findCustomerLedgerDetailList(String customerCode);


}

