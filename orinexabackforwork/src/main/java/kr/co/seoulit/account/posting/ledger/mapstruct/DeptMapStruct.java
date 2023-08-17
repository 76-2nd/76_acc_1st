package kr.co.seoulit.account.posting.ledger.mapstruct;

import kr.co.seoulit.account.posting.ledger.entity.AssetItemEntity;
import kr.co.seoulit.account.posting.ledger.entity.DeptEntity;
import kr.co.seoulit.account.posting.ledger.dto.DeptResDto;
import kr.co.seoulit.account.sys.common.Mapstruct.EntityResMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.Optional;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface DeptMapStruct extends EntityResMapper<DeptEntity, DeptResDto> {


    DeptResDto toDto(Optional<AssetItemEntity> entity) ;
}



