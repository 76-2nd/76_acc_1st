package kr.co.seoulit.account.posting.business.mapstruct;

import kr.co.seoulit.account.posting.business.DTO.JournalDetailreqDto;
import kr.co.seoulit.account.posting.business.Entity.JournalDetailEntity;
import kr.co.seoulit.account.sys.common.Mapstruct.EntityReqMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JournalDetailReqMapstruct extends EntityReqMapper <JournalDetailEntity,JournalDetailreqDto> {
}
