package kr.co.seoulit.account.sys.common.interceptor;


import com.nexacro.java.xapi.data.DataSetList;
import com.nexacro.java.xapi.data.Debugger;
import com.nexacro.java.xapi.data.PlatformData;

import com.nexacro.java.xapi.data.VariableList;
import com.nexacro.java.xapi.tx.HttpPlatformRequest;
import com.nexacro.java.xapi.tx.HttpPlatformResponse;
import com.nexacro.java.xapi.tx.PlatformType;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@SuppressWarnings("deprecation")
@Component
@Log4j2
public class nexacroNInterceptor implements HandlerInterceptor {

    /* 
        view 단에서 넘어온 데이터를 받아 jsp 에서 사용 가능 한 객체로 전처리(xml --> Object)
        Controller 에서 받기 전에 Interceptor 에 먼저 들어옴
    */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object Handler)
            throws Exception {
        System.out.println("NexacroNInterceptor 실행");
        HttpPlatformRequest httpPlatformRequest = new HttpPlatformRequest(request); //tobesoft에서 재공한 dataset을 사용하기 위한 객체
        httpPlatformRequest.receiveData(); // data set을 받는  메서드 , xml 데이터를 parsing (객체 형태로 바꿈)
        PlatformData reqData = httpPlatformRequest.getData(); // 넥사에서 트렌잭션 요청 할 때, 클라이언트쪽에서 넘어온 데이터를 받기 위한 platformData
        PlatformData resData = new PlatformData(); // 넥사크로로 다시 데이터를 보내기 위한 객체 , 빈껍데기
//        System.out.println(log.isDebugEnabled()); // root 가 debug로 수정되어 있는지 확인용
        if(log.isDebugEnabled()){
            log.debug(" 로거 if문 수정 부분 💀💀 여기야 여기 해골~");
            //꺄르르르르르르르르르르르ㅡ르르르를르ㅡ르르르를르ㅡ르를르
            debug(reqData.getDataSetList(), reqData.getVariableList());
        }
//        debug(reqData.getDataSetList(), reqData.getVariableList()); // 로직이 구림 if 문으로 씌워라!!
        System.out.println("========================"+reqData.getDataSetList()+"=====================찍히는값");
        request.setAttribute("reqData", reqData);
//        request.setAttribute("variableList", reqData.getVariableList()); // 어차피 reqData로 넘김
        request.setAttribute("resData", resData);
       System.out.println("@@@@@@@@@@@@XplatformInterceptor preHandle메서드 종료");
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception exception) throws Exception {
        PlatformData resData = (PlatformData) request.getAttribute("resData");//tobesoft에서 재공한 xplatform을 사용하기 위한 객체
        if(resData == null){
            return;
        }
        VariableList variableList = resData.getVariableList(); //tobesoft에서 재공한 xplatform을 사용하기 위한 객체
        if (exception != null) {
            exception.printStackTrace();
            variableList.add("ErrorCode", -1);
            variableList.add("ErrorMsg", exception.getMessage());
        } else {
            variableList.add("ErrorCode", 0);
            variableList.add("ErrorMsg", "success");
        }
        //tobesoft에서 재공한 xplatform을 사용하기 위한 객체
        HttpPlatformResponse httpPlatformResponse = new HttpPlatformResponse(response, PlatformType.CONTENT_TYPE_XML, "UTF-8");
        httpPlatformResponse.setData(resData);
        httpPlatformResponse.sendData();

        debug(resData.getDataSetList(), resData.getVariableList());
        resData = null;
        System.out.println("XplatformInterceptor 종료");
    }

    private void debug(DataSetList dataSetList, VariableList variableList) {
        //tobesoft에서 재공한 xplatform debug를 위한 객체
        Debugger debugger = new Debugger();
        // DEBUG - DataSet
        for (int n = 0; n < dataSetList.size(); n++) {
            System.out.println("debug11@@@@@@"+debugger.detail(dataSetList.get(n)));
        }
        // DEBUG - VariableList
        for (int n = 0; n < variableList.size(); n++) {
            System.out.println("debug22@@@@@@"+debugger.detail(variableList.get(n)));
        }
    }
}