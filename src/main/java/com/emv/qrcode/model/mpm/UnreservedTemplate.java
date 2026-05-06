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

import com.emv.qrcode.core.model.TLV;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents an Unreserved Template in Merchant Presented Mode (MPM).
 * This class models a template for proprietary or future use within tags 80-99
 * of an MPM QR code.
 *
 * <p>Each Unreserved Template contains a Globally Unique Identifier and
 * optional context-specific data. The template uses TLV (Tag-Length-Value)
 * encoding.</p>
 *
 * @see com.emv.qrcode.core.model.TLV
 * @see com.emv.qrcode.model.mpm.Unreserved
 * @see com.emv.qrcode.model.mpm.constants.UnreservedTemplateFieldCodes
 * @since EMVCo QR Code Specification v1.0
 */
@Setter
public class UnreservedTemplate implements TLV<String, Unreserved> {

  private static final long serialVersionUID = -1445641777082739037L;

  private String tag;

  private Unreserved value;

  public UnreservedTemplate() {
    super();
  }

  public UnreservedTemplate(final String tag) {
    this.setTag(tag);
  }

  public UnreservedTemplate(final String tag, final String globallyUniqueIdentifier) {
    this.setTag(tag);
    this.setValue(new Unreserved(globallyUniqueIdentifier));
  }

  public void addContextSpecificData(final String tag, final String value) {   
    this.setValue(Optional.ofNullable(this.getValue()).orElse(new Unreserved()));
    this.getValue().addContextSpecificData(tag, value);
  }

  @Override
  public String getTag() {
    return tag;
  }

  @Override
  public Unreserved getValue() {
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
