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

/**
 * Represents a Consumer Presented Mode (CPM) QR code data structure.
 * This class models the complete CPM QR code as defined by EMVCo,
 * containing a Payload Format Indicator, Application Templates, and optionally
 * a Common Data Template and other templates.
 *
 * <p>The CPM QR code is used when the consumer presents the QR code to the merchant
 * for payment processing. It uses BER-TLV (Basic Encoding Rules - Tag-Length-Value)
 * encoding for data representation.</p>
 *
 * @see com.emv.qrcode.model.cpm.PayloadFormatIndicator
 * @see com.emv.qrcode.model.cpm.ApplicationTemplate
 * @see com.emv.qrcode.model.cpm.CommonDataTemplate
 * @see com.emv.qrcode.core.model.cpm.BERTemplate
 * @since EMVCo QR Code Specification v1.0
 */
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

  /**
   * Adds an Application Template to the QR code data.
   *
   * @param applicationTemplate the application template to add
   */
  public void addApplicationTemplate(final ApplicationTemplate applicationTemplate) {
    applicationTemplates.add(applicationTemplate);
  }

  /**
   * Adds an other template to the QR code data.
   *
   * @param otherTemplate the other template to add
   */
  public void addOtherTemplate(final BERTemplate<byte[]> otherTemplate) {
    otherTemplates.add(otherTemplate);
  }

  /**
   * Converts the QR code data to a byte array.
   *
   * @return the byte array representation of the QR code data
   * @throws IOException if an I/O error occurs
   */
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

  /**
   * Converts the QR code data to a Base64 encoded string.
   *
   * @return the Base64 encoded string
   * @throws IOException if an I/O error occurs
   */
  public String toBase64() throws IOException {
    return Base64.encodeBase64String(getBytes());
  }

  /**
   * Converts the QR code data to a hexadecimal string representation.
   *
   * @return the hexadecimal encoded string
   * @throws IOException if an I/O error occurs
   */
  public String toHex() throws IOException {
    return Hex.encodeHexString(getBytes(), false);
  }

}
