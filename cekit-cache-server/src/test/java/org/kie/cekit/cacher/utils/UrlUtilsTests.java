package org.kie.cekit.cacher.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UrlUtilsTests {


    @Test
    public void urlUtilsIsUrlValidTest() {
        Assertions.assertTrue(UrlUtils.isUrlValid("https://repo1.maven.org/maven2/org/jboss/jandex/2.1.1.Final/jandex-2.1.1.Final-sources.jar"));
        Assertions.assertFalse(UrlUtils.isUrlValid("sds://repo1.maven.org/maven2/org/jboss/jandex/2.1.1.Final/jandex-2.1.1.Final-sources.jar"));
        Assertions.assertFalse(UrlUtils.isUrlValid("/test"));

    }

    @Test
    public void urlUtilsGetFileNameTest() {
        Assertions.assertEquals("jandex-2.1.1.Final-sources.jar", UrlUtils.getFileName("https://repo1.maven.org/maven2/org/jboss/jandex/2.1.1.Final/jandex-2.1.1.Final-sources.jar"));
    }

}
