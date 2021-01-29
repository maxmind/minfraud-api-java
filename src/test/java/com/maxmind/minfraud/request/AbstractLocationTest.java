package com.maxmind.minfraud.request;

import com.maxmind.minfraud.request.AbstractLocation.Builder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public abstract class AbstractLocationTest {
    abstract Builder builder();


    @Test
    public void testFirstName() {
        AbstractLocation loc = this.builder().firstName("frst").build();
        assertEquals("frst", loc.getFirstName());
    }

    @Test
    public void testLastName() {
        AbstractLocation loc = this.builder().lastName("last").build();
        assertEquals("last", loc.getLastName());
    }

    @Test
    public void testCompany() {
        AbstractLocation loc = this.builder().company("company").build();
        assertEquals("company", loc.getCompany());
    }

    @Test
    public void testAddress() {
        AbstractLocation loc = this.builder().address("addr").build();
        assertEquals("addr", loc.getAddress());
    }

    @Test
    public void testAddress2() {
        AbstractLocation loc = this.builder().address2("addr2").build();
        assertEquals("addr2", loc.getAddress2());
    }

    @Test
    public void testCity() {
        AbstractLocation loc = this.builder().city("Pdx").build();
        assertEquals("Pdx", loc.getCity());
    }

    @Test
    public void testRegion() {
        AbstractLocation loc = this.builder().region("MN").build();
        assertEquals("MN", loc.getRegion());
    }

    @Test
    public void testCountry() {
        AbstractLocation loc = this.builder().country("US").build();
        assertEquals("US", loc.getCountry());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCountryThatIsTooLong() {
        this.builder().country("USA").build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCountryWithNumbers() {
        this.builder().country("U1").build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCountryInWrongCase() {
        this.builder().country("us").build();
    }

    @Test
    public void testPostal() {
        AbstractLocation loc = this.builder().postal("03231").build();
        assertEquals("03231", loc.getPostal());
    }

    @Test
    public void testPhoneNumber() {
        String phone = "321-321-3213";
        AbstractLocation loc = this.builder().phoneNumber(phone).build();
        assertEquals(phone, loc.getPhoneNumber());
    }

    @Test
    public void testPhoneCountryCode() {
        AbstractLocation loc = this.builder().phoneCountryCode("1").build();
        assertEquals("1", loc.getPhoneCountryCode());
    }
}
