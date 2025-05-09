package lk.sliit.itpm.demo.dto;

import lombok.Data;

@Data
public class EmailRequestDTO {
    private String to;
    private String subject;
    private String body;
}
