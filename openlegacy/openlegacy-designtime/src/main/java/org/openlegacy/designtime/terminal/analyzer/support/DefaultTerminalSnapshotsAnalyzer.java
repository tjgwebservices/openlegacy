package org.openlegacy.designtime.terminal.analyzer.support;

import org.drools.KnowledgeBase;
import org.drools.runtime.StatefulKnowledgeSession;
import org.openlegacy.designtime.analyzer.SnapshotsSorter;
import org.openlegacy.designtime.rules.RuleDefinition;
import org.openlegacy.designtime.rules.RuleParametersSet;
import org.openlegacy.designtime.rules.support.RuleParametersSetBean;
import org.openlegacy.designtime.terminal.analyzer.ScreenEntityDefinitionsBuilder;
import org.openlegacy.designtime.terminal.analyzer.TerminalSnapshotsAnalyzer;
import org.openlegacy.designtime.utils.DroolsUtil;
import org.openlegacy.terminal.TerminalSnapshot;
import org.openlegacy.terminal.definitions.ScreenEntityDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class DefaultTerminalSnapshotsAnalyzer implements TerminalSnapshotsAnalyzer {

	@Inject
	private SnapshotsSorter<TerminalSnapshot> snapshotsSorter;

	@Inject
	private ScreenEntityDefinitionsBuilder screenEntityDefinitionsBuilder;

	private RuleDefinition[] ruleDefinitions;

	private String processName;

	public Map<String, ScreenEntityDefinition> analyzeSnapshots(List<TerminalSnapshot> snapshots) {

		TerminalSnapshotsAnalyzerContext snapshotsAnalyzerContext = new TerminalSnapshotsAnalyzerContext();
		snapshotsAnalyzerContext.setActiveSnapshots(snapshots);

		List<String> droolsResources = new ArrayList<String>();

		List<RuleParametersSet> ruleParametersFacts = new ArrayList<RuleParametersSet>();
		for (RuleDefinition ruleDefinition : ruleDefinitions) {
			if (!ruleDefinition.isEnable()) {
				continue;
			}
			droolsResources.add(ruleDefinition.getDroolsFile());

			List<RuleParametersSet> ruleParametersSets = ruleDefinition.getRuleParameterSets();
			for (RuleParametersSet ruleParametersSet : ruleParametersSets) {
				((RuleParametersSetBean)ruleParametersSet).setRuleId(ruleDefinition.getRuleId());
				ruleParametersFacts.add(ruleParametersSet);
			}
		}

		KnowledgeBase knowledgeBase = DroolsUtil.createKnowledgeBase(droolsResources.toArray(new String[droolsResources.size()]));
		StatefulKnowledgeSession session = knowledgeBase.newStatefulKnowledgeSession();

		for (RuleParametersSet ruleParametersSet : ruleParametersFacts) {
			session.insert(ruleParametersSet);

		}

		session.setGlobal("snapshotsAnalyzerContext", snapshotsAnalyzerContext);
		session.setGlobal("snapshotsSorter", snapshotsSorter);
		session.setGlobal("screenEntityDefinitionsBuilder", screenEntityDefinitionsBuilder);

		try {
			session.startProcess(processName);
			session.fireAllRules();
		} finally {
			session.dispose();
		}

		return snapshotsAnalyzerContext.getEntitiesDefinitions();
	}

	public void setRuleDefinitions(RuleDefinition[] ruleDefinitions) {
		this.ruleDefinitions = ruleDefinitions;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}
}
