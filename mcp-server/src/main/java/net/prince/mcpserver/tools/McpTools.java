package net.prince.mcpserver.tools;

import net.prince.mcpserver.client.RestProductClient;
import net.prince.mcpserver.model.Product;
import org.springframework.ai.mcp.annotation.McpArg;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class McpTools {

    private final RestProductClient restProductClient;

    public McpTools(RestProductClient restProductClient) {
        this.restProductClient = restProductClient;
    }

    @McpTool(name = "AllProducts", description = "Retrieve all products")
    public List<Product> getAllProducts(){
        return restProductClient.findAllProducts();
    }

    @McpTool(name = "SingleProduct", description = "Retrieve one product")
    public Product getProduct(@McpArg(name = "Id", description = "Identifiant of product") String id){
        return restProductClient.findProduct(id);
    }
}
