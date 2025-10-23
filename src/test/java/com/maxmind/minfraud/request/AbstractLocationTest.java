package com.maxmind.minfraud.request;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.maxmind.minfraud.request.AbstractLocation.Builder;
import org.junit.jupiter.api.Test;

public abstract class AbstractLocationTest {
    abstract Builder<?> builder();


    @Test
    public void testFirstName() {
        AbstractLocation loc = this.builder().firstName("frst").build();
        assertEquals("frst", loc.firstName());
    }

    @Test
    public void testLastName() {
        AbstractLocation loc = this.builder().lastName("last").build();
        assertEquals("last", loc.lastName());
    }

    @Test
    public void testCompany() {
        AbstractLocation loc = this.builder().company("company").build();
        assertEquals("company", loc.company());
    }

    @Test
    public void testAddress() {
        AbstractLocation loc = this.builder().address("addr").build();
        assertEquals("addr", loc.address());
    }

    @Test
    public void testAddress2() {
        AbstractLocation loc = this.builder().address2("addr2").build();
        assertEquals("addr2", loc.address2());
    }

    @Test
    public void testCity() {
        AbstractLocation loc = this.builder().city("Pdx").build();
        assertEquals("Pdx", loc.city());
    }

    @Test
    public void testRegion() {
        AbstractLocation loc = this.builder().region("MN").build();
        assertEquals("MN", loc.region());
    }

    @Test
    public void testCountry() {
        AbstractLocation loc = this.builder().country("US").build();
        assertEquals("US", loc.country());
    }

    @Test
    public void testCountryThatIsTooLong() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.builder().country("USA").build()
        );
    }

    @Test
    public void testCountryWithNumbers() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.builder().country("U1").build()
        );
    }

    @Test
    public void testCountryInWrongCase() {
        assertThrows(
            IllegalArgumentException.class,
            () -> this.builder().country("us").build()
        );
    }

    @Test
    public void testPostal() {
        AbstractLocation loc = this.builder().postal("03231").build();
        assertEquals("03231", loc.postal());
    }

    @Test
    public void testPhoneNumber() {
        String phone = "321-321-3213";
        AbstractLocation loc = this.builder().phoneNumber(phone).build();
        assertEquals(phone, loc.phoneNumber());
    }

    @Test
    public void testPhoneCountryCode() {
        AbstractLocation loc = this.builder().phoneCountryCode("1").build();
        assertEquals("1", loc.phoneCountryCode());
    }
}
