package com.url.app.dto.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.url.app.utility.AppSQL;

/**
 * The persistent class for the feedback_question database table.
 */
@Entity
@Table(name = "feedback_question")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@NamedQuery(name = "FeedbackQuestion.findAll", query = AppSQL.QRY_FIND_ALL_FEEDBACK_QUESTION)
public class FeedbackQuestion extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "feedback_question_id", unique = true, nullable = false)
	private Integer feedbackQuestionId;

	@Column(name = "is_active", nullable = false)
	private Integer isActive;

	@Column(nullable = false, length = 500)
	private String question;

	//bi-directional many-to-one association to FeedbackAnswer
	@OneToMany(mappedBy = "feedbackQuestion", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonBackReference(value = "feedbackQuestion_feedbackAnswer")
	private Set<FeedbackAnswer> feedbackAnswers = new HashSet<>(0);

	//bi-directional many-to-one association to Course
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "course_id", nullable = false)
	private Course course;

	public FeedbackQuestion() {
		super();
	}

	public Integer getFeedbackQuestionId() {
		return this.feedbackQuestionId;
	}

	public void setFeedbackQuestionId(Integer feedbackQuestionId) {
		this.feedbackQuestionId = feedbackQuestionId;
	}

	public Integer getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Set<FeedbackAnswer> getFeedbackAnswers() {
		return this.feedbackAnswers;
	}

	public void setFeedbackAnswers(Set<FeedbackAnswer> feedbackAnswers) {
		this.feedbackAnswers = feedbackAnswers;
	}

	public boolean addFeedbackAnswer(FeedbackAnswer feedbackAnswer) {
		feedbackAnswer.setFeedbackQuestion(this);

		return getFeedbackAnswers().add(feedbackAnswer);
	}

	public boolean removeFeedbackAnswer(FeedbackAnswer feedbackAnswer) {
		return getFeedbackAnswers().remove(feedbackAnswer);
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Override
	public int hashCode() {
		return Objects.hash(feedbackQuestionId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof FeedbackQuestion)) {
			return false;
		}
		FeedbackQuestion other = (FeedbackQuestion) obj;

		return Objects.equals(this.getFeedbackQuestionId(), other.getFeedbackQuestionId());
	}
}