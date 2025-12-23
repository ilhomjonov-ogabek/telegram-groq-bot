package org.example.demo.service;


import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface BotResponseService {

  BotApiMethod<?> passStartButtonRequestContact(Update update);

  BotApiMethod<?> defaultMessage(Update update);

  BotApiMethod<?> passTrayStartButtonResponseStatePage(Update update);

  BotApiMethod<?> sendPhoneNumber(Update update);

  BotApiMethod<?> passCategoryzButtonResponceStatePage(Update update);

  BotApiMethod<?> pressCategory(Update update);


  BotApiMethod<?> pressBasket(Update update);

  BotApiMethod<?> pressNatification(Update update);

  BotApiMethod<?> pressHistory(Update update);

  BotApiMethod<?> pressSettings(Update update);

  BotApiMethod<?> pressBack(Update update);

  BotApiMethod<?> pressNext(Update update);

  BotApiMethod<?> pressCProduct(Update update);

  BotApiMethod<?> pressAddBasket(Update update);

  BotApiMethod<?> pressBProduct(Update update);
}
