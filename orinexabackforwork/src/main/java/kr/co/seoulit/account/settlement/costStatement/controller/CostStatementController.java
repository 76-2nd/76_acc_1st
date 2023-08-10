package kr.co.seoulit.account.settlement.costStatement.controller;

import com.nexacro.java.xapi.data.PlatformData;
import kr.co.seoulit.account.settlement.costStatement.service.CostStatementService;
import kr.co.seoulit.account.settlement.costStatement.to.CostStatementBean;
import kr.co.seoulit.account.sys.common.mapper.DatasetBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/settlement")
public class CostStatementController {

    @Autowired
    private CostStatementService costStatementService;
    @Autowired
    private DatasetBeanMapper datasetBeanMapper;

    //원가명세서 조회
    @RequestMapping(value = "/selectedDate")
    public ArrayList<CostStatementBean> findCostStatement(@RequestAttribute("reqData") PlatformData reqData,
                                                          @RequestAttribute("resData")PlatformData resData) throws Exception {
        System.out.println("원가명세서_Controller");
        String selectedDate=reqData.getVariable("selectedDate").getString(); // 받은 데이터 추출
        System.out.println("selectedDate : " + selectedDate);

        HashMap<String,Object> params = new HashMap<>();
        params.put("selectedDate",selectedDate); // 데이터를 map에 담음

        ArrayList<CostStatementBean> costStatementList = costStatementService.findCostStatement(params); // Facade 호출
        datasetBeanMapper.beansToDataset(resData, costStatementList, CostStatementBean.class); // DTO -> Dataset
        return null;
    }
}
