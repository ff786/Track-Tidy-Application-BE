package lk.sliit.itpm.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserPromptRequest {
    private String userPromptRequest;

    @Override
    public String toString() {
        return "UserPromptRequest{" +
                "userPromptRequest='" + userPromptRequest + '\'' +
                '}';
    }
}
