# MaxMind minFraud v2.0 Java API

This is an early pre-release version. Don't use it.


## Example

```java

Transaction request = new Transaction.Builder(
        new Device.Builder(InetAddress.getByName("1.1.1.1"))
            .acceptLanguage("en-US")
            .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.90 Safari/537.36")
            .build()
    ).account(
        new Account.Builder()
            .userId("fdasf")
            .username("fdasfaf")
            .build()
    ).billing(
        new Billing.Builder()
            .address("11 fdasf ave.")
            .address2("Apt 1")
            .city("City")
            .company("company")
            .firstName("Frst")
            .lastName("Lst")
            .phoneCountryCode("1")
            .phoneNumber("321-321-3211")
            .postal("23132")
            .region("OR")
            .build()
    ).creditCard(
        new CreditCard.Builder()
            .avsResult('N')
            .bankName("BanK name")
            .bankPhoneCountryCode("1")
            .bankPhoneNumber("313-231-3213")
            .cvvResult('Y')
            .issuerIdNumber("213312")
            .last4Digits("3211")
            .build()
    ).email(
        new Email.Builder()
            .address("fasdf@fasf.com")
            .domain("fadsfd.com")
            .build()
    ).event(
        new Event.Builder()
            .shopId("2432")
            .time(new Date())
            .transactionId("Fdasf")
            .type(Event.Type.ACCOUNT_CREATION)
            .build()
    ).order(
        new Order.Builder()
            .affiliateId("fasdf")
            .amount(new BigDecimal(Double.toString(1.1)))
            .currency("USD")
            .discountCode("fdasf")
            .referrerUri("http://www.bldfa.com/fad")
            .subaffiliateId("fsaf")
            .build()
    ).payment(
        new Payment.Builder()
            .declineCode("dfsa")
            .processor(Payment.Processor.ADYEN)
            .wasAuthorized(true)
            .build()
    ).shipping(
        new Shipping.Builder()
            .region("fa")
            .postal("31331")
            .phoneNumber("313-545-3113")
            .phoneCountryCode("1")
            .deliverySpeed(Shipping.DeliverySpeed.EXPEDITED)
            .address("32 fdas st.")
            .address2("18")
            .city("Fdaf")
            .company("fdasf")
            .firstName("Ffads")
            .lastName("Fdaf")
            .build()
    ).addShoppingCartItem(
        new ShoppingCartItem.Builder()
            .category("fdas")
            .itemId("Fdas")
            .price(1.1)
            .quantity(100)
            .build()
    ).addShoppingCartItem(
        new ShoppingCartItem.Builder()
            .category("hfd")
            .itemId("fde")
            .price(3.)
            .quantity(1)
            .build()
    ).build();

WebServiceClient client = new WebServiceClient.Builder(6, "1234567890").build();


System.out.println(client.insights(request));
```

## Copyright and License

This software is Copyright © 2015- by MaxMind, Inc.

This is free software, licensed under the Apache License, Version 2.0 (the "License");
you may not use this software except in compliance with the License.

You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
