package fr.fs;

import fr.fs.dto.MailDto;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
class MailResourceTest {

    @Test
    void testSendMailEndpointOK() {
        MailDto mailDto = new MailDto();
        mailDto.setTo("duchatelle@gmail.com");
        mailDto.setText("Texte");
        mailDto.setSubject("Test");
        given()
                .header("Content-Type", "application/json")
                .header("ApiKey","Its0KForYou")
                .body(mailDto)
                .when().post("/mails")
                .then()
                .statusCode(200)
                .body(is("le message : Test, a été envoyé à : duchatelle@gmail.com"));


    }
    @Test
    void testSendMailEndpointNotOK() {
        MailDto mailDto = new MailDto();
        mailDto.setTo("duchatelle@gmail.com");
        mailDto.setText("Texte");
        mailDto.setSubject("Test");

        given()
                .header("Content-Type", "application/json")
                .header("ApiKey","ItsNot0KForYou")
                .body(mailDto)
                .when().post("/mails")
                .then()
                .statusCode(401);

    }
}