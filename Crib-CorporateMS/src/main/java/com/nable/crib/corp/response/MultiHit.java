package com.nable.crib.corp.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MultiHit {

	private String referenceNumber;
	private List<SearchResultItem> searchResultItem;
	
}
