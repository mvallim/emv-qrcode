package com.emv.qrcode.decoder.common;

//
//import com.emv.qrcode.core.model.TagLengthStringList;
//import com.emv.qrcode.core.model.TagLengthString;
//import com.emv.qrcode.decoder.Decoder;
//
//// @formatter:off
//public final class ListTagLengthStringDecoder extends Decoder<TagLengthStringList> {
//
//  public ListTagLengthStringDecoder(final String source) {
//    super(source);
//  }
//
//  @Override
//  protected TagLengthStringList decode() {
//    final TagLengthStringList result = new TagLengthStringList();
//
//    forEachRemaining(value -> result.add(Decoder.decode(value, TagLengthString.class)));
//
//    return result;
//  }
//
//}
//
//// @formatter:on
