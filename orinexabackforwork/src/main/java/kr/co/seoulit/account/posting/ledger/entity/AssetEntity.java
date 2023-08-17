package kr.co.seoulit.account.posting.ledger.entity;

import kr.co.seoulit.account.sys.common.annotation.ColumnName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@Table(name="ASSET")
@NoArgsConstructor
@AllArgsConstructor
public class AssetEntity {

	@ColumnName(name = "ASSET_NAME")
	private String assetName;

	@Id
	@ColumnName(name = "ASSET_CODE")
	private String assetCode;

	//onetomany
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private Collection<AssetItemEntity> assetItem;
}