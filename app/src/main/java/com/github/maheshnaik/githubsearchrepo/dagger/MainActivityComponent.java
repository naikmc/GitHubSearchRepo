package com.github.maheshnaik.githubsearchrepo.dagger;

import com.github.maheshnaik.githubsearchrepo.MainActivity;
import com.github.maheshnaik.githubsearchrepo.network.GithubService;
import com.squareup.picasso.Picasso;

import dagger.Component;

@GithubApplicationScope
@Component(modules = {GithubServiceModule.class, ContextModule.class})
public interface MainActivityComponent {

    void injectHomeActivity(MainActivity homeActivity);

    Picasso getPicasso();

    GithubService getGithubService();
}
