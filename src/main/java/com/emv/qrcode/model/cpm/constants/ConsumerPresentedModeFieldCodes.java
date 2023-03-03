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

package com.emv.qrcode.model.cpm.constants;

import com.emv.qrcode.core.model.cpm.BERTag;

public final class ConsumerPresentedModeFieldCodes {

  public static final BERTag ID_PAYLOAD_FORMAT_INDICATOR = new BERTag(new byte[] { (byte) 0x85 }); // (M) Payload Format Indicator
  public static final BERTag ID_APPLICATION_TEMPLATE = new BERTag(new byte[] { 0x61 }); // (M) Application Template
  public static final BERTag ID_COMMON_DATA_TEMPLATE = new BERTag(new byte[] { 0x62 }); // (O) Common Data Template
  public static final BERTag ID_APPLICATION_SPECIFIC_TRANSPARENT_TEMPLATE = new BERTag(new byte[] { 0x63 }); // (O) Application Specific Transparent Template
  public static final BERTag ID_COMMON_DATA_TRANSPARENT_TEMPLATE = new BERTag(new byte[] { 0x64 }); // (O) Common Data Transparent Template

  private ConsumerPresentedModeFieldCodes() {
    super();
  }

}
