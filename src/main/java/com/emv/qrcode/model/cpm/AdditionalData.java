package com.emv.qrcode.model.cpm;

import com.emv.qrcode.core.model.BERTLV;

import lombok.Getter;

@Getter
public class AdditionalData {

  private BERTLV<String, String> applicationDefinitionFileName;

  private BERTLV<String, String> applicationLabel;

  private BERTLV<String, String> track2EquivalentData;

  private BERTLV<String, String> applicationPAN;

  private BERTLV<String, String> cardholderName;

  private BERTLV<String, String> languagePreference;

  private BERTLV<String, String> issuerURL;

  private BERTLV<String, String> applicationVersionNumber;

  private BERTLV<String, String> tokenRequestorID;

  private BERTLV<String, String> paymentAccountReference;

  private BERTLV<String, String> last4DigitsOfPAN;

  private BERTLV<String, String> cryptogramInformationData;

  private BERTLV<String, String> applicationTransactionCounter;

  private BERTLV<String, String> applicationCryptogram;

  private BERTLV<String, String> issuerApplicationData;

}
