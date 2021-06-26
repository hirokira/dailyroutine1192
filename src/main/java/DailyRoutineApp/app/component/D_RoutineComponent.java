package DailyRoutineApp.app.component;

import org.springframework.stereotype.Component;

import DailyRoutineApp.app.entity.D_Routine;



@Component
public class D_RoutineComponent {

	/*
	 * いいねが押された時、１加算して返却する処理a
	 */
	public Integer nicePntAdd(D_Routine routine) {
		return routine.getNicepnt()+1;//---routineオブジェクトのnicePntを１加算する
	}

}
