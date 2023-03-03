/*
 * Copyright 2023 the original author or authors.
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

import java.nio.charset.StandardCharsets;

import org.apache.commons.lang3.StringUtils;

public class BERTLAlphanumeric extends BERTLV {

  private static final long serialVersionUID = 4743677275454890695L;

  public BERTLAlphanumeric(final BERTag tag, final byte[] value) {
    super(tag, value);
  }

  public BERTLAlphanumeric(final byte[] tag, final byte[] value) {
    super(tag, value);
  }

  public BERTLAlphanumeric(final byte[] tag, final String value) {
    super(tag, StringUtils.isNotEmpty(value) ? value.getBytes(StandardCharsets.UTF_8) : EMPTY_BYTES);
  }

  public BERTLAlphanumeric(final BERTag tag, final String value) {
    super(tag, StringUtils.isNotEmpty(value) ? value.getBytes(StandardCharsets.UTF_8) : EMPTY_BYTES);
  }

  public final void setValue(final String value) {
    setValue(StringUtils.isNotEmpty(value) ? value.getBytes(StandardCharsets.UTF_8) : EMPTY_BYTES);
  }

  @Override
  public String getStringValue() {
    return new String(value, StandardCharsets.UTF_8);
  }

}
