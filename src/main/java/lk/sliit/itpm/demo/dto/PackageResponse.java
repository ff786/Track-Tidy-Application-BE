package lk.sliit.itpm.demo.dto;

public class PackageResponse {
    private String role = "assistant";
    private String content;

    public PackageResponse(String content) {
        this.content = content;
    }

    public String getRole() {
        return role;
    }

    public String getContent() {
        return content;
    }
}

