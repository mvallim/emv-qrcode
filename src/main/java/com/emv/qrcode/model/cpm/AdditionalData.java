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
import com.emv.qrcode.core.model.cpm.BERTLNumeric;
import com.emv.qrcode.core.model.cpm.BERTLV;
import com.emv.qrcode.core.model.cpm.BERTag;
import com.emv.qrcode.model.cpm.constants.TagTransactionProcessingCodes;

import lombok.Getter;

@Getter
public abstract class AdditionalData implements Serializable {

  private static final long serialVersionUID = -2275311356136693642L;

  private final Map<BERTag, BERTLV> additionalDataMap = new LinkedHashMap<>();

  public final void addAdditionalData(final BERTLV bertlv) {
    if (Objects.nonNull(bertlv)) {
      additionalDataMap.put(bertlv.getTag(), bertlv);
    }
  }

  public final void setApplicationDefinitionFileName(final String applicationDefinitionFileName) {
    addAdditionalData(new BERTLBinary(TagTransactionProcessingCodes.ID_APPLICATION_DEFINITION_FILE_NAME, applicationDefinitionFileName));
  }

  public final void setApplicationLabel(final String applicationLabel) {
    addAdditionalData(new BERTLAlphanumeric(TagTransactionProcessingCodes.ID_APPLICATION_LABEL, applicationLabel));
  }

  public final void setTrack2EquivalentData(final String track2EquivalentData) {
    addAdditionalData(new BERTLBinary(TagTransactionProcessingCodes.ID_TRACK_2_EQUIVALENT_DATA, track2EquivalentData));
  }

  public final void setApplicationPAN(final String applicationPAN) {
    addAdditionalData(new BERTLNumeric(TagTransactionProcessingCodes.ID_APPLICATION_PAN, applicationPAN));
  }

  public final void setCardholderName(final String cardholderName) {
    addAdditionalData(new BERTLAlphanumeric(TagTransactionProcessingCodes.ID_CARDHOLDER_NAME, cardholderName));
  }

  public final void setLanguagePreference(final String languagePreference) {
    addAdditionalData(new BERTLAlphanumeric(TagTransactionProcessingCodes.ID_LANGUAGE_PREFERENCE, languagePreference));
  }

  public final void setIssuerURL(final String issuerURL) {
    addAdditionalData(new BERTLAlphanumeric(TagTransactionProcessingCodes.ID_ISSUER_URL, issuerURL));
  }

  public final void setApplicationVersionNumber(final String applicationVersionNumber) {
    addAdditionalData(new BERTLBinary(TagTransactionProcessingCodes.ID_APPLICATION_VERSION_NUMBER, applicationVersionNumber));
  }

  public final void setTokenRequestorID(final String tokenRequestorID) {
    addAdditionalData(new BERTLNumeric(TagTransactionProcessingCodes.ID_TOKEN_REQUESTOR_ID, tokenRequestorID));
  }

  public final void setPaymentAccountReference(final String paymentAccountReference) {
    addAdditionalData(new BERTLAlphanumeric(TagTransactionProcessingCodes.ID_PAYMENT_ACCOUNT_REFERENCE, paymentAccountReference));
  }

  public final void setLast4DigitsOfPAN(final String last4DigitsOfPAN) {
    addAdditionalData(new BERTLAlphanumeric(TagTransactionProcessingCodes.ID_LAST_4_DIGITS_OF_PAN, last4DigitsOfPAN));
  }

  public final void setCryptogramInformationData(final String cryptogramInformationData) {
    addAdditionalData(new BERTLAlphanumeric(TagTransactionProcessingCodes.ID_CRYPTOGRAM_INFORMATION_DATA, cryptogramInformationData));
  }

  public final void setApplicationTransactionCounter(final String applicationTransactionCounter) {
    addAdditionalData(new BERTLBinary(TagTransactionProcessingCodes.ID_APPLICATION_TRANSACTION_COUNTER, applicationTransactionCounter));
  }

  public final void setApplicationCryptogram(final String applicationCryptogram) {
    addAdditionalData(new BERTLBinary(TagTransactionProcessingCodes.ID_APPLICATION_CRYPTOGRAM, applicationCryptogram));
  }

  public final void setIssuerApplicationData(final String issuerApplicationData) {
    addAdditionalData(new BERTLBinary(TagTransactionProcessingCodes.ID_ISSUER_APPLICATION_DATA, issuerApplicationData));
  }

  public final void setUnpredictableNumber(final String unpredictableNumber) {
    addAdditionalData(new BERTLBinary(TagTransactionProcessingCodes.ID_UNPREDICTABLE_NUMBER, unpredictableNumber));
  }

  public final BERTLV getAdditionalData(final BERTag tag) {
    return additionalDataMap.get(tag);
  }

  public final BERTLV getApplicationDefinitionFileName() {
    return getAdditionalData(TagTransactionProcessingCodes.ID_APPLICATION_DEFINITION_FILE_NAME);
  }

  public final BERTLV getApplicationLabel() {
    return getAdditionalData(TagTransactionProcessingCodes.ID_APPLICATION_LABEL);
  }

  public final BERTLV getTrack2EquivalentData() {
    return getAdditionalData(TagTransactionProcessingCodes.ID_TRACK_2_EQUIVALENT_DATA);
  }

  public final BERTLV getApplicationPAN() {
    return getAdditionalData(TagTransactionProcessingCodes.ID_APPLICATION_PAN);
  }

  public final BERTLV getCardholderName() {
    return getAdditionalData(TagTransactionProcessingCodes.ID_CARDHOLDER_NAME);
  }

  public final BERTLV getLanguagePreference() {
    return getAdditionalData(TagTransactionProcessingCodes.ID_LANGUAGE_PREFERENCE);
  }

  public final BERTLV getIssuerURL() {
    return getAdditionalData(TagTransactionProcessingCodes.ID_ISSUER_URL);
  }

  public final BERTLV getApplicationVersionNumber() {
    return getAdditionalData(TagTransactionProcessingCodes.ID_APPLICATION_VERSION_NUMBER);
  }

  public final BERTLV getTokenRequestorID() {
    return getAdditionalData(TagTransactionProcessingCodes.ID_TOKEN_REQUESTOR_ID);
  }

  public final BERTLV getPaymentAccountReference() {
    return getAdditionalData(TagTransactionProcessingCodes.ID_PAYMENT_ACCOUNT_REFERENCE);
  }

  public final BERTLV getLast4DigitsOfPAN() {
    return getAdditionalData(TagTransactionProcessingCodes.ID_LAST_4_DIGITS_OF_PAN);
  }

  public final BERTLV getCryptogramInformationData() {
    return getAdditionalData(TagTransactionProcessingCodes.ID_CRYPTOGRAM_INFORMATION_DATA);
  }

  public final BERTLV getApplicationTransactionCounter() {
    return getAdditionalData(TagTransactionProcessingCodes.ID_APPLICATION_TRANSACTION_COUNTER);
  }

  public final BERTLV getApplicationCryptogram() {
    return getAdditionalData(TagTransactionProcessingCodes.ID_APPLICATION_CRYPTOGRAM);
  }

  public final BERTLV getIssuerApplicationData() {
    return getAdditionalData(TagTransactionProcessingCodes.ID_ISSUER_APPLICATION_DATA);
  }

  public final BERTLV getUnpredictableNumber() {
    return getAdditionalData(TagTransactionProcessingCodes.ID_UNPREDICTABLE_NUMBER);
  }

  public byte[] getBytes() throws IOException {
    try (final ByteArrayOutputStream out = new ByteArrayOutputStream()) {

      for (final Entry<BERTag, BERTLV> entry : additionalDataMap.entrySet()) {
        out.write(entry.getValue().getBytes());
      }

      return out.toByteArray();
    }
  }

}
