package kr.co.seoulit.account.posting.ledger.entity;

import kr.co.seoulit.account.sys.base.to.BaseBean;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "JOURNAL")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class JournalResEntity extends BaseBean {
	//분개
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "JOURNAL_NO")
	private String journalNo;

	private String slipNo;

	@Column(columnDefinition = "nchar")
	private String balanceDivision;

	@Column(name = "ACCOUNT_INNER_CODE")
	private String accountInnerCode;

	@Transient
	private String accountName;
	private String customerCode;
	private String customerName;

	@Column(columnDefinition = "number")
	private String leftDebtorPrice;

	@Column(columnDefinition = "number")
	private String rightCreditsPrice;

	@OneToMany
	@JoinColumn(name = "journalDetailNo")
	private List<JournalDetailResEntity> journalDetailList;

}