package org.example.demo.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@MappedSuperclass
public class ReturnChatAndMessageId {

    private String chatIdString;

    private String messageIdString;

    private String userIdString;

    private Long messageIdLong;

    private Long userIdLong;

    private Long chatIdLong;

}
