package br.com.ecarrara.completejoker.rule;

import android.support.test.espresso.IdlingRegistry;

import org.junit.rules.ExternalResource;

import br.com.ecarrara.completejoker.core.IdlingResourceProvider;

public class IdlingResourceRule extends ExternalResource {

    private IdlingResourceProvider idlingResourceProvider;

    public IdlingResourceRule(IdlingResourceProvider idlingResourceProvider) {
        super();
        this.idlingResourceProvider = idlingResourceProvider;
    }

    @Override
    protected void before() throws Throwable {
        super.before();
        IdlingResourceProvider.setIdlingResourceProvider(idlingResourceProvider);
        IdlingRegistry.getInstance().register(idlingResourceProvider.getIdlingResource());
    }

    @Override
    protected void after() {
        super.after();
        IdlingResourceProvider.clearIdlingResourceProvider();
    }

}
