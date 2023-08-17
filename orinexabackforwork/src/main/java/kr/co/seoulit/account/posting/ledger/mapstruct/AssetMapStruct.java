package kr.co.seoulit.account.posting.ledger.mapstruct;

import kr.co.seoulit.account.posting.ledger.entity.AssetEntity;
import kr.co.seoulit.account.posting.ledger.entity.AssetItemEntity;
import kr.co.seoulit.account.posting.ledger.dto.AssetResDto;
import kr.co.seoulit.account.sys.common.Mapstruct.EntityResMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.Optional;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface AssetMapStruct extends EntityResMapper<AssetEntity, AssetResDto> {

    AssetResDto toDto(Optional<AssetItemEntity> entity) ;
}



