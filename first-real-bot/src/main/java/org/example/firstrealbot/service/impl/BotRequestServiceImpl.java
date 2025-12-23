package org.example.firstrealbot.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.firstrealbot.repo.UserRepository;
import org.example.firstrealbot.service.BotRequestService;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class BotRequestServiceImpl implements BotRequestService {

  private final UserRepository userRepository;

  @Override
  public boolean equalsStart(Update update) {
    if (update == null || !update.hasMessage() || !update.getMessage().hasText()) return false;

    return update.getMessage().getText().equals("/start") && userRepository.findByChatId(update.getMessage().getChatId()).isEmpty();
  }

  @Override
  public boolean equalsPhoneNumber(Update update) {
    return false;
  }
}
