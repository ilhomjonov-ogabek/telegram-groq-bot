package org.example.firstrealbot.methods;

import lombok.RequiredArgsConstructor;
import org.example.firstrealbot.entity.step.RequestEnum;
import org.example.firstrealbot.service.BotRequestService;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@RequiredArgsConstructor
public class MethodsImpl {


  public RequestEnum requestCheckReturnEnum(Update update){

    System.out.println(update.getMessage().getText().toString());
    if(update.getMessage().getText().equals("/start")){
      return RequestEnum.START;
    }else {
      return RequestEnum.START;
    }
  }

}
