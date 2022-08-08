package com.garagesale.repository;

import com.garagesale.domain.Asset;
import com.garagesale.domain.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Long> {
    List<Issue> findByAsset(Asset asset);
}
