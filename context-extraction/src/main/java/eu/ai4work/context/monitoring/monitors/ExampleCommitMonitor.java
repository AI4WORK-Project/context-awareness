package eu.ai4work.context.monitoring.monitors;

/*
 * #%L
 * AI4Work Context Awareness
 * %%
 * Copyright (C) 2024 ATB – Institut für angewandte Systemtechnik Bremen GmbH
 * %%
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 * #L%
 */

import eu.ai4work.context.monitoring.config.models.datasources.ExampleDataSource;
import org.eclipse.opensmartclide.context.monitoring.config.models.DataSource;
import org.eclipse.opensmartclide.context.monitoring.config.models.Interpreter;
import org.eclipse.opensmartclide.context.monitoring.config.models.Monitor;
import org.eclipse.opensmartclide.context.monitoring.index.Indexer;
import org.eclipse.opensmartclide.context.monitoring.monitors.webservice.WebServiceMonitor;
import org.eclipse.opensmartclide.context.tools.ontology.AmIMonitoringConfiguration;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ExampleCommitMonitor extends WebServiceMonitor {
    public ExampleCommitMonitor(final DataSource dataSource,
                               final Interpreter interpreter,
                               final Monitor monitor,
                               final Indexer indexer,
                               final AmIMonitoringConfiguration configuration) throws IOException, TimeoutException {
        super(dataSource, interpreter, monitor, indexer, configuration);
        if (!(dataSource instanceof ExampleDataSource)) {
            throw new IllegalArgumentException("Given dataSource must be of type ExampleDataSource!");
        }
        this.logger.info("Initialized ExampleCommitMonitor for uri: " + dataSource.getUri());
    }
}
