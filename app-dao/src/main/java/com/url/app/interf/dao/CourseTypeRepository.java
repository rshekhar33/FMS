package com.url.app.interf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.url.app.dto.entity.CourseType;

@Repository(value = "courseTypeRepository")
public interface CourseTypeRepository extends JpaRepository<CourseType, Integer> {

	/**
	 * Get all course types data.
	 * 
	 * @return list of course types.
	 */
	List<CourseType> findAllByOrderByCourseTypeId();
}