package page;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPage {
    private static final String CRIA_USUARIO_ENDPOINT = "/users";
    private static final String REGISTRA_USUARIO_ENDPOINT = "/register";
    private static final String LOGIN_USUARIO_ENDPOINT = "/login";
    private static final String LISTA_USUARIO_ENDPOINT = "/users/{userId}";

    public static String defineEndpoint(String operation) {
        return switch (operation) {
            case "cria" -> CRIA_USUARIO_ENDPOINT;
            case "registra" -> REGISTRA_USUARIO_ENDPOINT;
            case "login" -> LOGIN_USUARIO_ENDPOINT;
            case "lista por id" -> LISTA_USUARIO_ENDPOINT;
            default -> throw new IllegalArgumentException("Endpoint não localizado");
        };
    }
}
