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

package com.emv.qrcode.model.mpm;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.emv.qrcode.core.model.TLV;

import lombok.Setter;

@Setter
public class MerchantAccountInformationTemplate implements TLV<String, MerchantAccountInformation> {

  private static final long serialVersionUID = 1504801865799183162L;

  private String tag;

  private MerchantAccountInformation value;

  public MerchantAccountInformationTemplate() {
    super();
  }

  public MerchantAccountInformationTemplate(final String tag) {
    this(tag, null);
  }

  public MerchantAccountInformationTemplate(final String tag, final MerchantAccountInformation value) {
    setTag(tag);
    setValue(value);
  }

  @Override
  public String getTag() {
    return tag;
  }

  @Override
  public MerchantAccountInformation getValue() {
    return value;
  }

  public <T extends MerchantAccountInformation> T getTypeValue(final Class<T> clazz) {
    return clazz.isInstance(value) ? clazz.cast(value) : null;
  }

  @Override
  public String toString() {

    if (Objects.isNull(value)) {
      return StringUtils.EMPTY;
    }

    final String string = value.toString();

    if (StringUtils.isBlank(string)) {
      return StringUtils.EMPTY;
    }

    return String.format("%s%02d%s", tag, string.length(), string);
  }
}
