package com.campuscatalyst.domain.research;

/**
 * Intent type classification for keywords.
 */
public enum IntentType {
    /**
     * User is seeking information
     */
    INFORMATIONAL,

    /**
     * User is looking to navigate to a specific page/site
     */
    NAVIGATIONAL,

    /**
     * User is looking to make a purchase or take action
     */
    TRANSACTIONAL,

    /**
     * User is researching before a potential purchase
     */
    COMMERCIAL
}
