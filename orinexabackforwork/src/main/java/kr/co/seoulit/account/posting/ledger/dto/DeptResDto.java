package kr.co.seoulit.account.posting.ledger.dto;

import kr.co.seoulit.account.sys.common.annotation.Dataset;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Dataset(name = "ds_deptName")
@AllArgsConstructor
@NoArgsConstructor
public class DeptResDto {


	private String deptName;
	
}
