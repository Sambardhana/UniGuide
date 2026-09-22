/**
 * Exception handling and custom application exceptions for UniGuide.
 * <p>
 * Responsibilities:
 * <ul>
 *   <li>Define domain-specific runtime exceptions (e.g., resource not found, unauthorized, validation errors).</li>
 *   <li>Provide centralized {@code @RestControllerAdvice} handlers for intercepting exceptions.</li>
 *   <li>Translate application exceptions into standardized, client-friendly error response payloads.</li>
 * </ul>
 */
package com.uniguide.exception;
