package com.taqtile.tq1demo.triggerlist.presentation.viewmodel;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 7/26/16.
 */
public class TriggerListItemViewModel {

    private final String mDate;

    private final String mType;

    private final String mFenceId;

    private final String mFenceName;

    public TriggerListItemViewModel(String date, String type, String fenceId,
                                    String fenceName) {
        mDate = checkNotNull(date, "trigger date cannot be null");
        mType = checkNotNull(type, "type cannot be null");
        mFenceId = checkNotNull(fenceId, "fence id cannot be null");
        mFenceName = checkNotNull(fenceName, "fence name cannot be null");
    }


    public String getFenceName() {
        return mFenceName;
    }

    public String getDate() {
        return mDate;
    }

    public String getType() {
        return mType;
    }

    public String getFenceId() {
        return mFenceId;
    }
}
