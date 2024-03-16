package center.unit.letter.presentation.letter.dto.response;

import center.unit.letter.domain.contact.Contact;
import center.unit.letter.domain.letter.type.MediumType;
import center.unit.letter.presentation.contact.dto.ContactDto;
import center.unit.letter.presentation.letter.dto.MyLetterVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MyLetterResponse {
    private Long id;
    private String myLetterType;
    private MediumType mediumType;
    private LocalDateTime createdAt;
    private LocalDateTime arriveAt;
    private ContactDto contact;
    private String previewText;
    private String phoneNumber;

    public MyLetterResponse(MyLetterVO myLetterVO) {
        this.id = myLetterVO.getId();
        this.myLetterType = myLetterVO.getMyLetterType();
        this.mediumType = myLetterVO.getMediumType();
        this.createdAt = myLetterVO.getCreatedAt();
        this.arriveAt = myLetterVO.getArriveAt();
        this.contact = myLetterVO.getContact();
        this.previewText = myLetterVO.getPreviewText();
        this.phoneNumber = myLetterVO.getPhoneNumber();
    }
}
