package com.aptech.common.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "reviews_votes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReviewVote extends IdBasedEntity {
	private static final int VOTE_UP_POINT = 1;
	private static final int VOTE_DOWN_POINT = -1;

	private int votes;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "review_id")
	private Review review;

}
