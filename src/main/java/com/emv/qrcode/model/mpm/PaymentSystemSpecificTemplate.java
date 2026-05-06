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

/**
 * Represents a Payment System Specific Template in Merchant Presented Mode (MPM).
 * This class models a template that contains payment system-specific data
 * within tags 50-99 of an MPM QR code.
 *
 * <p>The template uses TLV (Tag-Length-Value) encoding where the tag identifies
 * the template, and the value contains a PaymentSystemSpecific object.</p>
 *
 * @see com.emv.qrcode.core.model.TLV
 * @see com.emv.qrcode.model.mpm.PaymentSystemSpecific
 * @see com.emv.qrcode.model.mpm.constants.PaymentSystemSpecificFieldCodes
 * @since EMVCo QR Code Specification v1.0
 */
@Setter
public class PaymentSystemSpecificTemplate implements TLV<String, PaymentSystemSpecific> {

  private static final long serialVersionUID = -1445641777082739037L;

  private String tag;

  private PaymentSystemSpecific value;

  @Override
  public String getTag() {
    return tag;
  }

  @Override
  public PaymentSystemSpecific getValue() {
    return value;
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
