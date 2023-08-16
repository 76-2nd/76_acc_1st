package kr.co.seoulit.account.posting.ledger.repository;

import kr.co.seoulit.account.posting.ledger.entity.AssetItemEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface AssetItemRepository extends CrudRepository<AssetItemEntity, String> {

    Optional<AssetItemEntity> findById(String assetItemCode);
}