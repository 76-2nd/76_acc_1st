package kr.co.seoulit.account.operate.system.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import kr.co.seoulit.account.operate.system.to.AccountDetailEntity;
import org.apache.ibatis.annotations.Mapper;

import kr.co.seoulit.account.operate.system.to.AccountEntity;
import kr.co.seoulit.account.operate.system.to.AccountControlEntity;
import kr.co.seoulit.account.operate.system.to.PeriodEntity;

@Mapper
public interface AccountSubjectMapper {



    //사용하는 계정코드들
    void insertAccountDetail(AccountDetailEntity accountDetailEntity);


    List<AccountDetailEntity> forFindDuplication();


    //상세 계정목록 가져오기
     ArrayList<AccountDetailEntity> selectDetailAccountList(String parentAccountInnerCode);

    ArrayList<AccountEntity> selectParentAccountList();

    AccountDetailEntity selectAccountDetail(String accountInnerCode);

    void updateAccountDetail(HashMap<String,Object> map);

    void deleteAccountDetail(String accountInnerCode);



    //아직 사용하지 않는 계정 관련 코드들

    public AccountEntity selectAccount(String accountCode);


    public void updateAccount(AccountEntity accountEntity);

    public ArrayList<AccountEntity> selectAccountListByName(String accountName);

    public ArrayList<AccountControlEntity> selectAccountControlList(String accountCode);

    public ArrayList<AccountEntity> selectDetailBudgetList(String code);

    public ArrayList<AccountEntity> selectParentBudgetList();
    public ArrayList<AccountEntity> selectAccountList();

    public ArrayList<PeriodEntity> selectAccountPeriodList();

    public ArrayList<PeriodEntity> selectPeriodList();

}
