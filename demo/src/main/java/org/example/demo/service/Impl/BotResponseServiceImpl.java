package org.example.demo.service.Impl;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.example.demo.entity.Category;
import org.example.demo.entity.Notification;
import org.example.demo.entity.Products;
import org.example.demo.entity.ReturnChatAndMessageId;
import org.example.demo.entity.User;
import org.example.demo.entity.enums.RequestEnum;
import org.example.demo.entity.result.InlineResUser;
import org.example.demo.entity.step.UserSteps;
import org.example.demo.repo.CategoryRepository;
import org.example.demo.repo.NotificationRepository;
import org.example.demo.repo.ProductRepository;
import org.example.demo.repo.UserRepository;
import org.example.demo.service.BotResponseService;
import org.example.demo.service.MethodsService;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;


@Service
@RequiredArgsConstructor
public class BotResponseServiceImpl implements BotResponseService {

  private final MethodsService methodsService;
  private final UserRepository userRepository;
  private final CategoryRepository categoryRepository;
  private final ProductRepository productRepository;
  private final NotificationRepository notificationRepository;
  private InlineResUser inlineResUser;
  private RequestEnum requestEnum;

  @Override
  public BotApiMethod<?> passStartButtonRequestContact(Update update) {
    List<KeyboardRow> keyboard = new ArrayList<>();
    ReturnChatAndMessageId chatIdAndMessageId = methodsService.getChatIdAndMessageId(update);
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
    message.setChatId(update.getMessage().getChatId().toString());
    message.setText("Telefon raqamingizni yuboring 👇");
    message.setReplyMarkup(keyboardMarkup);
    userRepository.save(User
        .builder()
        .userId(update.getMessage().getFrom().getId())
        .chatId(update.getMessage().getChatId())
        .firstName(update.getMessage().getFrom().getFirstName())
        .role(1)
        .lastName(update.getMessage().getFrom().getLastName())
        .step(UserSteps.REGISTER.toString())
        .build());
    return message;
  }

  @Override
  public BotApiMethod<?> defaultMessage(Update update) {
    SendMessage message = new SendMessage();
    message.setChatId(update.getMessage().getChatId().toString());
    message.setText("Nomalum text iltimos boshqa kiriting");
    return message;
  }

  @Override
  public BotApiMethod<?> passTrayStartButtonResponseStatePage(Update update) {
    Optional<User> user = userRepository.findByChatId(update.getMessage().getFrom().getId());
    if (user.isPresent() && user.get().getPhoneNumber() == null) {
      return passStartButtonRequestContact(update);

    }

    SendMessage message = new SendMessage();
    message.setChatId(update.getMessage().getChatId().toString());
    return getMainMenuKeyboard(message);

    /*SendMessage response = new SendMessage();
    response.setChatId(update.getMessage().getChatId().toString());
    return getMainMenuKeyboard(response);*/
    /*return sendPhoneNumber(update);*/
  }


  @Override
  public BotApiMethod<?> sendPhoneNumber(Update update) {
    if (update.hasMessage()) {
      Message message = update.getMessage();

      if (message.hasContact()) {
        String phoneNumber = message.getContact().getPhoneNumber();
        Long chatId = message.getChatId();
        Optional<User> byChatId = userRepository.findByChatId(chatId);
        byChatId.ifPresent(user -> {
          user.setPhoneNumber(phoneNumber);
          user.setOldStep(UserSteps.REGISTER.toString());
          user.setStep(UserSteps.MENU.toString());

        });
        userRepository.save(byChatId.get());
        SendMessage response = new SendMessage();
        response.setChatId(chatId.toString());
        response.setText("Muvaffaqiyatli ro'yxatdan o'tdingiz🥳🥳");
        SendMessage message1 = getMainMenuKeyboard(response);
        return message1;

      }

    }

    return null;
  }


  @Override
  public BotApiMethod<?> pressBasket(Update update) {

    Long chatId = update.getMessage().getChatId();

    User user = userRepository.findByChatId(chatId).get();
    user.setOldStep(UserSteps.MENU.toString());
    user.setStep(UserSteps.BASKET.toString());
    userRepository.save(user);

    List<Products> userProducts = productRepository.findByUserId(
        update.getMessage().getFrom().getId());

    SendMessage sendMessage = new SendMessage();
    sendMessage.setChatId(chatId.toString());
    sendMessage.setText("Your basket:");

    if (userProducts.isEmpty()) {
      sendMessage.setText("Nothing not found from basket!");
      return sendMessage;
    }

    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
    List<List<InlineKeyboardButton>> rows = new ArrayList<>();
    List<InlineKeyboardButton> currentRow = new ArrayList<>();

    for (int i = 0; i < userProducts.size(); i++) {
      Products product = userProducts.get(i);

      InlineKeyboardButton button = InlineKeyboardButton.builder()
          .text(product.getName())
          .callbackData("B_PRODUCT" + product.getId().toString())
          .build();

      currentRow.add(button);

      if (currentRow.size() == 2 || i == userProducts.size() - 1) {
        rows.add(currentRow);
        currentRow = new ArrayList<>();
      }
    }

    inlineKeyboardMarkup.setKeyboard(rows);
    sendMessage.setReplyMarkup(inlineKeyboardMarkup);
    return sendMessage;
  }

  @Override
  public BotApiMethod<?> pressNatification(Update update) {
    Long chatId = update.getMessage().getChatId();

    User user = userRepository.findByChatId(chatId).get();
    user.setOldStep(UserSteps.MENU.toString());
    user.setStep(UserSteps.NOTIFICATION.toString());
    userRepository.save(user);

    List<Notification> userNotification = notificationRepository.findByUserId(update
        .getMessage()
        .getFrom()
        .getId()
    );

    SendMessage sendMessage = new SendMessage();
    sendMessage.setChatId(chatId.toString());
    sendMessage.setText("Your notifications:");

    if (userNotification.isEmpty()) {
      sendMessage.setText("Nothing not found from your notifications!");
      return sendMessage;
    }

    /*InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
    List<List<InlineKeyboardButton>> rows = new ArrayList<>();
    List<InlineKeyboardButton> currentRow = new ArrayList<>();
*/
    for (int i = 0; i < userNotification.size(); i++) {
      Notification notification = userNotification.get(i);

      sendMessage.setText(i + "-" + notification.getDescription());

    /*  InlineKeyboardButton button = InlineKeyboardButton.builder()
          .text(notification.getDescription())
          .callbackData(notification.getId().toString())
          .build();

      currentRow.add(button);

      if (currentRow.size() == 2 || i == userNotification.size() - 1) {
        rows.add(currentRow);
        currentRow = new ArrayList<>();
      }
    }

    inlineKeyboardMarkup.setKeyboard(rows);
    sendMessage.setReplyMarkup(inlineKeyboardMarkup);*/
    }
    return sendMessage;
  }

  @Override
  public BotApiMethod<?> pressHistory(Update update) {
    Long chatId = update.getMessage().getChatId();

    User user = userRepository.findByChatId(chatId).get();
    user.setOldStep(UserSteps.MENU.toString());
    user.setStep(UserSteps.HISTORY.toString());
    userRepository.save(user);

    List<Products> userProducts = productRepository.findByUserAndActive(user.getUserId(), false);

    SendMessage sendMessage = new SendMessage();
    sendMessage.setChatId(chatId.toString());
    sendMessage.setText("Your history:");

    if (userProducts.isEmpty()) {
      sendMessage.setText("Nothing not found from history!");
      return sendMessage;
    }

    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
    List<List<InlineKeyboardButton>> rows = new ArrayList<>();
    List<InlineKeyboardButton> currentRow = new ArrayList<>();

    for (int i = 0; i < userProducts.size(); i++) {
      Products product = userProducts.get(i);

      InlineKeyboardButton button = InlineKeyboardButton.builder()
          .text(product.getName())
          .callbackData(inlineResUser.C_PRODUCT + product.getId().toString())
          .build();

      currentRow.add(button);

      if (currentRow.size() == 2 || i == userProducts.size() - 1) {
        rows.add(currentRow);
        currentRow = new ArrayList<>();
      }
    }

    inlineKeyboardMarkup.setKeyboard(rows);
    sendMessage.setReplyMarkup(inlineKeyboardMarkup);
    return sendMessage;
  }

  @Override
  public BotApiMethod<?> pressSettings(Update update) {
    return null;
  }

  @Override
  public BotApiMethod<?> pressBack(Update update) {
    Long chatId = update.getCallbackQuery().getMessage().getChatId();
    int messageId = update.getCallbackQuery().getMessage().getMessageId();
    User user = userRepository.findByChatId(chatId).get();
    user.setOldStep(UserSteps.MENU.toString());
    user.setStep(UserSteps.CATEGORY.toString());
    userRepository.save(user);

    EditMessageText editMessageText = new EditMessageText();
    editMessageText.setChatId(chatId.toString());
    editMessageText.setMessageId(messageId);

    return null;
  }

  @Override
  public BotApiMethod<?> pressNext(Update update) {
    return null;
  }

  @Override
  public BotApiMethod<?> pressCProduct(Update update) {
    Long chatId = update.getCallbackQuery().getMessage().getChatId();
    String data = update.getCallbackQuery().getData();
    int messageId = update.getCallbackQuery().getMessage().getMessageId();
    Long productId = (long) Integer.parseInt(data.substring(9));
    User user = userRepository.findByChatId(chatId).get();
    user.setOldStep(UserSteps.C_PRODUCTS.toString());
    user.setStep(UserSteps.PRODUCT.toString());
    userRepository.save(user);
    Optional<Products> product = productRepository.findById(productId);

    EditMessageText editMessageText = new EditMessageText();
    editMessageText.setChatId(chatId.toString());
    editMessageText.setMessageId(messageId);
    editMessageText.setText(
        "Name: " + product.get().getName() + "\n" +
            "Price: " + product.get().getPrice().toString() + "\n" +
            "Information: " + product.get().getInformation() + "\n" +
            "Count: " + product.get().getCount()
    );

    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
    List<List<InlineKeyboardButton>> rows = new ArrayList<>();
    List<InlineKeyboardButton> currentRow = new ArrayList<>();

    InlineKeyboardButton buttonBasket = new InlineKeyboardButton();
    buttonBasket.setText("Add to basket");
    buttonBasket.setCallbackData("basket" + productId);

    InlineKeyboardButton back = new InlineKeyboardButton();
    back.setText("Back");
    back.setCallbackData("back");

    currentRow.add(back);
    currentRow.add(buttonBasket);
    rows.add(currentRow);
    inlineKeyboardMarkup.setKeyboard(rows);
    editMessageText.setReplyMarkup(inlineKeyboardMarkup);

    return editMessageText;
  }

  @Override
  @Transactional
  public BotApiMethod<?> pressAddBasket(Update update) {
    long chatId = update.getCallbackQuery().getMessage().getChatId();
    int messageId = update.getCallbackQuery().getMessage().getMessageId();
    User user = userRepository.findByChatId(chatId).get();
    System.out.println(update.getCallbackQuery().getData());
    Long productId = (long) Integer.parseInt(update.getCallbackQuery().getData().substring(6));
    Products products = productRepository.getById(productId);
    products.setUserCount(products.getUserCount() + 1);
    products.setUserId(user.getUserId());
    products.setCount(products.getCount() - 1);
    productRepository.save(products);

    return pressCProduct(chatId, productId, messageId);
  }

  @Override
  public BotApiMethod<?> pressBProduct(Update update) {
    Long chatId = update.getCallbackQuery().getMessage().getChatId();
    String data = update.getCallbackQuery().getData();
    int messageId = update.getCallbackQuery().getMessage().getMessageId();
    Long productId = (long) Integer.parseInt(data.substring(9));
    User user = userRepository.findByChatId(chatId).get();
    user.setOldStep(UserSteps.BASKET.toString());
    user.setStep(UserSteps.B_PRODUCTS.toString());
    userRepository.save(user);
    Optional<Products> product = productRepository.findById(productId);

    EditMessageText editMessageText = new EditMessageText();
    editMessageText.setChatId(chatId.toString());
    editMessageText.setMessageId(messageId);
    editMessageText.setText(
        "Name: " + product.get().getName() + "\n" +
            "Price: " + product.get().getPrice().toString() + "\n" +
            "Information: " + product.get().getInformation() + "\n" +
            "Count: " + product.get().getUserCount()
    );

    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
    List<List<InlineKeyboardButton>> rows = new ArrayList<>();
    List<InlineKeyboardButton> currentRow = new ArrayList<>();
    InlineKeyboardButton buttonBuy = new InlineKeyboardButton();
    buttonBuy.setText("Buy");
    buttonBuy.setCallbackData("buy" + productId);

    InlineKeyboardButton cancel =  new InlineKeyboardButton();
    cancel.setText("Cancel");
    cancel.setCallbackData("cancel" + productId);

    InlineKeyboardButton back = new InlineKeyboardButton();
    back.setText("Back");
    back.setCallbackData("backOldStep");

    currentRow.add(back);
    currentRow.add(cancel);
    currentRow.add(buttonBuy);
    rows.add(currentRow);

    inlineKeyboardMarkup.setKeyboard(rows);
    editMessageText.setReplyMarkup(inlineKeyboardMarkup);

    return editMessageText;
  }

  public BotApiMethod<?> pressCProduct(Long chatId, Long productId, int messageId) {
    User user = userRepository.findByChatId(chatId).get();
    user.setOldStep(UserSteps.C_PRODUCTS.toString());
    user.setStep(UserSteps.PRODUCT.toString());
    userRepository.save(user);
    Optional<Products> product = productRepository.findById(productId);

    EditMessageText editMessageText = new EditMessageText();
    editMessageText.setChatId(chatId.toString());
    editMessageText.setMessageId(messageId);
    editMessageText.setText(
        "Name: " + product.get().getName() + "\n" +
            "Price: " + product.get().getPrice().toString() + "\n" +
            "Information: " + product.get().getInformation() + "\n" +
            "Count: " + product.get().getCount()
    );

    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
    List<List<InlineKeyboardButton>> rows = new ArrayList<>();
    List<InlineKeyboardButton> currentRow = new ArrayList<>();

    InlineKeyboardButton buttonBasket = new InlineKeyboardButton();
    buttonBasket.setText("Add to basket");
    buttonBasket.setCallbackData("basket" + productId);

    InlineKeyboardButton back = new InlineKeyboardButton();
    back.setText("Back");
    back.setCallbackData("back");

    currentRow.add(back);
    currentRow.add(buttonBasket);
    rows.add(currentRow);
    inlineKeyboardMarkup.setKeyboard(rows);
    editMessageText.setReplyMarkup(inlineKeyboardMarkup);

    return editMessageText;
  }


  public SendMessage getMainMenuKeyboard(SendMessage sendMessage) {
    sendMessage.setText("Asosiy menyudan birini tanlang:");

    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
    replyKeyboardMarkup.setResizeKeyboard(true);
    replyKeyboardMarkup.setOneTimeKeyboard(false);

    List<KeyboardRow> keyboard = new ArrayList<>();

    KeyboardRow row1 = new KeyboardRow();
    row1.add("Category 📂");
    row1.add("Savat 🛒");

    KeyboardRow row2 = new KeyboardRow();
    row2.add("Xabarnoma 🔔");
    row2.add("Sotib olinganlar 📦");

    KeyboardRow row3 = new KeyboardRow();
    row3.add("Sozlamalar ⚙️");

    keyboard.add(row1);
    keyboard.add(row2);
    keyboard.add(row3);

    replyKeyboardMarkup.setKeyboard(keyboard);

    sendMessage.setReplyMarkup(replyKeyboardMarkup);

    return sendMessage;
  }

  @Override
  public BotApiMethod<?> passCategoryzButtonResponceStatePage(Update update) {
    Long chatId = update.getMessage().getChatId();

    User user = userRepository.findByChatId(chatId).get();
    user.setOldStep(UserSteps.MENU.toString());
    user.setStep(UserSteps.CATEGORY.toString());
    userRepository.save(user);

    SendMessage sendMessage = new SendMessage();
    sendMessage.setChatId(chatId.toString());
    sendMessage.setText("Categories:");

    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
    List<List<InlineKeyboardButton>> rows = new ArrayList<>();
    List<InlineKeyboardButton> currentRow = new ArrayList<>();

    List<Category> categories = categoryRepository.findAll();

    for (int i = 0; i < categories.size(); i++) {
      Category category = categories.get(i);

      InlineKeyboardButton button = InlineKeyboardButton.builder()
          .text(category.getName())
          .callbackData(inlineResUser.CATEGORY + category.getId().toString())
          .build();

      currentRow.add(button);

      if (currentRow.size() == 2 || i == categories.size() - 1) {
        rows.add(currentRow);
        currentRow = new ArrayList<>();
      }
    }

    inlineKeyboardMarkup.setKeyboard(rows);
    sendMessage.setReplyMarkup(inlineKeyboardMarkup);

    return sendMessage;
  }

  @Override
  public BotApiMethod<?> pressCategory(Update update) {
    Long chatId = update.getCallbackQuery().getMessage().getChatId();
    String data = update.getCallbackQuery().getData();
    int messageId = update.getCallbackQuery().getMessage().getMessageId();
    int categoryId = Integer.parseInt(data.substring(8));
    User user = userRepository.findByChatId(chatId).get();
    user.setOldStep(UserSteps.CATEGORY.toString());
    user.setStep(UserSteps.C_PRODUCTS.toString());
    userRepository.save(user);

    EditMessageText editMessage = new EditMessageText();
    editMessage.setChatId(chatId.toString());
    editMessage.setMessageId(messageId);
    editMessage.setText("Products:");

    InlineKeyboardMarkup productMarkup = new InlineKeyboardMarkup();
    List<List<InlineKeyboardButton>> rows = new ArrayList<>();

    InlineKeyboardButton product1 = new InlineKeyboardButton();
    List<Products> byCategoryId = productRepository.findByCategoryId((long) categoryId);
    for (Products products : byCategoryId) {
      product1.setText(products.getName());
      product1.setCallbackData(inlineResUser.C_PRODUCT + products.getId().toString());
    }

    InlineKeyboardButton button = new InlineKeyboardButton();
    button.setText("Back");
    button.setCallbackData("back");

   /* InlineKeyboardButton button2 = new InlineKeyboardButton();
    button2.setText("Next");
    button2.setCallbackData("next");*/

    List<InlineKeyboardButton> row2 = new ArrayList<>();
    row2.add(button);

    List<InlineKeyboardButton> row1 = new ArrayList<>();
    row1.add(product1);
    rows.add(row1);
    rows.add(row2);

    productMarkup.setKeyboard(rows);

    editMessage.setReplyMarkup(productMarkup);

    return editMessage;
  }


}
