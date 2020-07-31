package com.emv.qrcode.mpm.model;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import com.emv.qrcode.core.Parser;
import com.emv.qrcode.core.model.DataType;
import com.emv.qrcode.core.model.DrawData;
import com.emv.qrcode.mpm.constants.EMVQRFieldCodes;

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

  public EMVQR(final String value) {
    final Parser parser = Parser.parse(value);

    while (parser.hasNext()) {
      switch (parser.getId()) {
        case EMVQRFieldCodes.ID_PAYLOAD_FORMAT_INDICATOR:
          setPayloadFormatIndicator(parser.next());
          break;
        case EMVQRFieldCodes.ID_POINT_OF_INITIATION_METHOD:
          setPointOfInitiationMethod(parser.next());
          break;
        case EMVQRFieldCodes.ID_MERCHANT_CATEGORY_CODE:
          setMerchantCategoryCode(parser.next());
          break;
        case EMVQRFieldCodes.ID_TRANSACTION_CURRENCY:
          setTransactionCurrency(parser.next());
          break;
        case EMVQRFieldCodes.ID_TRANSACTION_AMOUNT:
          setTransactionAmount(parser.next());
          break;
        case EMVQRFieldCodes.ID_TIP_OR_CONVENIENCE_INDICATOR:
          setTipOrConvenienceIndicator(parser.next());
          break;
        case EMVQRFieldCodes.ID_VALUE_OF_CONVENIENCE_FEE_FIXED:
          setValueOfConvenienceFeeFixed(parser.next());
          break;
        case EMVQRFieldCodes.ID_VALUE_OF_CONVENIENCE_FEE_PERCENTAGE:
          setValueOfConvenienceFeePercentage(parser.next());
          break;
        case EMVQRFieldCodes.ID_COUNTRY_CODE:
          setCountryCode(parser.next());
          break;
        case EMVQRFieldCodes.ID_MERCHANT_NAME:
          setMerchantName(parser.next());
          break;
        case EMVQRFieldCodes.ID_MERCHANT_CITY:
          setMerchantCity(parser.next());
          break;
        case EMVQRFieldCodes.ID_POSTAL_CODE:
          setPostalCode(parser.next());
          break;
        case EMVQRFieldCodes.ID_CRC:
          setCRC(parser.next());
          break;
        case EMVQRFieldCodes.ID_ADDITIONAL_DATA_FIELD_TEMPLATE:
          break;
        case EMVQRFieldCodes.ID_MERCHANT_INFORMATION_LANGUAGE_TEMPLATE:
          break;
        default:
          break;
      }
    }
  }

  public void setPayloadFormatIndicator(final String value) {
    payloadFormatIndicator = new TagLengthString(EMVQRFieldCodes.ID_MERCHANT_NAME, value);
  }

  public void setPointOfInitiationMethod(final String value) {
    pointOfInitiationMethod = new TagLengthString(EMVQRFieldCodes.ID_POINT_OF_INITIATION_METHOD, value);
  }

  public void setMerchantCategoryCode(final String value) {
    merchantCategoryCode = new TagLengthString(EMVQRFieldCodes.ID_MERCHANT_CATEGORY_CODE, value);
  }

  public void setTransactionCurrency(final String value) {
    transactionCurrency = new TagLengthString(EMVQRFieldCodes.ID_TRANSACTION_CURRENCY, value);
  }

  public void setTransactionAmount(final String value) {
    transactionAmount = new TagLengthString(EMVQRFieldCodes.ID_TRANSACTION_AMOUNT, value);
  }

  public void setTipOrConvenienceIndicator(final String value) {
    tipOrConvenienceIndicator = new TagLengthString(EMVQRFieldCodes.ID_TIP_OR_CONVENIENCE_INDICATOR, value);
  }

  public void setValueOfConvenienceFeeFixed(final String value) {
    valueOfConvenienceFeeFixed = new TagLengthString(EMVQRFieldCodes.ID_VALUE_OF_CONVENIENCE_FEE_FIXED, value);
  }

  public void setValueOfConvenienceFeePercentage(final String value) {
    valueOfConvenienceFeePercentage = new TagLengthString(EMVQRFieldCodes.ID_VALUE_OF_CONVENIENCE_FEE_PERCENTAGE, value);
  }

  public void setCountryCode(final String value) {
    countryCode = new TagLengthString(EMVQRFieldCodes.ID_COUNTRY_CODE, value);
  }

  public void setMerchantName(final String value) {
    merchantName = new TagLengthString(EMVQRFieldCodes.ID_MERCHANT_NAME, value);
  }

  public void setMerchantCity(final String value) {
    merchantCity = new TagLengthString(EMVQRFieldCodes.ID_MERCHANT_CITY, value);
  }

  public void setPostalCode(final String value) {
    postalCode = new TagLengthString(EMVQRFieldCodes.ID_POSTAL_CODE, value);
  }

  public void setCRC(final String value) {
    cRC = new TagLengthString(EMVQRFieldCodes.ID_MERCHANT_NAME, value);
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

    Optional.ofNullable(merchantInformationLanguageTemplate).ifPresent(tlv -> sb.append(tlv.draw(type)));

    for (final TagLengthString tagLengthString : rFUforEMVCo) {
      Optional.ofNullable(tagLengthString).ifPresent(tlv -> sb.append(tlv.draw(type)));
    }

    for (final Entry<String, UnreservedTemplate> entry : unreservedTemplates.entrySet()) {
      Optional.ofNullable(entry.getValue()).ifPresent(tlv -> sb.append(tlv.draw(type)));
    }

    Optional.ofNullable(cRC).ifPresent(tlv -> sb.append(tlv.draw(type)));

    return sb.toString();
  }

}
