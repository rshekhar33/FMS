package com.url.app.dto.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.url.app.utility.AppSQL;

/**
 * The persistent class for the feedback_answer database table.
 */
@Entity
@Table(name = "feedback_answer")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@NamedQuery(name = "FeedbackAnswer.findAll", query = AppSQL.QRY_FIND_ALL_FEEDBACK_ANSWER)
public class FeedbackAnswer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "feedback_answer_id", unique = true, nullable = false)
	private Integer feedbackAnswerId;

	@Column(nullable = false, length = 500)
	private String answer;

	@Column(name = "created_by", updatable = false, nullable = false)
	private Integer createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", updatable = false, nullable = false)
	@CreatedDate
	private Date createdDate;

	@Column(name = "is_active", nullable = false)
	private Integer isActive;

	@Column(name = "modified_by", nullable = false)
	private Integer modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_date", nullable = false)
	@LastModifiedDate
	private Date modifiedDate;

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

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public Integer getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
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