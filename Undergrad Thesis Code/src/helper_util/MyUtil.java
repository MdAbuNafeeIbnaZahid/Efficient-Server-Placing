package helper_util;

/**
 * Created by nafee on 11/30/17.
 */
public class MyUtil {
    public static int nc2(int n)
    {
        if ( n < 0 )
        {
            throw  new IllegalArgumentException();
        }

        return (n*(n-1) )>>1;
    }
}
