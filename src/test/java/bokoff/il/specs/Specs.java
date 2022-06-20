package bokoff.il.specs;

import static io.restassured.RestAssured.with;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class Specs {
public static RequestSpecification request = with()
    .baseUri("https://reqres.in")
    .basePath("/api")
    .log().all()
    .contentType(JSON);

public static ResponseSpecification successRegisterTest = new ResponseSpecBuilder()
    .expectStatusCode(200)
    .expectBody("id", is(4))
    .expectBody("token", is("QpwL5tke4Pnpja7X4"))
    .build();

public static ResponseSpecification responseSuccess = new ResponseSpecBuilder()
    .expectStatusCode(200)
    .log(LogDetail.BODY)
    .build();

public static ResponseSpecification responseFailed = new ResponseSpecBuilder()
    .expectStatusCode(400)
    .log(LogDetail.BODY)
    .build();

public static ResponseSpecification updateUserResponse = new ResponseSpecBuilder()
    .expectStatusCode(200)
    .log(LogDetail.BODY)
    .expectBody("name", is("ilya"))
    .expectBody("job", is("QAA"))
    .build();
}
