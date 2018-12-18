package com.textiq.api.rest.dto.request;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GroupByRequest implements Serializable {

	/** serialVersionUID*/
	private static final long serialVersionUID = 1L;
	
	@JsonAlias("id")
	private String setId;
	private String groupByFields;
	private String groupName;
	
	public List<String> getGroupByFieldsAsList() {
		return Arrays.asList(groupByFields.split(","));
	}
}
