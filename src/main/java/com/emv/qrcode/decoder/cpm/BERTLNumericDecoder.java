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

package com.emv.qrcode.decoder.cpm;

import com.emv.qrcode.core.model.cpm.BERTLNumeric;
import com.emv.qrcode.core.utils.BERUtils;

/**
 * Decoder for parsing BER-TLV Numeric data from CPM QR code byte arrays.
 * This decoder creates BERTLNumeric objects from BER-encoded byte arrays.
 *
 * @see DecoderCpm
 * @see BERTLNumeric
 */
public final class BERTLNumericDecoder extends DecoderCpm<BERTLNumeric> {

  /**
   * Constructs a BERTLNumericDecoder with the specified source byte array.
   *
   * @param source the CPM QR code byte array to decode
   */
  public BERTLNumericDecoder(final byte[] source) {
    super(source);
  }

  @Override
  protected BERTLNumeric decode() {
    final byte[] value = iterator.next();
    return new BERTLNumeric(BERUtils.valueOfTag(value), BERUtils.valueOf(value));
  }

}