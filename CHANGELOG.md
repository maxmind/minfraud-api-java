CHANGELOG
=========

4.1.0 (2026-01-20)
------------------

* Added `anonymizer` property to `IpAddress` response model. This contains
  information about whether the IP address is an anonymous network, including
  confidence score, VPN provider name, and various anonymizer flags.
* Added `BANQUEST`, `SUMMIT_PAYMENTS`, and `YAADPAY` to the
  `Payment.Processor` enum.

4.0.0 (2025-11-20)
------------------

* BREAKING: Removed deprecated `TransactionReport.Builder(InetAddress, Tag)`
  constructor. Use `Builder(Tag)` and `ipAddress(InetAddress)` instead.
* BREAKING: Removed deprecated `getUrl()` methods from `HttpException` and
  `InvalidRequestException`. Use `uri()` instead.
* BREAKING: Removed deprecated constructors from `FactorsResponse`,
  `InsightsResponse`, and `Phone` classes.
* BREAKING: Removed deprecated `Subscores` class and
  `FactorsResponse.getSubscores()` method. Use `getRiskScoreReasons()`
  instead.
* BREAKING: Java 17 is now required (previously Java 11).
* BREAKING: Updated `geoip2` dependency to 5.0.0-SNAPSHOT. This introduces
  several breaking changes due to changes in the geoip2 library:
  * The `IpAddress` class no longer extends `InsightsResponse` from geoip2.
    It now uses composition instead. All public methods remain the same, but
    code relying on `IpAddress` being an `InsightsResponse` will need to be
    updated.
  * The `GeoIp2Location` class no longer extends `Location` from geoip2. It
    now stores location data directly. All public methods remain the same, but
    code relying on `GeoIp2Location` being a `Location` will need to be
    updated.
  * Removed the `getMaxMind()` method from the `IpAddress` class as this data
    is not populated in minFraud responses.
* BREAKING: Converted all response classes to Java records. This change makes
  these classes more concise and provides better immutability guarantees.
  * All `get*()` accessor methods in response classes are now deprecated and
    will be removed in 5.0.0. Use the automatically generated record accessor
    methods instead (e.g., use `riskScore()` instead of `getRiskScore()`).
  * The response class hierarchy has been flattened. `InsightsResponse` no
    longer extends `ScoreResponse`, and `FactorsResponse` no longer extends
    `InsightsResponse`. Instead, `InsightsResponse` and `FactorsResponse` now
    include all fields from their former parent classes directly.
  * All response classes now implement `JsonSerializable` instead of extending
    `AbstractModel`. The `toJson()` method remains available for serialization.
  * Removed the `AbstractAddress` interface.
* BREAKING: Updated all request classes to use record-style method naming. The
  `get` prefix has been removed from all accessor methods (e.g., use `userId()`
  instead of `getUserId()`). This applies to all request classes including
  `Account`, `Billing`, `CreditCard`, `CustomInputs`, `Device`, `Email`,
  `Event`, `Order`, `Payment`, `Shipping`, `ShoppingCartItem`, `Transaction`,
  and `TransactionReport`. Unlike response classes, no deprecated helper methods
  were added as these methods are primarily used for serialization.
* BREAKING: Updated exception classes to use record-style method naming. The
  `get` prefix has been removed from all accessor methods. For `HttpException`,
  use `httpStatus()` and `uri()` instead of `getHttpStatus()` and `getUri()`.
  For `InvalidRequestException`, use `code()`, `httpStatus()`, and `uri()`
  instead of `getCode()`, `getHttpStatus()`, and `getUri()`. No deprecated
  helper methods were added.
* Added `CREDIT_APPLICATION`, `FUND_TRANSFER`, and `SIM_SWAP` to the
  `Event.Type` enum.
* Added the input `/event/party`. This is the party submitting the
  transaction. You may provide this using the `party` method on
  `Event.Builder`.
* Added the input `/payment/method`. This is the payment method associated
  with the transaction. You may provide this using the `method` method on
  `Payment.Builder`.
* Added new email domain fields to the `EmailDomain` response model:
  * `classification` - A classification of the email domain. Possible values
    are `BUSINESS`, `EDUCATION`, `GOVERNMENT`, and `ISP_EMAIL`.
  * `risk` - A risk score associated with the email domain, ranging from 0.01
    to 99. Higher scores indicate higher risk.
  * `volume` - The activity on the email domain across the minFraud network,
    expressed in sightings per million. This value ranges from 0.001 to
    1,000,000.
  * `visit` - An `EmailDomainVisit` object containing information about an
    automated visit to the email domain, including:
    * `status` - The status of the domain based on the automated visit.
      Possible values are `LIVE`, `DNS_ERROR`, `NETWORK_ERROR`, `HTTP_ERROR`,
      `PARKED`, and `PRE_DEVELOPMENT`.
    * `lastVisitedOn` - The date when the automated visit was last completed.
    * `hasRedirect` - Whether the domain redirects to another URL.
* Added support for forward-compatible enum deserialization. Enums in response
  models will now return `null` for unknown values instead of throwing an
  exception. This allows the client to handle new enum values added by the
  server without requiring an immediate client update. This required adding
  `READ_ENUMS_USING_TO_STRING` and `READ_UNKNOWN_ENUM_VALUES_AS_NULL` to the
  Jackson `ObjectMapper` configuration.
* Added `SECUREPAY` to the `Payment.Processor` enum.
* `WebServiceClient.Builder` now has an `httpClient()` method to allow
  passing in a custom `HttpClient`.

3.8.0 (2025-06-09)
------------------

* `commons-validator` has been removed as a dependency. This module now does
  its own email and domain name validation. This was done to reduce the number
  of dependencies and any security vulnerabilities in them. The new email
  validation of the local part is somewhat more lax than the previous
  validation.

3.7.2 (2025-05-28)
------------------

* First release using Central Portal instead of Legacy OSSRH.
* Update `geoip2` dependency.

3.7.1 (2025-05-23)
------------------

* Updated `pom.xml` to fix issue with javadoc generation during the release
  process.

3.7.0 (2025-05-23)
------------------

* Added support for the `/billing_phone/matches_postal` and
  `/shipping_phone/matches_postal` outputs. These are available as the
  `matchesPostal` method on `com.maxmind.minfraud.response.Phone`.
* Added `CRYPTOMUS` to the `Payment.Processor` enum.

3.6.0 (2025-02-10)
------------------

* The minFraud Factors subscores have been deprecated. They will be removed
  in March 2025. Please see [our release notes](https://dev.maxmind.com/minfraud/release-notes/2024/#deprecation-of-risk-factor-scoressubscores)
  for more information.
* Added `EPAYCO` to the `Payment.Processor` enum.

3.6.0-beta.1 (2024-09-06)
-------------------------

* Added support for the new risk reasons outputs in minFraud Factors. The risk
  reasons output codes and reasons are currently in beta and are subject to
  change. We recommend that you use these beta outputs with caution and avoid
  relying on them for critical applications.

3.5.0 (2024-07-08)
------------------

* Updated `TransactionReport` to make the `ipAddress` parameter optional. Now
  the `tag` and at least one of the following parameters must be supplied:
  `ipAddress`, `maxmindId`, `minfraudId`, `transactionId`.
* The `TransactionReport.Builder(INetAddress, Tag)` constructor has been
  deprecated in favor of the new `TransactionReport.Builder(Tag)` constructor.
* Added `getBillingPhone` and `getShippingPhone` methods to the minFraud
  Insights and Factors response models. These contain objects with information
  about the respective phone numbers. Please see [our developer
  site](https://dev.maxmind.com/minfraud/api-documentation/responses/) for
  more information.
* Added `PAYCONEX` to the `Payment.Processor` enum.

3.4.0 (2024-04-16)
------------------

* Added `PXP_FINANCIAL` and `TRUSTPAY` to the `Payment.Processor` enum.
* Equivalent domain names are now normalized when `hashAddress` is used.
  For example, `googlemail.com` will become `gmail.com`.
* Periods are now removed from `gmail.com` email address local parts when
  `hashAddress` is used. For example, `f.o.o@gmail.com` will become
  `foo@gmail.com`.
* Fastmail alias subdomain email addresses are now normalized when
  `hashAddress` is used. For example, `alias@user.fastmail.com` will become
  `user@fastmail.com`.
* Additional `yahoo.com` email addresses now have aliases removed from
  their local part when `hashAddress` is used. For example,
  `foo-bar@yahoo.com` will become `foo@yahoo.com` for additional
  `yahoo.com` domains.
* Duplicate `.com`s are now removed from email domain names when
  `hashAddress` is used. For example, `example.com.com` will become
  `example.com`.
* Certain TLD typos are now normalized when `hashAddress` is used. For
  example, `example.comcom` will become `example.com`.
* Additional `gmail.com` domain names with leading digits are now
  normalized when `hashAddress` is used. For example, `100gmail.com` will
  become `gmail.com`.
* Additional `gmail.com` typos are now normalized when `hashAddress` is
  used. For example, `gmali.com` will become `gmail.com`.
* When `hashAddress` is used, all trailing periods are now removed from an
  email address domain. Previously only a single period was removed.
* When `hashAddress` is used, the local part of an email address is now
  normalized to NFC.

3.3.0 (2023-12-05)
------------------

* Updated `geoip2` dependency to version that includes the `isAnycast` method
  on `com.maxmind.geoip2.record.Traits`. This returns `true` if the IP
  address belongs to an [anycast network](https://en.wikipedia.org/wiki/Anycast).
  This is available in minFraud Insights and Factors.

3.2.0 (2023-10-27)
------------------

* Updated `geoip2` to 4.1.0 to prevent exception when deserializing the
  new `Satellite` value to the `ConnectionType` enum. Pull request by
  Neal Zhu. GitHub #278.
* Jackson was updated to 2.15.2.

3.1.0 (2023-03-02)
------------------

* `com.maxmind.geoip2` is now a transitive dependency of this Java module.
* Added `PLACETOPAY` to the `Payment.Processor` enum.

3.0.0 (2022-12-12)
------------------

* This library is now a Java module.
* Removed deprecated `last4Digits` method on `CreditCard.Builder` and
  `getLast4Digits` method on `CreditCard`. Use `lastDigits` and
  `getLastDigits` instead.
* Removed deprecated `connectTimeout(int)`, `readTimeout(int)`, and
  `proxy(Proxy)` on `WebServiceClient.Builder`. Use
  `connectTimeout(Duration)`, `requestTimeout(Duration)`, and
  `proxy(ProxySelector)` instead.
* `WebServiceClient` no longer implements `Closeable` and the `Close`
  method has been removed. This method was deprecated when the library
  switched to using `java.net.http.HttpClient`.

2.2.0 (2022-10-31)
------------------

* Added `GOOGLE_PAY` and `SHOPIFY_PAYMENTS` to the `Payment.Processor` enum.
* Updated Jackson and GeoIP2 dependencies.

2.1.0 (2022-03-28)
------------------

* Added the input `/credit_card/country`. This is the country where the
  issuer of the card is located. This may be passed instead of the
  `/credit_card/issuer_id_number` if you do not wish to pass partial
  account numbers or if your payment processor does not provide them. You
  may provide this using the `country` method on `CreditCard.Builder`.

2.0.0 (2022-01-24)
------------------

* Java 11 or greater is now required.
* Apache HttpClient has been replaced with `java.net.http.HttpClient`.
* The `close()` method on `WebServiceClient` is now deprecated. It
  no longer does anything.
* On `WebServiceClient.Builder`:
  * `connectTimeout(int)` has been deprecated in favor of
    `connectTimeout(Duration)`.
  * `readTimeout(int)` has been deprecated in favor of
    `requestTimeout(Duration)`.
  * `proxy(Proxy)` has been deprecated in favor of `proxy(ProxySelector)`.
* On `HttpException` and `InvalidRequestException`, `getUrl()` has been
  deprecated in favor of `getUri()`. Constructors that took a `URL` have
  been replaced with the equivalent taking a `URI`.
* Deprecated constructors on model classes were removed.
* Removed deprecated response methods:
  * `Email.getAddressMd5()`
  * `Subscores.getEmailTenure()`
  * `Subscores.getIpTenure()`
* Removed `GeoIp2Country` and its associated `isHighRisk()` method.
  `IpAddress.getCountry()` now returns a `com.maxmind.geoip2.record.Country`.
* Removed deprecated `Payment.Processor.VERAPAY` enum value. Use `VEREPAY`
  instead.
* `Email.getDomain()` will now return an empty object rather than null if
  there is no domain data. This is match other response model class getters.
* Upgraded the `geoip2` dependency to 2.16.1. This adds mobile country code
  (MCC) and mobile network code (MNC) to minFraud Insights and Factors
  responses. These are available at
  `response.getIpAddress.getTraits.getMobileCountryCode()` and
  `response.getIpAddress.getTraits.getMobileNetworkCode()`. We expect this
  data to be available by late January 2022.
* The following payment processors were added to the `Payment.Processor` enum:
  * `BOACOMPRA`
  * `BOKU`
  * `COREGATEWAY`
  * `FISERV`
  * `NEOPAY`
  * `NEOSURF`
  * `OPENBUCKS`
  * `PAYSERA`
  * `PAYVISION`
  * `TRUSTLY`
  * `WINDCAVE`
* `com.maxmind.minfraud.request.CreditCard.last4Digits` has been deprecated in
  favor of `lastDigits` and will be removed in a future release. `lastDigits`
  / `last4Digits` also now supports two digit values in addition to the
  previous four digit values.
* Eight digit `com.maxmind.minfraud.request.CreditCard.issuerIdNumber` inputs are
  now supported in addition to the previously accepted six digit `issuerIdNumber`.
  In most cases, you should send the last four digits for
  `com.maxmind.minfraud.request.CreditCard.last4Digits`. If you send a
  `issuerIdNumber` that contains an eight digit IIN, and if the credit card brand
  is not one of the following, you should send the last two digits for
  `lastDigits`:
  * `Discover`
  * `JCB`
  * `Mastercard`
  * `UnionPay`
  * `Visa`
* Apache Commons Codec is no longer used for generating MD5s.

1.18.0 (2021-08-31)
-------------------

* The following payment processors were added to the `Payment.Processor` enum:
  * `CARDKNOX`
  * `CREDITGUARD`
  * `CREDORAX`
  * `DATACAP`
  * `DLOCAL`
  * `ONPAY`
  * `SAFECHARGE`
* Documented the new `test` disposition action.
* Added support for the `/disposition/rule_label` output in Score, Insights and
  Factors. This is available at `response.getDisposition().getRuleLabel()`, and
  is the label of the custom rule that was triggered by the transaction.
* Added support for the `/credit_card/was_3d_secure_successful` input in Score,
  Insights and Factors. This input should indicate whether or not the outcome of
  3D-Secure verification (e.g. Safekey, SecureCode, Verified by Visa) was
  successful. `true` if customer verification was successful, or `false` if the
  customer failed verification. If 3-D Secure verification was not used, was
  unavailable, or resulted in another outcome other than success or failure, do
  not include this field. Use the `was3dSecureSuccessful(Boolean)` method on
  `com.maxmind.minfraud.request.CreditCard.Builder` to set it.

1.17.0 (2021-02-02)
-------------------

* The following payment processors were added to the `Payment.Processor` enum:
  * `APPLE_PAY`
  * `APS_PAYMENTS`
* Added additional normalizing of the email address if `hashAddress` is
  enabled.
* Added support for the IP address risk reasons in the minFraud Insights and
  Factors responses. This is available at `response.getIpAddress().getRiskReasons()`.
  It is a list of `IpRiskReason` objects.

1.16.0 (2020-10-14)
-------------------

* The HTTP client now allows up to 20 connections to be active at once.
  Previously the limit was 2. Reported by mjancewicz. GitHub #110.
* `TSYS` was added to the `Payment.Processor` enum.
* The device IP address is no longer a required input.

1.15.0 (2020-07-09)
-------------------

* The following payment processors were added to the `Payment.Processor` enum:
  * `CASHFREE`
  * `FIRST_ATLANTIC_COMMERCE`
  * `KOMOJU`
  * `PAYTM`
  * `RAZORPAY`
  * `SYSTEMPAY`
* Added support for three new Factors outputs: `/subscores/device` (the risk
  associated with the device), `/subscores/email_local_part` (the risk
  associated with the part of the email address before the @ symbol) and
  `/subscores/shipping_address` (the risk associated with the shipping
  address).

1.14.0 (2020-06-10)
-------------------

* Added support for Report Transaction API. Report Transaction requests can be
  created via `TransactionReport.Builder()`.
* Updated Jackson dependencies to 2.11.0.

1.13.0 (2020-04-06)
-------------------

* Added support for the new credit card output `/credit_card/is_business`.
  This indicates whether the card is a business card. It may be accessed via
  `response.getCreditCard().isBusiness()` on the minFraud Insights and Factors
  response objects.

1.12.0 (2020-03-26)
-------------------

* Added support for the new email output `/email/domain/first_seen` This can
  be accessed via `response.getEmail().getDomain().getFirstSeen()`.
* Added `Device.getLastSeenDateTime()`, `Device.getLocalDateTime()`,
  `Email.getFirstSeenDate()`, and `GeoIp2Location.getLocalDateTime()` methods
  that return `java.time` objects rather than strings.
* The request event time is now stored internally as a `ZonedDateTime`. An
  `Event.Builder.time(ZonedDateTime)` method was also added to the event
  builder.
* The following payment processors were added to the `Payment.Processor` enum:
  * `CARDPAY`
  * `EPX`

1.11.0 (2020-02-21)
-------------------

* Added support for the new email output `/email/is_disposable`. This can
  be accessed via the `isDisposable()` method on the `Email` response
  object.

1.10.0 (2019-12-19)
-------------------

* IMPORTANT: Java 8 is now required. If you need Java 7 support, please
  continue using 1.9.0.
* Added constructor to `com.maxmind.minfraud.request.Email.Builder` that
  allows validation to be disabled.
* The client-side validation for numeric custom inputs has been updated to
  match the server-side validation. The valid range is -9,999,999,999,999
  to 9,999,999,999,999. Previously, larger numbers were allowed.
* Responses with chunked encoding are now handled correctly.
* The following payment processors were added to the `Payment.Processor` enum:
  * `AFFIRM`
  * `AFTERPAY`
  * `CETELEM`
  * `DATACASH`
  * `DOTPAY`
  * `ECOMMPAY`
  * `G2A_PAY`
  * `GOCARDLESS`
  * `INTERAC`
  * `KLARNA`
  * `MERCANET`
  * `PAYEEZY`
  * `PAYLIKE`
  * `PAYMENT_EXPRESS`
  * `PAYSAFECARD`
  * `SMARTDEBIT`
  * `SYNAPSEFI`
  * `VEREPAY`
* Deprecated `VERAPAY` in the `Payment.Processor` enum. This was a misspelling
  of `VEREPAY`.
* Deprecated `getEmailTenure()` and `getIpTenure()` methods of
  `com.maxmind.minfraud.response.Subscores`.
* Deprecated the `isHighRisk()` method of `com.maxmind.minfraud.response.GeoIP2Country`.

1.9.0 (2018-04-11)
------------------

* Renamed MaxMind user ID to account ID in the code and added support for the
  new `ACCOUNT_ID_REQUIRED` error code.
* The following payment processors were added to the `Payment.Processor` enum:
  * `CCAVENUE`
  * `CT_PAYMENTS`
  * `DALENYS`
  * `ONEY`
  * `POSCONNECT`
* Added new type to the `Event.Type` enum: `PAYOUT_CHANGE`
* Added support for new Device output:
  * `/device/local_time`
* Added support for new CreditCard output:
  * `/credit_card/is_virtual`

1.8.0 (2018-01-19)
------------------

* Updated `geoip2` dependency. This version adds the `isInEuropeanUnion()`
  method to `com.maxmind.geoip2.record.Country` and
  `com.maxmind.minfraud.response.GeoIp2Country`. This returns `true` if the
  country is a member state of the European Union.
* The web service client now correctly handles a proxy of `Proxy.NO_PROXY`.
  PR by Ernest Sadykov. GitHub #32.
* The following payment processors were added to the `Payment.Processor` enum:
  * `CYBERSOURCE`
  * `TRANSACT_PRO`
  * `WIRECARD`

1.7.0 (2017-10-30)
------------------

* The following payment processors were added to the `Payment.Processor` enum:
  * `BPOINT`
  * `CHECKOUT_COM`
  * `EMERCHANTPAY`
  * `HEARTLAND`
  * `PAYWAY`
* Updated `geoip2` dependency to add support for GeoIP2 Precision
  Insights anonymizer fields.
* Replaced use of deprecated `com.fasterxml.jackson.databind.util.ISO8601DateFormat`
  with `com.fasterxml.jackson.databind.util.StdDateFormat` where
  `withColonInTimeZone` is set to `true`.

1.6.0 (2017-09-08)
------------------

* Behavior change! Default to sending the plain text email address rather
  than its MD5 hash. Previously only the MD5 hash of the email address
  would be sent, and sending the plain text email address was not possible.
  If you wish to send only the MD5 hash of the email address, you must now
  call `hashAddress()` on the `Email` builder in addition to `address()`.
* When sending a hashed email address, the address is now lower-cased
  before the MD5 is calculated.
* Update Jackson and WireMock dependencies.

1.5.1 (2017-08-14)
------------------

* Corrected serialization of `/event/time` input to be valid RFC 3339. Reported by
  Luis Rojas. GitHub #26.

1.5.0 (2017-07-07)
------------------

* Added support for custom inputs. These can be set up from your account portal.
* Added support for new Device inputs. These are:
  * `/device/session_age`
  * `/device/session_id`
* Added support for new Email outputs. These are:
  * `/email/first_seen`
* The following payment processors were added to the `Payment.Processor` enum:
  * `AMERICAN_EXPRESS_PAYMENT_GATEWAY`
  * `BLUESNAP`
  * `COMMDOO`
  * `CUROPAYMENTS`
  * `EXACT`
  * `OCEANPAYMENT`
  * `PAYMENTWALL`
  * `PAYZA`
  * `SECURETRADING`
  * `SOLIDTRUST_PAY`
  * `VANTIV`
  * `VERICHECK`
  * `VPOS`

1.4.0 (2017-02-22)
------------------

* Added the following new values to the `Payment.Processor` enum:
  `EBS`, `HIPAY`, and `LEMON_WAY`.
* Updated the docs for `com.maxmind.minfraud.response.AbstractAddress` now
  that `isPostalInCity` may be returned for addresses world-wide.
* Updated dependencies.

1.3.0 (2016-11-21)
------------------

* The disposition was added to the minFraud response models. This is used to
  return the disposition of the transaction as set by the custom rules for the
  account.

1.2.0 (2016-11-11)
------------------

* Added `/credit_card/token` input. Use the `token(String)` method on
  `com.maxmind.minfraud.request.CreditCard.Builder` to set it.
* All validation regular expressions are now pre-compiled.

1.1.1 (2016-10-12)
------------------

* Non-ASCII characters are now correctly encoded as UTF-8 in the request body.
  Reported by Julien Guery. GitHub #17.

1.1.0 (2016-10-10)
------------------

* Added two new types to the `Event.Type` enum: `EMAIL_CHANGE` and
  `PASSWORD_RESET`.
* Update Jackson and WireMock dependencies.

1.0.0 (2016-09-16)
------------------

* First production release.
* Connections will now be reused between requests made with the same
  `WebServiceClient` object.
* `WebServiceClient` now implements `Closeable`.
* You are now able to set a proxy to use via the `WebServiceClient.Builder`
  `proxy(Proxy)` method.
* Updated dependencies.
* Added the following new values to the `Payment.Processor` enum:
  `CONCEPT_PAYMENTS`, `ECOMM365`, `ORANGEPAY`, and `PACNET_SERVICES`.

0.5.0 (2016-06-08)
------------------

* BREAKING CHANGE: `getCreditsRemaining()` has been removed from the web
  service models and has been replaced by `getQueriesRemaining()`.
* Added `getQueriesRemaining()` and `getFundsRemaining()`. Note that
  `getFundsRemaining()` will not be returned by the web service until our new
  credit system is in place.
* Added `getLastSeen()` and `getConfidence()` to the `Device` response model.
* This API now throws an `IllegalArgumentException` when `null` values are
  passed to constructors or methods that require non-null values.

0.4.0 (2016-05-23)
------------------

* Added support for the minFraud Factors.
* Added IP address risk to the minFraud Score model.
* Handle `PERMISSION_REQUIRED` errors by throwing a
  `PermissionRequiredException`.
* Updated dependency.
* Added the following new values to the `Payment.Processor` enum:
  `CCNOW`, `DALPAY`, `EPAY` (replaces `EPAYEU`), `PAYPLUS`, `PINPAYMENTS`,
  `QUICKPAY`, and `VERAPAY

0.3.0 (2016-01-22)
------------------

* Added support for new minFraud Insights outputs. These are:
  * `/credit_card/brand`
  * `/credit_card/type`
  * `/device/id`
  * `/email/is_free`
  * `/email/is_high_risk`
* The `Warning.getInput()` method has been replaced by
  `Warning.getInputPointer()`, which returns a JSON Pointer rather than array.
* The `ScoreResponse.getId()` and `InsightsResponse.getId()` methods now
  return `UUID` objects instead of strings.

0.2.0 (2016-01-15)
------------------

* Added support for `is_gift` and `has_gift_message` to the order input
  object.
* Jackson now uses the constructors to deserialize to the response model
  classes rather than overriding the access modifiers on them.
* A null pointer bug was fixed in `getInput()` of the `Warning` model when
  the `input` array is not present in the JSON object.
* Update GeoIP2 and other dependencies.

0.1.0 (2015-06-29)
------------------

* First beta release.

0.0.2 (2015-06-17)
------------------

* Add org.sonatype.oss to the Maven configuration.

0.0.1 (2015-06-17)
------------------

* Initial release
