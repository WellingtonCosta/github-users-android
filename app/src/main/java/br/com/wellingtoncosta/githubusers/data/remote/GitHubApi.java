package br.com.wellingtoncosta.githubusers.data.remote;

import java.util.List;

import br.com.wellingtoncosta.githubusers.domain.model.Repo;
import br.com.wellingtoncosta.githubusers.domain.model.User;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author Wellington Costa on 26/12/2017.
 */
public interface GitHubApi {

    @GET
    Observable<List<User>> getUsers();

    @GET("{username}")
    Observable<User> getUser(@Path("{username}") String username);

    @GET("{username}/followres")
    Observable<List<User>> getFollowers(@Path("{username}") String username);

    @GET("{username}/following")
    Observable<List<User>> getFollowing(@Path("{username}") String username);

    @GET("{username}/repos")
    Observable<List<Repo>> getRepos(@Path("{username}") String username);

    @GET("{username}/starred")
    Observable<List<Repo>> getStarredRepos(@Path("{username}") String username);

}