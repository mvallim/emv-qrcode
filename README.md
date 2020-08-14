# EMV QRCode

[![Build Status](https://travis-ci.org/mvallim/emv-qrcode.svg?branch=master)](https://travis-ci.org/mvallim/emv-qrcode)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=emv-qrcode&metric=alert_status)](https://sonarcloud.io/dashboard?id=emv-qrcode)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=emv-qrcode&metric=coverage)](https://sonarcloud.io/dashboard?id=emv-qrcode)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.mvallim/emv-qrcode/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.mvallim/emv-qrcode)
[![Hex.pm](https://img.shields.io/hexpm/l/plug.svg)](http://www.apache.org/licenses/LICENSE-2.0)

Java Based EMV QR Code Generator and Parser (MPM, CPM)

## Specification

- [EMV QR Code Specification for Payment Systems: Merchant-Presented Mode](docs/EMVCo-Merchant-Presented-QR-Specification-v1-1.pdf)
- [EMV QR Code Specification for Payment Systems: Consumer Presented Mode](docs/EMVCo-Consumer-Presented-QR-Specification-v1-1.pdf)
- [EMV Book 3 Application Specification](docs/EMV_v4.3_Book_3_Application_Specification_20120607062110791.pdf)
- [EMV Book 4 Other Interfaces](docs/EMV_v4.3_Book_4_Other_Interfaces_20120607062305603.pdf)

## 1. Quick Start

This chapter will show you how to get started with EMV QR Code.

### 1.1 Prerequisite

In order to use EMV QR Code within a Maven project, simply add the following dependency to your pom.xml. There are no other dependencies for EMV QR Code, which means other unwanted libraries will not overwhelm your project.

You can pull it from the central Maven repositories:

```xml
<dependency>
    <groupId>com.github.mvallim</groupId>
    <artifactId>emv-qrcode</artifactId>
    <version>0.0.2</version>
</dependency>
```

If you want to try a snapshot version, add the following repository:

```xml
<repository>
    <id>sonatype-snapshots</id>
    <name>Sonatype Snapshots</name>
    <url>https://oss.sonatype.org/content/repositories/snapshots</url>
    <snapshots>
        <enabled>true</enabled>
    </snapshots>
</repository>
```

### MPM (Merchant Presented Mode) Encode

```java
final AdditionalDataFieldTemplate additionalDataField = getAddtionalDataField();
final MerchantAccountInformationTemplate merchantAccountInformation = getMerchanAccountInformation();
final MerchantInformationLanguageTemplate merchantInformationLanguage = getMerchantInformationLanguage();
final UnreservedTemplate unreserved = getUnreserved();
final TagLengthString rFUforEMVCo = new TagLengthString("65", "00");

final MerchantPresentedMode merchantPresentMode = new MerchantPresentedMode();
merchantPresentMode.setAdditionalDataField(additionalDataField);
merchantPresentMode.setCountryCode("CN");
merchantPresentMode.setMerchantCategoryCode("4111");
merchantPresentMode.setMerchantCity("BEIJING");
merchantPresentMode.setMerchantInformationLanguage(merchantInformationLanguage);
merchantPresentMode.setMerchantName("BEST TRANSPORT");
merchantPresentMode.setPayloadFormatIndicator("01");
merchantPresentMode.setPointOfInitiationMethod("11");
merchantPresentMode.setPostalCode("1234567");
merchantPresentMode.setTipOrConvenienceIndicator("02");
merchantPresentMode.setTransactionAmount("23.72");
merchantPresentMode.setTransactionCurrency("156");
merchantPresentMode.setValueOfConvenienceFeeFixed("500");
merchantPresentMode.addMerchantAccountInformation(merchantAccountInformation);
merchantPresentMode.addRFUforEMVCo(rFUforEMVCo);
merchantPresentMode.addUnreserved(unreserved);

System.out.println(merchantPresentMode.toString());
    //"00020101021102160004hoge0104abcd520441115303156540523.7255020256035005802CN5914BEST TRANSPORT6007BEIJING6107123456762800205678900305098760505abcde0705klmno0805pqres0903tuv1004abcd5016000412340104ijkl64280002ZH0102北京0204最佳运输0304abcd65020080320016A01122334499887707081234567863046325";

private MerchantAccountInformationTemplate getMerchanAccountInformation() {
    final TagLengthString paymentNetworkSpecific = new TagLengthString();
    paymentNetworkSpecific.setTag("01");
    paymentNetworkSpecific.setValue("abcd");

    final MerchantAccountInformation merchantAccountInformationValue = new MerchantAccountInformation();
    merchantAccountInformationValue.setGloballyUniqueIdentifier("hoge");
    merchantAccountInformationValue.addPaymentNetworkSpecific(paymentNetworkSpecific);

    final MerchantAccountInformationTemplate merchantAccountInformation = new MerchantAccountInformationTemplate();
    merchantAccountInformation.setValue(merchantAccountInformationValue);
    merchantAccountInformation.setTag("02");

    return merchantAccountInformation;
}

private UnreservedTemplate getUnreserved() {
    final TagLengthString contextSpecificData = new TagLengthString();
    contextSpecificData.setTag("07");
    contextSpecificData.setValue("12345678");

    final Unreserved value = new Unreserved();
    value.setGloballyUniqueIdentifier("A011223344998877");
    value.addContextSpecificData(contextSpecificData);

    final UnreservedTemplate unreserved = new UnreservedTemplate();
    unreserved.setValue(value);
    unreserved.setTag("80");

    return unreserved;
}

private MerchantInformationLanguageTemplate getMerchantInformationLanguage() {
    final TagLengthString rFUforEMVCo = new TagLengthString();
    rFUforEMVCo.setTag("03");
    rFUforEMVCo.setValue("abcd");

    final MerchantInformationLanguage merchantInformationLanguageValue = new MerchantInformationLanguage();
    merchantInformationLanguageValue.setLanguagePreference("ZH");
    merchantInformationLanguageValue.setMerchantName("北京");
    merchantInformationLanguageValue.setMerchantCity("最佳运输");
    merchantInformationLanguageValue.addRFUforEMVCo(rFUforEMVCo);

    final MerchantInformationLanguageTemplate merchantInformationLanguage = new MerchantInformationLanguageTemplate();
    merchantInformationLanguage.setValue(merchantInformationLanguageValue);

    return merchantInformationLanguage;
}

private AdditionalDataFieldTemplate getAddtionalDataField() {
    final PaymentSystemSpecific paymentSystemSpecific = new PaymentSystemSpecific();
    paymentSystemSpecific.setGloballyUniqueIdentifier("1234");
    paymentSystemSpecific.addPaymentSystemSpecific(new TagLengthString("01", "ijkl"));

    final PaymentSystemSpecificTemplate paymentSystemSpecificTemplate = new PaymentSystemSpecificTemplate();
    paymentSystemSpecificTemplate.setTag("50");
    paymentSystemSpecificTemplate.setValue(paymentSystemSpecific);

    final TagLengthString rFUforEMVCo = new TagLengthString();
    rFUforEMVCo.setTag("10");
    rFUforEMVCo.setValue("abcd");

    final AdditionalDataField additionalDataFieldValue = new AdditionalDataField();
    additionalDataFieldValue.setAdditionalConsumerDataRequest("tuv");
    additionalDataFieldValue.setMobileNumber("67890");
    additionalDataFieldValue.setPurposeTransaction("pqres");
    additionalDataFieldValue.setReferenceLabel("abcde");
    additionalDataFieldValue.setStoreLabel("09876");
    additionalDataFieldValue.setTerminalLabel("klmno");
    additionalDataFieldValue.addPaymentSystemSpecific(paymentSystemSpecificTemplate);
    additionalDataFieldValue.addRFUforEMVCo(rFUforEMVCo);

    final AdditionalDataFieldTemplate additionalDataField = new AdditionalDataFieldTemplate();
    additionalDataField.setValue(additionalDataFieldValue);

    return additionalDataField;
}
```

### MPM (Merchant Presented Mode) Decode

```java
@Test
public void testSuccessDecode() {
    final String encoded = "00020101021102160004hoge0104abcd520441115303156540523.7255020256035005802CN5914BEST TRANSPORT6007BEIJING6107123456762800205678900305098760505abcde0705klmno0805pqres0903tuv1004abcd5016000412340104ijkl64280002ZH0102北京0204最佳运输0304abcd65020080320016A01122334499887707081234567863046325";

    final MerchantPresentedMode merchantPresentedMode = Decoder.decode(encoded, MerchantPresentedMode.class);

    assertThat(merchantPresentedMode.getCountryCode().getValue(), equalTo("CN"));
    assertThat(merchantPresentedMode.getMerchantCategoryCode().getValue(), equalTo("4111"));
    assertThat(merchantPresentedMode.getMerchantCity().getValue(), equalTo("BEIJING"));
    assertThat(merchantPresentedMode.getMerchantName().getValue(), equalTo("BEST TRANSPORT"));
    assertThat(merchantPresentedMode.getPayloadFormatIndicator().getValue(), equalTo("01"));
    assertThat(merchantPresentedMode.getPointOfInitiationMethod().getValue(), equalTo("11"));
    assertThat(merchantPresentedMode.getPostalCode().getValue(), equalTo("1234567"));
    assertThat(merchantPresentedMode.getTipOrConvenienceIndicator().getValue(), equalTo("02"));
    assertThat(merchantPresentedMode.getTransactionAmount().getValue(), equalTo("23.72"));
    assertThat(merchantPresentedMode.getTransactionCurrency().getValue(), equalTo("156"));
    assertThat(merchantPresentedMode.getValueOfConvenienceFeeFixed().getValue(), equalTo("500"));
}
```

### MPM (Merchant Presented Mode) Validate

```java
@Test
public void testSuccessValidate() {

    final MerchantPresentedMode merchantPresentMode = new MerchantPresentedMode();

    merchantPresentMode.setCountryCode("CN");
    merchantPresentMode.setMerchantCategoryCode("4111");
    merchantPresentMode.setMerchantCity("BEIJING");
    merchantPresentMode.setMerchantName("BEST TRANSPORT");
    merchantPresentMode.setPayloadFormatIndicator("01");
    merchantPresentMode.setPointOfInitiationMethod("11");
    merchantPresentMode.setPostalCode("1234567");
    merchantPresentMode.setTipOrConvenienceIndicator("02");
    merchantPresentMode.setTransactionAmount("23.72");
    merchantPresentMode.setTransactionCurrency("156");
    merchantPresentMode.setValueOfConvenienceFeeFixed("500");

    final ValidationResult validationResult = MerchantPresentedModeValidate.validate(merchantPresentMode);

    assertTrue(validationResult.isValid());
}
```

### CPM (Consumer Presented Mode)

```java
// Coming soon
```

## Contributing

Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [GitHub](https://github.com/mvallim/emv-qrcode) for versioning. For the versions available, see the [tags on this repository](https://github.com/mvallim/emv-qrcode/tags).

## Authors

* **Marcos Vallim** - *Initial work, Development, Test, Documentation* - [mvallim](https://github.com/mvallim)

See also the list of [contributors](CONTRIBUTORS.txt) who participated in this project.

## License

This project is licensed under the Apache License - see the [LICENSE](LICENSE) file for details
