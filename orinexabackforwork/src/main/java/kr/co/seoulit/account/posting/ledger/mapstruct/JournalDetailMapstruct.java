package kr.co.seoulit.account.posting.ledger.mapstruct;

import kr.co.seoulit.account.posting.ledger.dto.JournalDetailDtoRes;
import kr.co.seoulit.account.posting.ledger.entity.JournalDetailResEntity;
import kr.co.seoulit.account.sys.common.Mapstruct.EntityResMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JournalDetailMapstruct extends EntityResMapper<JournalDetailResEntity, JournalDetailDtoRes> {
}
