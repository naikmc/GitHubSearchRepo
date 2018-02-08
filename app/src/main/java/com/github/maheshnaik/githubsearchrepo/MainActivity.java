package com.github.maheshnaik.githubsearchrepo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.github.maheshnaik.githubsearchrepo.dagger.ContextModule;
import com.github.maheshnaik.githubsearchrepo.dagger.DaggerGithubApplicationComponent;
import com.github.maheshnaik.githubsearchrepo.dagger.DaggerMainActivityComponent;
import com.github.maheshnaik.githubsearchrepo.dagger.GithubApplicationComponent;
import com.github.maheshnaik.githubsearchrepo.dagger.MainActivityComponent;
import com.github.maheshnaik.githubsearchrepo.dagger.MainActivityModule;
import com.github.maheshnaik.githubsearchrepo.model.GithubRepo;
import com.github.maheshnaik.githubsearchrepo.model.GithubSearchResult;
import com.github.maheshnaik.githubsearchrepo.network.GithubService;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.repo_home_list)
    ListView listView;

    Call<GithubSearchResult> reposResult;

    @Inject
    GithubService githubService;

    @Inject
    SearchAdapterRepos adapterRepos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Timber.plant(new Timber.DebugTree());

        GithubApplicationComponent gitComponent = DaggerGithubApplicationComponent.builder()
                .contextModule(new ContextModule(getApplicationContext()))
                .build();
        MainActivityComponent component = DaggerMainActivityComponent.builder()
                .mainActivityModule(new MainActivityModule(this))
                .githubApplicationComponent(gitComponent)
                .build();

        component.injectHomeActivity(this);

        listView.setAdapter(adapterRepos);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Search Git Repo");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                reposResult = githubService.getSearchRepos(query);

                reposResult.enqueue(new Callback<GithubSearchResult>() {
                    @Override
                    public void onResponse(Call<GithubSearchResult> call, Response<GithubSearchResult> response) {
                        List<GithubRepo> searchResults = response.body().searchResults;
                        if (searchResults != null && !searchResults.isEmpty()) {
                            adapterRepos.updateData(searchResults);
                        } else {
                            Toast.makeText(MainActivity.this, "No Result ", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<GithubSearchResult> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Some Error Happened " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }

        });

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(reposResult != null) {
            reposResult.cancel();
        }
    }
}
