package com.expensetracker.expense_tracker.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {

        String uri = request.getRequestURI();

        // Only protect the dashboard
        if (uri.equals("/index.html")) {

            HttpSession session = request.getSession(false);

            if (session == null ||
                    session.getAttribute("loggedInUser") == null) {

                response.sendRedirect("/login.html");
                return false;
            }
        }

        return true;
    }
}