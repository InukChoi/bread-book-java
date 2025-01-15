package domain.member;

public class ResLoginDto {
    boolean result;
    String token;

    public ResLoginDto(boolean result, String token) {
        this.result = result;
        this.token = token;
    }

    public boolean isResult() {
        return result;
    }

    public String getToken() {
        return token;
    }
}
