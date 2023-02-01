package jpacrud.cms.domain.basic.entity;

import jpacrud.cms.domain.basic.dto.BasicCreateDto;
import jpacrud.cms.domain.basic.dto.BasicUpdateDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "basic")
@Getter
@Setter
@SuperBuilder
public class BasicEntity {

    @Comment("ID값")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyId;

    @Comment("업체번호")
    private int companyNum;

    @Comment("업체이름")
    private String companyName;

    @Comment("업체내용")
    private String companyContent;

    @Comment("사용/삭제여부")
    private String useYN;

    @Comment("등록일자")
    private LocalDateTime signUpDateTime;

    @Comment("등록일(날짜만)")
    private String signUpDate;

    // 생성
    public static BasicEntity create(BasicCreateDto dto) {
        String localDate = String.valueOf(LocalDate.now()).substring(0, 10);
        System.out.println("로컬데이트? : " + localDate);
        return BasicEntity.builder()
                .companyNum(dto.getCompanyNum())
                .companyName(dto.getCompanyName())
                .companyContent(dto.getCompanyContent())
                .useYN(dto.getUseYN())
                .signUpDateTime(LocalDateTime.now())
                .signUpDate(localDate)
                .build();
    }

    // 업데이트
    public void update(BasicUpdateDto dto){
        this.companyNum = dto.getCompanyNum();
        this.companyName = dto.getCompanyName();
        this.companyContent = dto.getCompanyContent();
    }

    // 삭제
    public void delete() {
        this.useYN = "N";
    }
}
