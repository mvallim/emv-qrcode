package com.emv.qrcode.mpm.model;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import com.emv.qrcode.core.DataType;
import com.emv.qrcode.core.DrawData;
import com.emv.qrcode.mpm.constants.EMVQRConstants;

import lombok.Getter;

@Getter
public class EMVQR implements Serializable, DrawData {

  private static final long serialVersionUID = 485352878727448583L;

  // Payload Format Indicator
  private TagLengthString payloadFormatIndicator;

  // Point of Initiation Method
  private TagLengthString pointOfInitiationMethod;

  // Merchant Account Information
  private final Map<String, MerchantAccountInformation> merchantAccountInformation = new LinkedHashMap<>();

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
  private AdditionalDataFieldTemplate additionalDataFieldTemplate;

  // CRC
  private TagLengthString cRC;

  // Merchant Information - Language Template
  private MerchantInformationLanguageTemplate merchantInformationLanguageTemplate;

  // RFU for EMVCo
  private final List<TagLengthString> rFUforEMVCo = new LinkedList<>();

  // Unreserved Templates
  private final Map<String, UnreservedTemplate> unreservedTemplates = new LinkedHashMap<>();

  public void setPayloadFormatIndicator(final String value) {
    payloadFormatIndicator = new TagLengthString(EMVQRConstants.ID_MERCHANT_NAME, value.length(), value);
  }

  public void setPointOfInitiationMethod(final String value) {
    pointOfInitiationMethod = new TagLengthString(EMVQRConstants.ID_POINT_OF_INITIATION_METHOD, value.length(), value);
  }

  public void setMerchantCategoryCode(final String value) {
    merchantCategoryCode = new TagLengthString(EMVQRConstants.ID_MERCHANT_CATEGORY_CODE, value.length(), value);
  }

  public void setTransactionCurrency(final String value) {
    transactionCurrency = new TagLengthString(EMVQRConstants.ID_TRANSACTION_CURRENCY, value.length(), value);
  }

  public void setTransactionAmount(final String value) {
    transactionAmount = new TagLengthString(EMVQRConstants.ID_TRANSACTION_AMOUNT, value.length(), value);
  }

  public void setTipOrConvenienceIndicator(final String value) {
    tipOrConvenienceIndicator = new TagLengthString(EMVQRConstants.ID_TIP_OR_CONVENIENCE_INDICATOR, value.length(), value);
  }

  public void setValueOfConvenienceFeeFixed(final String value) {
    valueOfConvenienceFeeFixed = new TagLengthString(EMVQRConstants.ID_VALUE_OF_CONVENIENCE_FEE_FIXED, value.length(), value);
  }

  public void setValueOfConvenienceFeePercentage(final String value) {
    valueOfConvenienceFeePercentage = new TagLengthString(EMVQRConstants.ID_VALUE_OF_CONVENIENCE_FEE_PERCENTAGE, value.length(), value);
  }

  public void setCountryCode(final String value) {
    countryCode = new TagLengthString(EMVQRConstants.ID_COUNTRY_CODE, value.length(), value);
  }

  public void setMerchantName(final String value) {
    merchantName = new TagLengthString(EMVQRConstants.ID_MERCHANT_NAME, value.length(), value);
  }

  public void setMerchantCity(final String value) {
    merchantCity = new TagLengthString(EMVQRConstants.ID_MERCHANT_CITY, value.length(), value);
  }

  public void setPostalCode(final String value) {
    postalCode = new TagLengthString(EMVQRConstants.ID_POSTAL_CODE, value.length(), value);
  }

  public void setCRC(final String value) {
    cRC = new TagLengthString(EMVQRConstants.ID_MERCHANT_NAME, value.length(), value);
  }

  public String binaryData() {
    return draw(DataType.BINARY);
  }

  public String rawData() {
    return draw(DataType.RAW);
  }

  // TODO: Implements this after
  public String jsonData() {
    return null;
  }

  @Override
  public String draw(final DataType type) {

    final StringBuilder sb = new StringBuilder();

    Optional.ofNullable(payloadFormatIndicator).ifPresent(tlv -> sb.append(tlv.draw(type)));
    Optional.ofNullable(pointOfInitiationMethod).ifPresent(tlv -> sb.append(tlv.draw(type)));

    for (final Entry<String, MerchantAccountInformation> entry : merchantAccountInformation.entrySet()) {
      sb.append(" ");
      Optional.ofNullable(entry.getValue()).ifPresent(tlv -> sb.append(tlv.draw(type)));
    }

    Optional.ofNullable(merchantCategoryCode).ifPresent(tlv -> sb.append(tlv.draw(type)));
    Optional.ofNullable(transactionCurrency).ifPresent(tlv -> sb.append(tlv.draw(type)));
    Optional.ofNullable(transactionAmount).ifPresent(tlv -> sb.append(tlv.draw(type)));
    Optional.ofNullable(tipOrConvenienceIndicator).ifPresent(tlv -> sb.append(tlv.draw(type)));
    Optional.ofNullable(valueOfConvenienceFeeFixed).ifPresent(tlv -> sb.append(tlv.draw(type)));
    Optional.ofNullable(valueOfConvenienceFeePercentage).ifPresent(tlv -> sb.append(tlv.draw(type)));
    Optional.ofNullable(countryCode).ifPresent(tlv -> sb.append(tlv.draw(type)));
    Optional.ofNullable(merchantName).ifPresent(tlv -> sb.append(tlv.draw(type)));
    Optional.ofNullable(merchantCity).ifPresent(tlv -> sb.append(tlv.draw(type)));
    Optional.ofNullable(postalCode).ifPresent(tlv -> sb.append(tlv.draw(type)));
    Optional.ofNullable(additionalDataFieldTemplate).ifPresent(tlv -> sb.append(tlv.draw(type)));
    sb.append(" ");

    Optional.ofNullable(merchantInformationLanguageTemplate).ifPresent(tlv -> sb.append(tlv.draw(type)));
    sb.append(" ");

    for (final TagLengthString tagLengthString : rFUforEMVCo) {
      sb.append(" ");
      Optional.ofNullable(tagLengthString).ifPresent(tlv -> sb.append(tlv.draw(type)));
    }

    for (final Entry<String, UnreservedTemplate> entry : unreservedTemplates.entrySet()) {
      sb.append(" ");
      Optional.ofNullable(entry.getValue()).ifPresent(tlv -> sb.append(tlv.draw(type)));
    }

    Optional.ofNullable(cRC).ifPresent(tlv -> sb.append(tlv.draw(type)));

    return sb.toString();
  }

}
