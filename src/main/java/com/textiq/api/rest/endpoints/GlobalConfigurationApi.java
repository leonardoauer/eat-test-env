package com.textiq.api.rest.endpoints;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.textiq.api.json.ApiSuccess;
import com.textiq.api.model.GlobalConfigDashboard;
import com.textiq.api.model.GlobalConfigDataView;
import com.textiq.api.model.GlobalConfiguration;
import com.textiq.api.rest.ApiCodes;

@RestController
public class GlobalConfigurationApi {
	
	@Autowired
	private Environment env;
	
	@GetMapping(path = { "/config" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getGlobalConfiguration() throws InterruptedException, ExecutionException {
		
		// Create GlobalConfiguration
		GlobalConfiguration globalConfiguration = new GlobalConfiguration();
		
		// Create AppConfigDashboard 
		GlobalConfigDashboard dashboard = new GlobalConfigDashboard();
		dashboard.setTypes(Arrays.asList(env.getProperty("dashboard.setTypes").split(",")));
		dashboard.setFilterValues(Arrays.asList(env.getProperty("dashboard.filterValues").split(",")));
		
		// Create EntitiesDataView
		GlobalConfigDataView entitiesDataView = new GlobalConfigDataView();
		entitiesDataView.setFetchLimit(Integer.valueOf(env.getProperty("entitiesDataView.fetchLimit")));
		entitiesDataView.setReadInterval(Integer.valueOf(env.getProperty("entitiesDataView.readInterval")));
		entitiesDataView.setHeadlineElement(env.getProperty("entitiesDataView.headlineElement"));
		entitiesDataView.setSummary(Arrays.asList(env.getProperty("entitiesDataView.summary").split(",")));
		entitiesDataView.setDetails(Arrays.asList(env.getProperty("entitiesDataView.details").split(",")));
		entitiesDataView.setNotShown(env.getProperty("entitiesDataView.notShown"));
		
		// Create EntitiesDataView
		GlobalConfigDataView documentsDataView = new GlobalConfigDataView();
		documentsDataView.setFetchLimit(Integer.valueOf(env.getProperty("documentsDataView.fetchLimit")));
		documentsDataView.setReadInterval(Integer.valueOf(env.getProperty("documentsDataView.readInterval")));
		documentsDataView.setHeadlineElement(env.getProperty("documentsDataView.headlineElement"));
		documentsDataView.setSummary(Arrays.asList(env.getProperty("documentsDataView.summary").split(",")));
		documentsDataView.setDetails(Arrays.asList(env.getProperty("documentsDataView.details").split(",")));
		documentsDataView.setNotShown(env.getProperty("documentsDataView.notShown"));
		
		globalConfiguration.setDashboard(dashboard);
		globalConfiguration.setEntitiesDataView(entitiesDataView);
		globalConfiguration.setDocumentsDataView(documentsDataView);
		
		return ResponseEntity.ok(ApiSuccess.builder()
				.code(ApiCodes.SUCCESS_MESSAGE)
				.data(globalConfiguration).build());
	}
}
