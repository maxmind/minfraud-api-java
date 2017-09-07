package com.maxmind.minfraud.request;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

/**
 * Helper for creating test requests.
 */
public class RequestTestHelper {
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        return new Transaction.Builder(
                new Device.Builder(InetAddress.getByName("81.2.69.160"))
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
                                .time(dateFormat.parse("2012-04-12T23:20:50.52Z"))
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
                                .phoneNumber("323-123-4321")
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
                                .phoneNumber("403-321-2323")
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
                                .issuerIdNumber("323132")
                                .bankName("Bank of No Hope")
                                .bankPhoneCountryCode("1")
                                .bankPhoneNumber("800-342-1232")
                                .avsResult('Y')
                                .cvvResult('N')
                                .last4Digits("7643")
                                .token("123456abc1234")
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
        return new String(Files.readAllBytes(Paths.get(resource.toURI())), StandardCharsets.UTF_8);
    }

    public static void verifyRequestFor(String service, String jsonFile) throws IOException, URISyntaxException {
        String requestBody = readJsonFile(jsonFile);

        verify(postRequestedFor(urlMatching("/minfraud/v2.0/" + service))
                .withRequestBody(equalToJson(requestBody))
                .withHeader("Content-Type", matching("application/json; charset=UTF-8")));
    }
}
