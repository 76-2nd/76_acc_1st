package kr.co.seoulit.account.posting.business.mapper;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Mapper;
import kr.co.seoulit.account.posting.business.Entity.SlipEntity;

@Mapper
public interface SlipApprovalAndReturnMapper {

    //메뉴-전표승인 : 승인요청 -> "승인완료"로 update
    void updateapproveSlip(SlipEntity slipEntity);

    //메뉴-일반전표 : 작성중 -> "승인요청"으로 update
    void updateapproveSlip2(SlipEntity slipEntity);


    public ArrayList<SlipEntity> selectDisApprovalSlipList();
}
