package com.maxmind.minfraud.input;

import com.maxmind.minfraud.exception.InvalidInputException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public abstract class AbstractLocationTest {
    abstract AbstractLocation.Builder builder();


    @Test
    public void testFirstName() throws Exception {
        AbstractLocation loc = builder().firstName("frst").build();
        assertEquals("frst", loc.getFirstName());
    }

    @Test
    public void testLastName() throws Exception {
        AbstractLocation loc = builder().lastName("last").build();
        assertEquals("last", loc.getLastName());
    }

    @Test
    public void testCompany() throws Exception {
        AbstractLocation loc = builder().company("company").build();
        assertEquals("company", loc.getCompany());
    }

    @Test
    public void testAddress() throws Exception {
        AbstractLocation loc = builder().address("addr").build();
        assertEquals("addr", loc.getAddress());
    }


    @Test
    public void testAddress2() throws Exception {
        AbstractLocation loc = builder().address2("addr2").build();
        assertEquals("addr2", loc.getAddress2());
    }

    @Test
    public void testCity() throws Exception {
        AbstractLocation loc = builder().city("Pdx").build();
        assertEquals("Pdx", loc.getCity());
    }

    @Test
    public void testRegion() throws Exception {
        AbstractLocation loc = builder().region("MN").build();
        assertEquals("MN", loc.getRegion());
    }

    @Test
    public void testCountry() throws Exception {
        AbstractLocation loc = builder().country("US").build();
        assertEquals("US", loc.getCountry());
    }

    @Test( expected = InvalidInputException.class )
    public void testCountryThatIsTooLong() throws Exception {
        AbstractLocation loc = builder().country("USA").build();
    }

    @Test( expected = InvalidInputException.class )
    public void testCountryWithNumbers() throws Exception {
        AbstractLocation loc = builder().country("U1").build();
    }

    @Test( expected = InvalidInputException.class )
    public void testCountryInWrongCase() throws Exception {
        AbstractLocation loc = builder().country("us").build();
    }

    @Test
    public void testPostal() throws Exception {
        AbstractLocation loc = builder().postal("03231").build();
        assertEquals("03231", loc.getPostal());
    }

    @Test
    public void testPhoneNumber() throws Exception {
        String phone = "321-321-3213";
        AbstractLocation loc = builder().phoneNumber(phone).build();
        assertEquals(phone, loc.getPhoneNumber());
    }

    @Test
    public void testPhoneCountryCode() throws Exception {
        AbstractLocation loc = builder().phoneCountryCode("1").build();
        assertEquals("1", loc.getPhoneCountryCode());
    }
}
