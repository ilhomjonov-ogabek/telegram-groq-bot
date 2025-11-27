package main;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import com.google.gson.*;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Main extends TelegramLongPollingBot {

  // .env fayldan o'qish
  private static final Dotenv dotenv = Dotenv.load();
  private static final String TELEGRAM_TOKEN = dotenv.get("TELEGRAM_BOT_TOKEN");
  private static final String BOT_USERNAME = dotenv.get("TELEGRAM_BOT_USERNAME");
  private static final String GROQ_API_KEY = dotenv.get("GROQ_API_KEY");
  private static final String GROQ_API_URL = "https://api.groq.com/openai/v1/chat/completions";

  @Override
  public String getBotUsername() { return BOT_USERNAME; }

  @Override
  public String getBotToken() { return TELEGRAM_TOKEN; }

  @Override
  public void onUpdateReceived(Update update) {
    if (update == null || !update.hasMessage()) return;
    Message msg = update.getMessage();
    Long chatId = msg.getChatId();

    try {
      if (msg.hasText()) {
        String text = msg.getText().trim().toLowerCase();

        if (text.equals("/start")) handleStart(chatId);
        else if (text.equals("/help")) handleHelp(chatId);
        else handleUserMessage(chatId, text);

      } else if (msg.hasPhoto()) {
        sendMessage(chatId, "⚠️ Rasmni hali tahlil qila olmayman.\nMatn yuboring 😊");
      }

    } catch (Exception ex) {
      sendMessage(chatId, "❌ Xatolik: " + ex.getMessage());
      ex.printStackTrace();
    }
  }

  private void handleStart(Long chatId) {
    sendMessage(chatId, "🤖 Salom! Men sun'iy intellekt botman.\nMenga savol yuboring!");
  }

  private void handleHelp(Long chatId) {
    sendMessage(chatId, "📘 Foydalanish bo‘yicha yordam:\n• 'Java da method nima?'\n• 'Matematika masala yech'\n• 'Tarjima: Hello world'");
  }

  private void handleUserMessage(Long chatId, String text) {
    sendMessage(chatId, "⚡ Javob tayyorlanmoqda...");
    try {
      String response = callGroq(text);
      sendMessage(chatId, response);
    } catch (Exception e) {
      sendMessage(chatId, "❌ Groq API xatolik: " + e.getMessage());
    }
  }

  private String callGroq(String userText) throws Exception {
    JsonObject req = new JsonObject();
    req.addProperty("model", "llama-3.3-70b-versatile");

    JsonArray messages = new JsonArray();

    JsonObject systemMessage = new JsonObject();
    systemMessage.addProperty("role", "system");
    systemMessage.addProperty("content", "Siz foydali AI yordamchisiz. Javoblar aniq va tushunarli bo‘lsin.");
    messages.add(systemMessage);

    JsonObject userMsg = new JsonObject();
    userMsg.addProperty("role", "user");
    userMsg.addProperty("content", userText);
    messages.add(userMsg);

    req.add("messages", messages);
    req.addProperty("temperature", 0.7);
    req.addProperty("max_tokens", 1024);

    String raw = sendHttpRequest(req.toString());
    return parseGroqResponse(raw);
  }

  private String sendHttpRequest(String body) throws Exception {
    URL url = new URL(GROQ_API_URL);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

    conn.setRequestMethod("POST");
    conn.setRequestProperty("Content-Type", "application/json");
    conn.setRequestProperty("Authorization", "Bearer " + GROQ_API_KEY);
    conn.setDoOutput(true);
    conn.setConnectTimeout(30000);
    conn.setReadTimeout(30000);

    try (OutputStream os = conn.getOutputStream()) {
      os.write(body.getBytes(StandardCharsets.UTF_8));
    }

    int code = conn.getResponseCode();
    InputStream is = (code >= 200 && code < 300) ? conn.getInputStream() : conn.getErrorStream();

    StringBuilder sb = new StringBuilder();
    try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
      String line;
      while ((line = br.readLine()) != null) sb.append(line);
    }

    conn.disconnect();
    if (code != 200) throw new Exception("API Error " + code + ": " + sb);

    return sb.toString();
  }

  private String parseGroqResponse(String json) {
    try {
      JsonObject obj = JsonParser.parseString(json).getAsJsonObject();
      JsonObject msg = obj.getAsJsonArray("choices")
          .get(0).getAsJsonObject()
          .getAsJsonObject("message");
      return msg.get("content").getAsString();
    } catch (Exception ex) {
      return "❌ Javobni o‘qishda xatolik: " + ex.getMessage();
    }
  }

  private void sendMessage(Long chatId, String text) {
    try { execute(new SendMessage(chatId.toString(), text)); }
    catch (TelegramApiException e) { e.printStackTrace(); }
  }

  public static void main(String[] args) {
    try {
      System.out.println("🚀 Bot ishga tushmoqda...");
      TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
      api.registerBot(new Main());
      System.out.println("✅ Bot ishlayapti!");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
