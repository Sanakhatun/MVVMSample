package com.professional.mvvmsample.viewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.professional.mvvmsample.repository.local.database.Sample;
import com.professional.mvvmsample.repository.model.DataModel;
import com.professional.mvvmsample.network.APIClient;
import com.professional.mvvmsample.repository.local.database.SampleRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends AndroidViewModel {

    private static final String TAG = MainActivityViewModel.class.getSimpleName();

    public MutableLiveData<List<Sample>> recordMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<String> recordMutableLiveDataFailureResponse = new MutableLiveData<>();

    private LiveData<List<Sample>> allRecords;

    private SampleRepository sampleRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        sampleRepository = new SampleRepository(application);
        allRecords = sampleRepository.getAllRecords();

    }

    public void loadData() {
        try {
            Call<DataModel> call = APIClient.getClient().fetchData();
            call.enqueue(new Callback<DataModel>() {
                @Override
                public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                    if (response != null && response.isSuccessful()) {
                        Log.i("Response", "Success" + response.body());
                        DataModel dataModel = response.body();
                        DataModel.Data data = dataModel.data;

                        List<DataModel.Record> recordList = data.records;

                        sampleRepository.isRowIsExist(recordList);

                    }
                }

                @Override
                public void onFailure(Call<DataModel> call, Throwable t) {
                    Log.i("Response", "Failure response" + t.getMessage());
                    recordMutableLiveDataFailureResponse.setValue(t.getMessage());
                }
            });
        } catch (Exception e) {
            Log.i(TAG, e.getMessage());
        }
    }


    public LiveData<List<Sample>> getAllRecords() {
        return allRecords;
    }

}


