package com.textiq.api.model;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GlobalConfigDashboard implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	private List<String> types;
	private List<String> filterValues;
}
