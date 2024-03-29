package com.te.lms.response;

import javax.websocket.server.ServerEndpoint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class SuccessResponse<T> {
	private T data;
	private String message;
}
