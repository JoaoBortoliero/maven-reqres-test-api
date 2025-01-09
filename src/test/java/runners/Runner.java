package runners;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines({"cucumber"})
@SelectClasspathResource("features/user.feature")
@ConfigurationParameter(key = "cucumber.glue", value="steps")
@IncludeTags("CP001")
public class Runner {

}

