package com.professional.mvvmsample.repository.local.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.professional.mvvmsample.repository.local.database.dao.SampleDao;
import com.professional.mvvmsample.repository.model.DataModel;

import java.util.List;

public class SampleRepository {

    private SampleDao sampleDao;

    private AppDatabase appDatabase;

    public SampleRepository(Application application) {
        appDatabase = AppDatabase.getInstance(application);
        sampleDao = appDatabase.sampleDao();
    }

    public void insert(Sample sample) {
        new SampleAsyncTask(sampleDao, "insert").execute(sample);
    }

    public void update(Sample sample) {
        new SampleAsyncTask(sampleDao, "update").execute(sample);
    }

    public void delete(Sample sample) {
        new SampleAsyncTask(sampleDao, "delete").execute(sample);
    }

    public LiveData<List<Sample>> getAllRecords() {
        return sampleDao.getRecords();
    }

    public void isRowIsExist(List<DataModel.Record> recordList) {
        new IsRecordExistsAsyncTask(sampleDao).execute(recordList);
    }

    private static class SampleAsyncTask extends AsyncTask<Sample, String, Void> {

        private SampleDao sampleDao;
        private String typeOfAction;

        private SampleAsyncTask(SampleDao sampleDao, String typeOfAction) {
            this.sampleDao = sampleDao;
            this.typeOfAction = typeOfAction;
        }

        @Override
        protected Void doInBackground(Sample... samples) {

            switch (typeOfAction) {
                case "insert": {
                    sampleDao.insert(samples[0]);
                    break;
                }

                case "update": {
                    sampleDao.update(samples[0]);
                    break;
                }

                case "delete": {
                    sampleDao.delete(samples[0]);
                    break;
                }

            }

            return null;
        }
    }

    private static class IsRecordExistsAsyncTask extends AsyncTask<List<DataModel.Record>, Void, Void> {

        private SampleDao sampleDao;

        private IsRecordExistsAsyncTask(SampleDao sampleDao) {
            this.sampleDao = sampleDao;
        }

        @Override
        protected Void doInBackground(List<DataModel.Record>... recordList) {
            if (recordList[0] != null && recordList[0].size() > 0) {
                for (int i = 0; i < recordList[0].size(); i++) {

                    Sample sample = new Sample();
                    sample.setTitle(recordList[0].get(i).title);
                    sample.setShortDescription(recordList[0].get(i).shortDescription);
                    sample.setCollectedValue(recordList[0].get(i).collectedValue);
                    sample.setTotalValue(recordList[0].get(i).totalValue);
                    sample.setStartDate(recordList[0].get(i).startDate);
                    sample.setEndDate(recordList[0].get(i).endDate);
                    sample.setMainImageURL(recordList[0].get(i).mainImageURL);

                    if (sampleDao.isRowIsExist(recordList[0].get(i).id)) {
                        sampleDao.update(sample);
                    } else {
                        sampleDao.insert(sample);
                    }

                }
            }
            return null;
        }
    }
}
