package com.emv.qrcode.decoder.cpm;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.NoSuchElementException;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

public class DecodeCpmIteratorTest {

  @Test
  public void testSuccessParseMinimal() throws DecoderException {
    final String encoded = "85054350563031611A4F07A0000000555555570F1234567890123458D191220112345F";

    final DecodeCpmIterator decodeIterator = new DecodeCpmIterator(Hex.decodeHex(encoded));

    assertThat(decodeIterator.hasNext(), equalTo(true));

    assertThatCode(() -> decodeIterator.forEachRemaining(stub -> {
    })).doesNotThrowAnyException();

    assertThat(decodeIterator.hasNext(), equalTo(false));
  }

  @Test
  public void testSuccessParseLong() throws DecoderException {
    final String encoded = "8505435056303161134F07A0000000555555500850726F647563743161134F07A0000000666666500850726F647563743262495A0812345678901234585F200E43415244484F4C4445522F454D565F2D08727565736465656E64219F100706010A030000009F2608584FD385FA234BCC9F360200019F37046D58EF13";

    final DecodeCpmIterator decodeIterator = new DecodeCpmIterator(Hex.decodeHex(encoded));

    assertThat(decodeIterator.hasNext(), equalTo(true));

    assertThatCode(() -> decodeIterator.forEachRemaining(stub -> {
    })).doesNotThrowAnyException();

    assertThat(decodeIterator.hasNext(), equalTo(false));

  }

  @Test
  public void testFailParse() throws DecoderException {
    final String encoded = "8505435056303161134F07A0000000555555500850726F647563743161134F07A0000000666666500850726F647563743262495A0812345678901234585F200E43415244484F4C4445522F454D565F2D08727565736465656E64219F100706010A030000009F2608584FD385FA234BCC9F360200019F37046D58";

    final DecodeCpmIterator decodeIterator = new DecodeCpmIterator(Hex.decodeHex(encoded));

    assertThat(decodeIterator.hasNext(), equalTo(true));

    assertThatCode(() -> decodeIterator.forEachRemaining(stub -> {
    })).doesNotThrowAnyException();

    final Throwable throwable = catchThrowable(() -> decodeIterator.next());

    assertThat(throwable).isInstanceOf(NoSuchElementException.class);

  }

}
