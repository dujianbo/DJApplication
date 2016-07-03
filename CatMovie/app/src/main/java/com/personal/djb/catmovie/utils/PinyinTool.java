package com.personal.djb.catmovie.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Android将军
 *
 */
public class PinyinTool {
    public static HanyuPinyinOutputFormat format;
    public static List<Map<String,Map<String,String>>> list;
    public static Map<String,Map<String,String>> map;
    public static Map<String,String> pMap;
    public static List<String> strList;
    /**
     *  功能：根据联系人姓名生成拼音缩写与拼写全写
     * @param strList 存储联系人姓名的List
     * @throws BadHanyuPinyinOutputFormatCombination
     */
    public static void setData(List<String> strList) throws BadHanyuPinyinOutputFormatCombination
    {
        PinyinTool.strList=strList;
        format=new HanyuPinyinOutputFormat();
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
        list=new ArrayList<Map<String,Map<String,String>>>();
        for(int i=0;i<strList.size();i++)
        {
            map=new HashMap<String, Map<String,String>>();
            pMap=new HashMap<String, String>();
            pMap.put("simple", getSimple(strList.get(i)));
            pMap.put("complex", getComplex(strList.get(i)));
            map.put(strList.get(i), pMap);
            list.add(map);
        }

    }
    /**
     * 功能：获取字符串str的拼音缩写
     * @param str
     * @return
     */
    public static String getSimple(String str)
    {
        StringBuilder sb=new StringBuilder();
        String tempSimple=null;
        for(int i=0;i<str.length();i++)
        {
            tempSimple=getCharacterSimple(str.charAt(i));
            if(tempSimple==null)
            {
                sb.append(str.charAt(i));
            }else
            {
                sb.append(tempSimple);
            }
        }

        return sb.toString();
    }
    /**
     * 功能：获取字符C的拼音首字母
     * @param c
     * @return
     */
    public static String getCharacterSimple(char c)
    {
        String[] str=null;
        try {
            str=PinyinHelper.toHanyuPinyinStringArray(c, format);
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(str==null)
            return null;
        return str[0].charAt(0)+"";
    }
    public static String getComplex(String str)
    {
        StringBuilder sb=new StringBuilder();
        String tempSimple=null;
        for(int i=0;i<str.length();i++)
        {
            tempSimple=getCharacterComplex(str.charAt(i));
            if(tempSimple==null)
            {
                sb.append(str.charAt(i));
            }else
            {
                sb.append(tempSimple);
                //              System.out.println(sb.toString());
            }
        }

        return sb.toString();
    }
    public static String getCharacterComplex(char c)
    {
        String[] str=null;
        try {
            str=PinyinHelper.toHanyuPinyinStringArray(c, format);
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(str==null)
            return null;
        return str[0];
    }
    /**
     * 功能：搜索符合条件的联系人
     * @param str 当前联系人List
     * @return 新的联系人List
     */
    public static List<String> search(String str)
    {
        List<String> temp=new ArrayList<String>();
        for(int i=0;i<list.size();i++)
        {
            if(list.get(i).get(strList.get(i)).get("complex").contains(str)||list.get(i).get(strList.get(i)).get("simple").contains(str))
            {
                System.out.println(strList.get(i));
                temp.add(strList.get(i));
            }
        }
        return temp;

    }

    /**
     * 将字符串中的中文转化为拼音,其他字符不变
     */
    public static String getPingYin(String inputString) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);

        Pattern p = Pattern.compile("^[\u4E00-\u9FA5A-Za-z_]+$");
        Matcher matcher = p.matcher(inputString.substring(0, 1));
        if (matcher.find()) {
            char[] input = inputString.trim().toCharArray();
            String output = "";
            try {
                for (int i = 0; i < input.length; i++) {
                    if (java.lang.Character.toString(input[i]).matches(
                            "[\\u4E00-\\u9FA5]+")) {
                        String[] temp = PinyinHelper.toHanyuPinyinStringArray(
                                input[i], format);
                        output += temp[0];
                    } else
                        output += java.lang.Character.toString(input[i]);
                }
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                e.printStackTrace();
            }
            return output;
        } else {
            return "";
        }
    }

    public static String converterToFirstSpell(String chines) {
        String pinyinName = "";
        char[] nameChar = chines.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < nameChar.length; i++) {
            if (nameChar[i] > 128) {
                try {
                    pinyinName += PinyinHelper.toHanyuPinyinStringArray(
                            nameChar[i], defaultFormat)[0].charAt(0);
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pinyinName += nameChar[i];
            }
        }
        return pinyinName;
    }

}