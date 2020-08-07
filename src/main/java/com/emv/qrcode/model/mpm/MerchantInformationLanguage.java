package com.emv.qrcode.model.mpm;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.emv.qrcode.core.model.TLV;
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
  private final List<TagLengthString> rFUforEMVCo = new LinkedList<>();

  public final void setLanguagePreference(final String languagePreference) {
    this.languagePreference = new TagLengthString(MerchantInformationLanguageFieldCodes.ID_LANGUAGE_PREFERENCE, languagePreference);
  }

  public final void setMerchantName(final String merchantName) {
    this.merchantName = new TagLengthString(MerchantInformationLanguageFieldCodes.ID_MERCHANT_NAME, merchantName);
  }

  public final void setMerchantCity(final String merchantCity) {
    this.merchantCity = new TagLengthString(MerchantInformationLanguageFieldCodes.ID_MERCHANT_CITY, merchantCity);
  }

  public void setLanguagePreference(final TagLengthString languagePreference) {
    this.languagePreference = languagePreference;
  }

  public void setMerchantName(final TagLengthString merchantName) {
    this.merchantName = merchantName;
  }

  public void setMerchantCity(final TagLengthString merchantCity) {
    this.merchantCity = merchantCity;
  }

  public void addRFUforEMVCo(final TagLengthString tagLengthString) {
    rFUforEMVCo.add(tagLengthString);
  }

  @Override
  public String toString() {

    final StringBuilder sb = new StringBuilder();

    Optional.ofNullable(languagePreference).ifPresent(tlv -> sb.append(tlv.toString()));
    Optional.ofNullable(merchantName).ifPresent(tlv -> sb.append(tlv.toString()));
    Optional.ofNullable(merchantCity).ifPresent(tlv -> sb.append(tlv.toString()));

    for (final TLV<String, String> tagLengthString : rFUforEMVCo) {
      Optional.ofNullable(tagLengthString).ifPresent(tlv -> sb.append(tlv.toString()));
    }

    final String string = sb.toString();

    if (StringUtils.isBlank(string)) {
      return StringUtils.EMPTY;
    }

    return string;
  }

}
