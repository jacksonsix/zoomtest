package com.elementfleet.ordering3.stock.fsm.events;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.elementfleet.ordering3.exception.OrderingExceptionMessage;
import com.elementfleet.ordering3.fsm.IEventType;
import com.elementfleet.ordering3.project.event.CommonProjectEvent;
import com.elementfleet.ordering3.project.stock.StockWorkflowEventType;

public class StockEvent extends CommonProjectEvent {

	public static final String STOCK_PROJECT_MAJOR_EVENT_CODE = "OR";

	public static final String EVENT_ID_KEY = "eventId";
	public static final String COMPONENT_ID_KEY = "componentId";
	public static final String EVENT_MAJOR_CODE_KEY = "eventMajorCode";
	public static final String EVENT_MINOR_CODE_KEY = "eventMinorCode";
	public static final String USER_NAME_KEY = "userName";
	public static final String STOCK_MESSAGE_KEY = "message";
	public static final String STOCK_EVENT = "stockEvent";
	public static final String STOCK_COMPLETED = "stockCompleted";

	private LocalDateTime timestamp; // NOSONAR
	private Map<String, Object> payload; // NOSONAR
	private final List<OrderingExceptionMessage> errorList; // NOSONAR

	private StockWorkflowEventType eventType; // NOSONAR
	private String taskCode; // NOSONAR

	public StockEvent() {
		this.timestamp = LocalDateTime.now();
		this.payload = new LinkedHashMap<>();
		this.errorList = new ArrayList<>();
		this.setPayloadItem(EVENT_ID_KEY, super.getId());

	}

	public StockEvent(String message, Map<String, Object> payload, StockWorkflowEventType eventType) {
		super(message, payload, eventType);
		this.errorList = new ArrayList<>();
		this.eventType = eventType;
		payload.put(EVENT_ID_KEY, getId());
	}

	@Override
	public IEventType getEventType() {
		return eventType;
	}

	@Override
	public List<OrderingExceptionMessage> getErrorList() {
		return this.errorList;
	}

	public StockEvent setEventId(final Long eventId) {
		this.setPayloadItem(EVENT_ID_KEY, eventId);
		return this;
	}

	public StockEvent addPayLoadItems(Map<String, Object> payload) {
		if (null == this.payload) {
			this.payload = new LinkedHashMap<>();
		}
		if (null != payload && !payload.isEmpty()) {
			payload.keySet().forEach(key -> this.setPayloadItem(key, payload.get(key)));
		}
		return this;
	}

	public StockEvent setEventType(final StockWorkflowEventType eventType) {
		this.eventType = eventType;
		return this;
	}

	@Override
	public StockEvent setPayloadItem(String key, Object item) {
		this.payload.put(key, item);
		return this;
	}

	public StockEvent setMessage(String message) {
		this.message = message;
		if (null != message) {
			this.setPayloadItem(STOCK_MESSAGE_KEY, message);
		}
		return this;
	}

	public StockEvent setUpfitEvent(String eventName) {
		this.setPayloadItem(STOCK_EVENT, Objects.requireNonNull(eventName));
		return this;
	}

	public StockEvent setUpfitComplete(String completed) {
		this.setPayloadItem(STOCK_COMPLETED, Objects.requireNonNull(completed));
		return this;
	}

	public StockEvent setTaskCode(String taskCode) {
		this.taskCode = taskCode;
		return this;
	}

	@Override
	public StockEvent setComponentId(final Long componentId) {
		this.setPayloadItem(COMPONENT_ID_KEY, Objects.requireNonNull(componentId));
		return this;
	}

	public String getTaskCode() {
		return this.taskCode;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

	@Override
	public LocalDateTime getTimestamp() {
		return this.timestamp;
	}

	@Override
	public Map<String, Object> getPayload() {
		return this.payload;
	}

	

}
