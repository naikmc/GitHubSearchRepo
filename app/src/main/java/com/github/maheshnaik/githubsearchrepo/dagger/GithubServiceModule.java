package com.github.maheshnaik.githubsearchrepo.dagger;

import android.content.Context;

import com.github.maheshnaik.githubsearchrepo.network.GithubService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class GithubServiceModule {

    @Provides
    @GithubApplicationScope
    public GithubService githubService(Retrofit gitHubRetrofit) {
        return gitHubRetrofit.create(GithubService.class);
    }

    @Provides
    @GithubApplicationScope
    public Gson gson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    @GithubApplicationScope
    public Retrofit retrofit( Gson gson) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://api.github.com/")
                .build();
    }

    @Provides
    @GithubApplicationScope
    public Picasso picasso(Context context) {
        return new Picasso.Builder(context)
                .build();
    }

}
