package com.emv.qrcode.model.mpm;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;

import com.emv.qrcode.core.CRC;
import com.emv.qrcode.core.model.SimpleTLV;
import com.emv.qrcode.core.model.TagLengthString;
import com.emv.qrcode.model.mpm.constants.MerchantPresentedModeCodes;

import lombok.Getter;

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
  private final List<TagLengthString> rFUforEMVCo = new LinkedList<>();

  // Unreserved Templates
  private final Map<String, UnreservedTemplate> unreserveds = new LinkedHashMap<>();
  
  public void setPayloadFormatIndicator(final TagLengthString payloadFormatIndicator) {
    this.payloadFormatIndicator = payloadFormatIndicator;
  }

  public void setPointOfInitiationMethod(final TagLengthString pointOfInitiationMethod) {
    this.pointOfInitiationMethod = pointOfInitiationMethod;
  }

  public void setMerchantCategoryCode(final TagLengthString merchantCategoryCode) {
    this.merchantCategoryCode = merchantCategoryCode;
  }

  public void setTransactionCurrency(final TagLengthString transactionCurrency) {
    this.transactionCurrency = transactionCurrency;
  }

  public void setTransactionAmount(final TagLengthString transactionAmount) {
    this.transactionAmount = transactionAmount;
  }

  public void setTipOrConvenienceIndicator(final TagLengthString tipOrConvenienceIndicator) {
    this.tipOrConvenienceIndicator = tipOrConvenienceIndicator;
  }

  public void setValueOfConvenienceFeeFixed(final TagLengthString valueOfConvenienceFeeFixed) {
    this.valueOfConvenienceFeeFixed = valueOfConvenienceFeeFixed;
  }

  public void setValueOfConvenienceFeePercentage(final TagLengthString valueOfConvenienceFeePercentage) {
    this.valueOfConvenienceFeePercentage = valueOfConvenienceFeePercentage;
  }

  public void setCountryCode(final TagLengthString countryCode) {
    this.countryCode = countryCode;
  }

  public void setMerchantName(final TagLengthString merchantName) {
    this.merchantName = merchantName;
  }

  public void setMerchantCity(final TagLengthString merchantCity) {
    this.merchantCity = merchantCity;
  }

  public void setPostalCode(final TagLengthString postalCode) {
    this.postalCode = postalCode;
  }

  public void setAdditionalDataField(final AdditionalDataFieldTemplate additionalDataField) {
    this.additionalDataField = additionalDataField;
  }

  public void setCRC(final TagLengthString cRC) {
    this.cRC = cRC;
  }

  public void setMerchantInformationLanguage(final MerchantInformationLanguageTemplate merchantInformationLanguage) {
    this.merchantInformationLanguage = merchantInformationLanguage;
  }
  
  public final void setPayloadFormatIndicator(final String payloadFormatIndicator) {
    this.payloadFormatIndicator = new TagLengthString(MerchantPresentedModeCodes.ID_PAYLOAD_FORMAT_INDICATOR, payloadFormatIndicator);
  }

  public final void setPointOfInitiationMethod(final String pointOfInitiationMethod) {
    this.pointOfInitiationMethod = new TagLengthString(MerchantPresentedModeCodes.ID_POINT_OF_INITIATION_METHOD, pointOfInitiationMethod);
  }

  public final void setMerchantCategoryCode(final String merchantCategoryCode) {
    this.merchantCategoryCode = new TagLengthString(MerchantPresentedModeCodes.ID_MERCHANT_CATEGORY_CODE, merchantCategoryCode);
  }

  public final void setTransactionCurrency(final String transactionCurrency) {
    this.transactionCurrency = new TagLengthString(MerchantPresentedModeCodes.ID_TRANSACTION_CURRENCY, transactionCurrency);
  }

  public final void setTransactionAmount(final String transactionAmount) {
    this.transactionAmount = new TagLengthString(MerchantPresentedModeCodes.ID_TRANSACTION_AMOUNT, transactionAmount);
  }

  public final void setTipOrConvenienceIndicator(final String tipOrConvenienceIndicator) {
    this.tipOrConvenienceIndicator = new TagLengthString(MerchantPresentedModeCodes.ID_TIP_OR_CONVENIENCE_INDICATOR, tipOrConvenienceIndicator);
  }

  public final void setValueOfConvenienceFeeFixed(final String valueOfConvenienceFeeFixed) {
    this.valueOfConvenienceFeeFixed = new TagLengthString(MerchantPresentedModeCodes.ID_VALUE_OF_CONVENIENCE_FEE_FIXED, valueOfConvenienceFeeFixed);
  }

  public final void setValueOfConvenienceFeePercentage(final String valueOfConvenienceFeePercentage) {
    this.valueOfConvenienceFeePercentage = new TagLengthString(MerchantPresentedModeCodes.ID_VALUE_OF_CONVENIENCE_FEE_PERCENTAGE, valueOfConvenienceFeePercentage);
  }

  public final void setCountryCode(final String countryCode) {
    this.countryCode = new TagLengthString(MerchantPresentedModeCodes.ID_COUNTRY_CODE, countryCode);
  }

  public final void setMerchantName(final String merchantName) {
    this.merchantName = new TagLengthString(MerchantPresentedModeCodes.ID_MERCHANT_NAME, merchantName);
  }

  public final void setMerchantCity(final String merchantCity) {
    this.merchantCity = new TagLengthString(MerchantPresentedModeCodes.ID_MERCHANT_CITY, merchantCity);
  }

  public final void setPostalCode(final String postalCode) {
    this.postalCode = new TagLengthString(MerchantPresentedModeCodes.ID_POSTAL_CODE, postalCode);
  }
  
  public final void setCRC(final String cRC) {
    this.cRC = new TagLengthString(MerchantPresentedModeCodes.ID_CRC, cRC);;
  }

  public void addUnreserved(final UnreservedTemplate unreserved) {
    this.unreserveds.put(unreserved.getTag(), unreserved);
  }

  public void addMerchantAccountInformation(final MerchantAccountInformationTemplate merchantAccountInformation) {
    this.merchantAccountInformation.put(merchantAccountInformation.getTag(), merchantAccountInformation);
  }

  public void addRFUforEMVCo(final TagLengthString rFUforEMVCo) {
    this.rFUforEMVCo.add(rFUforEMVCo);
  }

  public String toHex() {
    return Hex.encodeHexString(toString().getBytes(StandardCharsets.UTF_8), false);
  }

  public String toBase64() {
    return Base64.encodeBase64String(toString().getBytes(StandardCharsets.UTF_8));
  }

  @Override
  public String toString() {

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

    for (final SimpleTLV<String> tagLengthString : rFUforEMVCo) {
      Optional.ofNullable(tagLengthString).ifPresent(tlv -> sb.append(tlv.toString()));
    }

    for (final Entry<String, UnreservedTemplate> entry : unreserveds.entrySet()) {
      Optional.ofNullable(entry.getValue()).ifPresent(tlv -> sb.append(tlv.toString()));
    }

    final String string = sb.toString();

    if (StringUtils.isBlank(string)) {
      return StringUtils.EMPTY;
    }

    sb.append(MerchantPresentedModeCodes.ID_CRC + "04");

    sb.append(Integer.toHexString(CRC.crc16(sb.toString().getBytes(StandardCharsets.UTF_8))).toUpperCase());

    return sb.toString();
  }

}
