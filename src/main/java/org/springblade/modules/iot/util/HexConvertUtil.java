package org.springblade.modules.iot.util;

public class HexConvertUtil {

    public static String  convertStringToHex(String str){

        char[] chars = str.toCharArray();

        StringBuffer hex = new StringBuffer();
        for(int i = 0; i < chars.length; i++){
            hex.append(Integer.toHexString((int)chars[i]));
        }

        return hex.toString();
    }

    public static String convertHexToString(String hex){

        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        for( int i=0; i<hex.length()-1; i+=2 ){

            String s = hex.substring(i, (i + 2));
            int decimal = Integer.parseInt(s, 16);
            sb.append((char)decimal);
            sb2.append(decimal);
        }

        return sb.toString();
    }
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        // toUpperCase将字符串中的所有字符转换为大写
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        // toCharArray将此字符串转换为一个新的字符数组。
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }
    //返回匹配字符
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    //将字节数组转换为short类型，即统计字符串长度
    public static short bytes2Short2(byte[] b) {
        short i = (short) (((b[1] & 0xff) << 8) | b[0] & 0xff);
        return i;
    }
    //将字节数组转换为16进制字符串
    public static String BinaryToHexString(byte[] bytes) {
        String hexStr = "0123456789ABCDEF";
        String result = "";
        String hex = "";
        for (byte b : bytes) {
            hex = String.valueOf(hexStr.charAt((b & 0xF0) >> 4));
            hex += String.valueOf(hexStr.charAt(b & 0x0F));
            result += hex + " ";
        }
        return result;
    }

    public static void main(String[] args) {


//        System.out.println("======ASCII码转换为16进制======");
//        String str = "0001DD";
//        System.out.println("字符串: " + str);
//        String hex = HexConvertUtil.convertStringToHex(str);
//        String hex="33 00 38 39 38 36 30 34 31 30 31 31 31 38 34 " +
//                "30 33 35 35 36 32 31 2C 41 2C 33 30 31 35 2E 38 35 36 36 " +
//                "30 36 2C 4E 2C 31 32 30 30 37 2E 30 33 31 33 39 31 2C 45";
        String hex="38393836303242303130313935303033373230332C412C333031352E3834303435312C4E2C31323030372E3033313931";
        System.out.println("string:"+new String(HexConvertUtil.hexStringToBytes(hex.replace(" ",""))));

//        String hex = "00 01  00 00 15 02  02 02 03 00  DD".replace(" ","");
        System.out.println("====转换为16进制=====" + hex);
//        System.out.println(HexConvertUtil.convertStringToHex(hex));

        System.out.println("======16进制转换为ASCII======");
        System.out.println("Hex : " + hex);
        System.out.println("ASCII : " + HexConvertUtil.convertHexToString(hex));
//        System.out.println("binary:"+HexConvertUtil.hexStringToBytes(hex));

        byte[] bytes = HexConvertUtil.hexStringToBytes( hex );

        System.out.println(HexConvertUtil.BinaryToHexString( bytes ));
    }
}
