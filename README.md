# MaxMind minFraud Score, Insights, and Factors Java API

## Description ##

This package provides an API for the [MaxMind minFraud Score, Insights, Factors
and Report Transaction web services](https://dev.maxmind.com/minfraud/).

## Installation ##

### Maven ###

We recommend installing this package with [Maven](https://maven.apache.org/).
To do this, add the dependency to your pom.xml:

```xml
    <dependency>
        <groupId>com.maxmind.minfraud</groupId>
        <artifactId>minfraud</artifactId>
        <version>1.18.0</version>
    </dependency>
```

### Gradle ###

Add the following to your `build.gradle` file:

```
repositories {
    mavenCentral()
}
dependencies {
    compile 'com.maxmind.minfraud:minfraud:1.18.0'
}
```

## API Documentation ##

API documentation is viewable on our [GitHub
Page](https://maxmind.github.io/minfraud-api-java/) under the API tab.

## Usage ##

To use this API, first create a new `WebServiceClient` object. The constructor
takes your MaxMind account ID, license key, and an optional options array as
arguments. For example:

```java
WebServiceClient client = new WebServiceClient.Builder(6, "ABCD567890").build();
```

Then create a new `Transaction` object. This represents the transaction that
you are sending to minFraud. The class is instantiated using an inner builder
class, `Transaction.Builder`. Each builder method takes a corresponding
request model object. Each of these objects is similarly built up with
corresponding builder classes. For example:

```java
Transaction transaction = new Transaction.Builder(
        new Device.Builder(InetAddress.getByName("1.1.1.1")).build()
    ).email(
        new Email.Builder()
            .address("fraud@ster.com")
            .build()
    ).build();
```

After creating the transaction object, send a Score request by calling the
`score` method:

```java
ScoreResponse score = client.score(transaction);
```

an Insights request by calling `insights` method:

```java
InsightsResponse insights = client.insights(transaction);
```

a Factors request by calling `factors` method:

```java
FactorsResponse factors = client.factors(transaction);
```

If the request succeeds, a model object will be returned for the endpoint.
If the request fails, an exception will be thrown.

To report a transaction:

```java
TransactionReport transaction = new TransactionReport.Builder(
         InetAddress.getByName("1.1.1.1"), Tag.NOT_FRAUD
     ).build();
client.reportTransaction(transaction);
```

`reportTransaction()` has a `void` return type. If the request fails, an
exception will be thrown.

See the API documentation for more details.

### Exceptions ###

Runtime exceptions:

* `IllegalArgumentException` - This will be thrown when an illegal argument
  is passed to a builder method. For instance, a country code that is not
  two capital letters.

Checked exceptions:

* `AuthenticationException` - This will be thrown when the server is unable to
  authenticate the request, e.g., if the license key or account ID is invalid.
* `InsufficientFundsException` - This will be thrown when your account is out of
  funds.
* `InvalidRequestException` - This will be thrown when the server rejects the
  request for another reason such as invalid JSON in the POST.
* `PermissionRequiredException` - This will be thrown when permission is
  required to use the service.
* `MinFraudException` - This will be thrown when the server returns an
  unexpected response. This also serves as the base class for the above
  checked exceptions.
* `HttpException` -This will be thrown when an unexpected HTTP error
  occurs such as an internal server error or other unexpected status code.

## Examples

### Insights

```java
Transaction request = new Transaction.Builder(
        new Device.Builder(InetAddress.getByName("1.1.1.1"))
            .acceptLanguage("en-US")
            .sessionAge(3600.6)
            .sessionId("foobar")
            .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.90 Safari/537.36")
            .build()
    ).account(
        new Account.Builder()
            .userId("usr-123")
            .username("fraudster9")
            .build()
    ).billing(
        new Billing.Builder()
            .address("11 Wall St.")
            .address2("Apt 1")
            .city("New Haven")
            .company("Company, Inc")
            .firstName("Mike")
            .lastName("Smith")
            .phoneCountryCode("1")
            .phoneNumber("321-321-3211")
            .postal("06510")
            .region("CT")
            .build()
    ).creditCard(
        new CreditCard.Builder()
            .avsResult('N')
            .bankName("BanK of New Haven")
            .bankPhoneCountryCode("1")
            .bankPhoneNumber("313-231-3213")
            .cvvResult('Y')
            .issuerIdNumber("213312")
            .lastDigits("3211")
            .was3dSecureSuccessful(true)
            .build()
    ).email(
        new Email.Builder()
            .address("fraud@ster.com")
            .domain("ster.com")
            .build()
    ).event(
        new Event.Builder()
            .shopId("2432")
            .time(new Date())
            .transactionId("tr1242")
            .type(Event.Type.ACCOUNT_CREATION)
            .build()
    ).order(
        new Order.Builder()
            .affiliateId("af5")
            .amount(new BigDecimal(Double.toString(1.1)))
            .currency("USD")
            .discountCode("10OFF")
            .referrerUri(new URI("https://www.google.com/"))
            .subaffiliateId("saf9")
            .build()
    ).payment(
        new Payment.Builder()
            .declineCode("invalid")
            .processor(Payment.Processor.ADYEN)
            .wasAuthorized(false)
            .build()
    ).shipping(
        new Shipping.Builder()
            .region("MN")
            .postal("55455")
            .phoneNumber("313-545-3113")
            .phoneCountryCode("1")
            .deliverySpeed(Shipping.DeliverySpeed.EXPEDITED)
            .address("32 Washington Ave.")
            .address2("18")
            .city("Minneapolis")
            .company("MinnCo")
            .firstName("John")
            .lastName("Doe")
            .build()
    ).addShoppingCartItem(
        new ShoppingCartItem.Builder()
            .category("TOYS")
            .itemId("t-132")
            .price(1.1)
            .quantity(100)
            .build()
    ).addShoppingCartItem(
        new ShoppingCartItem.Builder()
            .category("COSMETICS")
            .itemId("c-12312")
            .price(3.)
            .quantity(1)
            .build()
    ).customInputs(
        new CustomInputs.Builder()
            .put("float_input", 12.1)
            .put("integer_input", 3123)
            .put("string_input", "This is a string input.")
            .put("boolean_input", true)
            .build()
    ).build();

WebServiceClient client = new WebServiceClient.Builder(6, "ABCD567890").build();

System.out.println(client.insights(request));
```

### Report Transactions API

MaxMind encourages the use of this API, as data received through this channel
is continually used to improve the accuracy of our fraud detection algorithms.

To use the Report Transactions API, create a new `TransactionReport` object. An
IP address and a valid tag are required arguments. Additional params may
also be set, as documented below.

If the report is successful, nothing is returned. If the report fails, an
exception with be thrown.

See the API documentation for more details.

```java
TransactionReport transaction = new TransactionReport.Builder(InetAddress.getByName("1.1.1.1"), Tag.NOT_FRAUD)
    .chargebackCode("mycode")
    .maxmindId("12345678")
    .minfraudId(UUID.fromString("58fa38d8-4b87-458b-a22b-f00eda1aa20d"))
    .notes("notes go here")
    .transactionId("foo")
    .build();

WebServiceClient client = new WebServiceClient.Builder(6, "ABCD567890").build();

client.reportTransaction(transaction);
```

## Support ##

Please report all issues with this code using the
[GitHub issue tracker](https://github.com/maxmind/minfraud-api-java/issues).

If you are having an issue with the minFraud service that is not specific
to the client API, please see
[our support page](https://www.maxmind.com/en/support).

## Requirements ##

This code requires Java 11+.

## Contributing ##

Patches and pull requests are encouraged. Please include unit tests whenever
possible.

## Versioning ##

This API uses [Semantic Versioning](https://semver.org/).

## Copyright and License ##

This software is Copyright (c) 2015-2022 by MaxMind, Inc.

This is free software, licensed under the Apache License, Version 2.0.
