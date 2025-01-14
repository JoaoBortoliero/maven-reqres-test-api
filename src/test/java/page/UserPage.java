package page;

public class UserPage {
    private static final String CRIA_USUARIO_ENDPOINT = "/users";
    private static final String REGISTRA_USUARIO_ENDPOINT = "/register";
    private static final String LOGIN_USUARIO_ENDPOINT = "/login";

    public static String defineEndpoint(String operation) {
        return switch (operation) {
            case "cria" -> CRIA_USUARIO_ENDPOINT;
            case "registra" -> REGISTRA_USUARIO_ENDPOINT;
            case "login" -> LOGIN_USUARIO_ENDPOINT;
            default -> throw new IllegalArgumentException("Endpoint não localizado");
        };
    }
}
