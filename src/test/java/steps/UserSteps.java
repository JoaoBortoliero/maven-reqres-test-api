package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.Before;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class UserSteps {

    HashMap<String, String> user;
    private Response response;

    private static final String CRIA_USUARIO_ENDPOINT = "/users";
    private static final String REGISTRA_USUARIO_ENDPOINT = "/register";
//    private static final String

//    public HashMap criaHashMap(String key1, String key2, String value1, String value2) {
//        HashMap<String, String> data = new HashMap<>();
//        data.put(key1, value1);
//        data.put(key1, value2);
//        return data;
//    }

    // Tentar criar uma função que cria um hash map com os parametros recebidos para ser utilizado no passo de criação e registro de usuário
    @Given("crio usuario com {string} e {string}")
    public void crio_usuario_com(String name, String job) {
        user = new HashMap<>();
        user.put("name", name);
        user.put("job", job);
    }

    @When("realizo requisicao")
    public void realizo_requisicao() {
        try {
            response = given().
                            body(user).
                       when().
                            post("https://reqres.in/api/users");

            System.out.println("Status Code: " + response.getStatusCode());

            if (response.getStatusCode() != HttpStatus.SC_CREATED) {
                throw new RuntimeException("Erro ao criar usuário. Status: " + response.getStatusCode());
            }

        } catch (Exception e) {
            // Log detalhado do erro
            System.err.println("Erro durante a requisição: " + e.getMessage());
            throw new RuntimeException("Falha na requisição: " + e.getMessage(), e);
        }
    }

    @Then("informa sucesso na criacao")
    public void informa_sucesso_na_criacao() {
        response.then().
                statusCode(HttpStatus.SC_CREATED).
                body("createdAt", notNullValue());
    }

    @Given("registro usuario com {string} e {string}")
    public void registro_usuario_com(String email, String password) {
        user = new HashMap<>();
        user.put("email", email);
        user.put("password", password);
    }


}
