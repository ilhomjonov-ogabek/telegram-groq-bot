package org.example.firstrealbot.service;


import org.jvnet.hk2.annotations.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public interface BotResponseService {

  BotApiMethod<?> passStartRequestContact(Update update);



}
