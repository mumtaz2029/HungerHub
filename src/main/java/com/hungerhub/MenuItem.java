package com.hungerhub;

public record MenuItem(
        String name,
        String category,
        String description,
        String image,
        String rating,
        int price,
        String accent
) {
}
