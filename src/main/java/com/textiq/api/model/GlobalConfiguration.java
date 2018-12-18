package com.textiq.api.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GlobalConfiguration implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	private GlobalConfigDashboard dashboard;
	private GlobalConfigDataView entitiesDataView;
	private GlobalConfigDataView documentsDataView;
}
