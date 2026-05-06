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

package com.emv.qrcode.core.model.cpm;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;

import com.emv.qrcode.core.exception.DecodeValueException;

/**
 * Represents a BER-TLV element with a numeric (hex-encoded) value.
 * This class is used for Consumer Presented Mode (CPM) QR code data fields that contain numeric data.
 * The hex string is padded with a leading zero if the length is odd.
 *
 * @see BERTLV
 */
public class BERTLNumeric extends BERTLV {

  private static final long serialVersionUID = -2791656176543560953L;

  /**
   * Constructs a BERTLNumeric with the specified tag and byte array value.
   *
   * @param tag the BER tag
   * @param value the byte array value
   */
  public BERTLNumeric(final BERTag tag, final byte[] value) {
    super(tag, value);
  }

  /**
   * Constructs a BERTLNumeric with the specified tag bytes and byte array value.
   *
   * @param tag the tag as a byte array
   * @param value the byte array value
   */
  public BERTLNumeric(final byte[] tag, final byte[] value) {
    super(tag, value);
  }

  /**
   * Constructs a BERTLNumeric with the specified tag bytes and hex string value.
   * The hex string is decoded to bytes (padded with leading zero if odd length).
   *
   * @param tag the tag as a byte array
   * @param value the hex string value (will be decoded to bytes)
   * @throws DecodeValueException if the hex string is invalid
   */
  public BERTLNumeric(final byte[] tag, final String value) {
    super(tag, StringUtils.isNotEmpty(value) ? toPrimitives(value) : EMPTY_BYTES);
  }

  /**
   * Constructs a BERTLNumeric with the specified tag and hex string value.
   * The hex string is decoded to bytes (padded with leading zero if odd length).
   *
   * @param tag the BER tag
   * @param value the hex string value (will be decoded to bytes)
   * @throws DecodeValueException if the hex string is invalid
   */
  public BERTLNumeric(final BERTag tag, final String value) {
    super(tag, StringUtils.isNotEmpty(value) ? toPrimitives(value) : EMPTY_BYTES);
  }

  /**
   * Sets the value as a hex string. The hex string is decoded to bytes (padded with leading zero if odd length).
   *
   * @param value the hex string value to set
   * @throws DecodeValueException if the hex string is invalid
   */
  public final void setValue(final String value) {
    setValue(StringUtils.isNotEmpty(value) ? toPrimitives(value) : EMPTY_BYTES);
  }

  /**
   * Converts a hex string to a byte array. Pads with leading zero if the string has odd length.
   *
   * @param value the hex string to decode
   * @return the decoded byte array
   * @throws DecodeValueException if the hex string is invalid
   */
  private static byte[] toPrimitives(final String value) {
    try {
      return Hex.decodeHex(value.length() % 2 == 0 ? value : "0" + value);
    } catch (final DecoderException ex) {
      throw new DecodeValueException(value);
    }
  }

  @Override
  public String getStringValue() {
    return Hex.encodeHexString(value, false);
  }

}
