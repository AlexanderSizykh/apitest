package steps;

import impl.TriangleServiceImpl;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Triangle;
import org.junit.Assert;

import java.util.List;
import java.util.UUID;


public class TriangleSteps {
    private static TriangleServiceImpl triangleService = new TriangleServiceImpl();
    private static List<Triangle> triangles;

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

    @When("Creating new (.*) triangle with sides: (.*)")
    public void creatingNewTriangleWithSides(String description, String input) {
        Triangle triangle = Triangle.builder().input(input).build();
        triangleService.create(triangle);
    }

    @Then("There is\\are {int} triangle\\s in the response list")
    public void thereIsTriangleSInTheResponseList(int arg0) {
        triangles = triangleService.getAll();
        Assert.assertEquals(arg0, triangles.size());
    }

    @Then("The triangle with position in list #(.*) has sides (.*), (.*) and (.*)")
    public void theTriangleWithPositionInTheResponseListHasSidesFirstSideSecondSideAndThirdSide(int positionInTheResponseList, double first, double second, double third) {
        Triangle selectedTriangle = triangles.get(positionInTheResponseList-1);
        Assert.assertEquals(first, selectedTriangle.firstSide, 0);
        Assert.assertEquals(second, selectedTriangle.secondSide, 0);
        Assert.assertEquals(third, selectedTriangle.thirdSide, 0);

    }

    @When("Creating new triangle with request data: separator: (.*), input: (.*)")
    public void creatingNewTriangleWithRequestInput(String separator, String input) {
        Triangle triangle = Triangle.builder().separator(separator).input(input).build();
        triangleService.create(triangle);
        }
}

