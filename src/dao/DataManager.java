package dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import dto.ShoutDTO;
import dto.UserDTO;

public class DataManager {
	private ArrayList<UserDTO> userList;
	private ArrayList<ShoutDTO> shoutList;

	public DataManager() {
		//登録ユーザー情報を生成し、リストに追加
		userList = new ArrayList<UserDTO>();
		UserDTO udto;
		udto = new UserDTO("yamada", "pass1", "山田　太郎", "icon-user", "はじめまして");
		userList.add(udto);
		udto = new UserDTO("suzuki", "pass2", "鈴木　花子", "icon-user-female", "東京在住です");
		userList.add(udto);
		udto = new UserDTO("itou", "pass3", "伊藤　恵", "icon-bell", "趣味は読書です");
		userList.add(udto);
		shoutList = new ArrayList<ShoutDTO>();

		//書き込み情報を生成しリストに追加
		ShoutDTO sdto;
		sdto = new ShoutDTO("テスト", "icon-rocket", "2017-01-02 12:34:56", "こんばんは");
		shoutList.add(sdto);
	}

	//ログインIDとパスワード受け取り、登録ユーザー一覧に一致したものがあるか検索
	public UserDTO getLoginUser(String loginId, String password) {
		UserDTO user = null;

		for (UserDTO u : userList) {
			if (u.getLoginId().equals(loginId) && u.getPassword().equals(password)) {
				//一致したものがあれば、そのユーザー情報を返す
				user = u;
			}
		}

		return user;
	}

	public ArrayList<ShoutDTO> getShoutList() {
		return shoutList;
	}

	//書き込みリストのgetter
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
