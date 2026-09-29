package com.uniguide.service;

import com.uniguide.dto.ContactRequest;
import com.uniguide.dto.ContactResponse;
import com.uniguide.entity.Contact;
import com.uniguide.entity.Department;
import com.uniguide.exception.ResourceNotFoundException;
import com.uniguide.mapper.ContactMapper;
import com.uniguide.repository.ContactRepository;
import com.uniguide.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service managing university directory contacts, emergency helplines, and administrative desks.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContactService {

    private final ContactRepository contactRepository;
    private final DepartmentRepository departmentRepository;
    private final ContactMapper contactMapper;

    public List<ContactResponse> getAllContacts() {
        return contactRepository.findAll().stream()
                .map(contactMapper::toResponse)
                .toList();
    }

    public ContactResponse getContactById(Long id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact", "id", id));
        return contactMapper.toResponse(contact);
    }

    public List<ContactResponse> getContactsByCategory(String category) {
        return contactRepository.findByCategoryIgnoreCase(category).stream()
                .map(contactMapper::toResponse)
                .toList();
    }

    public List<ContactResponse> getContactsByDepartment(Long departmentId) {
        return contactRepository.findByDepartmentId(departmentId).stream()
                .map(contactMapper::toResponse)
                .toList();
    }

    public List<ContactResponse> searchContacts(String keyword) {
        return contactRepository.findByNameContainingIgnoreCase(keyword).stream()
                .map(contactMapper::toResponse)
                .toList();
    }

    @Transactional
    public ContactResponse createContact(ContactRequest request) {
        Department department = null;
        if (request.getDepartmentId() != null) {
            department = departmentRepository.findById(request.getDepartmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Department", "id", request.getDepartmentId()));
        }

        Contact contact = contactMapper.toEntity(request, department);
        Contact savedContact = contactRepository.save(contact);
        return contactMapper.toResponse(savedContact);
    }

    @Transactional
    public ContactResponse updateContact(Long id, ContactRequest request) {
        Contact existing = contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact", "id", id));

        Department department = null;
        if (request.getDepartmentId() != null) {
            department = departmentRepository.findById(request.getDepartmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Department", "id", request.getDepartmentId()));
        }

        existing.setName(request.getName());
        existing.setDesignation(request.getDesignation());
        existing.setCategory(request.getCategory());
        existing.setPhoneNumber(request.getPhoneNumber());
        existing.setEmail(request.getEmail());
        existing.setOfficeLocation(request.getOfficeLocation());
        existing.setDepartment(department);

        Contact updated = contactRepository.save(existing);
        return contactMapper.toResponse(updated);
    }

    @Transactional
    public void deleteContact(Long id) {
        if (!contactRepository.existsById(id)) {
            throw new ResourceNotFoundException("Contact", "id", id);
        }
        contactRepository.deleteById(id);
    }
}
