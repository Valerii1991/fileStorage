package converting;

/**
 Converts data from one type to another
 */
public class Converting {
    /**
     Convert string to int
     Params: file - the string to be converted
     */
    public static  int convertFromStringToInt(String s) {
        try{
            return Integer.valueOf(s);
        }
        catch (NumberFormatException e){
            return -1;
        }
    }

    /**
     Convert string to double
     Params: file - the string to be converted
     */
    public static double convertFromStringToDouble(String s){
        try{
            return Double.valueOf(s);
        }
        catch (NumberFormatException e){
            return 0;
        }
    }
}
