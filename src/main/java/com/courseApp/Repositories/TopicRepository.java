package com.courseApp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.courseApp.Model.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {

}
