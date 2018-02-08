
package com.github.maheshnaik.githubsearchrepo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GithubRepoOwner {

    @SerializedName("id")
    @Expose
    public long id;

    @SerializedName("avatar_url")
    @Expose
    public String avatarUrl;


}
