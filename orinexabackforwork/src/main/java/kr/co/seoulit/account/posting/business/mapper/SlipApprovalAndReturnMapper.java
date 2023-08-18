package kr.co.seoulit.account.posting.business.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import kr.co.seoulit.account.posting.business.Entity.SlipEntity;

@Mapper
public interface SlipApprovalAndReturnMapper {
	
    void updateapproveSlip(SlipEntity slipEntity);
    void updateapproveSlip2(SlipEntity slipEntity);


    public ArrayList<SlipEntity> selectDisApprovalSlipList();
}
