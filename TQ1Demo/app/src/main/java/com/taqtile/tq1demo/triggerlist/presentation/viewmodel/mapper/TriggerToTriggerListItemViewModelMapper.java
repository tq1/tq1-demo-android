package com.taqtile.tq1demo.triggerlist.presentation.viewmodel.mapper;

import com.taqtile.tq1demo.triggerlist.presentation.viewmodel.TriggerListItemViewModel;
import com.taqtile.tqgeolocationsdk.models.Trigger;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by taqtile on 7/26/16.
 */
public class TriggerToTriggerListItemViewModelMapper {

    public TriggerListItemViewModel mapTriggerToTriggerListItemViewModel(Trigger trigger) {

        String formattedDate = getFormattedDate(trigger.getTimestamp());

        TriggerListItemViewModel triggerListItemViewModel = new TriggerListItemViewModel(formattedDate,
                trigger.getEvent().toString(), trigger.getFenceId(), trigger.getFenceName());

        return triggerListItemViewModel;
    }

    /**
     * Converts the timestamp to the local date format String.
     *
     * @return the formatted received date.
     */
    public String getFormattedDate(int timestamp) {

        DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.getDefault());
        String date = formatter.format(new Date(timestamp * 1000L));

        return date;
    }


    public ArrayList<TriggerListItemViewModel> mapTriggerList(ArrayList<Trigger> triggers) {
        ArrayList<TriggerListItemViewModel> triggerListItemViewModelArrayList = new ArrayList<>();

        for(Trigger trigger: triggers) {
            TriggerListItemViewModel triggerListItemViewModel = mapTriggerToTriggerListItemViewModel(trigger);
            triggerListItemViewModelArrayList.add(triggerListItemViewModel);
        }

        return triggerListItemViewModelArrayList;
    }


}
