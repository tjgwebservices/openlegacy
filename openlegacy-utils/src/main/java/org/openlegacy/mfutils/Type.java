/*
 * @Author Bruce Martin
 * Created on 27/08/2005
 *
 * Purpose: To format a field for display on the screen and convert it
 * back to the record format
 *
 *
 * Modification log:
 * On 2006/06/28 by Jean-Francois Gagnon:
 *    - Added new constants for Fujitsu Zoned Numeric
 *      and Separate Sign numeric
 *  Version 0.56 Bruce martin
 *    - Added new constants for null terminated char
 *      and null padded char
 *
 *  Version 0.60 Bruce Martin
 *    - Added getFieldType to help with sorting etc
 */
package org.openlegacy.mfutils;

/**
 * A "Type" is the interface between the raw data in the file and the internal format (what is to be displayed on the screen in
 * the RecordEditor). A "Type" class needs to perform all conversions between how a field is stored in a file and Java Strings for
 * display on the screen.
 *
 * @author Bruce Martin
 *
 * @version 0.55
 */
public interface Type {

	public static final int BASE_16 = 16;

	public static final int USER_RANGE_START = 1000;
	public static final int DEFAULT_USER_RANGE_SIZE = 75;

	public static final int NULL_INT = -121;

	public static final int ftChar = 0;
	public static final int ftCharRightJust = 1;
	public static final int ftCharNullTerminated = 2;
	public static final int ftCharNullPadded = 3;

	public static final int ftHex = 4;
	public static final int ftNumLeftJustified = 5;
	public static final int ftNumRightJustified = 6;
	public static final int ftNumZeroPadded = 7;
	public static final int ftAssumedDecimal = 8;
	public static final int ftSignSeparateLead = 9;
	public static final int ftSignSeparateTrail = 10;
	public static final int ftDecimal = 11;
	public static final int ftBinaryInt = 15;
	public static final int ftPostiveBinaryInt = 16;
	public static final int ftFloat = 17;
	public static final int ftDouble = 18;
	public static final int ftNumAnyDecimal = 19;
	public static final int ftPositiveNumAnyDecimal = 20;
	public static final int ftBit = 21;
	public static final int ftAssumedDecimalPositive = 22;
	public static final int ftBinaryIntPositive = 23;

	public static final int ftNumZeroPaddedPN = 24;
	public static final int ftNumZeroPaddedPositive = 25;
	public static final int ftNumCommaDecimal = 26;
	public static final int ftNumCommaDecimalPN = 27;
	public static final int ftNumCommaDecimalPositive = 28;

	public static final int ftNumRightJustifiedPN = 29;

	public static final int ftPackedDecimal = 31;
	public static final int ftZonedNumeric = 32;
	public static final int ftPackedDecimalPostive = 33;
	// public static final int ftZonedNumericPositive = 34;
	public static final int ftBinaryBigEndian = 35;
	public static final int ftBinaryBigEndianPositive = 39;
	public static final int ftPositiveBinaryBigEndian = 36;
	public static final int ftRmComp = 37;
	public static final int ftRmCompPositive = 38;

	public static final int ftFjZonedNumeric = 41;
	public static final int ftNumRightJustCommaDp = 42;
	public static final int ftNumRightJustCommaDpPN = 43;

	/**
	 * Use ftMultiLineChar instead
	 */
	@Deprecated
	public static final int ftCharMultiLine = 51;

	public static final int ftDate = 71;
	public static final int ftDateYMD = 72;
	public static final int ftDateYYMD = 73;
	public static final int ftDateDMY = 74;
	public static final int ftDateDMYY = 75;

	public static final int ftCharRestOfFixedRecord = 80;
	public static final int ftCharRestOfRecord = 81;

	public static final int ftProtoField = 91;
	public static final int ftAvroField = 91;
	public static final int ftArrayField = 92;
	public static final int ftComboItemField = 93;
	public static final int ftAvroUnionField = 94;

	public static final int ftCheckBoxY = 109;
	public static final int ftCheckBoxTrue = 110;
	public static final int ftCheckBoxYN = 111;
	public static final int ftCheckBoxTF = 112;
	public static final int ftCheckBoxBoolean = 114;

	public static final int ftCsvArray = 115;
	public static final int ftXmlNameTag = 116;
	public static final int ftMultiLineEdit = 117;
	public static final int ftMultiLineChar = 118; /* used in PO / Tip Files */
	public static final int ftHtmlField = 119;

	public static final int NT_TEXT = 1;
	public static final int NT_DATE = 11;
	public static final int NT_NUMBER = 21;

}