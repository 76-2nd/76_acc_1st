package kr.co.seoulit.account.posting.ledger.controller;

import java.util.ArrayList;

import kr.co.seoulit.account.posting.ledger.dto.AssetItemReqDto;
import kr.co.seoulit.account.posting.ledger.dto.AssetItemResDto;
import kr.co.seoulit.account.posting.ledger.dto.AssetResDto;
import kr.co.seoulit.account.posting.ledger.dto.DeptResDto;
import kr.co.seoulit.account.posting.ledger.entity.AssetEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nexacro.java.xapi.data.PlatformData;

import kr.co.seoulit.account.posting.ledger.service.LedgerService;
import kr.co.seoulit.account.sys.common.mapper.DatasetBeanMapper;

@RestController
@RequestMapping("/posting")
public class AssetManagementController{

	@Autowired
    private LedgerService ledgerService;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;

	@RequestMapping("/assetList")
	public ArrayList<AssetEntity> assetList(@RequestAttribute("reqData") PlatformData reqData,
											@RequestAttribute("resData") PlatformData resData) throws Exception {

		ArrayList<AssetResDto> AssetList = ledgerService.findAssetList();
		datasetBeanMapper.beansToDataset(resData, AssetList, AssetResDto.class);
		return null;
	}

	@RequestMapping("/assetitemlist")
    public ArrayList<AssetItemResDto> assetItemList(@RequestAttribute("reqData") PlatformData reqData,
													@RequestAttribute("resData") PlatformData resData) throws Exception{
        String assetCode = reqData.getVariable("assetCode").getString();

        	ArrayList<AssetItemResDto> AssetItemList = ledgerService.findAssetItemList(assetCode);
        	datasetBeanMapper.beansToDataset(resData, AssetItemList, AssetItemResDto.class);
        	return null;
    }

	@RequestMapping("/deptlist")
	public ArrayList<DeptResDto> deptList(@RequestAttribute("reqData") PlatformData reqData,
										  @RequestAttribute("resData") PlatformData resData) throws Exception {

		ArrayList<DeptResDto> DeptList = ledgerService.findDeptList();
		datasetBeanMapper.beansToDataset(resData, DeptList, DeptResDto.class);

		return DeptList;
	}

	@PostMapping("/assetitemlistRegist")
	public void assetStorage(@RequestAttribute("reqData") PlatformData reqData,
							 @RequestAttribute("resData") PlatformData resData) throws Exception {

		AssetItemReqDto assetItemReqDto = datasetBeanMapper.datasetToBean(reqData, AssetItemReqDto.class);
		ledgerService.assetStorage(assetItemReqDto);

	}


	@RequestMapping("/assetremoval")
	public void assetRemove(@RequestAttribute("reqData") PlatformData reqData,
							@RequestAttribute("resData") PlatformData resData) throws Exception {

		String assetItemCode = reqData.getVariable("code").getString();
		ledgerService.removeAssetItem(assetItemCode);

	}
}
