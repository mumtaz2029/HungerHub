package com.hungerhub;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "MenuServlet", urlPatterns = "/api/menu")
public class MenuServlet extends HttpServlet {
    private static final List<MenuItem> CUSTOMER_FAVORITES = List.of(
            new MenuItem(
                    "Firecracker Paneer Pizza",
                    "Pizza",
                    "Crispy crust, smoky paneer, bell peppers, mozzarella, and a bright chilli drizzle.",
                    "https://images.unsplash.com/photo-1604382354936-07c5d9983bd3?auto=format&fit=crop&w=900&q=80",
                    "4.9",
                    349,
                    "#ef4444"
            ),
            new MenuItem(
                    "Royal Hyderabadi Biryani",
                    "Biryani",
                    "Layered basmati rice, aromatic spices, fried onions, mint, and cooling raita.",
                    "https://images.unsplash.com/photo-1633945274405-b6c8069047b0?auto=format&fit=crop&w=900&q=80",
                    "4.8",
                    289,
                    "#f97316"
            ),
            new MenuItem(
                    "Stacked Street Burger",
                    "Burger",
                    "Toasted bun, juicy patty, cheese, crunchy greens, pickles, and signature hub sauce.",
                    "https://images.unsplash.com/photo-1568901346375-23c9450c58cd?auto=format&fit=crop&w=900&q=80",
                    "4.7",
                    229,
                    "#22c55e"
            ),
            new MenuItem(
                    "Choco Lava Cloud",
                    "Dessert",
                    "Warm chocolate cake with a molten center, vanilla cream, and cocoa crumble.",
                    "https://images.unsplash.com/photo-1606313564200-e75d5e30476c?auto=format&fit=crop&w=900&q=80",
                    "4.9",
                    179,
                    "#a855f7"
            )
    );

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-store");
        response.getWriter().write(toJson(CUSTOMER_FAVORITES));
    }

    private String toJson(List<MenuItem> items) {
        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < items.size(); i++) {
            MenuItem item = items.get(i);
            if (i > 0) {
                json.append(',');
            }
            json.append('{')
                    .append("\"name\":\"").append(escape(item.name())).append("\",")
                    .append("\"category\":\"").append(escape(item.category())).append("\",")
                    .append("\"description\":\"").append(escape(item.description())).append("\",")
                    .append("\"image\":\"").append(escape(item.image())).append("\",")
                    .append("\"rating\":\"").append(escape(item.rating())).append("\",")
                    .append("\"price\":").append(item.price()).append(',')
                    .append("\"accent\":\"").append(escape(item.accent())).append("\"")
                    .append('}');
        }
        return json.append(']').toString();
    }

    private String escape(String value) {
        return value.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r");
    }
}
