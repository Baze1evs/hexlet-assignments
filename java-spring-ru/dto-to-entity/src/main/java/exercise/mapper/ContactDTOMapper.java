package exercise.mapper;

import exercise.dto.ContactCreateDTO;
import exercise.dto.ContactDTO;
import exercise.model.Contact;
import org.springframework.stereotype.Component;

@Component
public class ContactDTOMapper {
    public ContactDTO toDTO(Contact model) {
        var dto = new ContactDTO();

        dto.setId(model.getId());
        dto.setPhone(model.getPhone());
        dto.setFirstName(model.getFirstName());
        dto.setLastName(model.getLastName());
        dto.setCreatedAt(model.getCreatedAt());
        dto.setUpdatedAt(model.getUpdatedAt());

        return dto;
    }

    public Contact toModel(ContactCreateDTO dto) {
        var contact = new Contact();

        contact.setPhone(dto.getPhone());
        contact.setFirstName(dto.getFirstName());
        contact.setLastName(dto.getLastName());

        return contact;
    }
}
