package eu.ai4work.context.monitoring.config.models.datasources;

/*-
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

import lombok.NoArgsConstructor;
import org.eclipse.opensmartclide.context.monitoring.config.models.DataSource;
import org.eclipse.opensmartclide.context.monitoring.config.models.datasources.IDataSourceOptionValue;
import org.eclipse.opensmartclide.context.monitoring.config.models.datasources.MessageBrokerDataSourceOptions;
import org.eclipse.opensmartclide.context.monitoring.config.models.datasources.WebServiceDataSource;
import thewebsemantic.Namespace;
import thewebsemantic.RdfType;

import java.io.Serializable;

/**
 * ExampleDataSource
 *
 * @author scholze
 * @version $LastChangedRevision: 156 $
 */
@RdfType("ExampleDataSource")
@Namespace("http://atb-bremen.de/")
@NoArgsConstructor
public class ExampleDataSource extends WebServiceDataSource {

    public static final IDataSourceOptionValue ACCESS_TOKEN_OPTION = new IDataSourceOptionValue() {
        @Override
        public String getKeyName() {
            return "token";
        }

        @Override
        public Class<? extends Serializable> getValueType() {
            return String.class;
        }
    };

    public ExampleDataSource(final DataSource base) {
        this.id = base.getId();
        this.monitor = base.getMonitor();
        this.options = base.getOptions();
        this.type = base.getType().toString();
        this.uri = base.getUri();
    }

    public final String getExampleAccessToken() {
        return this.getOptionValue(ACCESS_TOKEN_OPTION, true);
    }

    public final String getMessageBrokerServer() {
        return this.getOptionValue(MessageBrokerDataSourceOptions.MessageBrokerServer, true);
    }

    public final Integer getMessageBrokerPort() {
        return this.getOptionValue(MessageBrokerDataSourceOptions.MessageBrokerPort, true);
    }

    public final String getOutgoingExchange() {
        return this.getOptionValue(MessageBrokerDataSourceOptions.OutgoingExchange, true);
    }

    public final String getOutgoingTopic() {
        return this.getOptionValue(MessageBrokerDataSourceOptions.OutgoingTopic, true);
    }

    public final String getOutgoingQueue() {
        return this.getOptionValue(MessageBrokerDataSourceOptions.OutgoingQueue, true);
    }

    public final boolean isOutgoingDurable() {
        final Serializable value = this.getOptionValue(MessageBrokerDataSourceOptions.OutgoingDurable, false);
        return value != null && (boolean) value;
    }
}
