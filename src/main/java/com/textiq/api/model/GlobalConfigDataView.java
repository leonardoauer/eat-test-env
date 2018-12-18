package com.textiq.api.model;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class GlobalConfigDataView implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	private Integer fetchLimit;
	private Integer readInterval;
	private String headlineElement;
	private List<String> summary;
	private List<String> details;
	private String notShown;
}
