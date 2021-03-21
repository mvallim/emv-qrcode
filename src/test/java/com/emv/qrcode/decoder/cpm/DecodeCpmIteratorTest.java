package com.emv.qrcode.decoder.cpm;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.nio.ByteBuffer;
import java.util.NoSuchElementException;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

// @formatter:off
public class DecodeCpmIteratorTest {

  @Test
  public void testSuccessParseMinimal() throws DecoderException {
    final String encoded = "85054350563031611A4F07A0000000555"
      + "555570F1234567890123458D191220112345F";

    final byte[] decodeHex = Hex.decodeHex(encoded);

    final DecodeCpmIterator decodeIterator = new DecodeCpmIterator(decodeHex);

    assertThat(decodeIterator.hasNext(), equalTo(true));

    final ByteBuffer byteBuffer = ByteBuffer.allocate(35);

    assertThatCode(() -> decodeIterator.forEachRemaining(byteBuffer::put)).doesNotThrowAnyException();

    assertThat(decodeIterator.hasNext(), equalTo(false));
    assertThat(byteBuffer.array(), equalTo(decodeHex));
  }

  @Test
  public void testSuccessParseLong() throws DecoderException {
    final String encoded = "8505435056303161134F07A0000000555"
      + "555500850726F647563743161134F07A00000006666665008507"
      + "26F647563743262495A0812345678901234585F200E434152444"
      + "84F4C4445522F454D565F2D08727565736465656E64219F10070"
      + "6010A030000009F2608584FD385FA234BCC9F360200019F37046"
      + "D58EF13";

    final byte[] decodeHex = Hex.decodeHex(encoded);

    final DecodeCpmIterator decodeIterator = new DecodeCpmIterator(decodeHex);

    assertThat(decodeIterator.hasNext(), equalTo(true));

    final ByteBuffer byteBuffer = ByteBuffer.allocate(124);

    assertThatCode(() -> decodeIterator.forEachRemaining(byteBuffer::put)).doesNotThrowAnyException();

    assertThat(decodeIterator.hasNext(), equalTo(false));
    assertThat(byteBuffer.array(), equalTo(decodeHex));
  }

  @Test
  public void testSuccessParseLongTag() throws DecoderException {
    final String encoded = "9F37046D58EF13";

    final byte[] decodeHex = Hex.decodeHex(encoded);

    final DecodeCpmIterator decodeIterator = new DecodeCpmIterator(decodeHex);

    assertThat(decodeIterator.hasNext(), equalTo(true));

    final ByteBuffer byteBuffer = ByteBuffer.allocate(7);

    assertThatCode(() -> decodeIterator.forEachRemaining(byteBuffer::put)).doesNotThrowAnyException();

    assertThat(decodeIterator.hasNext(), equalTo(false));
    assertThat(byteBuffer.array(), equalTo(decodeHex));
  }

  @Test
  public void testSuccessParseLongLength() throws DecoderException {
    final String encoded = "9F37046D58EF137081C08F01049F3201"
      + "0392249FFBFB7FEEC7B04367B3E4C671C30B4AEEADA2C193495"
      + "8DD6104D150EAFD3C052C970E8D90819052D778E3332B720F4F"
      + "E41D7C1BED0645EA7DEF14E4E36F8090A142B7E1B17DDECFAA8"
      + "0FCB4BF041C2D4404AD1E7F19C9565B937F5EB502906FEE32F5"
      + "21E532ABFC37F0461E91AA46798D74C4BAFA0881A30E1F9BB87"
      + "AB785C7E29A4546D1B7FD6F98A46519FB7F53203A93AAC95C5B"
      + "53B8CC6E9AD3DBC925CC72B96EDD783BB0D7B6E8E978BB355E4"
      + "55E7A5BCA57C495";

    final byte[] decodeHex = Hex.decodeHex(encoded);

    final DecodeCpmIterator decodeIterator = new DecodeCpmIterator(decodeHex);

    assertThat(decodeIterator.hasNext(), equalTo(true));

    final ByteBuffer byteBuffer = ByteBuffer.allocate(202);

    assertThatCode(() -> decodeIterator.forEachRemaining(byteBuffer::put)).doesNotThrowAnyException();

    assertThat(decodeIterator.hasNext(), equalTo(false));
    assertThat(byteBuffer.array(), equalTo(decodeHex));
  }

  @Test
  public void testFailParse() throws DecoderException {
    final String encoded = "8505435056303161134F07A0000000555"
        + "555500850726F647563743161134F07A00000006666665008507"
        + "26F647563743262495A0812345678901234585F200E434152444"
        + "84F4C4445522F454D565F2D08727565736465656E64219F10070"
        + "6010A030000009F2608584FD385FA234BCC9F360200019F37046"
        + "D58EF13";

    final byte[] decodeHex = Hex.decodeHex(encoded);

    final DecodeCpmIterator decodeIterator = new DecodeCpmIterator(decodeHex);

    assertThat(decodeIterator.hasNext(), equalTo(true));

    final ByteBuffer byteBuffer = ByteBuffer.allocate(124);

    assertThatCode(() -> decodeIterator.forEachRemaining(byteBuffer::put)).doesNotThrowAnyException();

    assertThat(decodeIterator.hasNext(), equalTo(false));
    assertThat(byteBuffer.array(), equalTo(decodeHex));

    final Throwable throwable = catchThrowable(() -> decodeIterator.next());

    assertThat(throwable).isInstanceOf(NoSuchElementException.class);
  }

  @Test
  public void testFailParseOverflowShortValue() throws DecoderException {
    final String encoded = "4F83FFFFFFA00000005555";

    final DecodeCpmIterator decodeIterator = new DecodeCpmIterator(Hex.decodeHex(encoded));

    final IllegalStateException illegalStateException = catchThrowableOfType(() -> decodeIterator.hasNext(), IllegalStateException.class);

    assertThat(illegalStateException.getMessage(), equalTo("Decode the length is more then 2 bytes (65535)"));
  }

}
// @formatter:on
