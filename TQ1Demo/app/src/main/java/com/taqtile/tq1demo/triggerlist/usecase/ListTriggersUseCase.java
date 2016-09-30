package com.taqtile.tq1demo.triggerlist.usecase;

import android.support.annotation.NonNull;

import com.taqtile.tq1demo.data.model.DataSourceResponse;
import com.taqtile.tq1demo.data.source.triggers.TriggerRepository;
import com.taqtile.tqgeolocationsdk.models.Trigger;

import java.util.ArrayList;

import br.com.taqtile.android.cleanbase.domain.BaseUseCase;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 7/26/16.
 */
public class ListTriggersUseCase extends BaseUseCase<ListTriggersUseCase.RequestValues,
        ListTriggersUseCase.ResponseValue> {

    private final TriggerRepository mTriggerRepository;

    public ListTriggersUseCase(@NonNull TriggerRepository triggerRepository) {
        mTriggerRepository = checkNotNull(triggerRepository, "trigger repository cant be null");
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        DataSourceResponse<ArrayList<Trigger>> response = mTriggerRepository.getTriggers();

        if (response.isSuccess()) {
            getUseCaseCallback().onSuccess(
                    new ResponseValue(response.getResponse()));
        } else {
            //TODO: Send the error through callback
            getUseCaseCallback().onError();
        }
    }
    public static final class RequestValues implements BaseUseCase.RequestValues {
        public RequestValues() {
        }

    }

    public static final class ResponseValue implements BaseUseCase.ResponseValue {

        private final ArrayList<Trigger> mTriggersList;

        public ResponseValue(@NonNull ArrayList<Trigger> triggersList) {
            mTriggersList = checkNotNull(triggersList, "triggers list cant be null");
        }

        public ArrayList<Trigger> getTriggersList() {
            return mTriggersList;
        }

    }
}

