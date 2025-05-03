package com.nable.crib.corp.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchResultItem {
	
	public String name;
	public String bureauId;
	public String identifierSource;
	public String identifierValue;
	public String identifierMatched;
	public String surrogate;
	public String surrogateMatched;

}
