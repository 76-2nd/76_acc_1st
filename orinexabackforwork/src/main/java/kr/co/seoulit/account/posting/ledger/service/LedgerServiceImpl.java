package kr.co.seoulit.account.posting.ledger.service;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.seoulit.account.posting.ledger.entity.AssetItemEntity;
import kr.co.seoulit.account.posting.ledger.mapper.CustomerLedgerMapper;
import kr.co.seoulit.account.posting.ledger.dto.*;
import kr.co.seoulit.account.posting.ledger.mapstruct.AssetItemReqMapStruct;
import kr.co.seoulit.account.posting.ledger.mapstruct.AssetItemResMapStruct;
import kr.co.seoulit.account.posting.ledger.repository.AssetItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.seoulit.account.posting.business.Entity.JournalEntity;
import kr.co.seoulit.account.posting.business.Entity.JournalDetailEntity;
import kr.co.seoulit.account.posting.ledger.mapper.AssistantLedgerMapper;
import kr.co.seoulit.account.posting.ledger.mapper.AuxiliaryLedgerMapper;
import kr.co.seoulit.account.posting.ledger.mapper.JournalEntryMapper;

@Service
@Transactional
public class LedgerServiceImpl implements LedgerService {

	@Autowired
    private JournalEntryMapper journalEntryDAO;
	@Autowired
    private AuxiliaryLedgerMapper auxiliaryLedgerDAO;
	@Autowired
    private AssistantLedgerMapper assistantLedgerDAO;
	@Autowired
	private CustomerLedgerMapper customerLedgerDAO;

	@Autowired
	private AssetItemRepository assetItemRepository;

	@Autowired
	private AssetItemReqMapStruct assetItemReqMapStruct;
	@Autowired
	private AssetItemResMapStruct assetItemResMapStruct;


    @Override
	public ArrayList<CashJournalBean> findTotalCashJournal(HashMap<String, String> map) {

        	ArrayList<CashJournalBean> cashJournalList = auxiliaryLedgerDAO.selectTotalCashJournalList(map);

        return cashJournalList;
	}

	//현금출납장 조회
	@Override
	public ArrayList<CashJournalBean> findCashJournal(String fromDate, String toDate, String account) {

			HashMap<String, String> map = new HashMap<String, String>();
			map.put("fromDate", fromDate);
			map.put("toDate", toDate);
			map.put("account", account);
			System.out.println("account : " + account + "fromDate : " + fromDate + "toDate : " + toDate);

			ArrayList<CashJournalBean> cashJournalList = auxiliaryLedgerDAO.selectCashJournalList(map);

        return cashJournalList;
	}

	@Override
    public ArrayList<JournalEntity> findRangedJournalList(String fromDate, String toDate) {

        	ArrayList<JournalEntity> journalList = null;
        	journalList = journalEntryDAO.selectRangedJournalList(fromDate, toDate);

        return journalList;
    }

	@Override
    public ArrayList<JournalDetailEntity> findJournalDetailList(String journalNo) {

        	ArrayList<JournalDetailEntity> journalDetailEntities = null;
        	journalDetailEntities = journalEntryDAO.selectJournalDetailList(journalNo);

        return journalDetailEntities;
    }

	@Override
	public ArrayList<AssetBean> findAssetList() {


        	ArrayList<AssetBean> assetBean = null;
        	assetBean = assistantLedgerDAO.selectAssetList();

        return assetBean;
	}

	@Override
	public ArrayList<AssetItemResDto> findAssetItemList(String parentsCode) {

		ArrayList<AssetItemEntity> assetItemEntity= assistantLedgerDAO.selectAssetItemList(parentsCode);
		ArrayList<AssetItemResDto> assetItemResDto= (ArrayList<AssetItemResDto>) assetItemResMapStruct.toDto(assetItemEntity);
		return assetItemResDto;
	}

	@Override
	public ArrayList<DeptBean> findDeptList(){

        	ArrayList<DeptBean> DeptBean = null;
        	DeptBean = assistantLedgerDAO.selectDeptList();

        return DeptBean;
	}

	@Override
	public void assetStorage(AssetItemReqDto assetItemReqDto) {

		AssetItemEntity assetitemEntity= assetItemReqMapStruct.toEntity(assetItemReqDto);
		assetItemRepository.save(assetitemEntity);

	}


	@Override
	public void removeAssetItem(String assetItemCode) {

			assistantLedgerDAO.removeAssetItem(assetItemCode);

	}

	@Override
	public ArrayList<CustomerLedgerBean> findCustomerLedger(String fromDate, String toDate){

		ArrayList<CustomerLedgerBean> customerLedgerBean = customerLedgerDAO.selectCustomerLedgerList(fromDate,toDate);
			return customerLedgerBean;
	}
	@Override
	public ArrayList<CustomerLedgerDetailBean> findCustomerLedgerDetailList(String customerCode){
		ArrayList<CustomerLedgerDetailBean> customerLedgerDetailBean = customerLedgerDAO.selectCustomerLedgerDetailList(customerCode);
		return customerLedgerDetailBean;
	}
}
