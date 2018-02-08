
package com.github.maheshnaik.githubsearchrepo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GithubRepo {

    @SerializedName("id")
    @Expose
    public long id;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("owner")
    @Expose
    public GithubRepoOwner owner;

    @SerializedName("description")
    @Expose
    public String description;

    @SerializedName("stargazers_count")
    @Expose
    public long stargazersCount;

}
