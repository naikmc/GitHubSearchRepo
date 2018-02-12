package com.github.maheshnaik.githubsearchrepo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.github.maheshnaik.githubsearchrepo.model.GithubRepo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

public class SearchAdapterRepos extends BaseAdapter {

  private final List<GithubRepo> repoList = new ArrayList<>(0);
  private final Context context;
  private final Picasso picasso;

  @Inject
  public SearchAdapterRepos(Context context, Picasso picasso) {
    this.context = context;
    this.picasso = picasso;
  }

  @Override
  public int getCount() {
    return repoList.size();
  }

  @Override
  public GithubRepo getItem(int position) {
    return repoList.get(position);
  }

  @Override
  public boolean hasStableIds() {
    return true;
  }

  @Override
  public long getItemId(int position) {
    return repoList.get(position).id;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    SearchRepoListItem repoListItem;
    if (convertView == null) {
      repoListItem = new SearchRepoListItem(context, picasso);
    } else {
      repoListItem = SearchRepoListItem.class.cast(convertView);
    }

    repoListItem.setRepo(repoList.get(position));

    return repoListItem;
  }

  public void updateData(Collection<GithubRepo> githubRepos) {
    repoList.clear();
    if (githubRepos != null) {
      repoList.addAll(githubRepos);
    }
    notifyDataSetChanged();
  }

}
