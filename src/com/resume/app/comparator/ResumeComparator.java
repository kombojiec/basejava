package com.resume.app.comparator;

import com.resume.app.model.Resume;

import java.util.Comparator;

public class ResumeComparator implements Comparator<Resume> {
    @Override
    public int compare(Resume o1, Resume o2) {
        if(o1.getFullName().equals(o2.getFullName())) {
            return o1.getUuid().compareTo(o2.getFullName());
        }
        return o1.getFullName().compareTo(o2.getFullName());
    }
}
