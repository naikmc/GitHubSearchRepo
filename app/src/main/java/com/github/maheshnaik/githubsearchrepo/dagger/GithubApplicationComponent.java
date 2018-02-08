package com.github.maheshnaik.githubsearchrepo.dagger;

import com.github.maheshnaik.githubsearchrepo.network.GithubService;
import com.squareup.picasso.Picasso;

import dagger.Component;

@GithubApplicationScope
@Component(modules = {GithubServiceModule.class, ActivityModule.class})
public interface GithubApplicationComponent {

    Picasso getPicasso();

    GithubService getGithubService();
}
