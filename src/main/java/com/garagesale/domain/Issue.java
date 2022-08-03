package com.garagesale.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Issue {
    @Id
    @GeneratedValue
    private Long issue_id;
    private String description;

    public Long getIssue_id() {
        return issue_id;
    }

    public void setIssue_id(Long issue_id) {
        this.issue_id = issue_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
