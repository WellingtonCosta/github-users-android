package br.com.wellingtoncosta.githubusers.ui.userdetails.starredrepos;

import java.util.List;

import javax.inject.Inject;

import br.com.wellingtoncosta.githubusers.data.remote.response.Response;
import br.com.wellingtoncosta.githubusers.domain.model.Repo;
import br.com.wellingtoncosta.githubusers.domain.repository.RepoRepository;
import br.com.wellingtoncosta.githubusers.ui.base.BaseViewModel;
import br.com.wellingtoncosta.githubusers.util.scheduler.BaseScheduler;

/**
 * @author Wellington Costa on 27/12/2017.
 */
public class ListStarredReposViewModel extends BaseViewModel<List<Repo>> {

    private BaseScheduler scheduler;

    private RepoRepository repoRepository;

    private int currentPage = 1;

    @Inject
    public ListStarredReposViewModel(BaseScheduler scheduler, RepoRepository repoRepository) {
        this.scheduler = scheduler;
        this.repoRepository = repoRepository;
    }

    public void loadStarredRepos(String username) {
        repoRepository.getStarredRepos(username, currentPage)
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .doOnSubscribe(s -> loadingStatus.setValue(true))
                .doAfterTerminate(() -> loadingStatus.setValue(false))
                .subscribe(
                        user -> response.setValue(Response.success(user)),
                        throwable -> response.setValue(Response.error(throwable))
                );
    }

    void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}