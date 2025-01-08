package ai.jobbeacon.oauth2.domain;

public class AuthDTO {
    public record LoginRequest(String username, String password) {
    }

    public record Response(String message, String token) {
    }

    public record User(String username, String password) {
        public static User getInstance() {
            return new User("", "");
        }
    }
}
