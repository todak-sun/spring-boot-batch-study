package io.todak.study.springbootbatchstudy.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "PAGE_WARN")
@Entity
public class PageWarn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PAGE_WARN_ID")
    private Long id;

    @Column(name = "AMOUNT")
    private Long amount;

    @Column(name = "SUCCESS_STATUS")
    private boolean successStatus;

    public PageWarn(Long amount, boolean successStatus) {
        this.amount = amount;
        this.successStatus = successStatus;
    }

    public void success() {
        this.successStatus = true;
    }

}
