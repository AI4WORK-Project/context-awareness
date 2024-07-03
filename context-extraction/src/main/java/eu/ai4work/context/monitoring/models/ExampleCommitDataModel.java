package eu.ai4work.context.monitoring.models;

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

import lombok.Getter;
import lombok.Setter;
import org.apache.jena.rdf.model.Model;
import org.eclipse.opensmartclide.context.common.Version;
import org.eclipse.opensmartclide.context.common.util.ApplicationScenario;
import org.eclipse.opensmartclide.context.common.util.BusinessCase;
import org.eclipse.opensmartclide.context.monitoring.config.models.datasources.WebServiceDataSource;
import org.eclipse.opensmartclide.context.monitoring.models.IMonitoringDataModel;
import org.eclipse.opensmartclide.context.monitoring.rdf.RdfHelper;
import org.eclipse.opensmartclide.context.persistence.ModelOutputLanguage;
import org.simpleframework.xml.Root;
import thewebsemantic.Id;
import thewebsemantic.Namespace;
import thewebsemantic.RdfType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RdfType("ExampleCommitDataModel")
@Namespace(BusinessCase.NS_DUMMY_URL)
@Root
@Getter
@Setter
public class ExampleCommitDataModel implements IMonitoringDataModel<ExampleCommitDataModel, WebServiceDataSource> {
    private Date monitoredAt;
    private String documentIndexId = "index/broker";
    private String documentUri;
    @Id
    private String identifier;
    private WebServiceDataSource dataSource;
    private String implementingClassName = ExampleCommitDataModel.class.getName();
    private String monitoringDataVersion = Version.MONITORING_DATA.getVersionString();
    private List<ExampleCommitMessage> ExampleCommitMessages = new ArrayList<>();

    public ExampleCommitDataModel() {
        this.identifier = UUID.randomUUID().toString();
    }

    public void addGitMessage(ExampleCommitMessage ExampleCommitMessage) {
        if (!this.ExampleCommitMessages.contains(ExampleCommitMessage)) {
            this.ExampleCommitMessages.add(ExampleCommitMessage);
        }
    }

    @Override
    public ExampleCommitDataModel fromRdfModel(String rdfModel) {
        return RdfHelper.createMonitoringData(rdfModel, ExampleCommitDataModel.class);
    }

    @Override
    public ExampleCommitDataModel fromRdfModel(Model model) {
        return RdfHelper.createMonitoringData(model, ExampleCommitDataModel.class);
    }

    @Override
    public String toRdfString() {
        return ModelOutputLanguage.RDFXML.getModelAsString(this.toRdfModel());
    }

    @Override
    public Model toRdfModel() {
        return RdfHelper.createRdfModel(this);
    }

    @Override
    public String getContextIdentifierClassName() {
        return null;
    }

    @Override
    public void setDataSource(WebServiceDataSource dataSource) {
    }

    @Override
    public BusinessCase getBusinessCase() {
        return BusinessCase.getInstance(BusinessCase.NS_DUMMY_ID, BusinessCase.NS_DUMMY_URL);
    }

    @Override
    public ApplicationScenario getApplicationScenario() {
        return ApplicationScenario.getInstance(getBusinessCase());
    }

    @Override
    public boolean triggersContextChange() {
        return false;
    }

    @Override
    public void initialize() {

    }
}
