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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;

import org.apache.commons.codec.binary.Hex;

import com.emv.qrcode.core.model.TLV;
import com.emv.qrcode.core.utils.BERUtils;

public abstract class BERTLV implements TLV<BERTag, byte[]> {

  private static final long serialVersionUID = 1286326121944790325L;

  public static final byte[] EMPTY_BYTES = new byte[0];

  public static final BERTag EMPTY_TAG = new BERTag(EMPTY_BYTES);

  private BERTag tag;

  protected byte[] value;

  protected BERTLV(final BERTag tag, final byte[] value) {
    this.tag = tag;
    this.value = value;
  }

  protected BERTLV(final byte[] tag, final byte[] value) {
    this(new BERTag(tag), value);
  }

  @Override
  public BERTag getTag() {
    return tag;
  }

  public final void setTag(final BERTag tag) {
    this.tag = Optional.ofNullable(tag).orElse(EMPTY_TAG);
  }

  @Override
  public byte[] getValue() {
    return value;
  }

  public abstract String getStringValue();

  public final void setValue(final byte[] value) {
    this.value = Optional.ofNullable(value).orElse(EMPTY_BYTES);
  }

  @Override
  public final Integer getLength() {
    return value.length;
  }

  public final String toHex() throws IOException {
    return Hex.encodeHexString(getBytes(), false);
  }

  public byte[] getBytes() throws IOException {

    if (getLength() == 0) {
      return EMPTY_BYTES;
    }

    try (final ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
      stream.write(getTag().getBytes());
      stream.write(BERUtils.lengthOfValue(getLength()));
      stream.write(getValue());
      return stream.toByteArray();
    }

  }

}
