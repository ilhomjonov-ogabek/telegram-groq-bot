package org.example.demo.service;

import org.example.demo.entity.ReturnChatAndMessageId;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface MethodsService {
    ReturnChatAndMessageId getChatIdAndMessageId(Update update);
}
