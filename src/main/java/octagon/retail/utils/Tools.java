package octagon.retail.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Tools {

    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static boolean isNullOrZero(Long value) {
        return value == null || value.equals(0L);
    }

    public static boolean isNullOrEmptyList(List<?> listAny) {
        return listAny == null || listAny.isEmpty();
    }

    public static String validateAccountField(String fieldName, String text) {
        return validateAccountField(fieldName, text, 24, 8);
    }

    public static String validateAccountField(String fieldName, String text, int maxSize, int minSize) {
        if (isNullOrEmpty(text))
            return fieldName + " - талбар хоосон байна. ";

        if (Tools.getCharLength(text) > maxSize)
            return fieldName + " - талбар " + maxSize + "-ээс олон тэмдэгтээс бүтсэн байна. ";

        if (Tools.getCharLength(text) < minSize)
            return fieldName + " - талбар " + minSize + "-ээс бага тэмдэгтээс бүтсэн байна. ";

        if (!Tools.isNumeric(text))
            return fieldName + " - талбар дансны дугаар тоон утгаас бүтсэн байх ёстой. ";

        return "";
    }

    public static boolean isNullOrEmpty(Object value) {
        return value == null || convertToStringLower(value).equals("") || convertToStringLower(value).equals("null");
    }

    public static boolean compareValue(Object value_first, Object value_second) {
        return convertToStringLower(value_first).equals(convertToStringLower(value_second));
    }

    public static boolean compareValueNoSpace(Object value_first, Object value_second) {
        return convertToStringLower(value_first).replace("_", "").trim()
                .equals(convertToStringLower(value_second).replace("_", "").trim());
    }

    public static boolean compareNumber(Long value_first, Long value_second) {
        return convertToLong(value_first).equals(convertToLong(value_second));
    }

    public static boolean compareDecimal(BigDecimal value_first, BigDecimal value_second) {
        return convertToDecimal(value_first).compareTo(convertToDecimal(value_second)) == 0;
    }

    public static boolean containsValue(Object value_first, Object value_second) {
        return convertToStringLower(value_first).toLowerCase()
                .contains(convertToStringLower(value_second).toLowerCase());
    }

    public static boolean containsValueNoSpace(Object value_first, Object value_second) {
        return convertToStringLower(value_first).toLowerCase().replace(" ", "")
                .contains(convertToStringLower(value_second).toLowerCase().replace(" ", ""));
    }

    public static int indexOfValue(String source, String target) {
        return convertToStringLower(source).indexOf(convertToStringLower(target));
    }

    public static boolean startsWith(String source, String target) {
        return convertToStringLower(source).startsWith(convertToStringLower(target));
    }

    public static boolean endsWith(Object source, Object target) {
        return convertToStringLower(source).endsWith(convertToStringLower(target));
    }

    public static String convertToStringLower(Object value) {
        return convertToString(value).toLowerCase();
    }

    public static String convertToStringUpper(Object value) {
        return convertToString(value).toUpperCase();
    }

    public static String convertToString(Object value) {
        return value == null ? "" : value.toString();
    }

    public static String convertToStringNoSpace(Object value) {
        return value == null ? "" : value.toString().replace(" ", "");
    }

    public static String convertToNullString(Object value) {
        return isNullOrEmpty(value) ? "null" : value.toString();
    }

    public static String convertToNull(String value) {
        return isNullOrEmpty(value) ? null : value;
    }

    public static Long convertToLong(Object value) {
        return Tools.isNullOrEmpty(value) ? 0L : Long.parseLong(convertToStringNoSpace(value));
    }

    public static Integer convertToInteger(Object value) {
        return value == null ? 0 : Integer.parseInt(convertToString(value));
    }

    public static boolean containsValue(Field[] fields, String columnName) {
        return Arrays.stream(fields).filter(it -> Tools.compareValue(it.getName(), columnName)).count() > 0;
    }

    public static boolean isNullOrZero(Object object) {
        return convertToDecimal(object).compareTo(BigDecimal.ZERO) == 0;
    }

    public static BigDecimal convertToDecimal(Object value) {
        return convertToDecimal(value, false);
    }

    public static BigDecimal convertToDecimal(Object value, boolean isFullRound) {

        if (isNullOrEmpty(value)) {
            return BigDecimal.ZERO;
        }
        BigDecimal val = BigDecimal.ZERO;

        try {
            val = (new BigDecimal(value.toString())).setScale(isFullRound ? 0 : 2, RoundingMode.HALF_UP);
        } catch (Exception ex) {
            return val;
        }
        return val;
    }

    public static Double convertToDouble(Object value) {
        return value == null ? 0 : Double.parseDouble(convertToString(value));
    }

    public static boolean checkRange(Object value, Object start_range, Object end_range) {
        return convertToDecimal(value).compareTo(convertToDecimal(start_range)) > 0
                && convertToDecimal(value).compareTo(convertToDecimal(end_range)) <= 0;
    }

    public static byte[] readFileToByteArray(File file) {
        FileInputStream fis = null;
        byte[] bArray = new byte[(int) file.length()];
        try {
            fis = new FileInputStream(file);
            fis.read(bArray);

        } catch (IOException ioExp) {
            ioExp.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bArray;
    }

    public static void WriteToFile(String fileContent, String fileName) throws IOException {
        String projectPath = System.getProperty("user.dir");
        String tempFile = projectPath + File.separator + fileName;
        File file = new File(tempFile);
        // if file does exists, then delete and create a new file
        if (file.exists()) {
            try {
                File newFileName = new File(projectPath + File.separator + "backup_" + fileName);
                file.renameTo(newFileName);
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // write to file with OutputStreamWriter
        OutputStream outputStream = new FileOutputStream(file.getAbsoluteFile());

        Writer writer = new OutputStreamWriter(outputStream, "UTF-8");
        writer.write(fileContent);
        writer.close();
    }

    public static String convertToDateFormat(Date date, SimpleDateFormat dateFormat) {
        String dateStr = "";
        try {
            dateStr = dateFormat.format(date);
        } catch (Exception ex) {

        }
        return dateStr;
    }

    public static Date convertToDate(String dateStr) {
        Date convertedDate = null;
        try {
            convertedDate = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss").parse(dateStr);
        } catch (Exception ex) {
        }
        return convertedDate;
    }

    public static Date convertToDate(String dateStr, SimpleDateFormat dateFormat) {
        Date convertedDate = null;
        try {
            convertedDate = dateFormat.parse(dateStr);
        } catch (Exception ex) {
        }
        return convertedDate;
    }

    public static Date convertToDateNoTime(String dateStr) {
        Date convertedDate = null;
        try {
            convertedDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        } catch (Exception ex) {
        }
        return convertedDate;
    }

    public static String convertToJson(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
        }
        return json;
    }

    // FULLNAME
    public static String getFullName(String firstName, String lastName) {
        String fullName = "";
        if (!getFirstLetter(lastName).isEmpty()) {
            fullName += getFirstLetter(lastName) + ".";
        }
        return fullName + getFirstLetter(firstName) + getSubstring(firstName);
    }

    public static String getFirstLetter(String value) {
        return value == null || value.length() < 1 ? "" : value.substring(0, 1).toUpperCase();
    }

    public static String getSubstring(String value) {
        return value == null || value.length() < 1 ? "" : value.substring(1).toLowerCase();
    }

    public static String getBigName(String name) {
        return getFirstLetter(name) + getSubstring(name);
    }

    public static String convertToNumberString(Object value) {
        DecimalFormat formatter = new DecimalFormat("#,###.00");
        return formatter.format(Tools.convertToDecimal(value));
    }

    public static String getMonthStr(Long month) {
        if (month == null)
            return "0";
        if (month < 10L) {
            return "0" + Tools.convertToString(month);
        }
        return Tools.convertToString(month);
    }

    public static String getCustomertTypeUTF8(String customerType) {
        String retStr = "";
        switch (customerType) {
            case "INSURANCE":
                retStr = "Нийгмийн даатгал";
                break;
            case "XXOAT":
                retStr = "XXOAT";
                break;
        }
        return retStr;
    }

    public static String getMinDots(String object) {
        return object == null || object.isEmpty() ? "......" : object;
    }

    public static String joinString(String delimeter, Object... elems) {
        String joinedStr = "";
        for (int i = 0; i < elems.length; ++i) {
            if (elems[i] == null)
                continue;
            boolean isLastElement = (elems.length - 1) == i;
            if (elems[i] instanceof String) {
                joinedStr += Tools.convertToString(elems[i]) + (isLastElement ? "" : delimeter);
            }
        }

        return joinedStr;
    }

    public static int getCharLength(String value) {
        Charset UTF8_CHARSET = Charset.forName("UTF-8");
        byte[] abc = Tools.convertToString(value).getBytes(UTF8_CHARSET);
        return abc.length;
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static Date getDateFirstDay(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getDateLastDay(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date removeTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static String getMonthEng(Long month) {
        String monthStr = "";
        switch (Tools.convertToLong(month).intValue()) {
            case 1:
                monthStr = "JAN";
                break;
            case 2:
                monthStr = "FEB";
                break;
            case 3:
                monthStr = "MAR";
                break;
            case 4:
                monthStr = "APR";
                break;
            case 5:
                monthStr = "MAY";
                break;
            case 6:
                monthStr = "JUN";
                break;
            case 7:
                monthStr = "JUL";
                break;
            case 8:
                monthStr = "AUG";
                break;
            case 9:
                monthStr = "SEP";
                break;
            case 10:
                monthStr = "OCT";
                break;
            case 11:
                monthStr = "NOV";
                break;
            case 12:
                monthStr = "DEC";
                break;
        }
        return monthStr;
    };

    public static String getDateString(Long year, Long month) {
        String str = "";

        if (Tools.isNullOrEmpty(year) && Tools.isNullOrEmpty(month)) {
            return str = "";
        }

        if (Tools.compareValue(year, 0)) {
            return str = month + " сар";
        }

        if (Tools.compareValue(month, 0)) {
            return str = year + " жил";
        }

        return str = year + " жил" + " - " + month + " сар";
    }

    public static Date getExpiryDate() {
        Calendar cl = Calendar.getInstance();
        cl.set(Calendar.DAY_OF_MONTH, cl.get(Calendar.DAY_OF_MONTH) + 3);
        return cl.getTime();
    }

    public static boolean compareDate(Date firstVal, Date secondVal) {
        // 2 date null bol tentsuu gj uzne
        if (firstVal == null && secondVal == null)
            return true;

        // ali neg ni null ba nogoo utga ni ognootoi bol tentsehgui
        if ((firstVal == null && secondVal != null) || (firstVal != null && secondVal == null))
            return false;

        return firstVal.compareTo(secondVal) == 0;
    }

    public static boolean checkInsideBetweenDates(Date startDate, Date endDate, int calcYear, int calcMonth) {
        Calendar calStartDate = Calendar.getInstance();
        calStartDate.setTime(startDate);
        calStartDate.set(Calendar.MONTH, calStartDate.get(Calendar.MONTH));

        Calendar calEndDate = Calendar.getInstance();
        calEndDate.setTime(endDate);
        calEndDate.set(Calendar.MONTH, calEndDate.get(Calendar.MONTH));

        int startYear = calStartDate.get(Calendar.YEAR);
        int endYear = calEndDate.get(Calendar.YEAR);
        int startMonth = calStartDate.get(Calendar.MONTH) + 1;
        int endMonth = calEndDate.get(Calendar.MONTH) + 1;

        // startYear <= calcYear <= endYear
        if (startYear <= calcYear && calcYear <= endYear) {
            // ehleh jil ni tootsoolj bga jilees baga buyu tentsuu && duusah jil ni tootsooj
            // bga jilees ers ih bol
            if (startYear <= calcYear && calcYear < endYear)
                return true;
            // ehleh jil ni tootsoolj bga jilees ers baga && duusah jil ni tootsoolj bga
            // jiltei tentsuu sar ni baga baigaa ued
            if (startYear < calcYear && (endYear == calcYear && calcMonth <= endMonth))
                return true;

            // ehleh jil ni tootsoolj bga jilees ers baga && duusah jil ni tootsoolj bga
            // jiltei tentsuu sar ni baga baigaa ued
            if ((startYear == calcYear && startMonth <= calcMonth) && calcYear < endYear)
                return true;

            if ((startYear == calcYear && startMonth <= calcMonth) && (endYear == calcYear && calcMonth <= endMonth))
                return true;
        }
        return false;
    }

    public static String extractCodeBlockGroup(String[] cbArray) {
        return Tools.joinString("/",
                getValueByIndexFromArray(cbArray, 1),
                getValueByIndexFromArray(cbArray, 2),
                getValueByIndexFromArray(cbArray, 3),
                getValueByIndexFromArray(cbArray, 4),
                getValueByIndexFromArray(cbArray, 5).substring(0, 4));
    }

    public static String extractCodeBlockParent(String codeBlock) {
        String[] cbArray = codeBlock.split("/");
        return Tools.joinString("/",
                getValueByIndexFromArray(cbArray, 1),
                getValueByIndexFromArray(cbArray, 2),
                getValueByIndexFromArray(cbArray, 3));
    }

    public static String getValueByIndexFromArray(String[] anyArray, int index) {
        if (anyArray.length > index)
            return anyArray[index];
        return "";
    }

    public static BigDecimal getDecimalNotNullValue(Object value) {
        return new BigDecimal(value != null ? value.toString() : "0");
    }

    public static String amountWithDecimal(BigDecimal price) {
        DecimalFormat formatter = new DecimalFormat("#,##0.##");
        return formatter.format(price);
    }

    public static Long getQuarter(Long month) {
        Long quarter = 0L;
        switch (month.intValue()) {
            case 1:
            case 2:
            case 3:
                quarter = 1L;
                break;
            case 4:
            case 5:
            case 6:
                quarter = 2L;
                break;
            case 7:
            case 8:
            case 9:
                quarter = 3L;
                break;
            case 10:
            case 11:
            case 12:
                quarter = 4L;
                break;
        }
        return quarter;
    }

    public static LocalDate convertToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static String fillByLeadingZeros(String number, int length) {
        String str = Tools.convertToString(number);
        while (str.length() < length) {
            str = '0' + str;
        }
        return str;
    }

    public static String replaceChar(String value) {
        return value.replace("-", "").replace(" ", "");
    }
}
