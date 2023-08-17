package kr.co.seoulit.account.posting.ledger.entity;

import kr.co.seoulit.account.sys.base.to.BaseBean;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "JOURNAL_DETAIL")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class JournalDetailResEntity extends BaseBean {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "number")
    private String journalDetailNo;
    private String journalNo;
    private String accountControlCode;
    @Column(name = "DESCRIPTION")
    private String journalDescription;
    @Transient
    private String accountControlDescription;
    private String accountControlName;
    @Transient
    private String accountControlType; //일괄처리를 위한 상태값



}
