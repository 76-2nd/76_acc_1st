package kr.co.seoulit.account.posting.ledger.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.nexacro.java.xapi.data.PlatformData;

import kr.co.seoulit.account.posting.ledger.service.LedgerService;
import kr.co.seoulit.account.posting.ledger.service.LedgerServiceImpl;
import kr.co.seoulit.account.posting.ledger.to.AssetBean;
import kr.co.seoulit.account.posting.ledger.to.AssetItemBean;
import kr.co.seoulit.account.posting.ledger.to.DeptBean;
import kr.co.seoulit.account.sys.common.exception.DataAccessException;
import kr.co.seoulit.account.sys.common.mapper.DatasetBeanMapper;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("/posting")
public class AssetManagementController{

	@Autowired
    private LedgerService ledgerService;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;
    
	@RequestMapping("/assetList")
	public ArrayList<AssetBean> assetList(@RequestAttribute("reqData") PlatformData reqData,
            @RequestAttribute("resData") PlatformData resData) throws Exception{
    	
        	ArrayList<AssetBean> AssetList = ledgerService.findAssetList();
        	datasetBeanMapper.beansToDataset(resData, AssetList, AssetBean.class);
        	return null;
    }
    
	@RequestMapping("/assetitemlist")
    public ArrayList<AssetItemBean> assetItemList(@RequestAttribute("reqData") PlatformData reqData,
            @RequestAttribute("resData") PlatformData resData) throws Exception{
        String assetCode = reqData.getVariable("assetCode").getString();
    	
        	ArrayList<AssetItemBean> AssetItemList = ledgerService.findAssetItemList(assetCode);
        	datasetBeanMapper.beansToDataset(resData, AssetItemList, AssetItemBean.class);
        	return null;
    }
    
	@GetMapping("/deptlist")
    public ArrayList<DeptBean> deptList() {
    	
        	ArrayList<DeptBean> DeptList = ledgerService.findDeptList();

        	return DeptList;
    }
    
    @PostMapping("/assetstorage")
    public void assetStorage(@RequestParam(value="previousAssetItemCode", required=false) String previousAssetItemCode,
    								 @RequestParam(value="assetItemCode", required=false) String assetItemCode,
    								 @RequestParam(value="assetItemName", required=false) String assetItemName,
    								 @RequestParam(value="parentsCode", required=false) String parentsCode,
    								 @RequestParam(value="parentsName", required=false) String parentsName,
    								 @RequestParam(value="acquisitionDate", required=false) String acquisitionDate,
    								 @RequestParam(value="acquisitionAmount", required=false) String acquisitionAmount,
    								 @RequestParam(value="manageMentDept", required=false) String manageMentDept,
    								 @RequestParam(value="usefulLift", required=false) String usefulLift) {
    		
    	System.out.println(assetItemCode+"@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        	HashMap<String, Object> map = new HashMap<>();
        	map.put("assetItemCode", assetItemCode);
        	map.put("assetItemName", assetItemName);
        	map.put("parentsCode", parentsCode);
        	map.put("parentsName", parentsName);
        	map.put("acquisitionDate", acquisitionDate);
        	map.put("acquisitionAmount", Integer.parseInt((acquisitionAmount).replaceAll(",","")));
        	map.put("manageMentDept", manageMentDept);
        	map.put("usefulLift", usefulLift);
        	map.put("previousAssetItemCode", previousAssetItemCode);

        	
        	ledgerService.assetStorage(map);

    }
   @RequestMapping("/assetitemlistRegist")
    public void assetitemlistRegist(@RequestAttribute("reqData") PlatformData reqData,
            @RequestAttribute("resData") PlatformData resData) throws Exception{
	   AssetItemBean obj = datasetBeanMapper.datasetToBean(reqData,AssetItemBean.class);
	   ledgerService. Insertasset(obj);
    }
    @GetMapping("/assetremoval")
    public void assetRemove(@RequestParam String assetItemCode) {
    	
        	ledgerService.removeAssetItem(assetItemCode);

    }
}
