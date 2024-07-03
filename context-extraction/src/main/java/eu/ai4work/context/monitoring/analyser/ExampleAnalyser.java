package eu.ai4work.context.monitoring.analyser;

/*
 * #%L
 * AI4Work Context Awareness
 * %%
 * Copyright (C) 2024 – Institut für angewandte Systemtechnik Bremen GmbH
 * %%
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 * #L%
 */

import eu.ai4work.context.monitoring.config.models.datasources.ExampleDataSource;
import org.apache.lucene.document.Document;
import org.eclipse.opensmartclide.context.monitoring.analyser.webservice.WebServiceAnalyser;
import org.eclipse.opensmartclide.context.monitoring.config.models.DataSource;
import org.eclipse.opensmartclide.context.monitoring.config.models.InterpreterConfiguration;
import org.eclipse.opensmartclide.context.tools.ontology.AmIMonitoringConfiguration;
import org.eclipse.opensmartclide.context.monitoring.index.Indexer;
import eu.ai4work.context.monitoring.models.ExampleCommitDataModel;
import org.eclipse.opensmartclide.context.monitoring.models.IWebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

public class ExampleAnalyser extends WebServiceAnalyser<ExampleCommitDataModel> {

    private static final Logger logger = LoggerFactory.getLogger(ExampleAnalyser.class);

    public ExampleAnalyser(final DataSource dataSource,
                                final InterpreterConfiguration interpreterConfiguration,
                                final Indexer indexer,
                                final Document document,
                                final AmIMonitoringConfiguration amiConfiguration) {
        super(dataSource, interpreterConfiguration, indexer, document, amiConfiguration);
        if (!(dataSource instanceof ExampleDataSource)) {
            throw new IllegalArgumentException("Given dataSource must be of type ExampleDataSource!");
        }
    }

    @Override
    public List<ExampleCommitDataModel> analyseObject(IWebService service) {
        try {

            final ExampleCommitDataModel model = new ExampleCommitDataModel();
            model.setMonitoredAt(new Date());
            return List.of(model);
        } catch (Exception e) {
            logger.error("Error analysing service: {}", service);
            return List.of();
        }
    }
}
