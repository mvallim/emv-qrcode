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

package com.emv.qrcode.model.cpm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import com.emv.qrcode.core.model.cpm.BERTemplate;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ConsumerPresentedMode implements Serializable {

  private static final long serialVersionUID = -1395429978639674565L;

  // Payload Format Indicator
  @Setter
  private PayloadFormatIndicator payloadFormatIndicator;

  // Application Template
  private final List<ApplicationTemplate> applicationTemplates = new LinkedList<>();

  // Common Data Template
  @Setter
  private CommonDataTemplate commonDataTemplate;

  // Other template
  private final List<BERTemplate<byte[]>> otherTemplates = new LinkedList<>();

  public void addApplicationTemplate(final ApplicationTemplate applicationTemplate) {
    applicationTemplates.add(applicationTemplate);
  }

  public void addOtherTemplate(final BERTemplate<byte[]> otherTemplate) {
    otherTemplates.add(otherTemplate);
  }

  public byte[] getBytes() throws IOException {
    try (final ByteArrayOutputStream out = new ByteArrayOutputStream()) {

      if (Objects.nonNull(payloadFormatIndicator)) {
        out.write(payloadFormatIndicator.getBytes());
      }

      for (final BERTemplate<byte[]> applicationTemplate : applicationTemplates) {
        out.write(applicationTemplate.getBytes());
      }

      if (Objects.nonNull(commonDataTemplate)) {
        out.write(commonDataTemplate.getBytes());
      }

      for (final BERTemplate<byte[]> otherTemplate : otherTemplates) {
        out.write(otherTemplate.getBytes());
      }

      return out.toByteArray();
    }
  }

  public String toBase64() throws IOException {
    return Base64.encodeBase64String(getBytes());
  }

  public String toHex() throws IOException {
    return Hex.encodeHexString(getBytes(), false);
  }

}
