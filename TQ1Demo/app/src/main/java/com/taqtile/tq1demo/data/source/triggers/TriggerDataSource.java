package com.taqtile.tq1demo.data.source.triggers;

import com.taqtile.tq1demo.data.model.DataSourceResponse;
import com.taqtile.tqgeolocationsdk.models.Trigger;

import java.util.ArrayList;

import taqtile.android.sdk.push.TQInboxMessage;

/**
 * Created by taqtile on 7/26/16.
 */
public interface TriggerDataSource {

    DataSourceResponse<ArrayList<Trigger>> getTriggers();

}
