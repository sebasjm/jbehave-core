package org.jbehave.examples.trader;

import static org.jbehave.scenario.i18n.StringEncoder.ISO_8859_1;
import static org.jbehave.scenario.i18n.StringEncoder.UTF_8;

import java.util.Locale;

import org.hamcrest.text.StringEndsWith;
import org.jbehave.scenario.JUnitScenario;
import org.jbehave.scenario.PropertyBasedConfiguration;
import org.jbehave.scenario.definition.KeyWords;
import org.jbehave.scenario.i18n.I18nKeyWords;
import org.jbehave.scenario.i18n.StringEncoder;
import org.jbehave.scenario.parser.ClasspathScenarioDefiner;
import org.jbehave.scenario.parser.PatternScenarioParser;
import org.jbehave.scenario.parser.ScenarioDefiner;
import org.jbehave.scenario.parser.UnderscoredCamelCaseResolver;
import org.jbehave.scenario.reporters.PrintStreamScenarioReporter;
import org.jbehave.scenario.reporters.ScenarioReporter;

public class PtTraderScenario extends JUnitScenario {

	public PtTraderScenario() {
		this(Thread.currentThread().getContextClassLoader());
	}

	public PtTraderScenario(final ClassLoader classLoader) {
		super(new PropertyBasedConfiguration() {
			@Override
			public ScenarioDefiner forDefiningScenarios() {
				// use underscored camel case scenario files with extension ".cenario"
				return new ClasspathScenarioDefiner(
						new UnderscoredCamelCaseResolver(".cenario"),
						new PatternScenarioParser(this), classLoader);
			}

			@Override
			public ScenarioReporter forReportingScenarios() {
				// report outcome in Portuguese (to System.out)
				return new PrintStreamScenarioReporter(new I18nKeyWords(new Locale("pt"), new StringEncoder(UTF_8, ISO_8859_1)));
			}

			@Override
			public KeyWords keywords() {
				// use Portuguese for keywords
				return new I18nKeyWords(new Locale("pt"));
			}

		}, new PtTraderSteps(classLoader));
	}

}