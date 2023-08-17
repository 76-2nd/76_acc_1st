package kr.co.seoulit.account.posting.ledger.service;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.seoulit.account.posting.business.DTO.JournalDetailresDto;
import kr.co.seoulit.account.posting.business.mapstruct.JournalDetailResMapstruct;
import kr.co.seoulit.account.posting.ledger.entity.AssetEntity;
import kr.co.seoulit.account.posting.ledger.entity.AssetItemEntity;
import kr.co.seoulit.account.posting.ledger.entity.DeptEntity;
import kr.co.seoulit.account.posting.ledger.mapper.CustomerLedgerMapper;
import kr.co.seoulit.account.posting.ledger.dto.*;
import kr.co.seoulit.account.posting.ledger.mapstruct.AssetItemReqMapStruct;
import kr.co.seoulit.account.posting.ledger.mapstruct.AssetItemResMapStruct;
import kr.co.seoulit.account.posting.ledger.mapstruct.AssetMapStruct;
import kr.co.seoulit.account.posting.ledger.mapstruct.DeptMapStruct;
import kr.co.seoulit.account.posting.ledger.repository.AssetItemRepository;
import kr.co.seoulit.account.posting.ledger.repository.AssetRepository;
import kr.co.seoulit.account.posting.ledger.repository.DeptRepository;
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
	private AssetRepository assetRepository;
	@Autowired
	private DeptRepository deptRepository;

	@Autowired
	private AssetItemReqMapStruct assetItemReqMapStruct;
	@Autowired
	private AssetItemResMapStruct assetItemResMapStruct;
	@Autowired
	private AssetMapStruct assetMapStruct;
	@Autowired
	private DeptMapStruct deptMapStruct;
	@Autowired
	private JournalDetailResMapstruct journalDetailResMapstruct;


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
	public ArrayList<JournalDetailresDto> findJournalDetailList(String journalNo) {

		ArrayList<JournalDetailEntity> journalDetailEntity = journalEntryDAO.selectJournalDetailList(journalNo);
		ArrayList<JournalDetailresDto> journalDetailDto= (ArrayList<JournalDetailresDto>) journalDetailResMapstruct.toDto(journalDetailEntity);
		return journalDetailDto;
	}

	@Override
	public ArrayList<AssetResDto> findAssetList() {

		ArrayList<AssetEntity> assetEntity = (ArrayList<AssetEntity>) assetRepository.findAll();

		ArrayList<AssetResDto> resDto = (ArrayList<AssetResDto>) assetMapStruct.toDto(assetEntity);
		return resDto;

	}

	@Override
	public ArrayList<AssetItemResDto> findAssetItemList(String parentsCode) {

		ArrayList<AssetItemEntity> assetItemEntity= assistantLedgerDAO.selectAssetItemList(parentsCode);
		ArrayList<AssetItemResDto> assetItemResDto= (ArrayList<AssetItemResDto>) assetItemResMapStruct.toDto(assetItemEntity);
		return assetItemResDto;
	}

	@Override
	public ArrayList<DeptResDto> findDeptList() {

		ArrayList<DeptEntity> deptEntity = (ArrayList<DeptEntity>) deptRepository.findAll();

		ArrayList<DeptResDto> resDto = (ArrayList<DeptResDto>) deptMapStruct.toDto(deptEntity);
		return resDto;

	}

	@Override
	public void assetStorage(AssetItemReqDto assetItemReqDto) {

		AssetItemEntity assetitemEntity= assetItemReqMapStruct.toEntity(assetItemReqDto);
		assetItemRepository.save(assetitemEntity);

	}


	@Override
	public void removeAssetItem(String assetItemCode) {

		assetItemRepository.deleteById(assetItemCode);

	}

	@Override
	public ArrayList<CustomerLedgerBean> findCustomerLedger(String fromDate, String toDate){

		ArrayList<CustomerLedgerBean> customerLedgerBean = customerLedgerDAO.selectCustomerLedgerList(fromDate,toDate);
			return customerLedgerBean;
	}
	@Override
	public ArrayList<CustomerLedgerDetailResDto> findCustomerLedgerDetailList(String customerCode){
		ArrayList<CustomerLedgerDetailResDto> customerLedgerDetailResDto = customerLedgerDAO.selectCustomerLedgerDetailList(customerCode);
		return customerLedgerDetailResDto;
	}
}
