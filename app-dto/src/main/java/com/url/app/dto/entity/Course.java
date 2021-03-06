package com.url.app.dto.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.url.app.utility.AppSQL;

/**
 * The persistent class for the course database table.
 */
@Entity
@Table(name = "course")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@NamedQuery(name = "Course.findAll", query = AppSQL.QRY_FIND_ALL_COURSE)
public class Course extends ActiveBaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "course_id", unique = true, nullable = false)
	private Integer courseId;

	@Column(name = "course_code", unique = true, nullable = false, length = 50)
	private String courseCode;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@Column(name = "end_date")
	private Date endDate;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@Column(name = "start_date")
	private Date startDate;

	//bi-directional many-to-one association to CourseType
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "course_type_id", nullable = false)
	private CourseType courseType;

	//bi-directional many-to-one association to UserSkillset
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "module_id", referencedColumnName = "module_id", nullable = false)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
	private UserSkillset userSkillset;

	//bi-directional many-to-one association to FeedbackQuestion
	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonBackReference(value = "course_feedbackQuestion")
	private Set<FeedbackQuestion> feedbackQuestions = new HashSet<>(0);

	public Course() {
		super();
	}

	public Integer getCourseId() {
		return this.courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public String getCourseCode() {
		return this.courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public CourseType getCourseType() {
		return this.courseType;
	}

	public void setCourseType(CourseType courseType) {
		this.courseType = courseType;
	}

	public UserSkillset getUserSkillset() {
		return this.userSkillset;
	}

	public void setUserSkillset(UserSkillset userSkillset) {
		this.userSkillset = userSkillset;
	}

	public Set<FeedbackQuestion> getFeedbackQuestions() {
		return this.feedbackQuestions;
	}

	public void setFeedbackQuestions(Set<FeedbackQuestion> feedbackQuestions) {
		this.feedbackQuestions = feedbackQuestions;
	}

	public boolean addFeedbackQuestion(FeedbackQuestion feedbackQuestion) {
		feedbackQuestion.setCourse(this);

		return getFeedbackQuestions().add(feedbackQuestion);
	}

	public boolean removeFeedbackQuestion(FeedbackQuestion feedbackQuestion) {
		return getFeedbackQuestions().remove(feedbackQuestion);
	}

	@Override
	public int hashCode() {
		return Objects.hash(courseId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Course)) {
			return false;
		}
		Course other = (Course) obj;

		return Objects.equals(this.getCourseId(), other.getCourseId());
	}
}