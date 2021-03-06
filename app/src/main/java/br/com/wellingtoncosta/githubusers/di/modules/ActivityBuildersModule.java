package br.com.wellingtoncosta.githubusers.di.modules;

import br.com.wellingtoncosta.githubusers.ui.userdetails.UserDetailsActivity;
import br.com.wellingtoncosta.githubusers.ui.searchuser.SearchUsersActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author Wellington Costa on 26/12/2017.
 */
@Module
public interface ActivityBuildersModule {

    @ContributesAndroidInjector
    SearchUsersActivity contributeSearchUsersActivity();

    @ContributesAndroidInjector(modules = {FragmentBuildersModule.class})
    UserDetailsActivity contributeUserDetailsActivity();

}