package kr.co.seoulit.account.posting.ledger.entity;

import kr.co.seoulit.account.sys.common.annotation.ColumnName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name="ASSET_ITEM")
@NoArgsConstructor
@AllArgsConstructor
public class AssetItemEntity {

    @Id
    @ColumnName(name = "ASSET_ITEM_CODE")
    private String assetItemCode;

    @ColumnName(name = "ASSET_ITEM_NAME")
    private String assetItemName;

    @ColumnName(name = "ACQUISITION_DATE")
    private String acquisitionDate;

    @ColumnName(name = "ACQUISITION_AMOUNT")
    private int acquisitionAmount;

    @ColumnName(name = "USEFUL_LIFE")
    private int usefulLife;

    @ColumnName(name = "MANAGEMENT_DEPT_NAME")
    private String managementDeptName;


    @ColumnName(name = "PARENTS_CODE")
    private String parentsCode;

    @ColumnName(name = "PARENTS_NAME")
    private String parentsName;

    @Transient
    //@ColumnName(name = "ACCUMULATED_DEPRECIATION")
    private String accumulatedDepreciation;

    @Transient
    //@ColumnName(name = "RESIDUAL_VALUE")
    private String residualValue;

    @Transient
    //@ColumnName(name = "RESIDUAL_LIFE")
    private String residualLife;

}