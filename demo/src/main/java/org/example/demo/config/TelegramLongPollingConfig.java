package org.example.demo.config;



import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.demo.entity.enums.RequestEnum;
import org.example.demo.methods.AllMethodService;
import org.example.demo.service.BotResponseService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
@RequiredArgsConstructor
public class TelegramLongPollingConfig extends TelegramLongPollingBot {

  private final AllMethodService allMethodService;
  private final BotResponseService botResponseService;


  @Value("${telegram.bot.username}")
  private String botUsername;

  @Value("${telegram.bot.token}")
  private String botToken;

  @PostConstruct
  public void init() {
    try {
      TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
      botsApi.registerBot(this);
    } catch (TelegramApiException e) {
      e.printStackTrace();
    }
  }


  @SneakyThrows
  @Override
  public void onUpdateReceived(Update update) {
    RequestEnum requestObject = allMethodService.requestCheckReturnEnum(update);



        switch (requestObject) {

          case START -> execute(botResponseService.passStartButtonRequestContact(update));

          case TRAY_START -> execute(botResponseService.passTrayStartButtonResponseStatePage(update));

          case PHONE_NUMBER -> execute(botResponseService.sendPhoneNumber(update));

          case CATEGORIES -> execute(botResponseService.passCategoryzButtonResponceStatePage(update));

          case BASKET -> execute(botResponseService.pressBasket(update));

          case NATIFICATION ->execute(botResponseService.pressNatification(update));

          case HISTORY ->execute(botResponseService.pressHistory(update));

          case CATEGORY -> execute(botResponseService.pressCategory(update));

          case C_PRODUCT -> execute(botResponseService.pressCProduct(update));

          case ADD_BASKET -> execute(botResponseService.pressAddBasket(update));

          case B_PRODUCT -> execute(botResponseService.pressBProduct(update));

          case H_PRODUCT -> execute(botResponseService.pressHProduct(update));

          case BACK -> execute(botResponseService.pressBack(update));

          case BUY_PRODUCT -> execute(botResponseService.pressBuy(update));

          /*

          case NEXT -> execute();
*/

         /*


          case SETTINGS ->*/


          default -> botResponseService.defaultMessage(update);
        };



  }

  @Override
  public String getBotUsername() {
    return botUsername;
  }

  @Override
  public String getBotToken() {
    return botToken;
  }
}
