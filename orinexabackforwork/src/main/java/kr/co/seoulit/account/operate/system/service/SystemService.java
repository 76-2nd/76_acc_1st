package kr.co.seoulit.account.operate.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.seoulit.account.operate.system.to.*;

public interface SystemService {


				       	  //계정 관련


	void registerAccountDetail(AccountDetailEntity accountDetailEntity);

	List<AccountDetailEntity> findDuplication();


	 ArrayList<AccountEntity> findParentAccountList();

	//상세 계정코드 가져오기
	ArrayList<AccountDetailEntity> findDetailAccountList(String parentAccountInnerCode);
	AccountDetailEntity findAccountDetail(String accountInnerCode);
	void modifyAccountDetail(HashMap<String,Object> map);
	 void removeAccountDetail(String accountInnerCode);



											//아래는 아직 사용하지 않는 코드들

    public AccountEntity findAccount(String code);



    public void modifyAccount(AccountEntity accountEntity);

    public ArrayList<AccountEntity>findAccountList();

    ArrayList<AccountEntity> findAccountListByName(String accountName);

    ArrayList<AccountControlEntity> findAccountControlList(String accountCode);

    public ArrayList<AccountControlEntity> findAccountDetailList();

    public ArrayList<AccountEntity> findDetailBudgetList(String code);

    public ArrayList<AccountEntity> findParentBudgetList();





    public ArrayList<PeriodEntity> findAccountPeriodList();

    //periodpopup
    public ArrayList<PeriodEntity>  findPeriodList();


										//권한 관련


    public ArrayList<AuthorityEmpBean> findAuthorityEmp(String deptCode);

    public ArrayList<AuthorityMenuEntity> findEmpAuthorityList(String empCode);

	public void modifyAuthorityGroup(ArrayList<AuthorityEmpBean> authorityEmpBean, String dept);

	public ArrayList<AuthorityMenuEntity> findAuthorityGroup();

	public ArrayList<AuthorityMenuEntity> findAuthorityMenu(String menuName);

	public void addAuthorityGroup(String addAuthority,String nextGroupCode);

	public ArrayList<AuthorityEmpBean> findAuthorityGroupCode();

	public void modifyAuthorityMenu(ArrayList<AuthorityMenuEntity> authorityMenuEntity, String dept);

	public void removeAuthorityGroup(String groupCode);

	public ArrayList<BusinessBean> findBusinessList(); //�뾽�깭醫낅ぉ �쟾遺�議고쉶

	public ArrayList<DetailBusinessBean> findDetailBusiness(String businessName); // �뾽�깭醫낅ぉ �냼遺꾨쪟 �쟾遺�議고쉶

	public WorkplaceEntity findWorkplace(String workplaceCode); // 1媛쒖궗�뾽�옣 議고쉶

	public void registerWorkplace(WorkplaceEntity workplaceEntity); //�궗�뾽�옣異붽�

	public void removeWorkplace(String workplaceCode); //�궗�뾽�옣�궘�젣 //arraylist濡� 諛붽�爰쇱엫

	public void modifyApprovalStatus(ArrayList<String> getCodes,String status); //�궗�뾽�옣 �듅�씤�긽�깭 �뾽�뜲�씠�듃

	public ArrayList<WorkplaceEntity> findAllWorkplaceList(); //紐⑤뱺�궗�뾽�옣議고쉶









										//Board CRUD

	// 과제로 Mybatis에서 resultMap으로 필드에 생성자 주입을 위한 테스트 메서드 입니다.
	ArrayList<BoardBean> constBoardList();

	//아래 5개의 CRUD메서드는 정상 작동합니다.

	public ArrayList<BoardBean> findBoardList();

	public BoardBean findBoardDetail(String boardId);

	public void registerBoard(BoardBean bean);

	public void modifyBoard(BoardBean bean);

	public void removeBoard(String boardId);


}
