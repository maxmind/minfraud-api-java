CHANGELOG
=========

1.12.0
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
