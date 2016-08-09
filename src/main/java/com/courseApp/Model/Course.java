package com.courseApp.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "course")
public class Course implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long courseId;
	private String courseName;
	private String prerequisite;
	private String author;
	private String description;
	@Enumerated(EnumType.STRING)
	private SkillLevel skillLevel;

	Course() {

	}

	public Course(Long courseId, String courseName, String prerequisite, String author, String description,
			SkillLevel skillLevel) {
		this.courseId = courseId;
		this.courseName = courseName;
		this.prerequisite = prerequisite;
		this.author = author;
		this.description = description;
		this.skillLevel = skillLevel;
	}

	@JsonIgnore
	@ManyToMany(fetch=FetchType.EAGER,cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinTable(name = "course_user", joinColumns = @JoinColumn(name = "courseId", referencedColumnName = "courseId"), inverseJoinColumns = @JoinColumn(name = "userId", referencedColumnName = "userId"))
	private List<User> registeredUsers = new ArrayList<>();

	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "course", cascade = CascadeType.ALL)
	private List<Topic> topics = new ArrayList<>();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((courseId == null) ? 0 : courseId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (courseId == null) {
			if (other.courseId != null)
				return false;
		} else if (!courseId.equals(other.courseId))
			return false;
		return true;
	}

	

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getPrerequisite() {
		return prerequisite;
	}

	public void setPrerequisite(String prerequisite) {
		this.prerequisite = prerequisite;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public SkillLevel getSkillLevel() {
		return skillLevel;
	}

	public void setSkillLevel(SkillLevel skillLevel) {
		this.skillLevel = skillLevel;
	}

	public List<User> getRegisteredUsers() {
		return registeredUsers;
	}

	public void setRegisteredUsers(List<User> registeredUsers) {
		this.registeredUsers = registeredUsers;
	}

	public List<Topic> getTopics() {
		return topics;
	}

	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseName=" + courseName + ", prerequisite=" + prerequisite
				+ ", author=" + author + ", description=" + description + ", skillLevel=" + skillLevel
				+ ", registeredUsers=" + registeredUsers + ", topics=" + topics + "]";
	}
}
