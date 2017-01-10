CHANGELOG
=========

1.4.0
------------------

* Added support for custom inputs. These can be set up from your account portal.
* Updated the docs for `com.maxmind.minfraud.response.AbstractAddress` now
  that `isPostalInCity` may be returned for addresses world-wide.

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
