package com.x4fare.emvqrcode;

import br.gov.bcb.brcode.BRCodeConstants;
import com.emv.qrcode.core.CRC;
import com.emv.qrcode.core.isos.Country;
import com.emv.qrcode.core.isos.Currency;
import com.emv.qrcode.core.model.TagLengthString;
import com.emv.qrcode.decoder.mpm.DecoderMpm;
import com.emv.qrcode.model.mpm.*;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

// https://www.bcb.gov.br/content/estabilidadefinanceira/pix/Regulamento_Pix/II_ManualdePadroesparaIniciacaodoPix-versao1.pdf
// https://www.bcb.gov.br/content/estabilidadefinanceira/SiteAssets/Manual%20do%20BR%20Code.pdf
public class BasicBRCodeTest {

    @Test
    public void testEncodeSampleBRCodeSemCartao() throws IllegalAccessException {
//        Para fins estritamente ilustrativos, apresenta-se a estrutura de informações, dentro
//        do padrão BR Code, que comporão o QR Code para a iniciação de uma transação de
//        um arranjo de cartão (campo 4), de um PIX (campo 26) e de outro arranjo que seja
//        aceito por um determinado recebedor (campo 27).
//        0002 01
//        0414 12345678901234 <<<< REMOVIDO DESSE TESTE :)
//        2666
//        0014 BR.GOV.BCB.PIX
//        0144 66756C616E6F32303139406578616D706C652E636F6D
//        2730
//        0012 BR.COM.OUTRO
//        0110 0123456789
//        5204 0000
//        5303 986
//        5406 123.45
//        5802 BR
//        5915 NOMEDORECEBEDOR
//        6008 BRASILIA
//        6108 70074900
//        6253
//        0515 RP12345678-2019
//        5030
//        0017 BR.GOV.BCB.BRCODE
//        0105 1.0.0
//        8045
//        0014 BR.GOV.BCB.PIX
//        0123 PADRAO.URL.PIX/0123ABCD
//        8139
//        0012 BR.COM.OUTRO
//        0119 0123.ABCD.3456.WXYZ
//        6304 EB76
//        String sampleBrCode1 = "00020104141234567890123426660014BR.GOV.BCB.PIX014466756C616E6F32303139406578616" +
//                "D706C652E636F6D27300012BR.COM.OUTRO011001234567895204000053039865406123.45580" +
//                "2BR5915NOMEDORECEBEDOR6008BRASILIA61087007490062530515RP12345678-" +
//                "201950300017BR.GOV.BCB.BRCODE01051.0.080450014BR.GOV.BCB.PIX0123PADRAO.URL.PIX/0" +
//                "123ABCD81390012BR.COM.OUTRO01190123.ABCD.3456.WXYZ6304EB76";
        String sampleBrCode1 = "00020126660014br.gov.bcb.pix014466756C616E6F32303139406578616" +
                "D706C652E636F6D27300012BR.COM.OUTRO011001234567895204000053039865406123.45580" +
                "2BR5915NOMEDORECEBEDOR6008BRASILIA61087007490062530515RP12345678-" +
                "201950300017br.gov.bcb.brcode01051.0.080450014br.gov.bcb.pix0123PADRAO.URL.PIX/0" +
                "123ABCD81390012BR.COM.OUTRO01190123.ABCD.3456.WXYZ630405C6";

        MerchantAccountInformationTemplate merchantAccountInformationCard = new MerchantAccountInformationTemplate();
        merchantAccountInformationCard.setTag("04");
        merchantAccountInformationCard.setValue(new MerchantAccountInformation());
        merchantAccountInformationCard.getValue().addPaymentNetworkSpecific(new TagLengthString("","12345678901234"));

        MerchantAccountInformationTemplate merchantAccountInformationPix = new MerchantAccountInformationTemplate();
        merchantAccountInformationPix.setTag("26");
        merchantAccountInformationPix.setValue(new MerchantAccountInformation());
        merchantAccountInformationPix.getValue().setGloballyUniqueIdentifier(BRCodeConstants.PIX_GUI);
        merchantAccountInformationPix.getValue().addPaymentNetworkSpecific(new TagLengthString("01", "66756C616E6F32303139406578616D706C652E636F6D"));

        MerchantAccountInformationTemplate merchantAccountInformationOutro = new MerchantAccountInformationTemplate();
        merchantAccountInformationOutro.setTag("27");
        merchantAccountInformationOutro.setValue(new MerchantAccountInformation());
        merchantAccountInformationOutro.getValue().setGloballyUniqueIdentifier("BR.COM.OUTRO");
        merchantAccountInformationOutro.getValue().addPaymentNetworkSpecific(new TagLengthString("01", "0123456789"));

        final MerchantPresentedMode merchantPresentMode = new MerchantPresentedMode();
        merchantPresentMode.setPayloadFormatIndicator("01");

        merchantPresentMode.setMerchantCategoryCode("0000");
        merchantPresentMode.setTransactionCurrency(Currency.BRL.getNumber());
        merchantPresentMode.setTransactionAmount("123.45");
        merchantPresentMode.setCountryCode(Country.BR.getAlpha2());

        merchantPresentMode.setMerchantCity("BRASILIA");
        merchantPresentMode.setMerchantName("NOMEDORECEBEDOR");
        merchantPresentMode.setPostalCode("70074900");

        // Merchant accounts
//        merchantPresentMode.addMerchantAccountInformation(merchantAccountInformationCard);
        merchantPresentMode.addMerchantAccountInformation(merchantAccountInformationPix);
        merchantPresentMode.addMerchantAccountInformation(merchantAccountInformationOutro);

        // Additional Data Field
        AdditionalDataFieldTemplate additionalDataFieldTemplate = new AdditionalDataFieldTemplate();
        AdditionalDataField additionalDataField = new AdditionalDataField();
        additionalDataField.setReferenceLabel("RP12345678-2019");
        PaymentSystemSpecificTemplate paymentSystemSpecificTemplate = new PaymentSystemSpecificTemplate();
        paymentSystemSpecificTemplate.setTag("50");
        PaymentSystemSpecific paymentSystemSpecificValue = new PaymentSystemSpecific();
        paymentSystemSpecificValue.setGloballyUniqueIdentifier(BRCodeConstants.BRCODE_GUI);
        paymentSystemSpecificValue.addPaymentSystemSpecific(new TagLengthString("01", "1.0.0"));
        paymentSystemSpecificTemplate.setValue(paymentSystemSpecificValue);
        additionalDataField.addPaymentSystemSpecific(paymentSystemSpecificTemplate);
        additionalDataFieldTemplate.setValue(additionalDataField);
        merchantPresentMode.setAdditionalDataField(additionalDataFieldTemplate);

        // Unreserved templates
        UnreservedTemplate unreservedTemplatePix = new UnreservedTemplate();
        unreservedTemplatePix.setTag("80");
        Unreserved unreservedValuePix = new Unreserved();
        unreservedValuePix.setGloballyUniqueIdentifier(BRCodeConstants.PIX_GUI);
        unreservedValuePix.addContextSpecificData(new TagLengthString("01", "PADRAO.URL.PIX/0123ABCD"));
        unreservedTemplatePix.setValue(unreservedValuePix);
        merchantPresentMode.addUnreserved(unreservedTemplatePix);

        UnreservedTemplate unreservedTemplateOutro = new UnreservedTemplate();
        unreservedTemplateOutro.setTag("81");
        unreservedTemplateOutro.setValue(new Unreserved());
        unreservedTemplateOutro.getValue().setGloballyUniqueIdentifier("BR.COM.OUTRO");
        unreservedTemplateOutro.getValue().addContextSpecificData(new TagLengthString("01", "0123.ABCD.3456.WXYZ"));
        merchantPresentMode.addUnreserved(unreservedTemplateOutro);

        String mpmResult = merchantPresentMode.toString();
        assertThat(mpmResult, equalTo(sampleBrCode1));
    }

    @Test
    public void testEncodeSampleBRCode2() throws IllegalAccessException {
//        000201
//        010212
//        2658
//          0014BR
//        000201
//        2658
//        0014br.gov.bcb.pix
//        0136123e4567-e12b-12d1-a456-426655440000
//        52040000
//        5303986
//        5802BR
//        5913Fulano de Tal
//        6008BRASILIA
//        6207
//        0503***
//        63041D3D
        String sampleBrCode2 = "00020126580014br.gov.bcb.pix0136123e4567-e12b-12d1-a456-4266554400005204000053039865802BR5913Fulano de Tal6008BRASILIA62070503***63041D3D";

        final MerchantAccountInformation merchantAccountInformationValue = new MerchantAccountInformation();
        merchantAccountInformationValue.setGloballyUniqueIdentifier(BRCodeConstants.PIX_GUI);
        merchantAccountInformationValue.addPaymentNetworkSpecific(new TagLengthString("01", "123e4567-e12b-12d1-a456-426655440000"));

        final MerchantAccountInformationTemplate pixMerchantAccountInformation = new MerchantAccountInformationTemplate();
        pixMerchantAccountInformation.setValue(merchantAccountInformationValue);
        pixMerchantAccountInformation.setTag("26");

        final MerchantPresentedMode merchantPresentMode = new MerchantPresentedMode();
        merchantPresentMode.setPayloadFormatIndicator("01");
        merchantPresentMode.addMerchantAccountInformation(pixMerchantAccountInformation);
        merchantPresentMode.setTransactionCurrency(Currency.BRL.getNumber());
        merchantPresentMode.setCountryCode(Country.BR.getAlpha2());
        merchantPresentMode.setMerchantCategoryCode("0000");

        merchantPresentMode.setMerchantCity("BRASILIA");
        merchantPresentMode.setMerchantName("Fulano de Tal");

        final AdditionalDataField additionalDataFieldValue = new AdditionalDataField();
        additionalDataFieldValue.setReferenceLabel("***");

        final AdditionalDataFieldTemplate additionalDataField = new AdditionalDataFieldTemplate();
        additionalDataField.setValue(additionalDataFieldValue);
        merchantPresentMode.setAdditionalDataField(additionalDataField);
        String mpmResult = merchantPresentMode.toString();
        assertThat(mpmResult, equalTo(sampleBrCode2));
    }

    public void testDecodeSampleBRCode2() {
        String sampleBrCode2 = "00020126580014br.gov.bcb.pix0136123e4567-e12b-12d1-a456-4266554400005204000053039865802BR5913Fulano de Tal6008BRASILIA62070503***63041D3D";
        final MerchantPresentedMode merchantPresentedMode = DecoderMpm.decode(sampleBrCode2, MerchantPresentedMode.class);

        assertThat(merchantPresentedMode.getCountryCode().getValue(), equalTo("BR"));
        assertThat(merchantPresentedMode.getMerchantCategoryCode().getValue(), equalTo("0000"));
        assertThat(merchantPresentedMode.getMerchantCity().getValue(), equalTo("BRASILIA"));
        assertThat(merchantPresentedMode.getMerchantName().getValue(), equalTo("Fulano de Tal"));
        assertThat(merchantPresentedMode.getPayloadFormatIndicator().getValue(), equalTo("01"));
        assertThat(merchantPresentedMode.getTransactionCurrency().getValue(), equalTo("986"));
    }


    @Test
    public void testSampleBRCodeCRC1() throws IllegalAccessException {
        String input = "00020126580014br.gov.bcb.pix0136123e4567-e12b-12d1-a456-4266554400005204000053039865802BR5913Fulano de Tal6008BRASILIA62070503***6304";
        String inputCrc = "1D3D";
        final int crc16 = CRC.crc16(input.getBytes(StandardCharsets.UTF_8));
        assertEquals("CRC", inputCrc, String.format("%04X", crc16));
    }

    @Test
    public void testSampleBRCodeCRC2() throws IllegalAccessException {
        String input = "00020104141234567890123426660014BR.GOV.BCB.PIX014466756C616E6F32303139406578616D706C652E636F6D27300012BR.COM.OUTRO011001234567895204000053039865406123.455802BR5915NOMEDORECEBEDOR6008BRASILIA61087007490062530515RP12345678-201950300017BR.GOV.BCB.BRCODE01051.0.080450014BR.GOV.BCB.PIX0123PADRAO.URL.PIX/0123ABCD81390012BR.COM.OUTRO01190123.ABCD.3456.WXYZ6304";
        String inputCrc = "EB76";
        final int crc16 = CRC.crc16(input.getBytes(StandardCharsets.UTF_8));
        assertEquals("CRC", inputCrc, String.format("%04X", crc16));
    }
}
