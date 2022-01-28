package com.resume.app.util;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GsonLocalDateTimeAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {
    @Override
    public LocalDate deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        String string = jsonElement.getAsString();
        return LocalDate.parse(string, DateTimeFormatter.ISO_LOCAL_DATE);
    }

    @Override
    public JsonElement serialize(LocalDate localDate, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(localDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
    }
}
