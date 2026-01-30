package org.example.demo.service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.demo.repo.UserRepository;
import org.example.demo.service.BotRequestService;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;


@Service
@RequiredArgsConstructor
public class BotRequestServiceImpl implements BotRequestService {

  private final UserRepository userRepository;

  @Override
  public boolean equalsStart(Update update) {
    if (update == null || !update.hasMessage() || !update.getMessage().hasText()) {
      return false;
    }

    return update.getMessage().getText().equals("/start") && userRepository.findByChatId(
        update.getMessage().getChatId()).isEmpty();
  }

  @Override
  public boolean equalsTrayStart(Update update) {
    if (update == null || !update.hasMessage() || !update.getMessage().hasText()) {
      return false;
    }

    return update.getMessage().getText().equals("/start") && userRepository.findByChatId(
        update.getMessage().getChatId()).isPresent();
  }

  @Override
  public boolean equalsMenuPage(Update update) {
    return false;
  }

  @Override
  public boolean equalsCategory(Update update) {
    CallbackQuery callbackQuery = update.getCallbackQuery();
    String request = callbackQuery.getData();
      if (request.charAt(2) == 'T' && request.substring(0, 8).equals("CATEGORY")) {
        return true;
      }
    return false;
  }

  @Override
  public boolean equalsProduct(Update update) {
    return false;
  }

  @Override
  public boolean equalsBack(Update update) {

    CallbackQuery callbackQuery = update.getCallbackQuery();
    String request = callbackQuery.getData();

    if (request.equals("back")) {
      return true;
    }
    return false;
  }

  @Override
  public boolean equalsNext(Update update) {
    CallbackQuery callbackQuery = update.getCallbackQuery();
    String request = callbackQuery.getData();
    if (request.equals("next")) {
      return true;
    }
    return false;
  }


  @Override
  public boolean sendPhoneNumber(Update update) {
    if (update.hasMessage()) {
      Message message = update.getMessage();

      if (message.hasContact()) {
        String phoneNumber = message.getContact().getPhoneNumber();
        Long chatId = message.getChatId();

        System.out.println("Foydalanuvchi telefon raqami: " + phoneNumber);

        SendMessage response = new SendMessage();
        response.setChatId(chatId.toString());
        response.setText("Rahmat, telefon raqamingiz qabul qilindi: " + phoneNumber);
        return true;
      }

    }
    return false;

  }

  @Override
  public boolean equalsCategories(Update update) {

    if (update == null || !update.hasMessage() || !update.getMessage().hasText()
        || update.getMessage().getText() == null) {
      return false;
    }

    String text = update.getMessage().getText();

    return text.equalsIgnoreCase("Category 📂");

  }

  @Override
  public boolean equalsBasket(Update update) {

    if (update == null || !update.hasMessage() || !update.getMessage().hasText()
        || update.getMessage().getText() == null) {
      return false;
    }

    String text = update.getMessage().getText();

    return text.equalsIgnoreCase("Savat 🛒");
  }

  @Override
  public boolean equalsNatification(Update update) {
    if (update == null || !update.hasMessage() || !update.getMessage().hasText()
        || update.getMessage().getText() == null) {
      return false;
    }

    String text = update.getMessage().getText();

    return text.equalsIgnoreCase("Xabarnoma \uD83D\uDD14");
  }

  @Override
  public boolean equalsHistory(Update update) {
    if (update == null || !update.hasMessage() || !update.getMessage().hasText()
        || update.getMessage().getText() == null) {
      return false;
    }

    String text = update.getMessage().getText();

    return text.equalsIgnoreCase("Sotib olinganlar \uD83D\uDCE6");
  }

  @Override
  public boolean equalsCProduct(Update update) {
    CallbackQuery callbackQuery = update.getCallbackQuery();
    String request = callbackQuery.getData();
    if (request.charAt(1) == '_') {
      String substring = request.substring(0, 9);
      if (substring.equals("C_PRODUCT")) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean equalsAddBasket(Update update) {
    CallbackQuery callbackQuery = update.getCallbackQuery();
    String request = callbackQuery.getData();
    if (request.charAt(0) == 'b') {
      String substring = request.substring(0, 6);
      if (substring.equals("basket")) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean equalsBProduct(Update update) {
    CallbackQuery callbackQuery = update.getCallbackQuery();
    String request = callbackQuery.getData();
    System.out.println(request);
    if (request.charAt(0) == 'B') {
      String substring = request.substring(0, 9);
      if (substring.equals("B_PRODUCT")) {
        return true;
      }
    }

    return false;
  }

  @Override
  public boolean equalsHProduct(Update update) {
    CallbackQuery callbackQuery = update.getCallbackQuery();
    String request = callbackQuery.getData();
    System.out.println(request);
    if (request.charAt(0) == 'H') {
      String substring = request.substring(0, 9);
      if (substring.equals("H_PRODUCT")) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean equalsBuy(Update update) {
    CallbackQuery callbackQuery = update.getCallbackQuery();
    String request = callbackQuery.getData();
    if(request.charAt(0) == 'B' && request.substring(0, 11).equals("BUY_PRODUCT")){
      return true;
    }
    return false;
  }

  @Override
  public boolean equalsCancel(Update update) {
    CallbackQuery callbackQuery = update.getCallbackQuery();
    String request = callbackQuery.getData();
    if(request.charAt(0) == 'C' && request.substring(0,6).equals("CANCEL")){
      return true;
    }
    return false;
  }

}




