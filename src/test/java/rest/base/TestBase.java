package rest.base;

import lombok.extern.slf4j.Slf4j;
import org.junit.ClassRule;
import rest.rules.SuiteRule;

@Slf4j
public class TestBase {

    @ClassRule
    public static SuiteRule rule = SuiteRule.INSTANCE;
    //It emulates @BeforeSuite from TestNG

}
