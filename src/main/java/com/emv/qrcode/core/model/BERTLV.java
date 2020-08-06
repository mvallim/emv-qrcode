package com.emv.qrcode.core.model;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class BERTLV implements Serializable {

  private static final long serialVersionUID = 5805525412681335642L;

  public static final String TAG_APPLICATION_DEFINITION_FILE_NAME = "4F";
  public static final String TAG_APPLICATION_LABEL = "50";
  public static final String TAG_TRACK2_EQUIVALENT_DATA = "57";
  public static final String TAG_APPLICATION_PAN = "5A";
  public static final String TAG_CARDHOLDER_NAME = "5F20";
  public static final String TAG_LANGUAGE_PREFERENCE = "5F2D";
  public static final String TAG_ISSUER_URL = "5F50";
  public static final String TAG_APPLICATION_VERSION_NUMBER = "9F08";
  public static final String TAG_TOKEN_REQUESTOR_ID = "9F19";
  public static final String TAG_PAYMENT_ACCOUNT_REFERENCE = "9F24";
  public static final String TAG_LAST4_DIGITS_OF_PAN = "9F25";

  // Application Dedicated File (ADF) Name (4F)
  private TagLengthString applicationDefinitionFileName;

  // Application Label (50)
  private TagLengthString applicationLabel;

  // Track 2 Equivalent Data (57)
  private TagLengthString track2EquivalentData;

  // Application PAN (5A)
  private TagLengthString applicationPAN;

  // Cardholder Name (5F20)
  private TagLengthString cardholderName;

  // Language Preference (5F2D)
  private TagLengthString languagePreference;

  // Issuer URL (5F50)
  private TagLengthString issuerURL;

  // Application Version Number (9F08)
  private TagLengthString applicationVersionNumber;

  // Token Requestor ID (9F19)
  private TagLengthString tokenRequestorID;

  // Payment Account Reference (9F24)
  private TagLengthString paymentAccountReference;

  // Last 4 Digits of PAN (9F25)
  private TagLengthString last4DigitsOfPAN;

  public final void setApplicationDefinitionFileName(final String applicationDefinitionFileName) {
    this.applicationDefinitionFileName = new TagLengthString(TAG_APPLICATION_DEFINITION_FILE_NAME, applicationDefinitionFileName);
  }

  public final void setApplicationLabel(final String applicationLabel) {
    this.applicationLabel = new TagLengthString(TAG_APPLICATION_LABEL, applicationLabel);
  }

  public final void setTrack2EquivalentData(final String track2EquivalentData) {
    this.track2EquivalentData = new TagLengthString(TAG_TRACK2_EQUIVALENT_DATA, track2EquivalentData);
  }

  public final void setApplicationPAN(final String applicationPAN) {
    this.applicationPAN = new TagLengthString(TAG_APPLICATION_PAN, applicationPAN);
  }

  public final void setCardholderName(final String cardholderName) {
    this.cardholderName = new TagLengthString(TAG_CARDHOLDER_NAME, cardholderName);
  }

  public final void setLanguagePreference(final String languagePreference) {
    this.languagePreference = new TagLengthString(TAG_LANGUAGE_PREFERENCE, languagePreference);
  }

  public final void setIssuerURL(final String issuerURL) {
    this.issuerURL = new TagLengthString(TAG_ISSUER_URL, issuerURL);
  }

  public final void setApplicationVersionNumber(final String applicationVersionNumber) {
    this.applicationVersionNumber = new TagLengthString(TAG_APPLICATION_VERSION_NUMBER, applicationVersionNumber);
  }

  public final void setTokenRequestorID(final String tokenRequestorID) {
    this.tokenRequestorID = new TagLengthString(TAG_TOKEN_REQUESTOR_ID, tokenRequestorID);
  }

  public final void setPaymentAccountReference(final String paymentAccountReference) {
    this.paymentAccountReference = new TagLengthString(TAG_PAYMENT_ACCOUNT_REFERENCE, paymentAccountReference);
  }

  public final void setLast4DigitsOfPAN(final String last4DigitsOfPAN) {
    this.last4DigitsOfPAN = new TagLengthString(TAG_LAST4_DIGITS_OF_PAN, last4DigitsOfPAN);
  }

  public void setApplicationDefinitionFileName(final TagLengthString applicationDefinitionFileName) {
    this.applicationDefinitionFileName = applicationDefinitionFileName;
  }

  public void setApplicationLabel(final TagLengthString applicationLabel) {
    this.applicationLabel = applicationLabel;
  }

  public void setTrack2EquivalentData(final TagLengthString track2EquivalentData) {
    this.track2EquivalentData = track2EquivalentData;
  }

  public void setApplicationPAN(final TagLengthString applicationPAN) {
    this.applicationPAN = applicationPAN;
  }

  public void setCardholderName(final TagLengthString cardholderName) {
    this.cardholderName = cardholderName;
  }

  public void setLanguagePreference(final TagLengthString languagePreference) {
    this.languagePreference = languagePreference;
  }

  public void setIssuerURL(final TagLengthString issuerURL) {
    this.issuerURL = issuerURL;
  }

  public void setApplicationVersionNumber(final TagLengthString applicationVersionNumber) {
    this.applicationVersionNumber = applicationVersionNumber;
  }

  public void setTokenRequestorID(final TagLengthString tokenRequestorID) {
    this.tokenRequestorID = tokenRequestorID;
  }

  public void setPaymentAccountReference(final TagLengthString paymentAccountReference) {
    this.paymentAccountReference = paymentAccountReference;
  }

  public void setLast4DigitsOfPAN(final TagLengthString last4DigitsOfPAN) {
    this.last4DigitsOfPAN = last4DigitsOfPAN;
  }

}
