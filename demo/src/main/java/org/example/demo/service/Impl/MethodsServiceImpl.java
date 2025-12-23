package org.example.demo.service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.demo.entity.ReturnChatAndMessageId;
import org.example.demo.service.MethodsService;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;


@Service
@RequiredArgsConstructor
public class MethodsServiceImpl implements MethodsService {
    @Override
    public ReturnChatAndMessageId getChatIdAndMessageId(Update update) {
        return null;
    }
}
