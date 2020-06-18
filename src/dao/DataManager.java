package dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import dto.ShoutDTO;
import dto.UserDTO;

public class DataManager {
	private ArrayList<UserDTO> userList;	// 登録ユーザ情報リスト
	private ArrayList<ShoutDTO> shoutList;	// 書き込み内容リスト

	public DataManager() {
		// 登録ユーザ情報を生成し、リストに追加
		userList = new ArrayList<UserDTO>();
		UserDTO udto;
		udto = new UserDTO("yamada", "pass1", "山田　太郎", "icon-user", "はじめまして");
		userList.add(udto);
		udto = new UserDTO("suzuki", "pass2", "鈴木　花子", "icon-user-female", "東京都在住です");
		userList.add(udto);
		udto = new UserDTO("itou", "pass3", "伊藤　恵", "icon-bell", "趣味は読書");
		userList.add(udto);
		shoutList = new ArrayList<ShoutDTO>();

		// 書き込み情報を生成し、リストに追加
		ShoutDTO sdto;
		sdto = new ShoutDTO("テスト", "icon-rocket", " 2017-01-02 12:34:56", "こんばんは");
		shoutList.add(sdto);
	}

	// ログインID とパスワードを受け取り、登録ユーザ一覧に一致したものがあるか検索
	public UserDTO getLoginUser(String loginId, String password) {
		UserDTO user = null;

		for(UserDTO u : userList){
			if(u.getLoginId().equals(loginId) && u.getPassword().equals(password)){
				// 一致したものがあれば、そのユーザ情報の参照を戻す
				user = u;
			}
		}

		return user;
	}

	// 書き込み内容リストの getter
	public ArrayList<ShoutDTO> getShoutList() {
		return shoutList;
	}

	// ログインユーザ情報と書き込み内容を受け取り、リストに追加する
	public void setWriting(UserDTO user, String writing) {
		ShoutDTO s = new ShoutDTO();

		s.setUserName(user.getUserName());
		s.setIcon(user.getIcon());
		Calendar calender = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		s.setDate(sdf.format(calender.getTime()));
		s.setWriting(writing);

		shoutList.add(0, s);
	}
}
