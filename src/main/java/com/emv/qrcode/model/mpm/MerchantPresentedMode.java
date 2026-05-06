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

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;

import com.emv.qrcode.core.CRC;
import com.emv.qrcode.core.model.mpm.TagLengthString;
import com.emv.qrcode.model.mpm.constants.MerchantPresentedModeCodes;

import lombok.Getter;

/**
 * Represents a Merchant Presented Mode (MPM) QR code data structure as defined by EMVCo.
 * This class models all data fields that can be encoded in a merchant-presented QR code,
 * including merchant information, transaction details, and additional data fields.
 *
 * @see <a href="https://www.emvco.com/specifications/">EMVCo QR Code Specification</a>
 */
@Getter
public class MerchantPresentedMode implements Serializable {

  private static final long serialVersionUID = 485352878727448583L;

  // Payload Format Indicator
  private TagLengthString payloadFormatIndicator;

  // Point of Initiation Method
  private TagLengthString pointOfInitiationMethod;

  // Merchant Account Information
  private final Map<String, MerchantAccountInformationTemplate> merchantAccountInformation = new LinkedHashMap<>();

  // Merchant Category Code
  private TagLengthString merchantCategoryCode;

  // Transaction Currency
  private TagLengthString transactionCurrency;

  // Transaction Amount
  private TagLengthString transactionAmount;

  // Tip or Convenience Indicator
  private TagLengthString tipOrConvenienceIndicator;

  // Value of Convenience Fee Fixed
  private TagLengthString valueOfConvenienceFeeFixed;

  // Value of Convenience Fee Percentage
  private TagLengthString valueOfConvenienceFeePercentage;

  // Country Code
  private TagLengthString countryCode;

  // Merchant Name
  private TagLengthString merchantName;

  // Merchant City
  private TagLengthString merchantCity;

  // Postal Code
  private TagLengthString postalCode;

  // Additional Data Field Template
  private AdditionalDataFieldTemplate additionalDataField;

  // CRC
  private TagLengthString cRC;

  // Merchant Information - Language Template
  private MerchantInformationLanguageTemplate merchantInformationLanguage;

  // RFU for EMVCo
  private final Map<String, TagLengthString> rFUforEMVCo = new LinkedHashMap<>();

  // Unreserved Templates
  private final Map<String, UnreservedTemplate> unreserveds = new LinkedHashMap<>();

  /**
   * Sets the Additional Data Field Template.
   *
   * @param additionalDataField the additional data field template
   */
  public void setAdditionalDataField(final AdditionalDataFieldTemplate additionalDataField) {
    this.additionalDataField = additionalDataField;
  }

  /**
   * Sets the Merchant Information Language Template.
   *
   * @param merchantInformationLanguage the merchant information language template
   */
  public void setMerchantInformationLanguage(final MerchantInformationLanguageTemplate merchantInformationLanguage) {
    this.merchantInformationLanguage = merchantInformationLanguage;
  }

  /**
   * Sets the Payload Format Indicator (tag 00).
   *
   * @param payloadFormatIndicator the payload format indicator value
   */
  public final void setPayloadFormatIndicator(final String payloadFormatIndicator) {
    this.payloadFormatIndicator = new TagLengthString(MerchantPresentedModeCodes.ID_PAYLOAD_FORMAT_INDICATOR, payloadFormatIndicator);
  }

  /**
   * Sets the Point of Initiation Method (tag 01).
   *
   * @param pointOfInitiationMethod the point of initiation method value
   */
  public final void setPointOfInitiationMethod(final String pointOfInitiationMethod) {
    this.pointOfInitiationMethod = new TagLengthString(MerchantPresentedModeCodes.ID_POINT_OF_INITIATION_METHOD, pointOfInitiationMethod);
  }

  /**
   * Sets the Merchant Category Code (tag 52).
   *
   * @param merchantCategoryCode the merchant category code value
   */
  public final void setMerchantCategoryCode(final String merchantCategoryCode) {
    this.merchantCategoryCode = new TagLengthString(MerchantPresentedModeCodes.ID_MERCHANT_CATEGORY_CODE, merchantCategoryCode);
  }

  /**
   * Sets the Transaction Currency (tag 53).
   *
   * @param transactionCurrency the transaction currency code (ISO 4217)
   */
  public final void setTransactionCurrency(final String transactionCurrency) {
    this.transactionCurrency = new TagLengthString(MerchantPresentedModeCodes.ID_TRANSACTION_CURRENCY, transactionCurrency);
  }

  /**
   * Sets the Transaction Amount (tag 54).
   *
   * @param transactionAmount the transaction amount value
   */
  public final void setTransactionAmount(final String transactionAmount) {
    this.transactionAmount = new TagLengthString(MerchantPresentedModeCodes.ID_TRANSACTION_AMOUNT, transactionAmount);
  }

  /**
   * Sets the Tip or Convenience Indicator (tag 55).
   *
   * @param tipOrConvenienceIndicator the tip or convenience indicator value
   */
  public final void setTipOrConvenienceIndicator(final String tipOrConvenienceIndicator) {
    this.tipOrConvenienceIndicator = new TagLengthString(MerchantPresentedModeCodes.ID_TIP_OR_CONVENIENCE_INDICATOR, tipOrConvenienceIndicator);
  }

  /**
   * Sets the Value of Convenience Fee Fixed (tag 56).
   *
   * @param valueOfConvenienceFeeFixed the fixed convenience fee value
   */
  public final void setValueOfConvenienceFeeFixed(final String valueOfConvenienceFeeFixed) {
    this.valueOfConvenienceFeeFixed = new TagLengthString(MerchantPresentedModeCodes.ID_VALUE_OF_CONVENIENCE_FEE_FIXED, valueOfConvenienceFeeFixed);
  }

  /**
   * Sets the Value of Convenience Fee Percentage (tag 57).
   *
   * @param valueOfConvenienceFeePercentage the convenience fee percentage value
   */
  public final void setValueOfConvenienceFeePercentage(final String valueOfConvenienceFeePercentage) {
    this.valueOfConvenienceFeePercentage = new TagLengthString(MerchantPresentedModeCodes.ID_VALUE_OF_CONVENIENCE_FEE_PERCENTAGE, valueOfConvenienceFeePercentage);
  }

  /**
   * Sets the Country Code (tag 58).
   *
   * @param countryCode the country code (ISO 3166-1 alpha-2)
   */
  public final void setCountryCode(final String countryCode) {
    this.countryCode = new TagLengthString(MerchantPresentedModeCodes.ID_COUNTRY_CODE, countryCode);
  }

  /**
   * Sets the Merchant Name (tag 59).
   *
   * @param merchantName the merchant name
   */
  public final void setMerchantName(final String merchantName) {
    this.merchantName = new TagLengthString(MerchantPresentedModeCodes.ID_MERCHANT_NAME, merchantName);
  }

  /**
   * Sets the Merchant City (tag 60).
   *
   * @param merchantCity the merchant city
   */
  public final void setMerchantCity(final String merchantCity) {
    this.merchantCity = new TagLengthString(MerchantPresentedModeCodes.ID_MERCHANT_CITY, merchantCity);
  }

  /**
   * Sets the Postal Code (tag 61).
   *
   * @param postalCode the postal code
   */
  public final void setPostalCode(final String postalCode) {
    this.postalCode = new TagLengthString(MerchantPresentedModeCodes.ID_POSTAL_CODE, postalCode);
  }

  /**
   * Sets the CRC (tag 63).
   *
   * @param cRC the CRC value
   */
  public final void setCRC(final String cRC) {
    this.cRC = new TagLengthString(MerchantPresentedModeCodes.ID_CRC, cRC);
  }

  /**
   * Adds an Unreserved Template to the QR code data.
   *
   * @param unreserved the unreserved template to add
   */
  public final void addUnreserved(final UnreservedTemplate unreserved) {
    unreserveds.put(unreserved.getTag(), unreserved);
  }

  /**
   * Adds a Merchant Account Information Template to the QR code data.
   *
   * @param merchantAccountInformation the merchant account information template to add
   */
  public final void addMerchantAccountInformation(final MerchantAccountInformationTemplate merchantAccountInformation) {
    this.merchantAccountInformation.put(merchantAccountInformation.getTag(), merchantAccountInformation);
  }

  /**
   * Adds a Reserved for Future Use (RFU) field for EMVCo.
   *
   * @param rFUforEMVCo the RFU field to add
   */
  public final void addRFUforEMVCo(final TagLengthString rFUforEMVCo) {
    this.rFUforEMVCo.put(rFUforEMVCo.getTag(), rFUforEMVCo);
  }

  /**
   * Converts the QR code data to a hexadecimal string representation.
   *
   * @return the hexadecimal encoded string
   */
  public String toHex() {
    return Hex.encodeHexString(toString().getBytes(StandardCharsets.UTF_8), false);
  }

  /**
   * Converts the QR code data to a Base64 encoded string.
   *
   * @return the Base64 encoded string
   */
  public String toBase64() {
    return Base64.encodeBase64String(toString().getBytes(StandardCharsets.UTF_8));
  }

  @Override
  public String toString() {

    final StringBuilder sb = new StringBuilder(toStringWithoutCrc16());

    final String string = sb.toString();

    if (StringUtils.isBlank(string)) {
      return StringUtils.EMPTY;
    }

    final int crc16 = CRC.crc16(sb.toString().getBytes(StandardCharsets.UTF_8));

    sb.append(String.format("%04X", crc16));

    return sb.toString();
  }

  /**
   * Returns the string representation of the QR code data without the CRC16 checksum.
   *
   * @return the QR code data as a string without CRC
   */
  public String toStringWithoutCrc16() {
    final StringBuilder sb = new StringBuilder();

    Optional.ofNullable(payloadFormatIndicator).ifPresent(tlv -> sb.append(tlv.toString()));
    Optional.ofNullable(pointOfInitiationMethod).ifPresent(tlv -> sb.append(tlv.toString()));

    for (final Entry<String, MerchantAccountInformationTemplate> entry : merchantAccountInformation.entrySet()) {
      Optional.ofNullable(entry.getValue()).ifPresent(tlv -> sb.append(tlv.toString()));
    }

    Optional.ofNullable(merchantCategoryCode).ifPresent(tlv -> sb.append(tlv.toString()));
    Optional.ofNullable(transactionCurrency).ifPresent(tlv -> sb.append(tlv.toString()));
    Optional.ofNullable(transactionAmount).ifPresent(tlv -> sb.append(tlv.toString()));
    Optional.ofNullable(tipOrConvenienceIndicator).ifPresent(tlv -> sb.append(tlv.toString()));
    Optional.ofNullable(valueOfConvenienceFeeFixed).ifPresent(tlv -> sb.append(tlv.toString()));
    Optional.ofNullable(valueOfConvenienceFeePercentage).ifPresent(tlv -> sb.append(tlv.toString()));
    Optional.ofNullable(countryCode).ifPresent(tlv -> sb.append(tlv.toString()));
    Optional.ofNullable(merchantName).ifPresent(tlv -> sb.append(tlv.toString()));
    Optional.ofNullable(merchantCity).ifPresent(tlv -> sb.append(tlv.toString()));
    Optional.ofNullable(postalCode).ifPresent(tlv -> sb.append(tlv.toString()));
    Optional.ofNullable(additionalDataField).ifPresent(tlv -> sb.append(tlv.toString()));
    Optional.ofNullable(merchantInformationLanguage).ifPresent(tlv -> sb.append(tlv.toString()));

    for (final Entry<String, TagLengthString> entry : rFUforEMVCo.entrySet()) {
      Optional.ofNullable(entry.getValue()).ifPresent(tlv -> sb.append(tlv.toString()));
    }

    for (final Entry<String, UnreservedTemplate> entry : unreserveds.entrySet()) {
      Optional.ofNullable(entry.getValue()).ifPresent(tlv -> sb.append(tlv.toString()));
    }

    final String string = sb.toString();

    if (StringUtils.isBlank(string)) {
      return StringUtils.EMPTY;
    }

    sb.append(String.format("%s%s", MerchantPresentedModeCodes.ID_CRC, "04"));

    return sb.toString();
  }
}
