package com.example.FixMyTheru;

import com.example.FixMyTheru.Enum.Roles;
import com.example.FixMyTheru.Models.RegisterDetails;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterDetailsTest {

    @Test
    public void shouldSetAndGetBasicRegisterDetailsFields() {
        RegisterDetails user = new RegisterDetails();
        user.setId(5);
        user.setName("Sharan");
        user.setEmail("sharan@mail.com");
        user.setPassword("pass123");
        user.setUsername("sharan007");
        user.setRole("USER");
        user.setAddress("Chennai");

        Assert.assertEquals(user.getId(), 5);
        Assert.assertEquals(user.getName(), "Sharan");
        Assert.assertEquals(user.getEmail(), "sharan@mail.com");
        Assert.assertEquals(user.getPassword(), "pass123");
        Assert.assertEquals(user.getUsername(), "sharan007");
        Assert.assertEquals(user.getRole(), "USER");
        Assert.assertEquals(user.getAddress(), "Chennai");
    }

    @Test
    public void rolesEnumShouldContainExpectedValues() {
        Assert.assertEquals(Roles.USER.name(), "USER");
        Assert.assertEquals(Roles.ADMIN.name(), "ADMIN");
        Assert.assertEquals(Roles.MAINTANENCE.name(), "MAINTANENCE");
    }
}
