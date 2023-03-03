/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
   * @see https://en.wikipedia.org/wiki/Cyclic_redundancy_check
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
