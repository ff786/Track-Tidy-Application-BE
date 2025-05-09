package lk.sliit.itpm.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PackageRecommendationService {

    @Value("${openai.api.key}")
    private String openApiKey;

    private final RestTemplate restTemplate;

    public PackageRecommendationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String generatePackage(String codeSnippet) {
        String prompt = String.format("""
            You are a helpful assistant for the TrackTidy website. The user is looking for a package that includes:
            
            %s
        
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
            """, codeSnippet);

        Map<String, Object> messageSystem = Map.of("role", "system",
                "content", "You are a helpful assistant for the TrackTidy website.");

        Map<String, Object> messageUser = Map.of("role", "user", "content", prompt);

        Map<String, Object> request = Map.of(
                "model", "gpt-4o",
                "messages", List.of(messageSystem, messageUser),
                "max_tokens", 500,
                "temperature", 0.5,
                "n", 1
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(openApiKey);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(
                    "https://api.openai.com/v1/chat/completions", entity, Map.class
            );

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                List<Map<String, Object>> choices = (List<Map<String, Object>>) response.getBody().get("choices");
                if (!choices.isEmpty()) {
                    Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
                    return message.get("content").toString().trim();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "⚠️ Sorry, I'm having trouble generating a package suggestion right now.";

        }
        return null;
    }
}
