package com.example.demo.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "keyword")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeyWord {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userid_seq")
    @SequenceGenerator(name = "userid_seq", sequenceName = "userid_seq", allocationSize = 1)
	private Integer id;
	@Column(nullable = false)
	private String userId;
	@Column(nullable = false, length = 1000)
	private String keyword;
	@Column(length = 1000)
	private String content;
	@Column(name="createddate")
	private Date createddate;

	@PrePersist
    public void onPrePersist() {
        setCreateddate(new Date());
    }
}
