
package org.levavi.levaviapp.pojos;

import java.util.ArrayList;
import java.util.List;

public class OpeningHours {

    private boolean openNow;
    private List<Object> weekdayText = new ArrayList<Object>();

    /**
     * 
     * @return
     *     The openNow
     */
    public boolean isOpenNow() {
        return openNow;
    }

    /**
     * 
     * @param openNow
     *     The open_now
     */
    public void setOpenNow(boolean openNow) {
        this.openNow = openNow;
    }

    /**
     * 
     * @return
     *     The weekdayText
     */
    public List<Object> getWeekdayText() {
        return weekdayText;
    }

    /**
     * 
     * @param weekdayText
     *     The weekday_text
     */
    public void setWeekdayText(List<Object> weekdayText) {
        this.weekdayText = weekdayText;
    }

}
