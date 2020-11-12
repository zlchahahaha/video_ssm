package com.video.service;

import com.video.pojo.Subject;

import java.util.List;

public interface SubjectService {
    public List<Subject> findAll();

    Subject findBySubjectId(Integer subjectId);

    Subject findBySubjectName(String subjectName);
}
