package com.emv.qrcode.mpm.model;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import com.emv.qrcode.core.model.DataType;
import com.emv.qrcode.core.model.DrawData;
import com.emv.qrcode.mpm.constants.EMVQRFieldCodes;
import com.emv.qrcode.parsers.Parser;

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
    Optional.ofNullable(value).ifPresent(v -> this.payloadFormatIndicator = new TagLengthString(EMVQRFieldCodes.ID_PAYLOAD_FORMAT_INDICATOR, value));
  }

  public void setPointOfInitiationMethod(final String value) {
    Optional.ofNullable(value).ifPresent(v -> this.pointOfInitiationMethod = new TagLengthString(EMVQRFieldCodes.ID_POINT_OF_INITIATION_METHOD, value));
  }

  public void setMerchantCategoryCode(final String value) {
    Optional.ofNullable(value).ifPresent(v -> this.merchantCategoryCode = new TagLengthString(EMVQRFieldCodes.ID_MERCHANT_CATEGORY_CODE, value));
  }

  public void setTransactionCurrency(final String value) {
    Optional.ofNullable(value).ifPresent(v -> this.transactionCurrency = new TagLengthString(EMVQRFieldCodes.ID_TRANSACTION_CURRENCY, value));
  }

  public void setTransactionAmount(final String value) {
    Optional.ofNullable(value).ifPresent(v -> this.transactionAmount = new TagLengthString(EMVQRFieldCodes.ID_TRANSACTION_AMOUNT, value));
  }

  public void setTipOrConvenienceIndicator(final String value) {
    Optional.ofNullable(value).ifPresent(v -> this.tipOrConvenienceIndicator = new TagLengthString(EMVQRFieldCodes.ID_TIP_OR_CONVENIENCE_INDICATOR, value));
  }

  public void setValueOfConvenienceFeeFixed(final String value) {
    Optional.ofNullable(value).ifPresent(v -> this.valueOfConvenienceFeeFixed = new TagLengthString(EMVQRFieldCodes.ID_VALUE_OF_CONVENIENCE_FEE_FIXED, value));
  }

  public void setValueOfConvenienceFeePercentage(final String value) {
    Optional.ofNullable(value).ifPresent(v -> this.valueOfConvenienceFeePercentage = new TagLengthString(EMVQRFieldCodes.ID_VALUE_OF_CONVENIENCE_FEE_PERCENTAGE, value));
  }

  public void setCountryCode(final String value) {
    Optional.ofNullable(value).ifPresent(v -> this.countryCode = new TagLengthString(EMVQRFieldCodes.ID_COUNTRY_CODE, value));
  }

  public void setMerchantName(final String value) {
    Optional.ofNullable(value).ifPresent(v -> this.merchantName = new TagLengthString(EMVQRFieldCodes.ID_MERCHANT_NAME, value));
  }

  public void setMerchantCity(final String value) {
    Optional.ofNullable(value).ifPresent(v -> this.merchantCity = new TagLengthString(EMVQRFieldCodes.ID_MERCHANT_CITY, value));
  }

  public void setPostalCode(final String value) {
    Optional.ofNullable(value).ifPresent(v -> this.postalCode = new TagLengthString(EMVQRFieldCodes.ID_POSTAL_CODE, value));
  }

  public void setCRC(final String value) {
    Optional.ofNullable(value).ifPresent(v -> this.cRC = new TagLengthString(EMVQRFieldCodes.ID_MERCHANT_NAME, value));
  }

  public void setAdditionalDataFieldTemplate(final String value) {
    Optional.ofNullable(value).ifPresent(v -> this.additionalDataFieldTemplate = Parser.parse(value, AdditionalDataFieldTemplate.class));
  }

  public void setMerchantInformationLanguageTemplate(final String value) {
    Optional.ofNullable(value).ifPresent(v -> this.merchantInformationLanguageTemplate = Parser.parse(value, MerchantInformationLanguageTemplate.class));
  }
  
  public void addMerchantAccountInformation(final String value) {
    Optional.ofNullable(value).ifPresent(v -> {
      final String id = value.substring(0, Parser.ID_WORD_COUNT);
      merchantAccountInformation.put(id, new MerchantAccountInformation(id, value.substring(Parser.ID_WORD_COUNT)));
    });
  }
  
  public void addRFUforEMVCo(final String value) {
    Optional.ofNullable(value).ifPresent(v -> rFUforEMVCo.add(new TagLengthString(value.substring(0, Parser.ID_WORD_COUNT), value.substring(Parser.ID_WORD_COUNT))));
  }
  
  public void addUnreservedTemplates(final String value) {
    Optional.ofNullable(value).ifPresent(v -> {
      final String id = value.substring(0, Parser.ID_WORD_COUNT);
      unreservedTemplates.put(id, new UnreservedTemplate(id, value.substring(Parser.ID_WORD_COUNT)));
    });
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
