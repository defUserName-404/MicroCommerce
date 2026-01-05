package com.defusername.microcommerce.category;

import lombok.Builder;

@Builder
public record CategoryResponse(String categoryName, String categoryDescription) {

}
