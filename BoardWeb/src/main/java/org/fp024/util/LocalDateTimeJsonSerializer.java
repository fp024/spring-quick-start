package org.fp024.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeJsonSerializer implements JsonSerializer<LocalDateTime> {
  @Override
  public JsonElement serialize(
      LocalDateTime src, Type typeOfSrc, JsonSerializationContext context) {

    JsonObject jo = new JsonObject();

    jo.addProperty("datetime", src.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
    return jo;
  }
}
