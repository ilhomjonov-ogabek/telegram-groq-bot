package org.example.firstrealbot.service;


import org.telegram.telegrambots.meta.api.objects.Update;

public interface BotRequestService {

  boolean equalsStart(Update update);

  boolean equalsPhoneNumber(Update update);

}
