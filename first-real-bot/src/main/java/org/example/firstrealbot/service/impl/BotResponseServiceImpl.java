package org.example.firstrealbot.service.impl;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.firstrealbot.entity.ReturnChatAndMessageId;
import org.example.firstrealbot.service.BotResponseService;
import org.example.firstrealbot.service.MethodsService;
import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;


@Service
@RequiredArgsConstructor
@Component
public class BotResponseServiceImpl implements BotResponseService {

/*
  private final MethodsService methodsService;
*/

  /*@Override
  public BotApiMethod<?> passStartRequestContact(Update update) {
    List<KeyboardRow> keyboard = new ArrayList<>();
*//*
    ReturnChatAndMessageId chatIdAndMessageId = methodsService.getChatIdAndMessageId(update);
*//*
    KeyboardRow row1 = new KeyboardRow();
    KeyboardButton phoneButton = new KeyboardButton("📞 Telefon raqamni yuborish");
    phoneButton.setRequestContact(true);
    row1.add(phoneButton);
    keyboard.add(row1);
    ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
    keyboardMarkup.setKeyboard(keyboard);
    keyboardMarkup.setResizeKeyboard(true);
    keyboardMarkup.setOneTimeKeyboard(true);
    SendMessage message = new SendMessage();
    message.setChatId(update.getMessage().getChatId()); //chatIdAndMessageId.getChatIdString()
    message.setText("Telefon raqamingizni yuboring 👇");
    message.setReplyMarkup(keyboardMarkup);
    return message;  }*/
  @Override
  public BotApiMethod<?> passStartRequestContact(Update update) {
    System.out.println(update.getMessage().getText().toString());
    // 📞 Kontakt so‘raydigan tugma
    KeyboardButton contactButton =
        new KeyboardButton("📞 Telefon raqamni yuborish");
    contactButton.setRequestContact(true);

    KeyboardRow row = new KeyboardRow();
    row.add(contactButton);

    ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
    keyboard.setKeyboard(List.of(row));
    keyboard.setResizeKeyboard(true);
    keyboard.setOneTimeKeyboard(true);

    // ✉️ Xabar
    SendMessage message = new SendMessage();
    message.setChatId(update.getMessage().getChatId().toString());
    message.setText("Iltimos, telefon raqamingizni yuboring 👇");
    message.setReplyMarkup(keyboard);


    return message; // 🔥 WEBHOOKDA SHU YUBORADI
  }


}





