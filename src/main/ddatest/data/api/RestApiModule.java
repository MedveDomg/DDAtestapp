package com.example.medvedomg.ddatest.data.api;

import android.app.Application;

import com.example.medvedomg.ddatest.data.db.DbModule;
import com.example.medvedomg.ddatest.data.model.Student;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by medvedomg on 05.01.17.
 */
@Module
public class RestApiModule {

    public static String BASE_URL = "https://ddapp-sfa-api.azurewebsites.net/";

    @Provides
    @Singleton
    public Retrofit provideRestAdapter(Application application) {
        Retrofit builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return builder;
    }

    @Provides
    @Singleton
    public StudentApiInterface provideGithubApiService(Retrofit restAdapter) {
        return restAdapter.create(StudentApiInterface.class);
    }

    @Provides
    @Singleton
    public StudentManager provideUserManager(StudentApiInterface githubApiService) {
        return new StudentManager(githubApiService);
    }

    @Singleton
    @Provides
    DbModule provideDb(Application application, StudentManager manager) {
        return new DbModule(application, manager);
    }
}
