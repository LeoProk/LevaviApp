package org.levavi.levaviapp.AppSpecifics;

import java.util.HashMap;
import java.util.Map;


//java class for time json

public class TimeData {

    private String status;
    private String message;
    private String countryCode;
    private String zoneName;
    private String abbreviation;
    private String gmtOffset;
    private String dst;
    private long timestamp;
    private Map<String, Object> additionalProperties = new HashMap<>();

    /**
     *
     * @return
     * The status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @return
     * The message
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     * The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     *
     * @return
     * The countryCode
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     *
     * @param countryCode
     * The countryCode
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     *
     * @return
     * The zoneName
     */
    public String getZoneName() {
        return zoneName;
    }

    /**
     *
     * @param zoneName
     * The zoneName
     */
    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    /**
     *
     * @return
     * The abbreviation
     */
    public String getAbbreviation() {
        return abbreviation;
    }

    /**
     *
     * @param abbreviation
     * The abbreviation
     */
    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    /**
     *
     * @return
     * The gmtOffset
     */
    public String getGmtOffset() {
        return gmtOffset;
    }

    /**
     *
     * @param gmtOffset
     * The gmtOffset
     */
    public void setGmtOffset(String gmtOffset) {
        this.gmtOffset = gmtOffset;
    }

    /**
     *
     * @return
     * The dst
     */
    public String getDst() {
        return dst;
    }

    /**
     *
     * @param dst
     * The dst
     */
    public void setDst(String dst) {
        this.dst = dst;
    }

    /**
     *
     * @return
     * The timestamp
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     *
     * @param timestamp
     * The timestamp
     */
    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
