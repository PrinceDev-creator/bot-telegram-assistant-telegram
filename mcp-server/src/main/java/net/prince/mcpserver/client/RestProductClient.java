package net.prince.mcpserver.client;

import jakarta.annotation.PostConstruct;
import net.prince.mcpserver.model.DummyJSONResponse;
import net.prince.mcpserver.model.Product;
import net.prince.mcpserver.shared.DummyJSON;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class RestProductClient {

    private final RestClient restClient;

    @Value(DummyJSON.allProducts)
    private String allProductsURL;

    public RestProductClient() {
        this.restClient = RestClient.create();
    }


//    @Cacheable(value = "products")
    public List<Product> findAllProducts(){
        try {
            DummyJSONResponse response = restClient.get()
                    .uri(allProductsURL)
                    .retrieve()
                    .body(new ParameterizedTypeReference<DummyJSONResponse>(){
                    });
            assert response != null;
            return response.products();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    @Cacheable(value = "product", key = "#id")
    public Product findProduct(String id){
        return restClient.get()
                .uri(allProductsURL+"/"+id)
                .retrieve()
                .body(new ParameterizedTypeReference<Product>() {}
                );
    }

    @CacheEvict(value = {"products", "product"}, allEntries = true)
    public void clearCache(){}

}
