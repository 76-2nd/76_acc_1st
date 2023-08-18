package kr.co.seoulit.account.operate.system.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import kr.co.seoulit.account.operate.system.to.BoardBean;

import javax.transaction.Transactional;

@Mapper
public interface BoardMapper {


	ArrayList<BoardBean> constBoardList();


	//test 완료
	public ArrayList<BoardBean> selectBoardList();



	//Test 성공
	public BoardBean selectBoardDetail(String id);


	//test 성공
	public void insertBoard(BoardBean bean) ;



	//test 성공
	public void updateBoard(BoardBean bean) ;



	//Test 성공
	public void deleteBoard(String id);





//		//test 성공 ---> 안쓸거 같음
//		public List<BoardBean> selectBoard(int bno);


//	//사용안함
//	public ArrayList<BoardBean> selectBoardList();
//
//	//사용안함
//	public ArrayList<BoardBean> selectBoarddetail(String row);


}
