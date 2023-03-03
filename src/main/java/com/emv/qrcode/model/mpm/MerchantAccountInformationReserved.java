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

package com.emv.qrcode.model.mpm;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;

@Getter
public class MerchantAccountInformationReserved implements MerchantAccountInformation {

  private static final long serialVersionUID = -5918006580406564067L;

  private String value;

  public MerchantAccountInformationReserved() {
    super();
  }

  public MerchantAccountInformationReserved(final String value) {
    this.value = value;
  }

  @Override
  public String toString() {

    if (StringUtils.isBlank(value)) {
      return StringUtils.EMPTY;
    }

    return value;
  }

}
