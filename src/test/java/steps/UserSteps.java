package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.Before;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import java.util.HashMap;

import base.Base;
import page.UserPage;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserSteps extends Base {

    private HashMap<String, String> user;
    private Response response;
    private String operation;
    private Base base;

    public UserSteps() {
        base = new Base();
    }

    @Before
    public void setUp() {
        base.setUp();
    }

    @When("realizo requisicao")
    public void realizo_requisicao() {
        try {
            response = given().
                            body(user).
                       when().
                            post(UserPage.defineEndpoint(operation));
        } catch (Exception e) {
            System.err.println("Erro durante a requisição: " + e.getMessage());
            throw new RuntimeException("Falha na requisição: " + e.getMessage(), e);
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
    public void crio_usuario_com(String name, String job) {
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
    public void informa_sucesso_na_criacao() {
        response.then().
                statusCode(HttpStatus.SC_CREATED).
                body("createdAt", notNullValue());
    }

    @Given("registro usuario com {string} e {string}")
    public void registro_usuario_com(String email, String password) {
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
    public void informa_sucesso_no_registro() {
        response.then().
                    statusCode(HttpStatus.SC_OK).
                    body("$", hasKey("token")).
                    body("token", notNullValue());
    }

    @Given("registro usuario com {string}")
    public void registro_usuario_com(String email) {
        try {
            user = new HashMap<>();
            user.put("email", email);
            operation = "registra";
        } catch (Exception e) {
            System.err.println("Erro ao registrar usuário: " + e.getMessage());
            throw new RuntimeException("Falha ao registrar usuário", e);
        }
    }

//    @Then("informa falha na operacao")
//    public void informa_falha_na_operacao() {
//        response.then().
//                statusCode(HttpStatus.SC_BAD_REQUEST).
//                body("error", is("Missing password"));
//    }

    @Given("login usuario com {string} e {string}")
    public void login_usuario_com(String email, String password) {
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
    public void informa_sucesso_no_login() {
        response.then().
                    statusCode(HttpStatus.SC_OK).
                    body("$", hasKey("token")).
                    body("token", notNullValue());
    }

    @Given("login usuario com {string}")
    public void login_usuario_com(String email) {
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
    public void informa_falha_na_operacao() {
        response.then().
                statusCode(HttpStatus.SC_BAD_REQUEST).
                body("error", is("Missing password"));
    }

}
