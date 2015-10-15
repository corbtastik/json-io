package cworks.json;

import cworks.json.JsonJavaUtils;
import org.junit.Test;
import org.junit.Assert;

public class JsonJavaUtilsTest {

    @Test
    public void testSame() {

        Assert.assertEquals("firstName", JsonJavaUtils.toMethodName("firstName"));
    }

    @Test
    public void testUpperCamelCase() {

        Assert.assertEquals("firstName", JsonJavaUtils.toMethodName("FirstName"));
    }

    @Test
    public void testUpperCamelCaseWithUnderscore() {

        Assert.assertEquals("firstName", JsonJavaUtils.toMethodName("First_Name"));
    }

    @Test
    public void testAllUpperCase() {

        Assert.assertEquals("FIRSTNAME", JsonJavaUtils.toMethodName("FIRSTNAME"));
    }

    @Test
    public void testUppercaseUnderscoreField() {

        Assert.assertEquals("firstName", JsonJavaUtils.toMethodName("FIRST_NAME"));
    }

    @Test
    public void testUppercasePreUnderscoreField() {

        Assert.assertEquals("firstName", JsonJavaUtils.toMethodName("_FIRST_NAME"));
    }

    @Test
    public void testUppercasePreMultipleUnderscoreField() {

        Assert.assertEquals("firstName", JsonJavaUtils.toMethodName("___FIRST_NAME"));
    }

    @Test
    public void testUppercasePostUnderscoreField() {

        Assert.assertEquals("firstName", JsonJavaUtils.toMethodName("FIRST_NAME_"));
    }

    @Test
    public void testUppercasePostMultipleUnderscoreField() {

        Assert.assertEquals("firstName", JsonJavaUtils.toMethodName("FIRST_NAME___"));
    }

    @Test
    public void testUppercaseWrappedUnderscoreField() {

        Assert.assertEquals("firstName", JsonJavaUtils.toMethodName("_FIRST_NAME_"));
    }

    @Test
    public void testUppercaseMultiWrappedUnderscoreField() {

        Assert.assertEquals("firstName", JsonJavaUtils.toMethodName("___FIRST_NAME___"));
    }

    @Test
    public void testLowercase() {

        Assert.assertEquals("firstname", JsonJavaUtils.toMethodName("firstname"));
    }

    @Test
    public void testUnderscoreField() {

        Assert.assertEquals("firstName", JsonJavaUtils.toMethodName("first_name"));
    }

    @Test
    public void testPreUnderscoreField() {

        Assert.assertEquals("firstName", JsonJavaUtils.toMethodName("_first_name"));
    }

    @Test
    public void testPreMultipleUnderscoreField() {

        Assert.assertEquals("firstName", JsonJavaUtils.toMethodName("___first_name"));
    }

    @Test
    public void testPostUnderscoreField() {

        Assert.assertEquals("firstName", JsonJavaUtils.toMethodName("first_name_"));
    }

    @Test
    public void testPostMultipleUnderscoreField() {

        Assert.assertEquals("firstName", JsonJavaUtils.toMethodName("first_name___"));
    }

    @Test
    public void testWrappedUnderscoreField() {

        Assert.assertEquals("firstName", JsonJavaUtils.toMethodName("_first_name_"));
    }

    @Test
    public void testMultiWrappedUnderscoreField() {

        Assert.assertEquals("firstName", JsonJavaUtils.toMethodName("___first_name___"));
    }



}
