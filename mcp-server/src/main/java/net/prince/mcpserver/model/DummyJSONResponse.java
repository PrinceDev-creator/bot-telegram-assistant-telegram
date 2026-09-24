package net.prince.mcpserver.model;

import java.util.List;

public record DummyJSONResponse(
        List<Product> products,
        Integer total
) {
}
