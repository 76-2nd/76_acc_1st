package kr.co.seoulit.account.posting.ledger.dto;

import kr.co.seoulit.account.sys.common.annotation.Dataset;
import lombok.Data;

import java.io.Serializable;

@Data
@Dataset(name = "ds_assetitemRegist")
public class AssetItemReqDto implements Serializable {
	String assetItemCode;
	String assetItemName;
	String acquisitionDate; //취득일자
	int acquisitionAmount; //취득금액
	int usefulLife; //내용연수
	String managementDeptName; // 관리부서이름
	String parentsCode;
	String parentsName;
	//String accumulatedDepreciation; // 감가상각누계액
	//String residualValue; // 잔존가치
	String status;
	//String residualLife; //잔여연수


}
