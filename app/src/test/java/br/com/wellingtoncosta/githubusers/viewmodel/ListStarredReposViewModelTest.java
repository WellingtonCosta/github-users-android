package br.com.wellingtoncosta.githubusers.viewmodel;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.Observer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import br.com.wellingtoncosta.githubusers.data.remote.response.Response;
import br.com.wellingtoncosta.githubusers.domain.model.Repo;
import br.com.wellingtoncosta.githubusers.domain.repository.RepoRepository;
import br.com.wellingtoncosta.githubusers.ui.userdetails.starredrepos.ListStarredReposViewModel;
import br.com.wellingtoncosta.githubusers.util.scheduler.TestSchedulerProvider;
import io.reactivex.schedulers.TestScheduler;

import static br.com.wellingtoncosta.githubusers.Mocks.createStarredRepos;
import static io.reactivex.Observable.just;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * @author Wellington Costa on 28/12/2017.
 */
@RunWith(JUnit4.class)
public class ListStarredReposViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantExecutor = new InstantTaskExecutorRule();

    private RepoRepository repoRepository;

    private ListStarredReposViewModel listStarredReposViewModel;

    private static final String USERNAME_TEST = "WellingtonCosta";

    private static final int PAGE = 1;

    @Before
    public void setUp(){
        repoRepository = mock(RepoRepository.class);

        listStarredReposViewModel = new ListStarredReposViewModel(new TestSchedulerProvider(new TestScheduler()), repoRepository);

        when(repoRepository.getStarredRepos(USERNAME_TEST, PAGE)).thenReturn(just(createStarredRepos()));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void empty() {
        Observer<Response<List<Repo>>> result = mock(Observer.class);
        listStarredReposViewModel.getResponse().observeForever(result);
        verifyNoMoreInteractions(repoRepository);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void loadReposByUserWithSucess() {
        Observer<Response<List<Repo>>> result = mock(Observer.class);
        listStarredReposViewModel.getResponse().observeForever(result);
        listStarredReposViewModel.loadStarredRepos(USERNAME_TEST);
        verify(repoRepository).getStarredRepos(USERNAME_TEST, PAGE);
    }

}
