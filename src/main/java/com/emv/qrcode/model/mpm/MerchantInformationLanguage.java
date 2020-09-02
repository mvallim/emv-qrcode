package com.emv.qrcode.model.mpm;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.emv.qrcode.core.model.TagLengthString;
import com.emv.qrcode.model.mpm.constants.MerchantInformationLanguageFieldCodes;

import lombok.Getter;

@Getter
public class MerchantInformationLanguage implements Serializable {

  private static final long serialVersionUID = 6163271793010568887L;

  // Language Preference
  private TagLengthString languagePreference;

  // Merchant Name
  private TagLengthString merchantName;

  // Merchant City
  private TagLengthString merchantCity;

  // RFU for EMVCo
  private final Map<String, TagLengthString> rFUforEMVCo = new LinkedHashMap<>();

  public final void setLanguagePreference(final String languagePreference) {
    this.languagePreference = new TagLengthString(MerchantInformationLanguageFieldCodes.ID_LANGUAGE_PREFERENCE, languagePreference);
  }

  public final void setMerchantName(final String merchantName) {
    this.merchantName = new TagLengthString(MerchantInformationLanguageFieldCodes.ID_MERCHANT_NAME, merchantName);
  }

  public final void setMerchantCity(final String merchantCity) {
    this.merchantCity = new TagLengthString(MerchantInformationLanguageFieldCodes.ID_MERCHANT_CITY, merchantCity);
  }

  public final void addRFUforEMVCo(final TagLengthString tagLengthString) {
    rFUforEMVCo.put(tagLengthString.getTag(), tagLengthString);
  }

  @Override
  public String toString() {

    final StringBuilder sb = new StringBuilder();

    Optional.ofNullable(languagePreference).ifPresent(tlv -> sb.append(tlv.toString()));
    Optional.ofNullable(merchantName).ifPresent(tlv -> sb.append(tlv.toString()));
    Optional.ofNullable(merchantCity).ifPresent(tlv -> sb.append(tlv.toString()));

    for (final Entry<String, TagLengthString> entry : rFUforEMVCo.entrySet()) {
      Optional.ofNullable(entry.getValue()).ifPresent(tlv -> sb.append(tlv.toString()));
    }

    final String string = sb.toString();

    if (StringUtils.isBlank(string)) {
      return StringUtils.EMPTY;
    }

    return string;
  }

}
