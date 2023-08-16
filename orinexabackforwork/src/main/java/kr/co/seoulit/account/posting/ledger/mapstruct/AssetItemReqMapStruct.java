package kr.co.seoulit.account.posting.ledger.mapstruct;

import kr.co.seoulit.account.posting.ledger.entity.AssetItemEntity;
import kr.co.seoulit.account.posting.ledger.dto.AssetItemReqDto;
import kr.co.seoulit.account.sys.common.Mapstruct.EntityReqMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.Optional;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface AssetItemReqMapStruct extends EntityReqMapper<AssetItemEntity, AssetItemReqDto> {


    AssetItemReqDto toDto(Optional<AssetItemEntity> entity);

    @Override
    AssetItemEntity toEntity(AssetItemReqDto dto);
}



