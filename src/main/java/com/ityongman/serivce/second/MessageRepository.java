package com.ityongman.serivce.second;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ityongman.domain.second.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
