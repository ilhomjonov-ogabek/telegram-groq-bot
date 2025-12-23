package org.example.firstrealbot.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.firstrealbot.entity.ReturnChatAndMessageId;
import org.example.firstrealbot.service.MethodsService;
import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;


@Service
public class MethodsServiceImpl implements MethodsService {

  @Override
  public ReturnChatAndMessageId getChatIdAndMessageId(Update update) {
    ReturnChatAndMessageId returnChatAndMessageId = new ReturnChatAndMessageId(
        update.getMessage().getChatId().toString(),
        update.getMessage().getMessageId().toString(),
        update.getMessage().getFrom().getId().toString(),
        update.getMessage().getMessageId(),
        update.getMessage().getFrom().getId(),
        update.getMessage().getChatId()

    );
    return returnChatAndMessageId;

  }
}
