package rest.rules;

import lombok.extern.slf4j.Slf4j;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import rest.configuration.RestAssuredConfiguration;

import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public  class  SuiteRule implements TestRule {

    public static final SuiteRule INSTANCE = new SuiteRule();
    private final AtomicBoolean initialized = new AtomicBoolean();

    @Override
    public Statement apply(Statement base, Description description) {
        if (!initialized.get()) {
            beforeSuite(description);
            initialized.set(true);
        }

        return base;
    }

    private void beforeSuite(Description description) {
        log.info("Start Before suite");
        log.info("Setting rest assured");
        RestAssuredConfiguration.RestAssuredSetup();
    }

}