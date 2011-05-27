/*
 * Current Status
 * --------------
 * $Author: sthalluri $
 * $Source: /cvs/framework/src/mil/navy/msc/util/StringHelper.java,v $
 * $Date: 2007/04/26 15:59:48 $
 * $Revision: 1.2 $
 *
 */

package com.toast.util;


/**
 * <p>
 * This class provides helper methods for string handling.
 * </p>
 *
 * @author sthalluri
 * @version $Revision: 1.2 $ $Date: 2007/04/26 15:59:48 $
 */
public final class StringHelper {

    /**
     * Default constructor.
     *
     */
    private StringHelper() {
    }
    /**
     * 
     * <p>
     * Function to check if the string is null or null string.
     * </p>
     * 
     * @param str String
     * @return boolean
     */
    public static boolean isValid(String str) {
        if ((str == null) || ("".equals(str)) || ("".equals(str.trim()))) {
            return false;
        }
        return true;
    }

    
}
