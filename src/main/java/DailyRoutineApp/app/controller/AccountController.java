package DailyRoutineApp.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import DailyRoutineApp.app.daoImpl.AccountDaoImpl;
import DailyRoutineApp.app.entity.Account;
import DailyRoutineApp.app.entity.UserAccount;
import DailyRoutineApp.app.service.AccountCheckLogicService;
import DailyRoutineApp.app.service.AccountService;

@Controller
public class AccountController {


	@Autowired
	private AccountDaoImpl impl;

	@Autowired
	private AccountService acService;

	@Autowired
	private AccountCheckLogicService acLogicService;


	@Autowired
	private HttpSession session;

	/*
	 * アカウント一覧表示画面
	 */
	@RequestMapping(value="/account/index2",method = RequestMethod.GET)
	public ModelAndView acIndex(ModelAndView mav) {
		mav.setViewName("account/acIndex");
		List<Account> list = acService.acAll();
		mav.addObject("list", list);
		if(session.getAttribute("msg")!=null) {					//---セッションにメッセージが登録されていればVIEWへ送り、セッションは削除する
			mav.addObject("msg", session.getAttribute("msg"));
			session.removeAttribute("msg");						//---msgのセッション削除
		}
		return mav;
	}

	/*
	 * ページネーションを実装したアカウント一覧表示画面
	 */
	@RequestMapping(value="/account/index",method=RequestMethod.GET)
	public ModelAndView acIndexPage(ModelAndView mav,@PageableDefault(page=0,size=10)Pageable pageable) {
		mav.setViewName("account/acIndex");
		Page<Account> list = impl.acAll(pageable);
		mav.addObject("page", list);
		mav.addObject("list", list.getContent());
		if(session.getAttribute("msg")!=null) {					//---セッションにメッセージが登録されていればVIEWへ送り、セッションは削除する
			mav.addObject("msg", session.getAttribute("msg"));
			session.removeAttribute("msg");						//---msgのセッション削除
		}
		return mav;
	}

	/*
	 * アカウント新規作成画面
	 */
	@RequestMapping(value="/account/new",method=RequestMethod.GET)
	public ModelAndView acNew(ModelAndView mav) {
		mav.setViewName("account/acNew");
		Account ac = new Account();		//---空のモデル作成
		mav.addObject("formModel", ac);		//---空モデルをセット
		return mav;
	}

	/*
	 * アカウント新規作成
	 */
	@RequestMapping(value="/account/create",method=RequestMethod.POST)
	public ModelAndView acCreate(@ModelAttribute("formModel")@Validated Account account,
								BindingResult result,ModelAndView mav) {
		ModelAndView res = null;
		List<String> msgList = new ArrayList<String>();
		//---バリデーションエラーがない かつ 『アカウントID』、『アカウント表示名』が未登録時の処理
		if(!result.hasErrors() && acLogicService.acIdCheck(account) && acLogicService.acNameCheck(account)) {
			acService.accountSave(account);								//---インサート処理
			session.setAttribute("msg", "アカウントの登録が完了しました。");	//---セッションに完了メッセージ登録
			res = new ModelAndView("redirect:/login");				//---リダイレクト
		}else {														//---入力不備などがあった場合の処理
			mav.setViewName("account/acNew");
			mav.addObject("formModel", account);
			mav.addObject("result", result);
			msgList = acLogicService.acCheckMsg(account);				//---アカウントID、アカウント名が重複時のメッセージをリストに追加するメソッド
			mav.addObject("msgList", msgList);
			res = mav;
		}
		return res;
	}

	/*
	 * アカウント情報詳細確認
	 */
	@RequestMapping(value="/account/show/{accountid}",method=RequestMethod.GET)
	public ModelAndView acShow(@PathVariable("accountid")String accountid,ModelAndView mav) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserAccount user = UserAccount.class.cast(authentication.getPrincipal());
		Account account = acService.findById(accountid);	//---アカウント情報取得
		//---ログインユーザー名と、取得したアカウント名が一致する,または権限がADMIN,USER権限を持っている場合処理を行う。
		if(account.getAccountname().equals(user.getUsername()) || user.getAuthorities().size()==2) {
			mav.setViewName("account/acShow");
			mav.addObject("formModel", account);				//---アカウント情報をViewへ送る
		}else {
			mav.setViewName("error");
		}
		return mav;
	}

	/*
	 * アカウント情報編集画面
	 */
	@RequestMapping(value="/account/edit",method=RequestMethod.POST)
	public ModelAndView acEdit(@RequestParam("accountid")String accountid,ModelAndView mav) {
		Account account = acService.findById(accountid);	//---アカウント情報取得
		mav.setViewName("account/acEdit");
		mav.addObject("formModel", account);				//---アカウント情報をViewへ送る
		return mav;
	}

	/*
	 * アカウント情報編集
	 */
	@RequestMapping(value="/account/update",method=RequestMethod.POST)
	public ModelAndView acUpdate(@ModelAttribute("formModel")@Validated Account account,
								BindingResult result,@RequestParam("password_before")String pass,ModelAndView mav) {
		ModelAndView res = null;
		//---バリデーションエラーがない かつ 『アカウント表示名』が未登録　または　フォーム入力アカウント名が未更新 かつ　
		//---入力したパスワードとアカウントIDのパスワードが一致した時の処理
		if(!result.hasErrors() && (acLogicService.acNameCheck(account) || acLogicService.acNameUpdateCheck(account))
				&& acLogicService.passwordCheck(account.getAccountid(), pass)) {
			acService.updateAccount(account); 										//---アカウント情報更新処理
			res = new ModelAndView("redirect:/routine/top");							//---Viewをセット
			session.setAttribute("msg", "アカウント情報の更新が完了しました。");//---セッションに完了メッセージ登録
		}else {
			mav.setViewName("account/acEdit");								//---VIEWをセット
			mav.addObject("formModel", account);							//---Modelを追加
			mav.addObject("result", result);								//---バリデーション結果のエラーを設置
			mav.addObject("msgList", acLogicService.acUpdateCheckMsg(account,account.getAccountid(),pass));
			res = mav;
		}
		return res;
	}

	/*
	 * アカウント情報削除
	 */
	@RequestMapping(value="/account/delete",method=RequestMethod.POST)
	public ModelAndView acDelete(@RequestParam("accountid")String acId,ModelAndView mav) {
		ModelAndView res = null;
		if(acService.acCount(acService.findById(acId))!=0) {		//---リクエストで受取ったaccountidのレコードが0行以外なら削除実行
			acService.delete(acId);									//---引数のアカウントIDの行を削除する。
			session.setAttribute("msg", "削除が完了しました。");	//---セッションに完了メッセージ登録
			res = new ModelAndView("redirect:/account/index");		//---アカウント一覧画面へリダイレクト
		}else {
			mav.setViewName("error");								//---リクエストで受取ったaccountidのレコードが0行の場合、エラー画面へ遷移
			res = mav;
		}
		return res;
	}




}
