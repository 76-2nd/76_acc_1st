package kr.co.seoulit.account.posting.ledger.entity;

import kr.co.seoulit.account.sys.common.annotation.ColumnName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name="DEPARTMENT")
@NoArgsConstructor
@AllArgsConstructor
public class DeptEntity {

	@ColumnName(name = "DEPT_NAME")
	private String deptName;

	@Id
	@ColumnName(name = "DEPT_CODE")
	private String deptCode;

}