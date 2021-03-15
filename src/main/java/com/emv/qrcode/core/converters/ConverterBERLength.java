package com.emv.qrcode.core.converters;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ConverterBERLength implements Converter<Integer, byte[]> {

  public static ConverterBERLength INSTANCE = new ConverterBERLength();

  private ConverterBERLength() {
    super();
  }

  @Override
  public byte[] convert(final Integer value) {
    if (value > Byte.MAX_VALUE) {

      final Integer numberOfBytes = countBytes(value);

      if (numberOfBytes > 2) {
        throw new IllegalStateException("Encode the length is more then 2 bytes (65535)");
      }

      final byte[] bytes = new byte[numberOfBytes + 1];

      bytes[0] = (byte) (0x80 + numberOfBytes);

      final byte[] array = ByteBuffer.allocate(2).putShort(value.shortValue()).order(ByteOrder.LITTLE_ENDIAN).array();

      for (int i = 0; i < numberOfBytes; i++) {
        bytes[i + 1] = array[2 - numberOfBytes + i];
      }

      return bytes;
    }

    return new byte[] { value.byteValue() };
  }

  private Integer countBytes(final Integer value) {
    if (value == 0) {
      return 0;
    } else {
      return countBytes(value >> 8) + 1;
    }
  }

}
