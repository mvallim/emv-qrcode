package com.emv.qrcode.model.cpm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;

import org.apache.commons.codec.binary.Hex;

import com.emv.qrcode.core.model.BERTLString;
import com.emv.qrcode.model.cpm.constants.TagTransactionProcessingCodes;

import lombok.Getter;

@Getter
public abstract class AdditionalData implements Serializable {

  private static final long serialVersionUID = -2275311356136693642L;

  private BERTLString applicationDefinitionFileName;

  private BERTLString applicationLabel;

  private BERTLString track2EquivalentData;

  private BERTLString applicationPAN;

  private BERTLString cardholderName;

  private BERTLString languagePreference;

  private BERTLString issuerURL;

  private BERTLString applicationVersionNumber;

  private BERTLString tokenRequestorID;

  private BERTLString paymentAccountReference;

  private BERTLString last4DigitsOfPAN;

  private BERTLString cryptogramInformationData;

  private BERTLString applicationTransactionCounter;

  private BERTLString applicationCryptogram;

  private BERTLString issuerApplicationData;

  public void setApplicationDefinitionFileName(final BERTLString applicationDefinitionFileName) {
    this.applicationDefinitionFileName = applicationDefinitionFileName;
  }

  public void setApplicationLabel(final BERTLString applicationLabel) {
    this.applicationLabel = applicationLabel;
  }

  public void setTrack2EquivalentData(final BERTLString track2EquivalentData) {
    this.track2EquivalentData = track2EquivalentData;
  }

  public void setApplicationPAN(final BERTLString applicationPAN) {
    this.applicationPAN = applicationPAN;
  }

  public void setCardholderName(final BERTLString cardholderName) {
    this.cardholderName = cardholderName;
  }

  public void setLanguagePreference(final BERTLString languagePreference) {
    this.languagePreference = languagePreference;
  }

  public void setIssuerURL(final BERTLString issuerURL) {
    this.issuerURL = issuerURL;
  }

  public void setApplicationVersionNumber(final BERTLString applicationVersionNumber) {
    this.applicationVersionNumber = applicationVersionNumber;
  }

  public void setTokenRequestorID(final BERTLString tokenRequestorID) {
    this.tokenRequestorID = tokenRequestorID;
  }

  public void setPaymentAccountReference(final BERTLString paymentAccountReference) {
    this.paymentAccountReference = paymentAccountReference;
  }

  public void setLast4DigitsOfPAN(final BERTLString last4DigitsOfPAN) {
    this.last4DigitsOfPAN = last4DigitsOfPAN;
  }

  public void setCryptogramInformationData(final BERTLString cryptogramInformationData) {
    this.cryptogramInformationData = cryptogramInformationData;
  }

  public void setApplicationTransactionCounter(final BERTLString applicationTransactionCounter) {
    this.applicationTransactionCounter = applicationTransactionCounter;
  }

  public void setApplicationCryptogram(final BERTLString applicationCryptogram) {
    this.applicationCryptogram = applicationCryptogram;
  }

  public void setIssuerApplicationData(final BERTLString issuerApplicationData) {
    this.issuerApplicationData = issuerApplicationData;
  }

  public final void setApplicationDefinitionFileName(final String applicationDefinitionFileName) {
    this.applicationDefinitionFileName = new BERTLString(TagTransactionProcessingCodes.ID_APPLICATION_DEFINITION_FILE_NAME, applicationDefinitionFileName);
  }

  public final void setApplicationLabel(final String applicationLabel) {
    this.applicationLabel = new BERTLString(TagTransactionProcessingCodes.ID_APPLICATION_LABEL, applicationLabel);
  }

  public final void setTrack2EquivalentData(final String track2EquivalentData) {
    this.track2EquivalentData = new BERTLString(TagTransactionProcessingCodes.ID_TRACK_2_EQUIVALENT_DATA, track2EquivalentData);
  }

  public final void setApplicationPAN(final String applicationPAN) {
    this.applicationPAN = new BERTLString(TagTransactionProcessingCodes.ID_APPLICATION_PAN, applicationPAN);
  }

  public final void setCardholderName(final String cardholderName) {
    this.cardholderName = new BERTLString(TagTransactionProcessingCodes.ID_CARDHOLDER_NAME, cardholderName);
  }

  public final void setLanguagePreference(final String languagePreference) {
    this.languagePreference = new BERTLString(TagTransactionProcessingCodes.ID_LANGUAGE_PREFERENCE, languagePreference);
  }

  public final void setIssuerURL(final String issuerURL) {
    this.issuerURL = new BERTLString(TagTransactionProcessingCodes.ID_ISSUER_URL, issuerURL);
  }

  public final void setApplicationVersionNumber(final String applicationVersionNumber) {
    this.applicationVersionNumber = new BERTLString(TagTransactionProcessingCodes.ID_APPLICATION_VERSION_NUMBER, applicationVersionNumber);
  }

  public final void setTokenRequestorID(final String tokenRequestorID) {
    this.tokenRequestorID = new BERTLString(TagTransactionProcessingCodes.ID_TOKEN_REQUESTOR_ID, tokenRequestorID);
  }

  public final void setPaymentAccountReference(final String paymentAccountReference) {
    this.paymentAccountReference = new BERTLString(TagTransactionProcessingCodes.ID_PAYMENT_ACCOUNT_REFERENCE, paymentAccountReference);
  }

  public final void setLast4DigitsOfPAN(final String last4DigitsOfPAN) {
    this.last4DigitsOfPAN = new BERTLString(TagTransactionProcessingCodes.ID_LAST_4_DIGITS_OF_PAN, last4DigitsOfPAN);
  }

  public final void setCryptogramInformationData(final String cryptogramInformationData) {
    this.cryptogramInformationData = new BERTLString(TagTransactionProcessingCodes.ID_CRYPTOGRAM_INFORMATION_DATA, cryptogramInformationData);
  }

  public final void setApplicationTransactionCounter(final String applicationTransactionCounter) {
    this.applicationTransactionCounter = new BERTLString(TagTransactionProcessingCodes.ID_APPLICATION_TRANSACTION_COUNTER, applicationTransactionCounter);
  }

  public final void setApplicationCryptogram(final String applicationCryptogram) {
    this.applicationCryptogram = new BERTLString(TagTransactionProcessingCodes.ID_APPLICATION_CRYPTOGRAM, applicationCryptogram);
  }

  public final void setIssuerApplicationData(final String issuerApplicationData) {
    this.issuerApplicationData = new BERTLString(TagTransactionProcessingCodes.ID_ISSUER_APPLICATION_DATA, issuerApplicationData);
  }

  public byte[] getBytes() throws IOException {
    try (final ByteArrayOutputStream out = new ByteArrayOutputStream()) {

      if (Objects.nonNull(applicationDefinitionFileName)) {
        out.write(applicationDefinitionFileName.getBytes());
      }

      if (Objects.nonNull(applicationLabel)) {
        out.write(applicationLabel.getBytes());
      }

      if (Objects.nonNull(track2EquivalentData)) {
        out.write(track2EquivalentData.getBytes());
      }

      if (Objects.nonNull(applicationPAN)) {
        out.write(applicationPAN.getBytes());
      }

      if (Objects.nonNull(cardholderName)) {
        out.write(cardholderName.getBytes());
      }

      if (Objects.nonNull(languagePreference)) {
        out.write(languagePreference.getBytes());
      }

      if (Objects.nonNull(issuerURL)) {
        out.write(issuerURL.getBytes());
      }

      if (Objects.nonNull(applicationVersionNumber)) {
        out.write(applicationVersionNumber.getBytes());
      }

      if (Objects.nonNull(tokenRequestorID)) {
        out.write(tokenRequestorID.getBytes());
      }

      if (Objects.nonNull(paymentAccountReference)) {
        out.write(paymentAccountReference.getBytes());
      }

      if (Objects.nonNull(last4DigitsOfPAN)) {
        out.write(last4DigitsOfPAN.getBytes());
      }

      if (Objects.nonNull(cryptogramInformationData)) {
        out.write(cryptogramInformationData.getBytes());
      }

      if (Objects.nonNull(applicationTransactionCounter)) {
        out.write(applicationTransactionCounter.getBytes());
      }

      if (Objects.nonNull(applicationCryptogram)) {
        out.write(applicationCryptogram.getBytes());
      }

      if (Objects.nonNull(issuerApplicationData)) {
        out.write(issuerApplicationData.getBytes());
      }

      return out.toByteArray();
    }
  }

  public String toHex() throws IOException {
    return Hex.encodeHexString(getBytes(), false);
  }

}
