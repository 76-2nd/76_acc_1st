package kr.co.seoulit.account.posting.business.mapstruct;

import kr.co.seoulit.account.posting.business.DTO.JournalDto;
import kr.co.seoulit.account.posting.business.Entity.JournalEntity;
import kr.co.seoulit.account.sys.common.Mapstruct.EntityReqMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JournalReqMapstruct extends EntityReqMapper <JournalEntity, JournalDto> {
}
