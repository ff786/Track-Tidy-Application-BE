package lk.sliit.itpm.demo.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/track-ai")
public class PromptController {

    private final ChatClient.Builder chatClientBuilder;

    @Autowired
    public PromptController(ChatClient.Builder chatClientBuilder) {
        this.chatClientBuilder = chatClientBuilder;
    }

    @GetMapping("/tracktidy-package")
    public Flux<String> getPackageRecommendation(@RequestParam("message") String message) {
        ChatClient chatClient = chatClientBuilder.build();

        String systemPrompt = """
            You are a helpful assistant for the TrackTidy website. The user is looking for a package that includes:
        
            1. Maintenance & Service (plumbing, electrical, etc.)
            2. Grocery Shopping (they can purchase groceries up to a certain value)
            3. Home Inventory (requesting appliances or home-related items)

            You need to do the following:
            - First check if the user is clearly referring to one of the 3 standard packages.
                a. Basic: 30,000 (Grocery: 15,000, Maintenance: 10,000, Appliances: 5,000)
                b. Ultra: 70,000 (Grocery: 30,000, Maintenance: 20,000, Appliances: 20,000)
                c. Premium: 100,000 (Grocery: 50,000, Maintenance: 35,000, Appliances: 25,000)

            - If not, guide the user to create a custom package by asking:
                1. Monthly income
                2. How much they can spend
                3. Which category is more important to them
                4. Their preferences among Grocery / Maintenance / Inventory

            - Finally, provide a nicely formatted suggestion like this:
                {
                  "type": "Custom Package",
                  "totalBudget": 60000,
                  "breakdown": {
                    "Grocery": 25000,
                    "Maintenance": 20000,
                    "Inventory": 15000
                  },
                  "comment": "Based on your preferences, we’ve created a custom package favoring groceries and maintenance equally."
                }

            Always keep responses short, friendly, and suitable to show in a modern UI.
        """;

        List<Message> messages = List.of(
                new UserMessage(systemPrompt),
                new UserMessage(message)
        );

        return chatClient
                .prompt()
                .messages(messages)
                .stream()
                .content();
    }
}
