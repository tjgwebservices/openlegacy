package org.openlegacy.providers.jt400;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ Jt400OnLineSuite.class, Jt400OffLineSuite.class })
public class Jt400Suite {

}
