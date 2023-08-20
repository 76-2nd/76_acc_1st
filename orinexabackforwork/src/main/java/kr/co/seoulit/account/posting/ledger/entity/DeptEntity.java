package kr.co.seoulit.account.posting.ledger.entity;

import kr.co.seoulit.account.sys.common.annotation.ColumnName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@Table(name="DEPARTMENT")
@NoArgsConstructor
@AllArgsConstructor
public class DeptEntity implements Serializable {

	@ColumnName(name = "DEPT_NAME")
	private String deptName;

	@Id
	@ColumnName(name = "DEPT_CODE")
	private String deptCode;


	private String workplaceCode;
	private String workplaceName;
	private String companyCode;
	private String deptStartDate;
	private String deptEndDate;

}