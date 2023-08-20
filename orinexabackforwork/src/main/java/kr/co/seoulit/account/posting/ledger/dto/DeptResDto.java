package kr.co.seoulit.account.posting.ledger.dto;

import kr.co.seoulit.account.sys.common.annotation.Dataset;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@Dataset(name = "ds_department")
@AllArgsConstructor
@NoArgsConstructor
//@RedisHash("deptlist")
public class DeptResDto implements Serializable {

	@Id
	private String deptName;

	private String deptCode;


}
