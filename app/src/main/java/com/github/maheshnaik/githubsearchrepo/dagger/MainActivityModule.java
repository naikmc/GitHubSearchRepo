package com.github.maheshnaik.githubsearchrepo.dagger;

import com.github.maheshnaik.githubsearchrepo.MainActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    private final MainActivity homeActivity;

    public MainActivityModule(MainActivity homeActivity) {
        this.homeActivity = homeActivity;
    }

    @Provides
    @MainActivityScope
    public MainActivity homeActivity() {
        return homeActivity;
    }
}
