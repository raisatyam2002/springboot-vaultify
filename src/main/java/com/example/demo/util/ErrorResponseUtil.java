package com.example.demo.util;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletResponse;

public class ErrorResponseUtil {
    public static void sendError(HttpServletResponse response, int status, String message) throws IOException, java.io.IOException {
        response.setStatus(status);
        response.setContentType("application/json");
        response.getWriter().write("{\"error\": \"" + message + "\"}");
    }
}
