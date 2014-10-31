package org.openlegacy;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.openlegacy.db.OpenLegacyDbRuntimeSuite;
import org.openlegacy.rpc.OpenLegacyRpcRuntimeSuite;
import org.openlegacy.terminal.OpenLegacyScreensRuntimeSuite;

@RunWith(Suite.class)
@SuiteClasses({ OpenLegacyScreensRuntimeSuite.class, OpenLegacyRpcRuntimeSuite.class, OpenLegacyDbRuntimeSuite.class })
public class OpenLegacyRuntimeSuite {

}
