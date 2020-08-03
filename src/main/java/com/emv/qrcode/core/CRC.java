package com.emv.qrcode.core;

public final class CRC {

  private CRC() {
    super();
  }

  /**
   * Information technology—Telecommunications and information exchange between
   * systems—High-level data link control (HDLC) procedures.
   *
   * The checksum shall be calculated according to [ISO/IEC 13239] using the
   * polynomial '1021' (hex) and initial value 'FFFF' (hex).
   *
   * Implements CRC-16/CCITT-FALSE
   *
   * @param value
   * @return CRC16 integer
   */
  public static int crc16(final byte[] value) {
    final int polynomial = 0x1021; // 0001 0000 0010 0001 (0, 5, 12)

    int result = 0xFFFF; // initial value

    final byte[] bytes = value;

    for (final byte b : bytes) {
      for (int i = 0; i < 8; i++) {
        final boolean bit = (b >> 7 - i & 1) == 1;
        final boolean c15 = (result >> 15 & 1) == 1;
        result <<= 1;
        if (c15 ^ bit) {
          result ^= polynomial;
        }
      }
    }

    result &= 0xffff;

    return result;
  }

}
