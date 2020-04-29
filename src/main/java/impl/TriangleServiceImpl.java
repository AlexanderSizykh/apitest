package impl;
import com.fasterxml.jackson.databind.type.TypeFactory;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.DigitResult;
import models.Triangle;
import services.TriangleService;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static io.restassured.RestAssured.given;

public class TriangleServiceImpl implements TriangleService {

    private Type trianglesList = TypeFactory.defaultInstance().constructCollectionLikeType(ArrayList.class, Triangle.class);
    private final String SERVICE = "triangle/";

    @Override
    public Triangle create(Triangle triangle) {
        String url = URI + SERVICE;
        Response r = given()
                .contentType(ContentType.JSON)
                .body(triangle)
                .header(USER, TOKEN)
                .post(url);
        if (r.statusCode() == 200) {
            return r.as(Triangle.class);
        }
        return null;
    }

    @Override
    public Triangle get(UUID id) {
        String url = URI + SERVICE;
        Response r = given()
                .header(USER, TOKEN)
                .get(url + id);
        if (r.statusCode() == 200) {
            return r.as(Triangle.class);
        }
        return null;
    }

    @Override
    public List<Triangle> getAll() {
        String url = URI + SERVICE + "all";
        Response r = given()
                .contentType(ContentType.JSON)
                .header(USER, TOKEN)
                .get(url);
        if (r.statusCode() == 200) {
            return r.as(trianglesList);
        }
        return null;
    }

    @Override
    public boolean delete(UUID id) {
        String url = URI + SERVICE;
        Response r = given()
                .header(USER, TOKEN)
                .delete(url + id);
        if(r.statusCode() == 200) {
            return true;
        }
        return false;
    }

    @Override
    public DigitResult getPerimeter(UUID id) {
        String url = URI + SERVICE + id + "/perimeter";
        Response r = given()
                .header(USER, TOKEN)
                .get(url);
        if(r.statusCode() == 200) {
            return r.as(DigitResult.class);
        }
        return null;
    }

    @Override
    public DigitResult getArea(UUID id) {
        String url = URI + SERVICE + id + "/area";
        Response r = given()
                .header(USER, TOKEN)
                .get(url);
        if(r.statusCode() == 200) {
            return r.as(DigitResult.class);
        }
        return null;    }

}