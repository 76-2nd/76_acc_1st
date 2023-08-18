package kr.co.seoulit.account.operate.system.to;

import kr.co.seoulit.account.sys.base.to.BaseBean;
import kr.co.seoulit.account.sys.common.annotation.Dataset;
import lombok.Data;
import lombok.ToString;

import java.util.Date;


@Data
@Dataset(name="ds_board")
@ToString
public class BoardBean extends BaseBean {


	//DB의 테이블에서 필요한 정보만 추출해온다.
	private String id;
	private String title;
	private String contents;
	private String writtenBy;
	private Date writeDate;
	private Date updateDateTime;



	public BoardBean(){}
	public BoardBean(String id,
					 String title,
					 String contents,
					 String writtenBy,
					 Date writeDate,
					 Date updateDateTime
	) {
		this.id = id;
		this.title = title;
		this.contents = contents;
		this.writtenBy = writtenBy;
		this.writeDate = writeDate;
		this.updateDateTime = updateDateTime;
	}




}
