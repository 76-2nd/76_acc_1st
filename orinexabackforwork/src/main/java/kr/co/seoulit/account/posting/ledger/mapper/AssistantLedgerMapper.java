package kr.co.seoulit.account.posting.ledger.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.seoulit.account.posting.ledger.entity.AssetItemEntity;
import org.apache.ibatis.annotations.Mapper;

import kr.co.seoulit.account.posting.ledger.dto.AssetBean;
import kr.co.seoulit.account.posting.ledger.dto.DeptBean;

@Mapper
public interface AssistantLedgerMapper {

	ArrayList<AssetBean> selectAssetList();

	public ArrayList<AssetItemEntity> selectAssetItemList(String assetCode);

	ArrayList<DeptBean> selectDeptList();

	void removeAssetItem(String assetItemCode);
}
