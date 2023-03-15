package com.ts.mvc.infra.api.dto;

import java.util.List;

import javax.mail.FetchProfile.Item;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WeatherDto {
    private Response response;

    @Data
    @AllArgsConstructor
	public
    class Response {
        private Header header;
        private Body body;

        @Data
        @AllArgsConstructor
        class Header {
            String resultCode;
            String resultMsg;
        }

        @Data
        @AllArgsConstructor
		public
        class Body {
            private String dataType;
            private int pageNo;
            private int totalCount;
            private int numOfRows;
            private Items items;

            @Data
            @AllArgsConstructor
			public
            class Items {
                private List<Item> item;
            }
        }
    }
}