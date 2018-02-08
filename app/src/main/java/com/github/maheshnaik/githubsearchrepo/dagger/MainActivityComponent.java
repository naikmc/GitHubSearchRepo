package com.github.maheshnaik.githubsearchrepo.dagger;

import com.github.maheshnaik.githubsearchrepo.MainActivity;

import dagger.Component;

@MainActivityScope
@Component(modules = MainActivityModule.class, dependencies = GithubApplicationComponent.class)
public interface MainActivityComponent {

    void injectHomeActivity(MainActivity homeActivity);
}
