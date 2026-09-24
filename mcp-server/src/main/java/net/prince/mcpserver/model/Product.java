package net.prince.mcpserver.model;

public record Product(
        Long id,
        String title,
        String category,
        Double price,
        Double discountPercentage,
        Integer stock
) {
}
