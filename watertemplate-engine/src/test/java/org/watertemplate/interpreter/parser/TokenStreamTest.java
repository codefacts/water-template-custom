package org.watertemplate.interpreter.parser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.watertemplate.interpreter.parser.Terminal.*;
import static org.watertemplate.interpreter.parser.TokenFixture.*;

public class TokenStreamTest {

    private TokenStream tokenStream;

    @Before
    public void before() {
        tokenStream = new TokenStream(
                If(),
                PropertyKey("foo"),
                Else(),
                EndOfBlock());
    }

    @Test
    public void hasAny() {
        Assert.assertFalse(new TokenStream().hasAny());
        Assert.assertTrue(tokenStream.hasAny());
    }

    @Test
    public void currentAndShift() {
        assertCurrentIsOfType(IF);
        tokenStream.shift();
        assertCurrentIsOfType(PROPERTY_KEY);
        tokenStream.shift();
        assertCurrentIsOfType(ELSE);
    }

    @Test
    public void saveAndReset() {
        int save = tokenStream.getCurrentTokenPosition();
        assertCurrentIsOfType(IF);
        tokenStream.shift();
        tokenStream.shift();
        tokenStream.shift();
        assertCurrentIsNotOfType(IF);
        tokenStream.reset(save);
        assertCurrentIsOfType(IF);
    }

    @Test
    public void remaining() {
        Assert.assertEquals(4, tokenStream.remaining());
        tokenStream.shift();
        tokenStream.shift();
        Assert.assertEquals(2, tokenStream.remaining());
        tokenStream.shift();
        tokenStream.shift();
        Assert.assertEquals(0, tokenStream.remaining());
    }

    private void assertCurrentIsOfType(final Terminal terminal) {
        Assert.assertTrue(current().canBe(terminal));
    }

    private void assertCurrentIsNotOfType(final Terminal terminal) {
        Assert.assertFalse(current().canBe(terminal));
    }

    private Token current() {
        return tokenStream.current();
    }
}
