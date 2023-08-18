package kr.co.seoulit.account.operate.system.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexacro.java.xapi.data.PlatformData;

import kr.co.seoulit.account.operate.system.service.SystemService;
import kr.co.seoulit.account.operate.system.to.BoardBean;
import kr.co.seoulit.account.operate.system.to.WorkplaceEntity;
import kr.co.seoulit.account.sys.common.mapper.DatasetBeanMapper;

@RestController
@RequestMapping("/operate")
public class BoardController {

	@Autowired
	private SystemService systemService;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;


	// 새로운 보드를 작성하기 위해 빈 객체를 전송
	@RequestMapping("/boardForm")
	public void boardForm(@RequestAttribute("reqData")PlatformData reqData,
						  @RequestAttribute("resData")PlatformData resData)throws Exception{

		BoardBean boardForm = new BoardBean();
		datasetBeanMapper.beanToDataset(resData, boardForm, BoardBean.class);

	}

	@RequestMapping("/registerBoard")
	public void findBoard(@RequestAttribute("reqData")PlatformData reqData,
						  @RequestAttribute("resData")PlatformData resData) throws Exception {
		BoardBean bean = datasetBeanMapper.datasetToBean(reqData, BoardBean.class);
		System.out.println("<<<<<<<<<this is bean for insert!!!>>>>>>>>>>>>"+bean);
		systemService.registerBoard(bean);
	}

	@RequestMapping("/modifyBoard")
	public void modifyBoard(@RequestAttribute("reqData")PlatformData reqData,
							@RequestAttribute("resData")PlatformData resData) throws Exception {
		System.out.println("This is resData : "+resData);
		System.out.println("<<<<<<<<<<<<modifyBoard has been called>>>>>>>>>>>>>>>>");
		BoardBean bean = datasetBeanMapper.datasetToBean(reqData, BoardBean.class);
		System.out.println("<<<<<<<<<<<<<this is Data for modify"+bean);
		systemService.modifyBoard(bean);
	}

	@RequestMapping("/findBoardList")
	public void findBoardList(@RequestAttribute("reqData")PlatformData reqData,
							  @RequestAttribute("resData")PlatformData resData) throws Exception {
		System.out.println("<<<<<<<<<<<request arrived at findboardList>>>>>>>>>>>>");
		ArrayList<BoardBean> boardList = systemService.findBoardList();
		datasetBeanMapper.beansToDataset(resData, boardList, BoardBean.class);
	}

	@RequestMapping("/findBoardDetail")
	public void findBoardDetail(@RequestAttribute("reqData")PlatformData reqData,
								@RequestAttribute("resData")PlatformData resData) throws Exception {
		String boardInfo = reqData.getVariable("bno").getString();
		System.out.println("this board info"+boardInfo);

		BoardBean boardDetail = systemService.findBoardDetail(boardInfo);
		datasetBeanMapper.beanToDataset(resData, boardDetail, BoardBean.class);

	}

	@RequestMapping("/removeBoard")
	public void removeBoard(@RequestAttribute("reqData")PlatformData reqData,
							@RequestAttribute("resData")PlatformData resData) throws Exception {
		String boardId = reqData.getVariable("bno").getString();
		System.out.println("<<<<<<<<<<<<<<<Board "+boardId+" has been arrived at removeBoard!!!");
		systemService.removeBoard(boardId);

	}


}
