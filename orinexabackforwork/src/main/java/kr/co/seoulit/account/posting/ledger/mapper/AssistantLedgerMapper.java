package kr.co.seoulit.account.posting.ledger.mapper;

import java.util.ArrayList;

import kr.co.seoulit.account.posting.ledger.entity.AssetItemEntity;
import org.apache.ibatis.annotations.Mapper;

import kr.co.seoulit.account.posting.ledger.dto.DeptBean;

@Mapper
public interface AssistantLedgerMapper {

	public ArrayList<AssetItemEntity> selectAssetItemList(String assetCode);

	ArrayList<DeptBean> selectDeptList();

}
