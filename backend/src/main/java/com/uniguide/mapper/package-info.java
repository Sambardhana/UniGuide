/**
 * Object mappers and transformation utilities for converting between entities and DTOs.
 * <p>
 * Responsibilities:
 * <ul>
 *   <li>Transform incoming request DTOs into JPA domain entities.</li>
 *   <li>Transform JPA domain entities into client response DTOs.</li>
 *   <li>Prevent entity leakage and isolate presentation models from persistence models.</li>
 * </ul>
 */
package com.uniguide.mapper;
