package DailyRoutineApp.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Entity
@Data
public class Routine_Detail {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false)
	private Integer r_Detailid;

//	@NotNull
//	@NotEmpty(message="CONTENTは必須項目です")
	@Length(min=1,message="CONTENTは必須項目です")
	@Column(nullable=false)
	private String content;

	@Column(nullable=false)
	private Integer success_cnt;

	@Column(nullable=false)
	private boolean complate_flg;

	@ManyToOne
	private D_Routine d_routine;


}
