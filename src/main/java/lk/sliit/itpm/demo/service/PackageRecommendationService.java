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

    /**
     * Monthly Income: 120000 I can spend 110000 on this service my preference is grocery, I can spend 65000 on grocery and I also prioritize grocery
     * @Author Farsith Fawzer
     */

    public String generatePackage(String codeSnippet) {

        System.out.println(codeSnippet);
        String prompt = String.format("""
        You are a helpful assistant for the TrackTidy website. The user is looking for a service package that includes:

        1. Maintenance & Service (plumbing, electrical, etc.)
        2. Grocery Shopping (they can purchase groceries up to a certain value)
        3. Home Inventory (requesting appliances or home-related items)

        Your job is to help the user find the most suitable package.

        Follow these steps:

        1. First, check if the user's description clearly matches any of the 3 standard packages below:
            a. Basic: 30,000 (Grocery: 15,000, Maintenance: 10,000, Appliances: 5,000)
            b. Ultra: 70,000 (Grocery: 30,000, Maintenance: 20,000, Appliances: 20,000)
            c. Premium: 100,000 (Grocery: 50,000, Maintenance: 35,000, Appliances: 25,000)

        2. If it's unclear or they want something different, then ask the following questions one at a time:
            - What is your monthly income?
            - How much can you spend on this service?
            - Which of these categories is most important to you: Grocery, Maintenance, or Inventory?
            - How would you like to prioritize your budget among Grocery, Maintenance, and Inventory?

        3. After gathering all the necessary inputs, create and return a clean and user-friendly package suggestion like this:

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

        Keep the tone friendly and answers short — suitable for a modern web UI.
        """);

        Map<String, Object> messageSystem = Map.of(
                "role", "system",
                "content", prompt
        );

        Map<String, Object> messageUser = Map.of(
                "role", "user",
                "content", codeSnippet
        );

        Map<String, Object> request = Map.of(
                "model", "gpt-4o",
                "messages", List.of(messageSystem, messageUser),
                "max_tokens", 3096,
                "temperature", 0.1,
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
