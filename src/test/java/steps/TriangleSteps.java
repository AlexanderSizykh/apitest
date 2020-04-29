package steps;

import impl.TriangleServiceImpl;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Triangle;
import org.junit.Assert;
import services.TriangleService;

import java.util.List;
import java.util.UUID;

public class TriangleSteps {
    private TriangleServiceImpl triangleService = new TriangleServiceImpl();

    private static  List<Triangle> triangles;

    @Given("There are no any triangles")
    public void thereAreNoAnyTriangles() {
            triangles = triangleService.getAll();
            int count = triangles.size();
            UUID id;
            while(count > 0) {
                id = triangles.get(count - 1).id;
                if(triangleService.delete(id)) {
                    count--;
                }
            }
        }


    @When("Creating new triangle with input {string}")
    public void creatingNewTriangleWithSides(String arg0) {
        Triangle triangle = Triangle.builder().input(arg0).build();
        triangleService.create(triangle);
    }

    @Then("There is {int} triangle\\(s) in the response list")
    public void thereIsTriangleSInTheResponseList(int arg0) {
        triangles = triangleService.getAll();
        Assert.assertEquals(triangles.size(), arg0);
    }

    @Then("The triangle #{int} in the response list has sides {int} \\(first), {int} \\(second) and {int} \\(third)")
    public void theTriangleWithIndexInTheResponseListHasSidesFirstSecondAndThird(int arg0, int arg1, int arg2, int arg3) {
        Triangle selectedTriangle = triangles.get(arg0-1);
        Assert.assertEquals(selectedTriangle.firstSide, arg1);
        Assert.assertEquals(selectedTriangle.secondSide, arg2);
        Assert.assertEquals(selectedTriangle.thirdSide, arg3);
    }
}
