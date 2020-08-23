package com.emv.qrcode.decoder.mpm;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.NoSuchElementException;

import org.junit.Test;

public class DecodeMpmIteratorTest {

  @Test
  public void testSuccessParse() {
    final String encoded = "00020101021102160004hoge0104abcd520441115303156540523.7255020256035005802CN5914BEST TRANSPORT6007BEIJING6107123456762950105123450205678900305098760405543210505abcde0605fghij0705klmno0805pqres0903tuv1004abcd5004ijkl64280002ZH0102北京0204最佳运输0304abcd65020080320016A0112233449988770708123456786304C659";

    final DecodeMpmIterator decodeIterator = new DecodeMpmIterator(encoded);

    assertThat(decodeIterator.hasNext(), equalTo(true));

    assertThatCode(() -> decodeIterator.forEachRemaining(stub -> {
    })).doesNotThrowAnyException();

    assertThat(decodeIterator.hasNext(), equalTo(false));
  }

  @Test
  public void testFailParse() {
    final String encoded = "00020101021102160004hoge0104abcd520441115303156540523.7255020256035005802CN5914BEST TRANSPORT6007BEIJING6107123456762950105123450205678900305098760405543210505abcde0605fghij0705klmno0805pqres0903tuv1004abcd5004ijkl64280002ZH0102北京0204最佳运输0304abcd65020080320016A0112233449988770708123456786304C659";

    final DecodeMpmIterator decodeIterator = new DecodeMpmIterator(encoded);

    assertThat(decodeIterator.hasNext(), equalTo(true));

    assertThatCode(() -> decodeIterator.forEachRemaining(stub -> { })).doesNotThrowAnyException();

    final Throwable throwable = catchThrowable(() -> decodeIterator.next());
    
    assertThat(throwable).isInstanceOf(NoSuchElementException.class);

  }

}
