package com.regpet.api.utils;

import com.regpet.api.models.User;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class TextUtilsTest {

    @Test
    @DisplayName("Should capitalize sent name")
    public void testCapitalizeNameWrongCase() {
        User user = new User();
        user.setName("JoNaTHan");
        String capitalizedName = TextUtils.capitalizeName(user.getName());
        assertNotNull(capitalizedName);
        assertEquals("Jonathan", capitalizedName);
    }

    @Test
    @DisplayName("Should capitalize sent name and surname")
    public void testCapitalizeNameSurnameWrongCase() {
        User user = new User();
        user.setName("WAlTer WhITe");
        String capitalizedName = TextUtils.capitalizeName(user.getName());
        assertNotNull(capitalizedName);
        assertEquals("Walter White", capitalizedName);
    }

    @Test
    @DisplayName("Shouldn't capitalize surname prefix")
    public void testCapitalizeNameSurnamePrefixWrongCase() {
        User user = new User();
        user.setName("DON DiEGo dE LA VeGA");
        String capitalizedName = TextUtils.capitalizeName(user.getName());
        assertNotNull(capitalizedName);
        assertEquals("Don Diego de la Vega", capitalizedName);
    }
}
