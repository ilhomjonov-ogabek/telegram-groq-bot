package org.example.demo.methods;

import lombok.RequiredArgsConstructor;
import org.example.demo.entity.enums.RequestEnum;
import org.example.demo.service.BotRequestService;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;


@Service
@RequiredArgsConstructor
public class AllMethodService {

  private final BotRequestService botRequestService;

  public RequestEnum requestCheckReturnEnum(Update update) {

    if (botRequestService.equalsStart(update)) {
      return RequestEnum.START;
    } else if (botRequestService.equalsTrayStart(update)) {
      return RequestEnum.TRAY_START;
    } else if (botRequestService.sendPhoneNumber(update)) {
      return RequestEnum.PHONE_NUMBER;
    } else if (botRequestService.equalsCategories(update)) {
      return RequestEnum.CATEGORIES;
    }else if (botRequestService.equalsBasket(update)) {
      return RequestEnum.BASKET;
    } else if (botRequestService.equalsNatification(update)) {
      return RequestEnum.NATIFICATION;
    } else if (botRequestService.equalsHistory(update)) {
      return RequestEnum.HISTORY;
    } else if (botRequestService.equalsCategory(update)) {
      return RequestEnum.CATEGORY;
    } else if (botRequestService.equalsBack(update)) {
      return RequestEnum.BACK;
    } else if (botRequestService.equalsNext(update)) {
      return RequestEnum.NEXT;
    }else if (botRequestService.equalsCProduct(update)) {
      return  RequestEnum.C_PRODUCT;
    }else if (botRequestService.equalsBProduct(update)) {
      return  RequestEnum.B_PRODUCT;
    } else if (botRequestService.equalsAddBasket(update)) {
      return  RequestEnum.ADD_BASKET;
    }else if(botRequestService.equalsHProduct(update)){
      return  RequestEnum.H_PRODUCT;
    } else if (botRequestService.equalsBuy(update)) {
      return  RequestEnum.BUY_PRODUCT;
    }
    return RequestEnum.TRAY_START;
  }
}
