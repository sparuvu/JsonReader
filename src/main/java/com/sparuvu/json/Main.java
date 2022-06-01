package com.sparuvu.json;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        if(args.length == 0) {
            System.err.println("Please pass a valid input");
            System.exit(0);
        }
        ObjectMapper mapper = new ObjectMapper();
        Map<String, ?> map = mapper.readValue(args[0], Map.class);
        Map<String, ?> output = JsonFlattener.flatten(map);
        System.out.println(mapper.writeValueAsString(output));
    }
}
