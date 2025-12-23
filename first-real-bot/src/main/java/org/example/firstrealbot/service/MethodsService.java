package org.example.firstrealbot.service;

import org.example.firstrealbot.entity.ReturnChatAndMessageId;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface MethodsService {

  ReturnChatAndMessageId getChatIdAndMessageId(Update update);

}
