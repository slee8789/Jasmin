package com.study.jasmin.jasmin.util;

/**
 *
 * @author <a href="mailto:kangwoo@jarusoft.com">kangwoo</a>
 * @version 1.0
 * @since 1.0
 */
public class ByteUtils {

    public static Byte DEFAULT_BYTE = new Byte((byte) 0);

    /**
     * <p></p>
     *
     * <pre>
     * ByteUtils.toByte("1", *)    = 0x01
     * ByteUtils.toByte("-1", *)   = 0xff
     * ByteUtils.toByte("a", 0x00) = 0x00
     * </pre>
     *
     * @param value 10
     * @param defaultValue
     * @return
     */
    public static byte toByte(String value, byte defaultValue) {
        try {
            return Byte.parseByte(value);
        } catch(Exception e) {
            return defaultValue;
        }
    }

    /**
     * <p>.</p>
     *
     * <pre>
     * ByteUtils.toByteObject("1", *)    = 0x01
     * ByteUtils.toByteObject("-1", *)   = 0xff
     * ByteUtils.toByteObject("a", 0x00) = 0x00
     * </pre>
     *
     * @param value 10
     * @param defaultValue
     * @return
     */
    public static Byte toByteObject(String value, Byte defaultValue) {
        try {
            return new Byte(value);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * <p>singed byt</p>
     * <p></p>
     *
     * @param b singed byte
     * @return unsinged byte
     */
    public static int unsignedByte(byte b) {
        return  b & 0xFF;
    }


    /**
     * <p></p>
     *
     * @param src
     * @param srcPos
     * @return
     */
    public static int toInt(byte[] src, int srcPos) {
        int dword = 0;
        for (int i = 0; i < 4; i++) {
            dword = (dword << 8) + (src[i + srcPos] & 0xFF);
        }
        return dword;
    }


    public static int byte4ToInt(byte[] retBuf, int set){
        int toInt = 0;

        toInt = ((retBuf[set+3]&0xFF) << 24) + ((retBuf[set+2]&0xFF) << 16) + ((retBuf[set+1]&0xFF) << 8) + (retBuf[set]&0xFF);
        return toInt;
    }
    /**
     * <p></p>
     *
     * @param src
     * @return
     */
    public static int toInt(byte[] src) {
        return toInt(src, 0);
    }

    /**
     * <p></p>
     *
     * @param src
     * @param srcPos
     * @return
     */
    public static long toLong(byte[] src, int srcPos) {
        long qword = 0;
        for (int i = 0; i < 8; i++) {
            qword = (qword << 8) + (src[i + srcPos] & 0xFF);
        }
        return qword;
    }

    /**
     * <p></p>
     *
     * @param src
     * @return
     */
    public static long toLong(byte[] src) {
        return toLong(src, 0);
    }

    /**
     * <p>int .</p>
     *
     * @param value
     * @param dest
     * @param destPos
     */
    public static void toBytes(int value, byte[] dest, int destPos) {
        for (int i = 0; i < 4; i++) {
            dest[i + destPos] = (byte)(value >> ((7 - i) * 8));
        }
    }

    /**
     * <p>int </p>
     *
     * @param value
     * @return
     */
    public static byte[] toBytes(int value) {
        byte[] dest = new byte[4];
        toBytes(value, dest, 0);
        return dest;
    }

    /**
     * <p>long </p>
     *
     * @param value
     * @param dest
     * @param destPos
     */
    public static void toBytes(long value, byte[] dest, int destPos) {
        for (int i = 0; i < 8; i++) {
            dest[i + destPos] = (byte)(value >> ((7 - i) * 8));
        }
    }

    /**
     * <p>long</p>
     *
     * @param value
     * @return
     */
    public static byte[] toBytes(long value) {
        byte[] dest = new byte[8];
        toBytes(value, dest, 0);
        return dest;
    }

    /**
     * <p>8, 10, 16</p>
     * <p>8, 10</p>
     *
     * <pre>
     * ByteUtils.toBytes(null)     = null
     * ByteUtils.toBytes("0E1F4E", 16) = [0x0e, 0xf4, 0x4e]
     * ByteUtils.toBytes("48414e", 16) = [0x48, 0x41, 0x4e]
     * </pre>
     *
     * @param digits
     * @param radix
     * @return
     * @throws NumberFormatException
     */
    public static byte[] toBytes(String digits, int radix) throws IllegalArgumentException, NumberFormatException {
        if (digits == null) {
            return null;
        }
        if (radix != 16 && radix != 10 && radix != 8) {
            throw new IllegalArgumentException("For input radix: \"" + radix + "\"");
        }
        int divLen = (radix == 16) ? 2 : 3;
        int length = digits.length();
        if (length % divLen == 1) {
            throw new IllegalArgumentException("For input string: \"" + digits + "\"");
        }
        length = length / divLen;
        byte[] bytes = new byte[length];
        for (int i = 0; i < length; i++) {
            int index = i * divLen;
            bytes[i] = (byte)(Short.parseShort(digits.substring(index, index+divLen), radix));
        }
        return bytes;
    }

    /**
     * <p></p>
     * <p></p>
     *
     * <pre>
     * ByteUtils.toBytesFromHexString(null)     = null
     * ByteUtils.toBytesFromHexString("0E1F4E") = [0x0e, 0xf4, 0x4e]
     * ByteUtils.toBytesFromHexString("48414e") = [0x48, 0x41, 0x4e]
     * </pre>
     *
     * @param digits 16
     * @return
     * @throws NumberFormatException
     * @see HexUtils.toBytes(String)
     */
    public static byte[] toBytesFromHexString(String digits) throws IllegalArgumentException, NumberFormatException {
        if (digits == null) {
            return null;
        }
        int length = digits.length();
        if (length % 2 == 1) {
            throw new IllegalArgumentException("For input string: \"" + digits + "\"");
        }
        length = length / 2;
        byte[] bytes = new byte[length];
        for (int i = 0; i < length; i++) {
            int index = i * 2;
            bytes[i] = (byte)(Short.parseShort(digits.substring(index, index+2), 16));
        }
        return bytes;
    }

    /**
     * <p>unsigned byte</p>
     *
     * ByteUtils.toHexString((byte)1)   = "01"
     * ByteUtils.toHexString((byte)255) = "ff"
     *
     * @param b unsigned byte
     * @return
     * @see HexUtils.toString(byte)
     */
    public static String toHexString(byte b) {
        StringBuffer result = new StringBuffer(3);
        result.append(Integer.toString((b & 0xF0) >> 4, 16));
        result.append(Integer.toString(b & 0x0F, 16));
        return result.toString();
    }

    /**
     * <p>unsigned byte</p>
     *
     * <pre>
     * ByteUtils.toHexString(null)                   = null
     * ByteUtils.toHexString([(byte)1, (byte)255])   = "01ff"
     * </pre>
     *
     * @param bytes unsigned byte's array
     * @return
     * @see HexUtils.toString(byte[])
     */
    public static String toHexString(byte[] bytes) {
        if (bytes == null) {
            return null;
        }

        StringBuffer result = new StringBuffer();
        for (byte b : bytes) {
            result.append(Integer.toString((b & 0xF0) >> 4, 16));
            result.append(Integer.toString(b & 0x0F, 16));
        }
        return result.toString();
    }

    /**
     * <p>unsigned byte</p>
     *
     * <pre>
     * ByteUtils.toHexString(null, *, *)                   = null
     * ByteUtils.toHexString([(byte)1, (byte)255], 0, 2)   = "01ff"
     * ByteUtils.toHexString([(byte)1, (byte)255], 0, 1)   = "01"
     * ByteUtils.toHexString([(byte)1, (byte)255], 1, 2)   = "ff"
     * </pre>
     *
     * @param bytes unsigned byte's array
     * @return
     * @see HexUtils.toString(byte[])
     */
    public static String toHexString(byte[] bytes, int offset, int length) {
        if (bytes == null) {
            return null;
        }

        StringBuffer result = new StringBuffer();
        for (int i = offset; i < offset + length; i++) {
            result.append(Integer.toString((bytes[i] & 0xF0) >> 4, 16));
            result.append(Integer.toString(bytes[i] & 0x0F, 16));
        }
        return result.toString();
    }

    /**
     * <p></p>
     *
     * <pre>
     * ArrayUtils.equals(null, null)                        = true
     * ArrayUtils.equals(["one", "two"], ["one", "two"])    = true
     * ArrayUtils.equals(["one", "two"], ["three", "four"]) = false
     * </pre>
     *
     * @param array1
     * @param array2
     * @return  <code>true</code>, <code>false</code>
     */
    public static boolean equals(byte[] array1, byte[] array2) {
        if (array1 == array2) {
            return true;
        }

        if (array1 == null || array2 == null) {
            return false;
        }

        if (array1.length != array2.length) {
            return false;
        }

        for (int i = 0; i < array1.length; i++) {
            if (array1[i] != array2[i]) {
                return false;
            }
        }

        return true;
    }

    public static final byte[] getbytes(byte src[], int offset, int length)
    {
        byte dest[] = new byte[length];
        System.arraycopy(src, offset, dest, 0, length);
        return dest;
    }

    private final static char[] HEX = new char[]{
            '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    /**
     * Convert bytes to a base16 string.
     */
    public static String Base16Encode(byte[] byteArray) {
        StringBuffer hexBuffer = new StringBuffer(byteArray.length * 2);
        for (int i = 0; i < byteArray.length; i++)
            for (int j = 1; j >= 0; j--)
                hexBuffer.append(HEX[(byteArray[i] >> (j * 4)) & 0xF]);
        return hexBuffer.toString();
    }

    /**
     * Convert a base16 string into a byte array.
     */
    public static byte[] Base16Decode(String s) {
        int len = s.length();
        byte[] r = new byte[len / 2];
        for (int i = 0; i < r.length; i++) {
            int digit1 = s.charAt(i * 2), digit2 = s.charAt(i * 2 + 1);
            if (digit1 >= '0' && digit1 <= '9')
                digit1 -= '0';
            else if (digit1 >= 'A' && digit1 <= 'F')
                digit1 -= 'A' - 10;
            if (digit2 >= '0' && digit2 <= '9')
                digit2 -= '0';
            else if (digit2 >= 'A' && digit2 <= 'F')
                digit2 -= 'A' - 10;

            r[i] = (byte) ((digit1 << 4) + digit2);
        }
        return r;
    }


    ////////////////////////////////////////KOINO F.O.R.E.V.E.R///////////////////////////////////////
    // create by kjh
    // C(little Endian) -> Java(bigEndian)
    public static short toShort(byte[] src) {
        short dword = 0;
        for (int i = 0; i < 2; i++) {
            dword = (short) (   (dword << 8) + (src[i] & 0xFF));
        }
        return dword;
    }
    public static short toShort(byte[] src, int nStart) {
        short dword = 0;
        for (int i = nStart; i < nStart + 2; i++) {
            dword = (short) (   (dword << 8) + (src[i] & 0xFF));
        }
        return dword;
    }

    //unsigned short를 받기위해 int형으로 처리
    public static int toShortReverse(byte[] src, int nStart) {
        int dword = 0;
        for (int i = nStart + 1; i >= nStart; i--) {
            dword = (int) (   (dword << 8) + (src[i] & 0xFF));
        }
        return dword;
    }

    public static int toIntReverse(byte[] src, int nStart) {
        byte tmp = 0;
        int nNum = 0;
        for(int i = nStart - 1, j = 0 ; i <  nStart + 1; i++, j++){  // 뒤집어 준다...
            tmp = src[i];
            src[i] = src[(nStart + 2) - j];
            src[(nStart + 2) - j] = tmp;
        }
        for (int i  = nStart - 1; i < nStart + 2; i++) {
            nNum = (int) ( (nNum << 8) + (src[i] & 0xFF));
        }
        return nNum;
    }

    public static long toLongReverse(byte[] src, int nStart) {
        byte tmp = 0;
        long nNum = 0;
        for(int i = nStart - 1, j = 0 ; i <  nStart + 1; i++, j++){
            tmp = src[i];
            src[i] = src[(nStart + 2) - j];
            src[(nStart + 2) - j] = tmp;
        }
        for (int i  = nStart - 1; i < nStart + 2; i++) {
            nNum = ( (nNum << 8) + (src[i] & 0xFF));
        }
        return nNum;
    }

    public static final long unsignedIntToLong(byte[] b) {
        long l = 0;
        l |= b[0] & 0xFF;
        l <<= 8;
        l |= b[1] & 0xFF;
        l <<= 8;
        l |= b[2] & 0xFF;
        l <<= 8;
        l |= b[3] & 0xFF;
        return l;
    }

    public static int toIntNotReverse(byte[] src, int nStart) {
        int dword = 0;
        for (int i = nStart; i < nStart + 4; i++) {
            dword = (int) (   (dword << 8) + (src[i] & 0xFF));
        }
        return dword;
    }
    // create by kjh
    // C(little Endian) -> Java(bigEndian)
    // param : C(little Endian) to short number value change
    public static short toShortBigEndian(short num) {
        short dword = 0;
        byte[] tmp = new byte[2];

        tmp[0] = (byte) (num & 0xff);
        tmp[1] = (byte) ((num >> 8) & 0xff);

        for (int i = 0; i < 2; i++) {
            dword = (short) ((dword << 8) + (tmp[i] & 0xFF));
        }

        return dword;
    }

    // create by kjh
    // C(little Endian) -> Java(bigEndian)
    // param : C(little Endian) to short number value change
    public static int toIntBigEndian(int num) {
        int dword = 0;
        byte[] tmp = new byte[4];

        tmp[0] = (byte) (num & 0xff);
        tmp[1] = (byte) ((num >> 8) & 0xff);
        tmp[2] = (byte) ((num >> 16) & 0xff);
        tmp[3] = (byte) ((num >> 24) & 0xff);

        for (int i = 0; i < 4; i++) {
            dword = (int) ((dword << 8) + (tmp[i] & 0xFF));
        }
        return dword;
    }

    // string
    public static byte[] stringCopyToByte(String v, byte[] data, int nStart) {
        if( v != null ) {
            byte[] val = v.getBytes();
            for( int i = 0 ; i < val.length; i++ )
            {
                data[i+nStart] = val[i];
            }
        }
        return data;
    }

    // 두 byte[]을 합친다.
    public static byte[] byteAddToByte(byte[] sourceA, byte[] sourceB) {
        if (sourceA == null || sourceB == null) {
            return null;
        }
        byte[] sourceAdd = new byte[sourceA.length + sourceB.length];
        System.arraycopy(sourceA, 0, sourceAdd, 0, sourceA.length);
        System.arraycopy(sourceB, 0, sourceAdd, sourceA.length, sourceB.length);
        return sourceAdd;
    }

    // create by kjh
    public static byte[] byteCopyToByte(byte[] res, byte[] dest, int nStart) {

        if(dest == null) return null;

        if( dest != null || res != null || res.length < dest.length ) {

            for( int i = nStart, n = 0 ; i < nStart + dest.length; i++, n++ ){
                if(res[i] == 0) break;
                dest[n] = res[i];
//				if((n != (dest.length - 1)) && (dest[n] == -52)){
//					dest[n] = 0;
//				}
            }
            dest[dest.length - 1] = '\0';
        }
        return dest;
    }

    public static final void convertShortToByte(short srcData, byte dest[], int offset)
    {
        dest[offset + 0] = (byte)(srcData & 0xff);
        dest[offset + 1] = (byte)((srcData >> 8) & 0xff);
    }

    public static final void convertIntToByte(int srcData, byte dest[], int offset)
    {
        dest[offset + 0] = (byte)(srcData & 0xff);
        dest[offset + 1] = (byte)((srcData >> 8) & 0xff);
        dest[offset + 2] = (byte)((srcData >> 16) & 0xff);
        dest[offset + 3] = (byte)((srcData >> 24) & 0xff);
    }

    public static final void convertLongToByte(long srcData, byte dest[], int offset)
    {
        dest[offset + 0] = (byte)(srcData & 0xff);
        dest[offset + 1] = (byte)((srcData >> 8) & 0xff);
        dest[offset + 2] = (byte)((srcData >> 16) & 0xff);
        dest[offset + 3] = (byte)((srcData >> 24) & 0xff);
        dest[offset + 4] = (byte)((srcData >> 32) & 0xff);
        dest[offset + 5] = (byte)((srcData >> 40) & 0xff);
        dest[offset + 6] = (byte)((srcData >> 48) & 0xff);
        dest[offset + 7] = (byte)((srcData >> 56) & 0xff);
    }

    public static long getLong(byte[] b, int off) {

        return ((b[off + 7] & 0xFFL) << 0) +

                ((b[off + 6] & 0xFFL) << 8) +

                ((b[off + 5] & 0xFFL) << 16) +

                ((b[off + 4] & 0xFFL) << 24) +

                ((b[off + 3] & 0xFFL) << 32) +

                ((b[off + 2] & 0xFFL) << 40) +

                ((b[off + 1] & 0xFFL) << 48) +

                (((long) b[off + 0]) << 56);

    }

    public static short getShort(byte[] b, int off) {

        return (short) (((b[off + 1] & 0xFF) << 0) +

                ((b[off + 0]) << 8));

    }

    public static int getInt(byte[] b, int off) {

        return ((b[off + 3] & 0xFF) << 0) +

                ((b[off + 2] & 0xFF) << 8) +

                ((b[off + 1] & 0xFF) << 16) +

                ((b[off + 0]) << 24);

    }

    public static long byte8ToLong(byte[] retBuf, int set){
        long toLong = 0;

        toLong = ((retBuf[set+7]&0xFF) << 56) + ((retBuf[set+6]&0xFF) << 48) + ((retBuf[set+5]&0xFF) << 40) + ((retBuf[set+4]&0xFF) << 32)
                + ((retBuf[set+3]&0xFF) << 24) + ((retBuf[set+2]&0xFF) << 16) + ((retBuf[set+1]&0xFF) << 8) + (retBuf[set]&0xFF);
        return toLong;
    }

    public static short byte2ToShort(byte[] retBuf, int set){
        short toShort = 0;

        toShort = (short) (((retBuf[set+1]&0xFF) << 8) + (retBuf[set]&0xFF));
        return toShort;
    }

    public static void putLong(byte[] b, int off, long val) {

        b[off + 0] = (byte) (val >>> 0);

        b[off + 1] = (byte) (val >>> 8);

        b[off + 2] = (byte) (val >>> 16);

        b[off + 3] = (byte) (val >>> 24);

        b[off + 4] = (byte) (val >>> 32);

        b[off + 5] = (byte) (val >>> 40);

        b[off + 6] = (byte) (val >>> 48);

        b[off + 7] = (byte) (val >>> 56);

    }
}

