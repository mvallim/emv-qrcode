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

package com.emv.qrcode.model.mpm.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Constants for Unreserved Template sub-field tags. These constants define the
 * EMVCo-assigned tag values for fields within Unreserved Templates (tags 80-99)
 * in Merchant Presented Mode.
 *
 * @see com.emv.qrcode.model.mpm.Unreserved
 * @see com.emv.qrcode.model.mpm.UnreservedTemplate
 * @since EMVCo QR Code Specification v1.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UnreservedTemplateFieldCodes {

  /**
   * Globally Unique Identifier - identifies the organization using the template.
   */
  public static final String ID_GLOBALLY_UNIQUE_IDENTIFIER = "00";

  /**
   * Start of Context Specific Data range (01-99).
   */
  public static final String ID_CONTEXT_SPECIFIC_DATA_START = "01";

  /**
   * End of Context Specific Data range (01-99).
   */
  public static final String ID_CONTEXT_SPECIFIC_DATA_END = "99";

  /**
   * Reserved: Alias for Context Specific Data range start.
   */
  public static final String ID_CONTEXT_SPECIFIC_DATA = ID_CONTEXT_SPECIFIC_DATA_START;

}
