package com.url.app.dto.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.url.app.utility.AppSQL;

/**
 * The persistent class for the feedback_answer database table.
 */
@Entity
@Table(name = "feedback_answer")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@NamedQuery(name = "FeedbackAnswer.findAll", query = AppSQL.QRY_FIND_ALL_FEEDBACK_ANSWER)
public class FeedbackAnswer extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "feedback_answer_id", unique = true, nullable = false)
	private Integer feedbackAnswerId;

	@Column(nullable = false, length = 500)
	private String answer;

	@Column(name = "is_active", nullable = false)
	private Integer isActive;

	//bi-directional many-to-one association to FeedbackQuestion
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "feedback_question_id", nullable = false)
	private FeedbackQuestion feedbackQuestion;

	public FeedbackAnswer() {
		super();
	}

	public Integer getFeedbackAnswerId() {
		return this.feedbackAnswerId;
	}

	public void setFeedbackAnswerId(Integer feedbackAnswerId) {
		this.feedbackAnswerId = feedbackAnswerId;
	}

	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Integer getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public FeedbackQuestion getFeedbackQuestion() {
		return this.feedbackQuestion;
	}

	public void setFeedbackQuestion(FeedbackQuestion feedbackQuestion) {
		this.feedbackQuestion = feedbackQuestion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(feedbackAnswerId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof FeedbackAnswer)) {
			return false;
		}
		FeedbackAnswer other = (FeedbackAnswer) obj;

		return Objects.equals(this.getFeedbackAnswerId(), other.getFeedbackAnswerId());
	}
}