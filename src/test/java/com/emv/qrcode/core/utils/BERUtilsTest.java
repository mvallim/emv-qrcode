package com.emv.qrcode.core.utils;

import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

public class BERUtilsTest {

  @Test
  public void testSuccessValueOfTag() {
    assertThat(Hex.encodeHexString(BERUtils.valueOfTag(new byte[] { 0x30 }), false), equalTo("30"));
    assertThat(Hex.encodeHexString(BERUtils.valueOfTag(new byte[] { 0x5F, 0x50 }), false), equalTo("5F50"));
    assertThat(Hex.encodeHexString(BERUtils.valueOfTag(new byte[] { 0x5F, (byte) 0x80, 0x50 }), false), equalTo("5F8050"));
    assertThat(Hex.encodeHexString(BERUtils.valueOfTag(new byte[] { 0x5F, (byte) 0x80, (byte) 0x90, 0x50 }), false), equalTo("5F809050"));
    assertThat(Hex.encodeHexString(BERUtils.valueOfTag(new byte[] { 0x5F, 0x50 }, 1), false), equalTo("50"));
    assertThat(Hex.encodeHexString(BERUtils.valueOfTag(new byte[] { 0x5F, (byte) 0x80, 0x50 }, 2), false), equalTo("50"));
    assertThat(Hex.encodeHexString(BERUtils.valueOfTag(new byte[] { 0x5F, (byte) 0x80, (byte) 0x90, 0x50 }, 3), false), equalTo("50"));
  }

  @Test
  public void testSuccessValueOfLength() {
    assertThat(BERUtils.valueOfLength(new byte[] { 0x4F, 0x00 }), equalTo(0));
    assertThat(BERUtils.valueOfLength(new byte[] { 0x4F, 0x30 }), equalTo(48));
    assertThat(BERUtils.valueOfLength(new byte[] { 0x4F, 0x7F }), equalTo(127));
    assertThat(BERUtils.valueOfLength(new byte[] { 0x4F, (byte) 0x81, (byte) 0x80 }), equalTo(128));
    assertThat(BERUtils.valueOfLength(new byte[] { 0x4F, (byte) 0x81, (byte) 0x85 }), equalTo(133));
    assertThat(BERUtils.valueOfLength(new byte[] { 0x4F, (byte) 0x81, (byte) 0xFF }), equalTo(255));
    assertThat(BERUtils.valueOfLength(new byte[] { 0x4F, (byte) 0x82, (byte) 0x01, 0x00 }), equalTo(256));
    assertThat(BERUtils.valueOfLength(new byte[] { 0x4F, (byte) 0x82, (byte) 0xEA, 0x60 }), equalTo(60000));
    assertThat(BERUtils.valueOfLength(new byte[] { 0x4F, (byte) 0x82, (byte) 0xFF, (byte) 0xFF }), equalTo(65535));
    assertThat(BERUtils.valueOfLength(new byte[] { 0x5F, 0x50, 0x00 }), equalTo(0));
    assertThat(BERUtils.valueOfLength(new byte[] { 0x5F, 0x50, 0x30 }), equalTo(48));
    assertThat(BERUtils.valueOfLength(new byte[] { 0x5F, 0x50, 0x7F }), equalTo(127));
    assertThat(BERUtils.valueOfLength(new byte[] { 0x5F, 0x50, (byte) 0x81, (byte) 0x80 }), equalTo(128));
    assertThat(BERUtils.valueOfLength(new byte[] { 0x5F, 0x50, (byte) 0x81, (byte) 0x85 }), equalTo(133));
    assertThat(BERUtils.valueOfLength(new byte[] { 0x5F, 0x50, (byte) 0x81, (byte) 0xFF }), equalTo(255));
    assertThat(BERUtils.valueOfLength(new byte[] { 0x5F, 0x50, (byte) 0x82, (byte) 0x01, 0x00 }), equalTo(256));
    assertThat(BERUtils.valueOfLength(new byte[] { 0x5F, 0x50, (byte) 0x82, (byte) 0xEA, 0x60 }), equalTo(60000));
    assertThat(BERUtils.valueOfLength(new byte[] { 0x5F, 0x50, (byte) 0x82, (byte) 0xFF, (byte) 0xFF }), equalTo(65535));
    assertThat(BERUtils.valueOfLength(new byte[] { 0x00, 0x5F, 0x50, 0x00 }, 1), equalTo(0));
    assertThat(BERUtils.valueOfLength(new byte[] { 0x00, 0x00, 0x00, 0x5F, 0x50, 0x7F }, 3), equalTo(127));
    assertThat(BERUtils.valueOfLength(new byte[] { 0x00, 0x5F, 0x50, (byte) 0x81, (byte) 0x80 }, 1), equalTo(128));
    assertThat(BERUtils.valueOfLength(new byte[] { 0x00, 0x00, 0x00, 0x5F, 0x50, (byte) 0x81, (byte) 0xFF }, 3), equalTo(255));
    assertThat(BERUtils.valueOfLength(new byte[] { 0x00, 0x5F, 0x50, (byte) 0x82, (byte) 0x01, 0x00 }, 1), equalTo(256));
    assertThat(BERUtils.valueOfLength(new byte[] { 0x00, 0x00, 0x00, 0x5F, 0x50, (byte) 0x82, (byte) 0xFF, (byte) 0xFF }, 3), equalTo(65535));
  }

  @Test
  public void testSuccessValueOf() {
    assertThat(BERUtils.valueOf(new byte[] { 0x4F, 0x00 }).length, equalTo(0));
    assertThat(BERUtils.valueOf(new byte[] { 0x4F, 0x01, 0x00 }).length, equalTo(1));
    assertThat(BERUtils.valueOf(new byte[] { 0x4F, 0x02, 0x00, 0x00 }).length, equalTo(2));
    assertThat(BERUtils.valueOf(new byte[] { 0x00, 0x4F, 0x00 }, 1).length, equalTo(0));
    assertThat(BERUtils.valueOf(new byte[] { 0x00, 0x00, 0x4F, 0x01, 0x00 }, 2).length, equalTo(1));
    assertThat(BERUtils.valueOf(new byte[] { 0x00, 0x00, 0x00, 0x4F, 0x02, 0x00, 0x00 }, 3).length, equalTo(2));
  }

  @Test
  public void testSuccessBucket() {
    assertThat(BERUtils.bucket(new byte[] { 0x4F, 0x00 }, 0).length, equalTo(2));
    assertThat(BERUtils.bucket(new byte[] { 0x4F, 0x01, 0x00 }, 0).length, equalTo(3));
    assertThat(BERUtils.bucket(new byte[] { 0x4F, 0x02, 0x00, 0x00 }, 0).length, equalTo(4));
    assertThat(BERUtils.bucket(new byte[] { 0x00, 0x4F, 0x00 }, 1).length, equalTo(2));
    assertThat(BERUtils.bucket(new byte[] { 0x00, 0x00, 0x4F, 0x01, 0x00 }, 2).length, equalTo(3));
    assertThat(BERUtils.bucket(new byte[] { 0x00, 0x00, 0x00, 0x4F, 0x02, 0x00, 0x00 }, 3).length, equalTo(4));
  }

  @Test
  public void testSuccessLengthToBytes() {
    assertThat(Hex.encodeHexString(BERUtils.lengthOfValue(2), false), equalTo("02"));
    assertThat(Hex.encodeHexString(BERUtils.lengthOfValue(4), false), equalTo("04"));
    assertThat(Hex.encodeHexString(BERUtils.lengthOfValue(8), false), equalTo("08"));
    assertThat(Hex.encodeHexString(BERUtils.lengthOfValue(16), false), equalTo("10"));
    assertThat(Hex.encodeHexString(BERUtils.lengthOfValue(32), false), equalTo("20"));
    assertThat(Hex.encodeHexString(BERUtils.lengthOfValue(64), false), equalTo("40"));
    assertThat(Hex.encodeHexString(BERUtils.lengthOfValue(128), false), equalTo("8180"));
    assertThat(Hex.encodeHexString(BERUtils.lengthOfValue(148), false), equalTo("8194"));
    assertThat(Hex.encodeHexString(BERUtils.lengthOfValue(158), false), equalTo("819E"));
    assertThat(Hex.encodeHexString(BERUtils.lengthOfValue(168), false), equalTo("81A8"));
    assertThat(Hex.encodeHexString(BERUtils.lengthOfValue(178), false), equalTo("81B2"));
    assertThat(Hex.encodeHexString(BERUtils.lengthOfValue(256), false), equalTo("820100"));
    assertThat(Hex.encodeHexString(BERUtils.lengthOfValue(333), false), equalTo("82014D"));
    assertThat(Hex.encodeHexString(BERUtils.lengthOfValue(512), false), equalTo("820200"));
    assertThat(Hex.encodeHexString(BERUtils.lengthOfValue(1024), false), equalTo("820400"));
    assertThat(Hex.encodeHexString(BERUtils.lengthOfValue(2048), false), equalTo("820800"));
    assertThat(Hex.encodeHexString(BERUtils.lengthOfValue(4096), false), equalTo("821000"));
    assertThat(Hex.encodeHexString(BERUtils.lengthOfValue(8192), false), equalTo("822000"));
    assertThat(Hex.encodeHexString(BERUtils.lengthOfValue(60000), false), equalTo("82EA60"));
    assertThat(Hex.encodeHexString(BERUtils.lengthOfValue(65535), false), equalTo("82FFFF"));
  }

  @Test
  public void testSuccessCountBytesOfTag() {
    assertThat(BERUtils.countBytesOfTag(new byte[] { 0x4F, 0x00 }), equalTo(1));
    assertThat(BERUtils.countBytesOfTag(new byte[] { 0x5F, 0x50 }), equalTo(2));
    assertThat(BERUtils.countBytesOfTag(new byte[] { 0x5F, (byte) 0x80, 0x50 }), equalTo(3));
    assertThat(BERUtils.countBytesOfTag(new byte[] { 0x5F, (byte) 0x80, (byte) 0x90, 0x50 }), equalTo(4));
    assertThat(BERUtils.countBytesOfTag(new byte[] { 0x00, 0x4F, 0x00 }, 1), equalTo(1));
    assertThat(BERUtils.countBytesOfTag(new byte[] { 0x00, 0x00, 0x5F, 0x50 }, 2), equalTo(2));
    assertThat(BERUtils.countBytesOfTag(new byte[] { 0x00, 0x00, 0x00, 0x5F, (byte) 0x80, 0x50 }, 3), equalTo(3));
    assertThat(BERUtils.countBytesOfTag(new byte[] { 0x00, 0x00, 0x00, 0x00, 0x5F, (byte) 0x80, (byte) 0x90, 0x50 }, 4), equalTo(4));
  }

  @Test
  public void testSuccessCountBytesOfLength() {
    assertThat(BERUtils.countBytesOfLength(new byte[] { 0x4F, 0x00 }), equalTo(1));
    assertThat(BERUtils.countBytesOfLength(new byte[] { 0x4F, (byte) 0x81, (byte) 0x80 }), equalTo(2));
    assertThat(BERUtils.countBytesOfLength(new byte[] { 0x4F, (byte) 0x82, (byte) 0x80, 0x00 }), equalTo(3));
    assertThat(BERUtils.countBytesOfLength(new byte[] { 0x00, 0x4F, 0x00 }, 1), equalTo(1));
    assertThat(BERUtils.countBytesOfLength(new byte[] { 0x00, 0x00, 0x4F, (byte) 0x81, (byte) 0x80 }, 2), equalTo(2));
    assertThat(BERUtils.countBytesOfLength(new byte[] { 0x00, 0x00, 0x00, 0x4F, (byte) 0x82, (byte) 0x80, 0x00 }, 3), equalTo(3));
  }

  @Test
  public void testFailCountBytesOfLength() {
    final IllegalStateException illegalStateException = catchThrowableOfType(() -> BERUtils.countBytesOfLength(new byte[] { 0x4F, (byte) 0x83, (byte) 0x80, 0x00, 0x00 }), IllegalStateException.class);
    assertThat(illegalStateException.getMessage(), equalTo("Decode the length is more then 2 bytes (65535)"));
  }

  @Test
  public void testFailLengthToBytes() {
    final IllegalStateException illegalStateException = catchThrowableOfType(() -> BERUtils.lengthOfValue(65536), IllegalStateException.class);
    assertThat(illegalStateException.getMessage(), equalTo("Encode the length is more then 2 bytes (65535)"));
  }

}
