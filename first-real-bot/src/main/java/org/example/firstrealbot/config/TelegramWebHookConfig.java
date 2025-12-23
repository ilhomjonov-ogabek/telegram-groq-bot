package org.example.firstrealbot.config;

import static org.example.firstrealbot.entity.step.RequestEnum.PHONE_NUMBER;
import static org.example.firstrealbot.entity.step.RequestEnum.START;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.firstrealbot.entity.step.RequestEnum;
import org.example.firstrealbot.methods.MethodsImpl;
import org.example.firstrealbot.service.impl.BotResponseServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@Configuration
@RequiredArgsConstructor
public class TelegramWebHookConfig extends TelegramLongPollingBot {

  private final MethodsImpl allMethods;
  private final BotResponseServiceImpl botRasponseService;


  @Value("${telegrambot.token}")
  private String botToken;

  @Value("${telegrambot.username}")
  private String botUserName;

  @Value("${telegrambot.webhook-path}")
  private String webhookPath;

  @Override
  public String getBotUsername() {
    return this.botUserName;
  }

  @Override
  public String getBotToken() {
    return this.botToken;
  }

  @Override
  public void onRegister() {
    super.onRegister();
  }

 /* @Override
  public String getBotPath() {
    return this.webhookPath;
  }

  @SneakyThrows
  @Override
  public BotApiMethod<?> onWebhookUpdateReceived(Update update) {

    RequestEnum requestObject = allMethods.requestCheckReturnEnum(update);

    return
        switch (requestObject){
          case START -> {
            BotApiMethod<?> execute = (BotApiMethod<?>) execute(
                botRasponseService.passStartRequestContact(update));
            yield  execute;

          }

          case PHONE_NUMBER -> null;
          default -> null;
        };

  }
*/

  @Override
  public void onUpdateReceived(Update update) {
    RequestEnum requestObject = allMethods.requestCheckReturnEnum(update);

    switch (requestObject) {
      case START:
        botRasponseService.passStartRequestContact(update);
        break;
      case PHONE_NUMBER:
        break;
      default:
        break;
    }

  }

  @Override
  public void onUpdatesReceived(List<Update> updates) {
    super.onUpdatesReceived(updates);
  }
}
