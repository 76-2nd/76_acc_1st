package kr.co.seoulit.account.posting.business.mapstruct;

import kr.co.seoulit.account.posting.business.DTO.SlipresDto;
import kr.co.seoulit.account.posting.business.Entity.SlipEntity;
import kr.co.seoulit.account.sys.common.Mapstruct.EntityResMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SlipResMapstruct extends EntityResMapper <SlipEntity, SlipresDto> {
}
