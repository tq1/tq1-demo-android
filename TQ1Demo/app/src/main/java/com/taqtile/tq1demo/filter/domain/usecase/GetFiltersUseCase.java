package com.taqtile.tq1demo.filter.domain.usecase;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import br.com.taqtile.android.cleanbase.domain.BaseUseCase;
import com.taqtile.tq1demo.data.model.DataSourceResponse;
import com.taqtile.tq1demo.data.model.FilterCategory;
import com.taqtile.tq1demo.data.source.filter.FilterRepository;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 5/31/16.
 */

public class GetFiltersUseCase extends BaseUseCase<GetFiltersUseCase.RequestValues,
        GetFiltersUseCase.ResponseValue> {

    private final FilterRepository mFilterRepository;

    public GetFiltersUseCase(@NonNull FilterRepository filterRepository) {
        mFilterRepository = checkNotNull(filterRepository, "filter repository cannot be null!");
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        DataSourceResponse<ArrayList<FilterCategory>> response = mFilterRepository.getFilters();

        if (response.isSuccess()) {
            getUseCaseCallback().onSuccess(
                    new ResponseValue(response.getResponse()));
        } else {
            //TODO: Send error through callback
            getUseCaseCallback().onError();
        }
    }

    public static final class RequestValues implements BaseUseCase.RequestValues {

    }

    public static final class ResponseValue implements BaseUseCase.ResponseValue {

        private final ArrayList<FilterCategory> mFilters;

        public ResponseValue(@NonNull ArrayList<FilterCategory> filters) {
            mFilters = checkNotNull(filters, "filters cannot be null!");
        }

        public ArrayList<FilterCategory> getFilters() {
            return mFilters;
        }

    }

}
