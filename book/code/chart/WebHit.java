/*
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * ---------------------------
 * WebHit.java
 * ---------------------------
 * (C) Copyright 2002-2004, by Richard Atkinson.
 *
 * Original Author:  Richard Atkinson;
 */


import java.util.Date;

public class WebHit {
    protected Date hitDate = null;
    protected String section = null;
    protected long hitCount = 0;

    public WebHit(Date dHitDate, String sSection, long lHitCount) {
        this.hitDate = dHitDate;
        this.section = sSection;
        this.hitCount = lHitCount;
    }

    public Date getHitDate() {
        return this.hitDate;
    }
    public String getSection() {
        return this.section;
    }
    public long getHitCount() {
        return this.hitCount;
    }

    public void setHitDate(Date dHitDate) {
        this.hitDate = dHitDate;
    }
    public void setSection(String sSection) {
        this.section = sSection;
    }
    public void setHitCount(long lHitCount) {
        this.hitCount = lHitCount;
    }

}
