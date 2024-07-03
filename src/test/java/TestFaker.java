import com.github.javafaker.Faker;

public class TestFaker {
    public static String fakerCustomerName() {
        return Faker.instance().letterify("Mark");
    }

    public static String fakerCustomerPhone() {
        return Faker.instance().numerify("89028942");
    }
    public static String fakerComment() {
        return Faker.instance().letterify("Send me your account number");
    }
}
