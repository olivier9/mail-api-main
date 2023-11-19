package fr.fs.dto;

import fr.fs.entities.MailEntity;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class MailDto {
    @Schema(required = true, example = "fsackebandt@gmail.com")
    private String to;
    private String subject;
    private String text;



    /*static
    public static List<MailDto> toDtoList(List<MailEntity> mailEntities) {
        List<MailDto> mailDtoList = new ArrayList<>();
        for (MailEntity mailEntity : mailEntities)
            mailDtoList.add(new MailDto(MailEntity));
        return mailDtoList;
    }*/
}
