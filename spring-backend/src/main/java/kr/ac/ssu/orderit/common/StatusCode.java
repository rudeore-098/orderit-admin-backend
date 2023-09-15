/**
 * @filename    : StatusCode.java
 * @description : Project status codes definition
 * @author      : jonghokim27
 */

package kr.ac.ssu.orderit.common;

import org.springframework.stereotype.Component;

@Component
public class StatusCode {
    // ==================================================================
    // Global

    /**
     * SSU2000
     */
    public final String SSU2000 = "SSU2000";
    public final String SSU2000_MSG = "OK";

    /**
     * SSU4000
     */
    public final String SSU4000 = "SSU4000";
    public final String SSU4000_MSG = "Bad Request";

    /**
     * SSU4001
     */
    public final String SSU4001 = "SSU4001";
    public final String SSU4001_MSG = "Unauthorized";

    /**
     * SSU4001
     */
    public final String SSU4004 = "SSU4004";
    public final String SSU4004_MSG = "Not found";

    /**
     * SSU5000
     */
    public final String SSU5000 = "SSU5000";
    public final String SSU5000_MSG = "Internal Server Error";

    //
    // ==================================================================

    // ==================================================================
    // Table login (01)

    /**
     * SSU2010
     */
    public final String SSU2010 = "SSU2010";
    public final String SSU2010_MSG = "Login success";

    //
    // ==================================================================

    // ==================================================================
    // Table menu (02)

    /**
     * SSU2020
     */
    public final String SSU2020 = "SSU2020";
    public final String SSU2020_MSG = "Get menu success";

    //
    // ==================================================================

    // ==================================================================
    // Table order (03)

    /**
     * SSU2030
     */
    public final String SSU2030 = "SSU2030";
    public final String SSU2030_MSG = "Order success";

    /**
     * SSU4030
     */
    public final String SSU4030 = "SSU4030";
    public final String SSU4030_MSG = "Menu not found";

    /**
     * SSU4031
     */
    public final String SSU4031 = "SSU4031";
    public final String SSU4031_MSG = "Bank deposit not found";

    //
    // ==================================================================
}