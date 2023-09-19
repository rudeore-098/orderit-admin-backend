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
}