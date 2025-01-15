package steps;

import base.Base;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.User;
import org.apache.http.HttpStatus;
import page.UserPage;

import java.util.HashMap;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UserSteps extends Base {

    private HashMap<String, String> user;
    private Response response;
    private String operation;
    private final Base base;

    private RequestSpecification requestSpecification;

    public UserSteps() {
        base = new Base();
    }

    @Before
    public void setUp() {
        base.setUp();
    }

    @When("realizo requisicao")
    public void realizoRequisicao() throws Exception {
        try {
            if (Objects.equals(operation, "lista por id")) {
                response = requestSpecification.when().
                    get(UserPage.defineEndpoint(operation));
            } else {
                response = given().
                    body(user).
                when().
                    post(UserPage.defineEndpoint(operation));
            }
        } catch (Exception e) {
            System.err.println("Erro durante a requisição: " + e.getMessage());
            throw new Exception("Falha na requisição: " + e.getMessage(), e);
        }
    }

//    public HashMap criaHashMap(String key1, String key2, String value1, String value2) {
//        HashMap<String, String> data = new HashMap<>();
//        data.put(key1, value1);
//        data.put(key1, value2);
//        return data;
//    }

    // Tentar criar uma função que cria um hash map com os parametros recebidos para ser utilizado no passo de criação e registro de usuário
    @Given("crio usuario com {string} e {string}")
    public void crioUsuarioCom(String name, String job) {
        try {
            user = new HashMap<>();
            user.put("name", name);
            user.put("job", job);
            operation = "cria";
        } catch (Exception e) {
            System.err.println("Erro ao criar usuário: " + e.getMessage());
            throw new RuntimeException("Falha ao criar usuário", e);
        }
    }

    @Then("informa sucesso na criacao")
    public void informaSucessoNaCriacao() {
        response.then().
            statusCode(HttpStatus.SC_CREATED).
            body("createdAt", notNullValue());
    }

    @Given("registro usuario com {string} e {string}")
    public void registroUsuarioCom(String email, String password) {
        try {
            user = new HashMap<>();
            user.put("email", email);
            user.put("password", password);
            operation = "registra";
        } catch (Exception e) {
            System.err.println("Erro ao registrar usuário: " + e.getMessage());
            throw new RuntimeException("Falha ao registrar usuário", e);
        }
    }

    @Then("informa sucesso no registro")
    public void informaSucessoNoRegistro() {
        response.then().
            statusCode(HttpStatus.SC_OK).
            body("$", hasKey("token")).
            body("token", notNullValue());
    }

    @Given("registro usuario com {string}")
    public void registroUsuarioCom(String email) {
        try {
            user = new HashMap<>();
            user.put("email", email);
            operation = "registra";
        } catch (Exception e) {
            System.err.println("Erro ao registrar usuário: " + e.getMessage());
            throw new RuntimeException("Falha ao registrar usuário", e);
        }
    }

    @Given("login usuario com {string} e {string}")
    public void loginUsuarioCom(String email, String password) {
       try {
           user = new HashMap<>();
           user.put("email", email);
           user.put("password", password);
           operation = "login";
       } catch (Exception e) {
           System.err.println("Erro ao logar usuário: " + e.getMessage());
           throw new RuntimeException("Falha ao logar usuário", e);
       }

    }

    @Then("informa sucesso no login")
    public void informaSucessoNoLogin() {
        response.then().
            statusCode(HttpStatus.SC_OK).
            body("$", hasKey("token")).
            body("token", notNullValue());
    }

    @Given("login usuario com {string}")
    public void loginUsuarioCom(String email) {
        try {
            user = new HashMap<>();
            user.put("email", email);
            operation = "login";
        } catch (Exception e) {
            System.err.println("Erro ao logar usuário: " + e.getMessage());
            throw new RuntimeException("Falha ao logar usuário", e);
        }

    }

    @Then("informa falha na operacao")
    public void informaFalhaNaOperacao() {
        response.then().
            statusCode(HttpStatus.SC_BAD_REQUEST).
            body("error", is("Missing password"));
    }

    @Given("usuario com identificador {int}")
    public void usuarioComIdentificador(int id) {
        try {
            requestSpecification = given().
                pathParam("userId", id);

            operation = "lista por id";
        } catch (Exception e) {
            System.err.println("Erro ao listar usuário por id: " + e.getMessage());
            throw new RuntimeException("Falha ao listar usuário por id", e);
        }
    }

    @Then("mostra usuario com identificador {int}")
    public void mostraUsuarioComIdentificadorId(int id) {
        User user = response.then().
            statusCode(HttpStatus.SC_OK).
        extract().
            body().jsonPath().getObject("data", User.class);

        assertThat(user.getId(), is(id));
        assertThat(user.getFirstName(), is("Janet"));
        assertThat(user.getLastName(), is("Weaver"));
        assertThat(user.getEmail(), containsString("@reqres.in"));
    }

    @Then("nao localiza usuario")
    public void naoLocalizaUsuario() {
        response.then().
            statusCode(HttpStatus.SC_NOT_FOUND).
            body(is("{}"));
    }

    // Comparar o json de retorno com o json modelo
}
