package kr.co.seoulit.account.operate.system.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import kr.co.seoulit.account.operate.system.to.AccountDetailEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.nexacro.java.xapi.data.PlatformData;

import kr.co.seoulit.account.operate.system.service.SystemService;

import kr.co.seoulit.account.operate.system.to.AccountEntity;
import kr.co.seoulit.account.operate.system.to.AccountControlEntity;
import kr.co.seoulit.account.operate.system.to.PeriodEntity;
import kr.co.seoulit.account.sys.common.mapper.DatasetBeanMapper;

@RestController
@RequestMapping("/operate")
public class AccountSubjectController {

    private SystemService systemService;
    private DatasetBeanMapper datasetBeanMapper;

    @Autowired
    public AccountSubjectController(SystemService systemService, DatasetBeanMapper datasetBeanMapper) {
    	this.systemService=systemService;
    	this.datasetBeanMapper=datasetBeanMapper;

    }

    ModelAndView mav = null;
    ModelMap map = new ModelMap();
    HashMap<String,Object> updateMap=null;






    @RequestMapping("/registerAccountDetail")
    public void registerAccountDetail(@RequestAttribute("reqData")PlatformData reqData,
                                      @RequestAttribute("resData")PlatformData  resData )throws Exception{
        System.out.println("<<<<<request has been arrived at registerAccountDetail");
        AccountDetailEntity bean = datasetBeanMapper.datasetToBean(reqData,AccountDetailEntity.class);
        System.out.println("<<<<<<<<<< AccountDetailData has been arrived at controller");
        systemService.registerAccountDetail(bean);
    }

    @RequestMapping("/forFindDuplication")
  public void forFindDuplication(@RequestAttribute("reqData")PlatformData reqData,
                          @RequestAttribute("resData")PlatformData resData)throws Exception {
        List<AccountDetailEntity> list=systemService.findDuplication();
        datasetBeanMapper.beansToDataset(resData,list, AccountDetailEntity.class);
    }

    //계정과목 가져오기
    @RequestMapping(value = "/findParentAccountList")
    public ArrayList<AccountEntity> findParentAccountList(@RequestAttribute("reqData")PlatformData reqData,
                                                          @RequestAttribute("resData")PlatformData resData) throws Exception {
        datasetBeanMapper.beansToDataset(resData, systemService.findParentAccountList(), AccountEntity.class);

        return null;
    }

    //상세 계정코드 목록 가져오기
    @RequestMapping(value = "/detailaccountlist")
    public ArrayList<AccountDetailEntity> findDetailAccountList(@RequestAttribute("reqData")PlatformData reqData,
                                                                @RequestAttribute("resData")PlatformData resData) throws Exception {
        String parentAccountInnerCode = reqData.getVariable("parentAccountInnerCode").getString();
        System.out.println(parentAccountInnerCode);

        ArrayList<AccountDetailEntity> accountList = systemService.findDetailAccountList(parentAccountInnerCode);
        datasetBeanMapper.beansToDataset(resData, accountList, AccountDetailEntity.class);

        return null;
    }

    @RequestMapping("/findAccountDetail")
    public void findAccountDetail(@RequestAttribute("reqData")PlatformData reqData,
                                  @RequestAttribute("resData")PlatformData resData)throws Exception{

        String accountInnerCode=reqData.getVariable("accountInnerCode").getString();
        AccountDetailEntity accountDetail=systemService.findAccountDetail(accountInnerCode);
        datasetBeanMapper.beanToDataset(resData,accountDetail, AccountDetailEntity.class);

    }

    @RequestMapping("/modifyAccountDetail")
    public void modifyAccountDetail(@RequestAttribute("reqData")PlatformData reqData,
                                    @RequestAttribute("resData")PlatformData resData){
        String accountInnerCode=reqData.getVariable("accountInnerCode").getString();
        String accountName=reqData.getVariable("accountName").getString();
        System.out.println("<<<<<<<<accountName = " + accountName+"    "+accountInnerCode);
        updateMap=new HashMap<>();
        updateMap.put("accountInnerCode",accountInnerCode);
        updateMap.put("accountName",accountName);
        systemService.modifyAccountDetail(updateMap);

    }

    @RequestMapping("/removeAccountDetail")
    public void removeDetailAccount(@RequestAttribute("reqData")PlatformData reqData,
                                    @RequestAttribute("resData")PlatformData resData){
            String accountInnerCode=reqData.getVariable("accountInnerCode").getString();
        System.out.println("<<<<accountInnerCode = " + accountInnerCode);
            systemService.removeAccountDetail(accountInnerCode);

    }





              //아래는 아직 사용하지 않는 코드들

    @GetMapping("/account")
    public AccountEntity findAccount(@RequestParam String accountCode) {

        AccountEntity accountEntity = systemService.findAccount(accountCode);

        return accountEntity;
    }




    @RequestMapping(value = "/findAccountControlList")
    public ArrayList<AccountControlEntity> findAccountControlList(@RequestAttribute("reqData")PlatformData reqData,
            @RequestAttribute("resData")PlatformData resData) throws Exception {
        String accountCode = reqData.getVariable("accountCode").getString();

            ArrayList<AccountControlEntity> accountControlList = systemService.findAccountControlList(accountCode);
            datasetBeanMapper.beansToDataset(resData, accountControlList, AccountControlEntity.class);


        return null;
    }

    /* nexacro */
    @RequestMapping(value = "/findAccountList")
    public ArrayList<AccountEntity> findAccountList(@RequestAttribute("reqData")PlatformData reqData,
                                                    @RequestAttribute("resData")PlatformData resData) throws Exception {
        ArrayList<AccountEntity> accountList = systemService.findAccountList();
        datasetBeanMapper.beansToDataset(resData, accountList, AccountEntity.class);
        return null;
    }

    /* nexacro */
    @RequestMapping(value = "/findAccountDetailList")
    public ArrayList<AccountControlEntity> findAccountDetailList(@RequestAttribute("reqData")PlatformData reqData,
                                                                @RequestAttribute("resData")PlatformData resData) throws Exception {
        System.out.println("전표 화면 출력 시 실행");
        ArrayList<AccountControlEntity> accountDetailList = systemService.findAccountDetailList();
        datasetBeanMapper.beansToDataset(resData, accountDetailList, AccountControlEntity.class);
        return null;
    }
   // @GetMapping("/accountlistbyname")
    @RequestMapping(value = "/findAccountListByName")
    public ArrayList<AccountEntity> findAccountListByName(@RequestAttribute("reqData")PlatformData reqData,
            @RequestAttribute("resData")PlatformData resData) throws Exception {
        String accountName = reqData.getVariable("accountName").getString();
        System.out.println("findAccountListByName의 accountName"+accountName);
        ArrayList<AccountEntity> accountListByName = systemService.findAccountListByName(accountName);

        datasetBeanMapper.beansToDataset(resData, accountListByName, AccountEntity.class);

        return null;
    }


//    @GetMapping("/accountmodification")
//    public void modifyAccount(@RequestParam String accountInnerCode,
//    						  @RequestParam String accountName) {
//
//            AccountEntity accountEntity = new AccountEntity();
//
//            accountEntity.setAccountInnerCode(accountInnerCode);
//            accountEntity.setAccountName(accountName);
//
//    }
    @GetMapping("/detailbudgetlist")
    public ArrayList<AccountEntity> findDetailBudgetList(@RequestParam String code) {

            ArrayList<AccountEntity> budgetList = systemService.findDetailBudgetList(code);

        return budgetList;
    }

    @GetMapping("/parentbudgetlist")
    public ArrayList<AccountEntity> findParentBudgetList() {

            ArrayList<AccountEntity> parentBudgetList = systemService.findParentBudgetList();

        return parentBudgetList;
    }
   // @RequestMapping("/accountperiodlist")
    public ArrayList<PeriodEntity> findAccountPeriodList() {

            ArrayList<PeriodEntity> accountPeriodList = systemService.findAccountPeriodList();

        return accountPeriodList;
    }
    @RequestMapping("/accountperiodlist")
    public ArrayList<PeriodEntity> findPeriodList(@RequestAttribute("reqData")PlatformData reqData,
            @RequestAttribute("resData")PlatformData resData) throws Exception {

            ArrayList<PeriodEntity> accountPeriodList = systemService.findPeriodList();
            datasetBeanMapper.beansToDataset(resData, accountPeriodList, PeriodEntity.class);

        return null;
    }
}
