package api;

import org.example.api.pojo.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiTest {

    private static final String URL = "https://restful-booker.herokuapp.com";

    @Test
    public void test() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());

        // Create token
        CreateTokenRequest createTokenRequest = new CreateTokenRequest("admin", "password123");
        CreateTokenResponse createTokenResponse = given()
                .body(createTokenRequest)
                .when()
                .post("/auth")
                .then().log().all()
                .extract().as(CreateTokenResponse.class);

        // Create Booking
        Booking bookingToCreate = new Booking(
                "Jim",
                "Brown",
                111,
                true,
                new BookingDates("2018-01-01", "2019-01-01"),
                "Breakfast"
        );
        CreateBookingResponse createBookingResponse = given()
                .body(bookingToCreate)
                .when()
                .post("/booking")
                .then().log().all()
                .extract().as(CreateBookingResponse.class);

        // Get created booking
        Booking createdBooking = given()
                .when()
                .get("/booking/" + createBookingResponse.getBookingid())
                .then().log().all()
                .extract().as(Booking.class);

        Assert.assertEquals(createdBooking, bookingToCreate);

        // Partial update booking
        PartialUpdateBooking partialUpdateBooking = new PartialUpdateBooking(1111);
        Booking updatedBookingResponse = given()
                .header("Cookie", "token=" + createTokenResponse.getToken())
                .body(partialUpdateBooking)
                .when()
                .patch("/booking/" + createBookingResponse.getBookingid())
                .then().log().all()
                .extract().as(Booking.class);

        // Get updated booking
        Booking updatedBooking = given()
                .when()
                .get("/booking/" + createBookingResponse.getBookingid())
                .then().log().all()
                .extract().as(Booking.class);

        Assert.assertEquals(updatedBooking.getTotalprice(), partialUpdateBooking.getTotalprice());

        // Get all bookings
        BookingId[] allBookingIds = given()
                .when()
                .get("/booking")
                .then().log().all()
                .extract().as(BookingId[].class);

        List<BookingId> allBooking = Arrays.asList(allBookingIds);

        boolean isBookingIdFound = false;
        for (BookingId bookingId: allBooking) {
            if (bookingId.getBookingid() == createBookingResponse.getBookingid()) {
                isBookingIdFound = true;
                break;
            }
        }
        Assert.assertTrue(isBookingIdFound);

        // Delete booking
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK201());
        given()
                .header("Cookie", "token=" + createTokenResponse.getToken())
                .when()
                .delete("/booking/" + createBookingResponse.getBookingid())
                .then().log().all();
    }
}
