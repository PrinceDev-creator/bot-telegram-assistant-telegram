package net.prince.mcpserver.shared;

public class DummyJSON {

    public static final String allProducts = "https://dummyjson.com/products";

    public static String singleProductURL(Long id){
        String idToString = String.valueOf(id);
        return allProducts+"/"+idToString;
    }

}
