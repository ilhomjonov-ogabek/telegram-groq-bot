package org.example.demo.service;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface BotRequestService {

    boolean equalsStart(Update update);

    boolean equalsTrayStart(Update update);

    boolean equalsMenuPage(Update update);

    boolean equalsCategory(Update update);
    boolean equalsProduct(Update update);

    boolean equalsBack(Update update);

    boolean equalsNext(Update update);


    boolean sendPhoneNumber(Update update);

  boolean equalsCategories(Update update);

  boolean equalsBasket(Update update);

  boolean equalsNatification(Update update);

  boolean equalsHistory(Update update);

  boolean equalsCProduct(Update update);

  boolean equalsAddBasket(Update update);

  boolean equalsBProduct(Update update);

  boolean equalsHProduct(Update update);

  boolean equalsBuy(Update update);
}
