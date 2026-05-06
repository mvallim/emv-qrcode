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
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import com.emv.qrcode.core.model.cpm.BERTLAlphanumeric;
import com.emv.qrcode.core.model.cpm.BERTLBinary;
import com.emv.qrcode.core.model.cpm.BERTLCompressedNumeric;
import com.emv.qrcode.core.model.cpm.BERTLNumeric;
import com.emv.qrcode.core.model.cpm.BERTLV;
import com.emv.qrcode.core.model.cpm.BERTag;
import com.emv.qrcode.model.cpm.constants.TagTransactionProcessingCodes;

import lombok.Getter;

/**
 * Abstract base class for additional data elements in Consumer Presented Mode (CPM) QR codes.
 * This class provides a map to store various BER-TLV data elements and methods to set/get them.
 *
 * @see BERTLV
 * @see BERTag
 */
@Getter
public abstract class AdditionalData implements Serializable {

  private static final long serialVersionUID = -2275311356136693642L;

  private final Map<BERTag, BERTLV> additionalDataMap = new LinkedHashMap<>();

  /**
   * Adds an additional data element to the map.
   *
   * @param bertlv the BER-TLV element to add
   */
  public final void addAdditionalData(final BERTLV bertlv) {
    if (Objects.nonNull(bertlv)) {
      additionalDataMap.put(bertlv.getTag(), bertlv);
    }
  }

  /**
   * Sets the Application Definition File Name (tag 4F).
   *
   * @param applicationDefinitionFileName the hex-encoded application definition file name
   */
  public final void setApplicationDefinitionFileName(final String applicationDefinitionFileName) {
    addAdditionalData(new BERTLBinary(TagTransactionProcessingCodes.ID_APPLICATION_DEFINITION_FILE_NAME, applicationDefinitionFileName));
  }

  /**
   * Sets the Application Label (tag 50).
   *
   * @param applicationLabel the application label
   */
  public final void setApplicationLabel(final String applicationLabel) {
    addAdditionalData(new BERTLAlphanumeric(TagTransactionProcessingCodes.ID_APPLICATION_LABEL, applicationLabel));
  }

  /**
   * Sets the Track 2 Equivalent Data (tag 57).
   *
   * @param track2EquivalentData the hex-encoded track 2 equivalent data
   */
  public final void setTrack2EquivalentData(final String track2EquivalentData) {
    addAdditionalData(new BERTLBinary(TagTransactionProcessingCodes.ID_TRACK_2_EQUIVALENT_DATA, track2EquivalentData));
  }

  /**
   * Sets the Application PAN (tag 5A).
   *
   * @param applicationPAN the compressed numeric application PAN
   */
  public final void setApplicationPAN(final String applicationPAN) {
    addAdditionalData(new BERTLCompressedNumeric(TagTransactionProcessingCodes.ID_APPLICATION_PAN, applicationPAN));
  }

  /**
   * Sets the Cardholder Name (tag 5F20).
   *
   * @param cardholderName the cardholder name
   */
  public final void setCardholderName(final String cardholderName) {
    addAdditionalData(new BERTLAlphanumeric(TagTransactionProcessingCodes.ID_CARDHOLDER_NAME, cardholderName));
  }

  /**
   * Sets the Language Preference (tag 5F2D).
   *
   * @param languagePreference the language preference (ISO 639-1 codes)
   */
  public final void setLanguagePreference(final String languagePreference) {
    addAdditionalData(new BERTLAlphanumeric(TagTransactionProcessingCodes.ID_LANGUAGE_PREFERENCE, languagePreference));
  }

  /**
   * Sets the Issuer URL (tag 5F50).
   *
   * @param issuerURL the issuer URL
   */
  public final void setIssuerURL(final String issuerURL) {
    addAdditionalData(new BERTLAlphanumeric(TagTransactionProcessingCodes.ID_ISSUER_URL, issuerURL));
  }

  /**
   * Sets the Application Version Number (tag 9F08).
   *
   * @param applicationVersionNumber the hex-encoded application version number
   */
  public final void setApplicationVersionNumber(final String applicationVersionNumber) {
    addAdditionalData(new BERTLBinary(TagTransactionProcessingCodes.ID_APPLICATION_VERSION_NUMBER, applicationVersionNumber));
  }

  /**
   * Sets the Token Requestor ID (tag 9F19).
   *
   * @param tokenRequestorID the numeric token requestor ID
   */
  public final void setTokenRequestorID(final String tokenRequestorID) {
    addAdditionalData(new BERTLNumeric(TagTransactionProcessingCodes.ID_TOKEN_REQUESTOR_ID, tokenRequestorID));
  }

  /**
   * Sets the Payment Account Reference (tag 9F24).
   *
   * @param paymentAccountReference the payment account reference
   */
  public final void setPaymentAccountReference(final String paymentAccountReference) {
    addAdditionalData(new BERTLAlphanumeric(TagTransactionProcessingCodes.ID_PAYMENT_ACCOUNT_REFERENCE, paymentAccountReference));
  }

  /**
   * Sets the Last 4 Digits of PAN (tag 9F27).
   *
   * @param last4DigitsOfPAN the numeric last 4 digits of PAN
   */
  public final void setLast4DigitsOfPAN(final String last4DigitsOfPAN) {
    addAdditionalData(new BERTLNumeric(TagTransactionProcessingCodes.ID_LAST_4_DIGITS_OF_PAN, last4DigitsOfPAN));
  }

  /**
   * Sets the Cryptogram Information Data (tag 9F27).
   *
   * @param cryptogramInformationData the hex-encoded cryptogram information data
   */
  public final void setCryptogramInformationData(final String cryptogramInformationData) {
    addAdditionalData(new BERTLBinary(TagTransactionProcessingCodes.ID_CRYPTOGRAM_INFORMATION_DATA, cryptogramInformationData));
  }

  /**
   * Sets the Application Transaction Counter (tag 9F36).
   *
   * @param applicationTransactionCounter the numeric application transaction counter
   */
  public final void setApplicationTransactionCounter(final String applicationTransactionCounter) {
    addAdditionalData(new BERTLNumeric(TagTransactionProcessingCodes.ID_APPLICATION_TRANSACTION_COUNTER, applicationTransactionCounter));
  }

  /**
   * Sets the Application Cryptogram (tag 9F26).
   *
   * @param applicationCryptogram the hex-encoded application cryptogram
   */
  public final void setApplicationCryptogram(final String applicationCryptogram) {
    addAdditionalData(new BERTLBinary(TagTransactionProcessingCodes.ID_APPLICATION_CRYPTOGRAM, applicationCryptogram));
  }

  /**
   * Sets the Issuer Application Data (tag 9F10).
   *
   * @param issuerApplicationData the hex-encoded issuer application data
   */
  public final void setIssuerApplicationData(final String issuerApplicationData) {
    addAdditionalData(new BERTLBinary(TagTransactionProcessingCodes.ID_ISSUER_APPLICATION_DATA, issuerApplicationData));
  }

  /**
   * Sets the Unpredictable Number (tag 9F37).
   *
   * @param unpredictableNumber the hex-encoded unpredictable number
   */
  public final void setUnpredictableNumber(final String unpredictableNumber) {
    addAdditionalData(new BERTLBinary(TagTransactionProcessingCodes.ID_UNPREDICTABLE_NUMBER, unpredictableNumber));
  }

  /**
   * Gets an additional data element by its BER tag.
   *
   * @param tag the BER tag
   * @return the BER-TLV element, or null if not found
   */
  public final BERTLV getAdditionalData(final BERTag tag) {
    return additionalDataMap.get(tag);
  }

  /**
   * Gets the Application Definition File Name.
   *
   * @return the BER-TLV element, or null if not set
   */
  public final BERTLV getApplicationDefinitionFileName() {
    return getAdditionalData(TagTransactionProcessingCodes.ID_APPLICATION_DEFINITION_FILE_NAME);
  }

  /**
   * Gets the Application Label.
   *
   * @return the BER-TLV element, or null if not set
   */
  public final BERTLV getApplicationLabel() {
    return getAdditionalData(TagTransactionProcessingCodes.ID_APPLICATION_LABEL);
  }

  /**
   * Gets the Track 2 Equivalent Data.
   *
   * @return the BER-TLV element, or null if not set
   */
  public final BERTLV getTrack2EquivalentData() {
    return getAdditionalData(TagTransactionProcessingCodes.ID_TRACK_2_EQUIVALENT_DATA);
  }

  /**
   * Gets the Application PAN.
   *
   * @return the BER-TLV element, or null if not set
   */
  public final BERTLV getApplicationPAN() {
    return getAdditionalData(TagTransactionProcessingCodes.ID_APPLICATION_PAN);
  }

  /**
   * Gets the Cardholder Name.
   *
   * @return the BER-TLV element, or null if not set
   */
  public final BERTLV getCardholderName() {
    return getAdditionalData(TagTransactionProcessingCodes.ID_CARDHOLDER_NAME);
  }

  /**
   * Gets the Language Preference.
   *
   * @return the BER-TLV element, or null if not set
   */
  public final BERTLV getLanguagePreference() {
    return getAdditionalData(TagTransactionProcessingCodes.ID_LANGUAGE_PREFERENCE);
  }

  /**
   * Gets the Issuer URL.
   *
   * @return the BER-TLV element, or null if not set
   */
  public final BERTLV getIssuerURL() {
    return getAdditionalData(TagTransactionProcessingCodes.ID_ISSUER_URL);
  }

  /**
   * Gets the Application Version Number.
   *
   * @return the BER-TLV element, or null if not set
   */
  public final BERTLV getApplicationVersionNumber() {
    return getAdditionalData(TagTransactionProcessingCodes.ID_APPLICATION_VERSION_NUMBER);
  }

  /**
   * Gets the Token Requestor ID.
   *
   * @return the BER-TLV element, or null if not set
   */
  public final BERTLV getTokenRequestorID() {
    return getAdditionalData(TagTransactionProcessingCodes.ID_TOKEN_REQUESTOR_ID);
  }

  /**
   * Gets the Payment Account Reference.
   *
   * @return the BER-TLV element, or null if not set
   */
  public final BERTLV getPaymentAccountReference() {
    return getAdditionalData(TagTransactionProcessingCodes.ID_PAYMENT_ACCOUNT_REFERENCE);
  }

  /**
   * Gets the Last 4 Digits of PAN.
   *
   * @return the BER-TLV element, or null if not set
   */
  public final BERTLV getLast4DigitsOfPAN() {
    return getAdditionalData(TagTransactionProcessingCodes.ID_LAST_4_DIGITS_OF_PAN);
  }

  /**
   * Gets the Cryptogram Information Data.
   *
   * @return the BER-TLV element, or null if not set
   */
  public final BERTLV getCryptogramInformationData() {
    return getAdditionalData(TagTransactionProcessingCodes.ID_CRYPTOGRAM_INFORMATION_DATA);
  }

  /**
   * Gets the Application Transaction Counter.
   *
   * @return the BER-TLV element, or null if not set
   */
  public final BERTLV getApplicationTransactionCounter() {
    return getAdditionalData(TagTransactionProcessingCodes.ID_APPLICATION_TRANSACTION_COUNTER);
  }

  /**
   * Gets the Application Cryptogram.
   *
   * @return the BER-TLV element, or null if not set
   */
  public final BERTLV getApplicationCryptogram() {
    return getAdditionalData(TagTransactionProcessingCodes.ID_APPLICATION_CRYPTOGRAM);
  }

  /**
   * Gets the Issuer Application Data.
   *
   * @return the BER-TLV element, or null if not set
   */
  public final BERTLV getIssuerApplicationData() {
    return getAdditionalData(TagTransactionProcessingCodes.ID_ISSUER_APPLICATION_DATA);
  }

  /**
   * Gets the Unpredictable Number.
   *
   * @return the BER-TLV element, or null if not set
   */
  public final BERTLV getUnpredictableNumber() {
    return getAdditionalData(TagTransactionProcessingCodes.ID_UNPREDICTABLE_NUMBER);
  }

  /**
   * Converts all additional data elements to a byte array.
   *
   * @return the concatenated bytes of all additional data elements
   * @throws IOException if an I/O error occurs
   */
  public byte[] getBytes() throws IOException {
    try (final ByteArrayOutputStream out = new ByteArrayOutputStream()) {

      for (final Entry<BERTag, BERTLV> entry : additionalDataMap.entrySet()) {
        out.write(entry.getValue().getBytes());
      }

      return out.toByteArray();
    }
  }

}
