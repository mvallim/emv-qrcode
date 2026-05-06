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

package com.emv.qrcode.core.model.mpm;

import org.apache.commons.lang3.StringUtils;

import com.emv.qrcode.core.model.TLV;

import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a Tag-Length-Value (TLV) data element for Merchant Presented Mode
 * (MPM) QR codes. This class stores tag and value as strings and provides TLV
 * encoding/decoding functionality.
 *
 * @see TLV
 */
@Setter
@NoArgsConstructor
public class TagLengthString implements TLV<String, String> {

  private static final long serialVersionUID = -6482977134879939277L;

  private String tag;

  private String value;

  /**
   * Constructs a TagLengthString with the specified tag and value.
   *
   * @param tag   the tag identifier
   * @param value the value to store
   */
  public TagLengthString(final String tag, final String value) {
    this.tag = tag;
    this.value = value;
  }

  @Override
  public String getTag() {
    return tag;
  }

  @Override
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {

    if (StringUtils.isBlank(value)) {
      return StringUtils.EMPTY;
    }

    return String.format("%s%02d%s", tag, value.length(), value);
  }

}
