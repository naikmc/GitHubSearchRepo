package com.github.maheshnaik.githubsearchrepo.network;

import com.github.maheshnaik.githubsearchrepo.model.GithubSearchResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GithubService {

  @GET("/search/repositories?")
  Call<GithubSearchResult> getSearchRepos(@Query("q") String search);

}
