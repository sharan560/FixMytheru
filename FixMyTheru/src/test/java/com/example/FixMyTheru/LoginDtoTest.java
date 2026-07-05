package com.example.FixMyTheru;

import com.example.FixMyTheru.Dto.LoginDto;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginDtoTest {

    @Test
    public void shouldSetAndGetLoginDtoValues() {
        LoginDto dto = new LoginDto();
        dto.setUsername("sharan");
        dto.setToken("token-123");
        dto.setRole("USER");
        dto.setId(1);

        Assert.assertEquals(dto.getUsername(), "sharan");
        Assert.assertEquals(dto.getToken(), "token-123");
        Assert.assertEquals(dto.getRole(), "USER");
        Assert.assertEquals(dto.getId(), 1);
    }
}
