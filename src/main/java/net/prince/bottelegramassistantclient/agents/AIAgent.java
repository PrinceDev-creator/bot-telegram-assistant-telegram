package net.prince.bottelegramassistantclient.agents;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class AIAgent {

    private final ChatClient chatClient;

//    @Value("classpath:/prompts/systemMessage.st")
//    private String systemMessage;

    public AIAgent(ChatClient.Builder chatClient, ToolCallbackProvider tools, ChatMemory memory){
        Arrays.stream(tools.getToolCallbacks()).forEach(toolCallback -> {
            System.out.println("----------------------");
            System.out.println(toolCallback.getToolDefinition());
            System.out.println("----------------------");
        });
        this.chatClient = chatClient
                .defaultSystem("Tu es un agent qui se charge de répondre aux questions des utilisateurs sur les produits de l'entreprise Ornitho en fonction du contexte" +
                        "Le PDG de Ornitho est le Professeur Prince BOGNONKPE, il est titulaire d'un doctorat en intelligence artificielle et enseigne à l'université de Princeton" +
                        "Ornitho est une très grande boîte, entreprise qui a des filiales dans une dizaine de pays partout dans le monde, elle est spécialisé dans 3 domaines"+
                        "la conception de logiciel d'intelligence artificielle spécialisée pour les entreprises, la vente de produits (comme amazon) et l'immobilier"+"Ornitho est subdivisé en 3 groupes :OrnithoSale, OrnithoAI et OrnithoImmob"+
                        "Si tu n'as pas de contexte sur la question, tu réponds par JE NE SAIS, fournissez-moi plus d'informations pour vous répondre")
                .defaultTools(tools)
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(memory).build())
                .build();

    }

    public String askAgent(String query, String conversationId) {
        return chatClient.prompt()
                .user(query)
                .advisors(advisor -> advisor
                        .param(ChatMemory.CONVERSATION_ID, conversationId))
                .call()
                .content();
    }
}
