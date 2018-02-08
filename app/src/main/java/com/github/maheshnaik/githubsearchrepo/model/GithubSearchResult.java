
package com.github.maheshnaik.githubsearchrepo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GithubSearchResult {

    @SerializedName("items")
    @Expose
    public List<GithubRepo>  searchResults = null;

}
