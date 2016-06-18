
package org.levavi.levaviapp.pojos;

import java.util.ArrayList;
import java.util.List;

public class Example {

    private List<Object> htmlAttributions = new ArrayList<Object>();
    private List<GooglePredictionData> googlePredictionDatas = new ArrayList<GooglePredictionData>();
    private String status;

    /**
     * 
     * @return
     *     The htmlAttributions
     */
    public List<Object> getHtmlAttributions() {
        return htmlAttributions;
    }

    /**
     * 
     * @param htmlAttributions
     *     The html_attributions
     */
    public void setHtmlAttributions(List<Object> htmlAttributions) {
        this.htmlAttributions = htmlAttributions;
    }

    /**
     * 
     * @return
     *     The googlePredictionDatas
     */
    public List<GooglePredictionData> getGooglePredictionDatas() {
        return googlePredictionDatas;
    }

    /**
     * 
     * @param googlePredictionDatas
     *     The googlePredictionDatas
     */
    public void setGooglePredictionDatas(List<GooglePredictionData> googlePredictionDatas) {
        this.googlePredictionDatas = googlePredictionDatas;
    }

    /**
     * 
     * @return
     *     The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

}
