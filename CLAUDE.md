# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build and Test Commands

### Running Tests
```bash
mvn test
```

### Running a Single Test Class
```bash
mvn test -Dtest=WebServiceClientTest
```

### Running a Single Test Method
```bash
mvn test -Dtest=WebServiceClientTest#testFullScoreTransaction
```

### Building the Project
```bash
mvn package
```

### Clean Build
```bash
mvn clean package
```

### Running Checkstyle
```bash
mvn checkstyle:check
```

Checkstyle runs automatically during the `test` phase and enforces Google Java Style conventions.

### Generating Javadocs
```bash
mvn javadoc:javadoc
```

## Architecture Overview

### Core Components

**WebServiceClient** (`src/main/java/com/maxmind/minfraud/WebServiceClient.java`)
- Main entry point for the API
- Provides methods: `score()`, `insights()`, `factors()`, and `reportTransaction()`
- Uses Java's built-in `HttpClient` for HTTP communication
- **Thread-safe** and designed to be reused across multiple requests for connection pooling
- Handles authentication via Basic Auth headers
- Supports configurable timeouts, proxies, and custom `HttpClient` instances

**Request Models** (`src/main/java/com/maxmind/minfraud/request/`)
- All request classes extend `AbstractModel`
- Built using the Builder pattern (e.g., `Transaction.Builder`, `Device.Builder`)
- `Transaction` is the primary request object composed of multiple optional sub-models:
  - `Account`, `Billing`, `CreditCard`, `Device`, `Email`, `Event`, `Order`, `Payment`, `Shipping`
  - `ShoppingCartItem` (can have multiple)
  - `CustomInputs` (for custom key-value pairs)
- All request models serialize to JSON via `toJson()` method
- Immutable after construction

**Response Models** (`src/main/java/com/maxmind/minfraud/response/`)
- Three main response types: `ScoreResponse`, `InsightsResponse`, `FactorsResponse`
- Use Java records (as of version 4.0.0) with deprecated getter methods for backwards compatibility
- Implement `JsonSerializable` interface
- `InsightsResponse` and `FactorsResponse` extend `ScoreResponse` with additional fields
- Response models include GeoIP2 data (this library depends on `com.maxmind.geoip2:geoip2`)

**Exception Hierarchy** (`src/main/java/com/maxmind/minfraud/exception/`)
- `MinFraudException` (base checked exception)
  - `AuthenticationException`
  - `InsufficientFundsException`
  - `InvalidRequestException`
  - `PermissionRequiredException`
- `HttpException` (for unexpected HTTP errors)

**JSON Handling**
- Uses Jackson for serialization/deserialization
- Centralized `Mapper` class provides configured `ObjectMapper` instance
- JSON property names use snake_case (e.g., `risk_score`, `ip_address`)
- `@JsonProperty` annotations map between camelCase Java and snake_case JSON

### Key Design Patterns

1. **Builder Pattern**: All request models use nested Builder classes for object construction
2. **Immutability**: Request models are immutable after construction; response models use records
3. **Composition**: The `Transaction` class composes multiple optional sub-models
4. **Thread Safety**: `WebServiceClient` is thread-safe and should be reused (enables connection pooling)
5. **Empty Object Defaults**: Response models return empty objects instead of null for better API ergonomics

## Java Code Guidelines

### Java Version
This project requires **Java 17+**. Use modern Java features appropriately:
- Records for immutable data models
- `var` for local variables when type is obvious
- Switch expressions
- Text blocks for multi-line strings
- Pattern matching where applicable

### Code Style
The project uses **Google Java Style** with Checkstyle enforcement (see `checkstyle.xml`):
- 4 spaces for indentation (no tabs)
- 100 character line length limit
- Opening braces on same line
- Member names: camelCase starting with lowercase (minimum 2 characters)
- Parameters: camelCase (single letter allowed)
- Constants: UPPER_SNAKE_CASE
- No star imports
- Variables should be declared close to where they're used
- No abbreviations in names except standard ones (e.g., ID, IP, URI, URL, JSON)

Run `mvn checkstyle:check` to verify compliance before committing.

### Javadoc Requirements
- All public classes, methods, and constructors require Javadoc
- Public fields require Javadoc
- Use `@param`, `@return`, `@throws` tags appropriately
- Records should document parameters in the record declaration
- Include examples in Javadoc where helpful

### Record Conventions

#### Alphabetical Parameter Ordering
Record parameters are **always** ordered alphabetically by field name for consistency:

```java
public record ScoreResponse(
    Disposition disposition,      // D
    Double fundsRemaining,        // F
    UUID id,                      // I
    ScoreIpAddress ipAddress,     // I (after "id")
    Integer queriesRemaining,     // Q
    Double riskScore,             // R
    List<Warning> warnings        // W
) implements JsonSerializable {
    // ...
}
```

#### Compact Canonical Constructors
Use compact canonical constructors to set defaults and ensure non-null values:

```java
public record ScoreResponse(...) {
    public ScoreResponse {
        disposition = disposition != null ? disposition : new Disposition();
        ipAddress = ipAddress != null ? ipAddress : new ScoreIpAddress();
        warnings = warnings != null ? List.copyOf(warnings) : List.of();
    }
}
```

This ensures users can safely call methods without null checks: `response.disposition().action()`.

#### Enum Pattern for Response Models

Response model enums use a simple, forward-compatible pattern that gracefully handles unknown values from the server.

**Pattern:**
```java
public enum Status {
    LIVE,
    PARKED,
    DNS_ERROR;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
```

**How it works:**
- Enum constants use `UPPER_SNAKE_CASE` (e.g., `DNS_ERROR`, `ISP_EMAIL`)
- Override `toString()` to return `name().toLowerCase()` for JSON serialization
- The `Mapper` class configures Jackson with:
  - `READ_ENUMS_USING_TO_STRING` - Deserializes using `toString()`
  - `READ_UNKNOWN_ENUM_VALUES_AS_NULL` - Unknown values become `null` instead of throwing exceptions

**Example:**
```java
// Serialization: DNS_ERROR → "dns_error"
Status status = Status.DNS_ERROR;
System.out.println(status);  // "dns_error"

// Deserialization: "dns_error" → DNS_ERROR
// Unknown: "future_value" → null (no exception!)
```

**When to use:**
- Use enums for response fields with fixed/enumerated values
- Use String for open-ended text fields
- Request enums use the same pattern but don't need forward compatibility concerns

### Deprecation Strategy

**Do NOT add deprecated getter methods for new fields.** Deprecated getters only exist for backward compatibility with fields that had JavaBeans-style getters before the record migration in version 4.0.0.

When deprecating existing fields:

```java
public record Response(
    @Deprecated(since = "4.x.0", forRemoval = true)
    @JsonProperty("old_field")
    String oldField,

    @JsonProperty("new_field")
    String newField
) {
    // The record accessor oldField() is automatically marked as deprecated

    // Keep the old deprecated getter ONLY if it existed before v4.0.0
    @Deprecated(since = "4.x.0", forRemoval = true)
    public String getOldField() {
        return oldField();
    }
}
```

Include helpful deprecation messages in JavaDoc pointing to alternatives.

### Avoiding Breaking Changes in Minor Versions

When adding a new field to a record during a **minor version release** (e.g., 4.1.0 → 4.2.0), you must maintain backward compatibility for code that constructs records directly.

**The Problem:** Adding a field to a record changes the canonical constructor signature, breaking existing code.

**The Solution:** Add a deprecated constructor matching the old signature:

```java
public record Email(
    @JsonProperty("address")
    String address,

    @JsonProperty("domain")
    String domain,

    // NEW FIELD added in minor version 4.2.0 (inserted alphabetically between "domain" and "first_seen")
    @JsonProperty("domain_last_seen")
    LocalDate domainLastSeen,

    @JsonProperty("first_seen")
    LocalDate firstSeen
) {
    // Updated default constructor with new field
    public Email() {
        this(null, null, null, null);
    }

    // Deprecated constructor maintaining old signature for backward compatibility
    @Deprecated(since = "4.2.0", forRemoval = true)
    public Email(
        String address,
        String domain,
        LocalDate firstSeen
    ) {
        // Call new constructor with null for the new field (in alphabetical position)
        this(address, domain, null, firstSeen);
    }
}
```

**For Major Versions (e.g., 4.x → 5.0):** Skip the deprecated constructor—breaking changes are expected.

Update `CHANGELOG.md` when adding fields:
```markdown
## 4.2.0 (2024-xx-xx)

* A new `domainLastSeen` field has been added to the `Email` response object...
```

### Testing Conventions
- Tests use JUnit 5 (Jupiter)
- Tests use WireMock for HTTP mocking
- Test methods should have descriptive names starting with `test`
- Use static imports for assertions and matchers
- Full request/response examples in test resources: `src/test/resources/`
- Update test JSON fixtures when adding response fields
- Verify proper serialization/deserialization in tests

### Multi-threaded Safety

Both request and response classes are immutable and thread-safe. `WebServiceClient` is explicitly designed to be thread-safe and should be reused:

```java
// Good: Create once, share across threads
WebServiceClient client = new WebServiceClient.Builder(accountId, licenseKey).build();

// Use in multiple threads
executor.submit(() -> client.score(transaction1));
executor.submit(() -> client.score(transaction2));
```

Reusing the client enables connection pooling and improves performance.

## Working with This Codebase

### Adding New Request Fields

1. Add the field to the appropriate request class in `request/` package
2. Follow the Builder pattern used by other request classes:
   ```java
   public Builder fieldName(Type val) {
       fieldName = val;
       return this;
   }
   ```
3. Add proper Javadoc and `@JsonProperty` annotation with snake_case name
4. Add validation in the builder if needed (e.g., throw `IllegalArgumentException`)
5. Update corresponding test class in `src/test/java/`
6. Add test JSON examples in `src/test/resources/`

### Adding New Response Fields

1. **Determine alphabetical position** for the new field
2. **Add to the record parameters** with `@JsonProperty`:
   ```java
   @JsonProperty("field_name")
   Type fieldName,
   ```
3. **For minor version releases**: Add a deprecated constructor matching the old signature (see "Avoiding Breaking Changes")
4. **Handle null values** in the compact canonical constructor if needed
5. **Do NOT add a deprecated getter** for the new field
6. **Add JavaDoc** describing the field
7. **Update test fixtures** (`src/test/resources/`) with example data
8. **Add test assertions** to verify proper deserialization
9. **Update CHANGELOG.md**

### Adding a New Response Record

When creating an entirely new record class in `response/`:

1. Use Java record syntax
2. Alphabetize parameters by field name
3. Add `@JsonProperty` annotations for all fields
4. Implement `JsonSerializable` interface
5. Add a compact canonical constructor to set defaults for null values
6. Provide comprehensive JavaDoc for all parameters
7. **Do NOT add deprecated getters** (only needed for legacy compatibility)

### Debugging HTTP Issues
- WireMock runs on dynamic ports in tests (`@RegisterExtension` with `dynamicPort()`)
- Check `WebServiceClient` for HTTP request construction
- Response body must be fully consumed even on errors (see `exhaustBody()` method)
- Error responses (4xx) include `code` and `error` fields in JSON body
- Use `verifyRequestFor()` helper in tests to check sent JSON

## Common Pitfalls and Solutions

### Problem: Breaking Changes in Minor Versions
Adding a new field to a record changes the canonical constructor signature, breaking existing code.

**Solution**: For minor version releases, add a deprecated constructor that maintains the old signature. See "Avoiding Breaking Changes in Minor Versions" section for details.

### Problem: Record Constructor Ambiguity
When you have two constructors with similar signatures, you may get "ambiguous constructor" errors.

**Solution**: Cast `null` parameters to their specific type:
```java
this((String) null, (LocalDate) null, (Integer) null);
```

### Problem: Test Failures After Adding New Fields
After adding new fields to a response model, tests fail with deserialization errors.

**Solution**: Update **all** related test fixtures:
1. Test JSON files (e.g., `score-response.json`, `insights-response.json`)
2. In-line JSON in test classes
3. Test assertions in `*ResponseTest.java` files

### Problem: Checkstyle Failures
Code style violations prevent merging.

**Solution**: Run `mvn checkstyle:check` regularly during development. Common issues:
- Lines exceeding 100 characters
- Missing Javadoc on public methods
- Incorrect indentation (use 4 spaces)
- Star imports

## Useful Patterns

### Pattern: Compact Canonical Constructor
Use to set defaults and ensure non-null values:

```java
public record InsightsResponse(...) {
    public InsightsResponse {
        // Ensure non-null with empty defaults
        disposition = disposition != null ? disposition : new Disposition();
        ipAddress = ipAddress != null ? ipAddress : new IpAddress();
        warnings = warnings != null ? List.copyOf(warnings) : List.of();
    }
}
```

### Pattern: Empty Object Defaults
Return empty objects instead of null for better API ergonomics:

```java
// Users can safely call without null checks
String action = response.disposition().action();  // Works even if disposition is "empty"
```

### Pattern: JsonSerializable Interface
All models implement `JsonSerializable` for consistent JSON output:

```java
public interface JsonSerializable {
    default String toJson() throws IOException {
        return Mapper.get().writeValueAsString(this);
    }
}
```

Usage:
```java
InsightsResponse response = client.insights(transaction);
String json = response.toJson();  // Pretty-printed JSON output
```

## Dependencies

The project depends on:
- **Jackson** (core, databind, annotations, datatype-jsr310): JSON handling
- **GeoIP2 Java API**: Sibling library providing GeoIP2 models used in responses
- **JUnit 5** (Jupiter): Testing framework
- **WireMock**: HTTP mocking for tests
- **jsonassert**: JSON comparison in tests

When updating dependencies, test thoroughly as noted in `README.dev.md`.

## Release Process

See `README.dev.md` for detailed release instructions. Key points:
- Releases require GPG signing
- Requires access to Central Portal (formerly Sonatype)
- Release script is `./dev-bin/release.sh`
- Update `CHANGELOG.md` before releasing with version and date
- Review open issues and PRs before releasing
- Bump copyright year in `README.md` if appropriate
