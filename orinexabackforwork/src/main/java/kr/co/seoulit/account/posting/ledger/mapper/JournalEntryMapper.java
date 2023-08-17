package kr.co.seoulit.account.posting.ledger.mapper;

import java.util.ArrayList;

import kr.co.seoulit.account.posting.ledger.entity.JournalDetailResEntity;
import org.apache.ibatis.annotations.Mapper;

import kr.co.seoulit.account.posting.ledger.entity.JournalResEntity;

@Mapper
public interface JournalEntryMapper {
	
    ArrayList<JournalResEntity> selectRangedJournalList(String fromDate, String toDate);
    
    ArrayList<JournalDetailResEntity> selectJournalDetailList(String journalNo);
}
