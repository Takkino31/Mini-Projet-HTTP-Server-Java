package com.miniproject.httpserver.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Json {

    private static ObjectMapper myObjectMapper;

    private static ObjectMapper defaultObjectMapper() {
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return om;
    }

    public static JsonNode parse(String jsonSrc) throws IOException {
        // return myObjectMapper.readTree(jsonSrc);
        try {
            // Check if myObjectMapper is null and initialize if needed
            if (myObjectMapper == null) {
                myObjectMapper = new ObjectMapper();
            }
            // Use myObjectMapper to parse JSON
            return myObjectMapper.readTree(jsonSrc);
        } catch (Exception e) {
            System.err.println("Erreur Parsing:::::");
            e.printStackTrace();
            return null;
        }
    }

    public static <A> A fromJson(JsonNode node, Class<A> clazz) throws JsonProcessingException {
        return myObjectMapper.treeToValue(node, clazz);
    }

    public static JsonNode toJson(Object obj) {
        return myObjectMapper.valueToTree(obj);
    }

    private static String stringify(JsonNode node) throws JsonProcessingException {
        return generateJson(node, false);
    }

    private static String stringifyPretty(JsonNode node) throws JsonProcessingException {
        return generateJson(node, true);
    }

    private static String generateJson(Object o, boolean pretty) throws JsonProcessingException {
        ObjectWriter objectWriter = myObjectMapper.writer();
        if (pretty)
            objectWriter = objectWriter.with(SerializationFeature.INDENT_OUTPUT);

        return objectWriter.writeValueAsString(o);
    }
}
