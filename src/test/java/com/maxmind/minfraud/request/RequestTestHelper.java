package com.maxmind.minfraud.request;

import com.maxmind.minfraud.request.TransactionReport.Tag;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.util.UUID;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

/**
 * Helper for creating test requests.
 */
public class RequestTestHelper {
    public static TransactionReport fullTransactionReport() throws Exception {
        return new TransactionReport.Builder(InetAddress.getByName("1.1.1.1"), Tag.NOT_FRAUD)
                .chargebackCode("foo")
                .maxmindId("12345678")
                .minfraudId(UUID.fromString("58fa38d8-4b87-458b-a22b-f00eda1aa20d"))
                .build();
    }

    public static Transaction fullTransaction() throws Exception {
        return makeTransaction(new Email.Builder()
                .address("test@maxmind.com")
                .domain("maxmind.com")
                .build());
    }

    public static Transaction fullTransactionEmailMd5() throws Exception {
        return makeTransaction(new Email.Builder()
                .address("test@maxmind.com")
                .hashAddress()
                .domain("maxmind.com")
                .build());
    }

    private static Transaction makeTransaction(Email e) throws Exception {
        return new Transaction.Builder(
                new Device.Builder(InetAddress.getByName("152.216.7.110"))
                        .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.89 Safari/537.36")
                        .sessionAge(3600.5)
                        .sessionId("foobar")
                        .acceptLanguage("en-US,en;q=0.8")
                        .build()
        )
                .event(
                        new Event
                                .Builder()
                                .transactionId("txn3134133")
                                .shopId("s2123")
                                .time(ZonedDateTime.parse("2012-04-12T23:20:50.52Z"))
                                .type(Event.Type.PURCHASE)
                                .build()
                )
                .account(
                        new Account.Builder()
                                .userId("3132")
                                .username("fred")
                                .build()
                ).email(
                        e
                ).billing(
                        new Billing.Builder()
                                .firstName("First")
                                .lastName("Last")
                                .company("Company")
                                .address("101 Address Rd.")
                                .address2("Unit 5")
                                .city("City of Thorns")
                                .region("CT")
                                .country("US")
                                .postal("06510")
                                .phoneNumber("123-456-7890")
                                .phoneCountryCode("1")
                                .build()
                ).shipping(
                        new Shipping.Builder()
                                .firstName("ShipFirst")
                                .lastName("ShipLast")
                                .company("ShipCo")
                                .address("322 Ship Addr. Ln.")
                                .address2("St. 43")
                                .city("Nowhere")
                                .region("OK")
                                .country("US")
                                .postal("73003")
                                .phoneNumber("123-456-0000")
                                .phoneCountryCode("1")
                                .deliverySpeed(Shipping.DeliverySpeed.SAME_DAY)
                                .build()
                ).payment(
                        new Payment.Builder()
                                .processor(Payment.Processor.STRIPE)
                                .wasAuthorized(false)
                                .declineCode("invalid number")
                                .build()
                ).creditCard(
                        new CreditCard.Builder()
                                .issuerIdNumber("411111")
                                .bankName("Bank of No Hope")
                                .bankPhoneCountryCode("1")
                                .bankPhoneNumber("123-456-1234")
                                .avsResult('Y')
                                .cvvResult('N')
                                .lastDigits("7643")
                                .token("123456abc1234")
                                .was3dSecureSuccessful(true)
                                .build()
                ).order(
                        new Order.Builder()
                                .amount(323.21)
                                .currency("USD")
                                .discountCode("FIRST")
                                .affiliateId("af12")
                                .subaffiliateId("saf42")
                                .referrerUri(new URI("http://www.amazon.com/"))
                                .isGift(true)
                                .hasGiftMessage(false)
                                .build()
                ).addShoppingCartItem(
                        new ShoppingCartItem.Builder()
                                .category("pets")
                                .itemId("ad23232")
                                .quantity(2)
                                .price(20.43)
                                .build()
                ).addShoppingCartItem(
                        new ShoppingCartItem.Builder()
                                .category("beauty")
                                .itemId("bst112")
                                .quantity(1)
                                .price(100.)
                                .build()
                ).customInputs(
                        new CustomInputs.Builder()
                                .put("float_input", 12.1)
                                .put("integer_input", 3123)
                                .put("string_input", "This is a string input.")
                                .put("boolean_input", true)
                                .build()
                ).build();
    }


    public static String readJsonFile(String name) throws IOException, URISyntaxException {
        URL resource = RequestTestHelper.class
                .getResource("/test-data/" + name + ".json");
        return Files.readString(Paths.get(resource.toURI()));
    }

    public static void verifyRequestFor(String service, String jsonFile) throws IOException, URISyntaxException {
        String requestBody = readJsonFile(jsonFile);

        verify(postRequestedFor(urlMatching("/minfraud/v2.0/" + service))
                .withRequestBody(equalToJson(requestBody))
                .withHeader("Content-Type", matching("application/json; charset=UTF-8")));
    }
}
